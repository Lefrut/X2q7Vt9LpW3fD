package com.effective.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.effective.login.databinding.FragmentLoginBinding
import com.effective.login.model.LoginStage
import com.effective.navigation.navController
import com.effective.ui.flow.collectWithLifecycle
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import com.effective.resources.R as Res

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    private val passwordTextWatcher =
        AfterTextWatcher { s -> viewModel.setPassword(s?.toString() ?: "") }
    private val emailTextWatcher =
        AfterTextWatcher { s -> viewModel.setEmail(s?.toString() ?: "") }


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        val inputLayout = binding.inputLayout
        val editText = inputLayout.editText!!
        inputLayout.isHintEnabled = false

        viewModel.loginStageState.collectWithLifecycle(viewLifecycleOwner) { loginStage ->
            when (loginStage) {
                LoginStage.Email -> {
                    viewModel.isErrorTextState.mapLatest { isError ->
                        isError.handleTextError(
                            inputLayout,
                            getString(Res.string.incorrect_email)
                        )
                    }.buffer(0).launchIn(this)
                    emailStageUi(editText, inputLayout)
                }

                LoginStage.Password -> {
                    viewModel.isErrorTextState.mapLatest { isError ->
                        isError.handleTextError(
                            inputLayout,
                            getString(Res.string.incorrect_password)
                        )
                    }.buffer(0).launchIn(this)
                    passwordStageUi(editText, inputLayout)
                }

                LoginStage.Code -> {
                    codeStageUi()
                }
            }
        }
    }

    private suspend fun codeStageUi() {
        binding.inputCard.visibility = View.GONE
        binding.bottomCard.visibility = View.GONE
        binding.bottomSpace.visibility = View.GONE
        binding.topSpace.visibility = View.GONE
        binding.title.visibility = View.GONE
        binding.codeLayout.visibility = View.VISIBLE

        val email = viewModel.emailState.value
        binding.codeTitle.text = getString(Res.string.sent_code_to_email, email)

        binding.otpView.setOtpCompletionListener {
            viewLifecycleOwner.lifecycleScope.launch {
                delay(100L)
                navController.navigateToBottomMenu()
            }
        }
        binding.otpView.setAnimationEnable(true)
    }

    private suspend fun emailStageUi(editText: EditText, textLayout: TextInputLayout) {
        binding.inputCard.visibility = View.VISIBLE
        binding.bottomCard.visibility = View.VISIBLE
        binding.title.visibility = View.VISIBLE
        binding.bottomSpace.visibility = View.VISIBLE
        binding.topSpace.visibility = View.VISIBLE
        binding.codeLayout.visibility = View.GONE

        textLayout.isHintEnabled = true
        textLayout.setHint(Res.string.write_email)
        textLayout.isHintEnabled = false

        editText.removeTextChangedListener(passwordTextWatcher)
        editText.addTextChangedListener(emailTextWatcher)

        textLayout.endIconMode = END_ICON_CUSTOM
        textLayout.setEndIconDrawable(Res.drawable.close_24)
        textLayout.setEndIconOnClickListener {
            viewModel.clearEmail()
        }
        textLayout.refreshEndIconDrawableState()

        binding.continueButton.setOnClickListener {
            viewModel.goPasswordStage()
        }

        viewModel.emailState.collectLatest {
            editText.setText(it)
            editText.setSelection(it.length)
        }


    }


    private suspend fun passwordStageUi(editText: EditText, textLayout: TextInputLayout) {
        binding.inputCard.visibility = View.VISIBLE
        binding.bottomCard.visibility = View.VISIBLE
        binding.bottomSpace.visibility = View.VISIBLE
        binding.topSpace.visibility = View.VISIBLE
        binding.title.visibility = View.VISIBLE
        binding.codeLayout.visibility = View.GONE


        textLayout.isHintEnabled = true
        textLayout.setHint(Res.string.write_password)
        textLayout.isHintEnabled = false


        editText.removeTextChangedListener(emailTextWatcher)
        editText.addTextChangedListener(passwordTextWatcher)

        textLayout.endIconMode = END_ICON_NONE
        textLayout.endIconDrawable = null
        textLayout.refreshEndIconDrawableState()

        textLayout.isPasswordVisibilityToggleEnabled = true

        binding.continueButton.setOnClickListener {
            viewModel.goCodeState()
        }

        viewModel.passwordState.collectLatest {
            editText.setText(it)
            editText.setSelection(it.length)
        }
    }

    private suspend fun Boolean.handleTextError(inputLayout: TextInputLayout, text: String) {
        viewModel.isErrorTextState.collectLatest {
            if (this) {
                inputLayout.isErrorEnabled = true
                inputLayout.error = text
            } else {
                inputLayout.error = null
                inputLayout.isErrorEnabled = false
            }
        }
    }


}

fun interface AfterTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}

fun interface DefaultTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }

}


