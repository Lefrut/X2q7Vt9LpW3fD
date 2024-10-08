package com.effective.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effective.login.model.LoginStage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginStageState = MutableStateFlow(LoginStage.Email)
    val loginStageState = _loginStageState.asStateFlow()

    private val _emailState = MutableStateFlow("")
    val emailState = _emailState.asStateFlow()

    private val _passwordState = MutableStateFlow("")
    val passwordState = _passwordState.asStateFlow()

    private val _isErrorText = MutableStateFlow(false)
    val isErrorTextState = _isErrorText.asStateFlow()

    fun setEmail(value: String) {
        _emailState.update { value }
    }

    fun setPassword(value: String) {
        _passwordState.update { value }
    }

    fun clearEmail() {
        _emailState.update { "" }
    }

    fun goPasswordStage() = viewModelScope.launch {
        val email = emailState.value
        if (!email.matches(EMAIL_REGEX) || email.length > 200) {
            _isErrorText.update { true }
            delay(3000)
            _isErrorText.update { false }
        } else {
            _isErrorText.update { false }
            _loginStageState.update { LoginStage.Password }
        }
    }

    fun goCodeState() = viewModelScope.launch {
        val password = passwordState.value
        if (password.length > 200 || password.length < 4 || password.isBlank()) {
            _isErrorText.update { true }
            delay(3000)
            _isErrorText.update { false }
        } else {
            _isErrorText.update { false }
            _loginStageState.update { LoginStage.Code }
        }
    }

    companion object {

        val EMAIL_REGEX =
            "^([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x22)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x22))*\\x40([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d))*\$".toRegex()

    }

}