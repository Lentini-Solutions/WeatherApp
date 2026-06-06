package com.example.cursosant.common.di

import com.example.cursosant.common.utils.FormatUtils
import com.example.cursosant.common.utils.IntentUtils
import com.example.cursosant.common.utils.NetworkUtils
import org.koin.dsl.module

val utilsModule = module {
    single { FormatUtils() }
    single { NetworkUtils(get()) }
    single { IntentUtils(get()) }
}