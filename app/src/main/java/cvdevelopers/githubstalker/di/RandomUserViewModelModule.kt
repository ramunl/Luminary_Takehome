package cvdevelopers.githubstalker.di

import cvdevelopers.githubstalker.viewmodel.RandomUsersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // Specific viewModel pattern to tell Koin how to build RandomUsersViewModel
    viewModel {
        RandomUsersViewModel(get(), get())
    }
}