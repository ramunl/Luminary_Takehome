package cvdevelopers.githubstalker

import android.content.Context
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.test.ext.junit.runners.AndroidJUnit4
import cvdevelopers.githubstalker.data.repository.ResultsDatabase
import cvdevelopers.githubstalker.data.util.isOnline
import cvdevelopers.githubstalker.utils.setIsFirstRequestSent
import cvdevelopers.githubstalker.viewmodel.RandomUsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class ResultViewModelTest : KoinTest {

    private val viewModel: RandomUsersViewModel by inject()
    private val randomUserDatabase: ResultsDatabase by inject()
    private val context: Context by inject()

    @Before()
    fun before() {
        loadKoinModules(roomTestModule)
    }

    @Test
    fun dataLoadingStateFirstLaunchTest() {
        //setup cold start
        context.setIsFirstRequestSent(false)
        randomUserDatabase.clearAllTables()

        runBlocking {
            viewModel.showLoading.set(false)
            viewModel.showLoading.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    val isLoading = viewModel.showLoading.get()
                    if (viewModel.userList?.isNotEmpty() == true) {
                        Assert.assertFalse(isLoading)
                    } else {
                        Assert.assertTrue(isLoading)
                    }
                }
            })
            launch(Dispatchers.IO) {
                Assert.assertTrue(context.isOnline())
                viewModel.getAllUsers()
            }
        }
    }
}