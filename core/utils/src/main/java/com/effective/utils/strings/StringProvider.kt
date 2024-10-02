package com.effective.utils.strings

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


interface StringProvider {

    fun getString(@StringRes resId: Int, vararg args: Any): String

    fun getQuantityStrings(
        @PluralsRes id: Int,
        quantity: Int,
        vararg formatArgs: Any
    ): String
}

class StringProviderImpl @Inject constructor(
    @ApplicationContext private val appContext: Context
) : StringProvider {

    init {
        synchronized(this) {
            provider = this
        }
    }

    override fun getString(
        @StringRes
        resId: Int,
        vararg args: Any
    ) = appContext.getString(resId, args)

    override fun getQuantityStrings(
        @PluralsRes id: Int,
        quantity: Int,
        vararg formatArgs: Any
    ) = appContext.resources.getQuantityString(id, quantity, formatArgs)

    companion object {
        var provider: StringProvider? = null
    }
}

val stringProviderImpl = StringProviderImpl.provider!!

@Module
@InstallIn(SingletonComponent::class)
abstract class StringProviderModule {

    @Binds
    abstract fun bindStringProvider(stringProviderImpl: StringProviderImpl): StringProvider

}


