package cvdevelopers.githubstalker

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import cvdevelopers.githubstalker.data.repository.RandomUserRepository
import cvdevelopers.githubstalker.domain.usecases.RandomUserUseCase
import cvdevelopers.githubstalker.utils.AppResult
import cvdevelopers.githubstalker.utils.isFirstRequestSent
import cvdevelopers.githubstalker.utils.setIsFirstRequestSent
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class RandomUserUseCaseTest : KoinTest {

    private val randomUserUseCase : RandomUserUseCase by inject()
    private val randomUserRepository: RandomUserRepository by inject()
    private val context: Context by inject()

    @Before()
    fun before() {
        loadKoinModules(roomTestModule)
    }


    @Test
    fun firstLaunchTest() = runBlocking {
        context.setIsFirstRequestSent(false)
        randomUserRepository.cleanCache()
        val res = randomUserUseCase.getRandomUsers()
        Assert.assertTrue(res is AppResult.Success)
        Assert.assertTrue((res as AppResult.Success).successData.isNotEmpty())
    }
    @Test
    fun readFromCacheTest() = runBlocking {
        Assert.assertTrue(randomUserUseCase.getFreshRandomUsers() is AppResult.Success)
        val res = randomUserRepository.getLocalPriorityResults(false)
        Assert.assertTrue(res is AppResult.Success)
        Assert.assertTrue((res as AppResult.Success).successData.isNotEmpty())
    }

    @Test
    fun notFirstLaunchStorageEmptyTest() = runBlocking {
        context.setIsFirstRequestSent(true)
        randomUserRepository.cleanCache()
        val res = randomUserUseCase.getRandomUsers()
        Assert.assertTrue(res is AppResult.Error)
    }

    @After
    fun after() {
    }
}