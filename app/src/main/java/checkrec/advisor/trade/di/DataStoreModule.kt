package checkrec.advisor.trade.di

import checkrec.advisor.trade.data.datastore.ERSIJOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { ERSIJOnboardingPrefs(androidContext()) }
}