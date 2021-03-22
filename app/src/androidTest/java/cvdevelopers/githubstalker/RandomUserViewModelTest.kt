package org.koin.sampleapp.view

import android.content.Context
import cvdevelopers.githubstalker.data.repository.RandomUserRepository
import cvdevelopers.githubstalker.viewmodel.RandomUsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.koin.test.KoinTest
import org.koin.test.inject

class ResultViewModelTest : KoinTest {

    private val viewModel: RandomUsersViewModel by inject()
    
    @Test
    fun dataLoadingStateTest() {
        viewModel.showLoading.set(false)
        runBlocking {
            launch(Dispatchers.IO) {
                viewModel.onRefresh()
                launch(Dispatchers.Main) {
                    Assert.assertTrue(viewModel.showLoading.get())
                }
            }
        }
    }
}