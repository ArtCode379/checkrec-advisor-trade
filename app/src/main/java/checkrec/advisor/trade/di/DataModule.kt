package checkrec.advisor.trade.di

import checkrec.advisor.trade.data.repository.BookingRepository
import checkrec.advisor.trade.data.repository.ERSIJOnboardingRepo
import checkrec.advisor.trade.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        ERSIJOnboardingRepo(
            ersijOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}