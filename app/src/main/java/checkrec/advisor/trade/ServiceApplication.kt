package checkrec.advisor.trade

import android.app.Application
import checkrec.advisor.trade.di.dataModule
import checkrec.advisor.trade.di.dispatcherModule
import checkrec.advisor.trade.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ServiceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@ServiceApplication)
            modules(appModules)
        }
    }
}