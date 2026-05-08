package checkrec.advisor.trade.di

import checkrec.advisor.trade.ui.viewmodel.BookingViewModel
import checkrec.advisor.trade.ui.viewmodel.CheckoutViewModel
import checkrec.advisor.trade.ui.viewmodel.ERSIJOnboardingVM
import checkrec.advisor.trade.ui.viewmodel.ServiceDetailsViewModel
import checkrec.advisor.trade.ui.viewmodel.ServiceViewModel
import checkrec.advisor.trade.ui.viewmodel.ERSIJSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        ERSIJSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ERSIJOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}