package cvdevelopers.githubstalker

import androidx.test.ext.junit.runners.AndroidJUnit4
import cvdevelopers.githubstalker.data.model.RandomUser
import cvdevelopers.githubstalker.data.repository.ResultsDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class RandomUserDatabaseTest : KoinTest {

    private val randomUserDatabase: ResultsDatabase by inject()

    @Before()
    fun before() {
        loadKoinModules(roomTestModule)
    }


    @Test
    fun multiplePagesAddTest() {
        randomUserDatabase.clearAllTables()
        val page1Size = MockedPage1.info.results
        randomUserDatabase.resultsDao.add(MockedPage1)
        val page2Size = MockedPage2.info.results
        randomUserDatabase.resultsDao.add(MockedPage2)
        val allPages = randomUserDatabase.resultsDao.findAll()
        val allUsers = mutableListOf<RandomUser>()
        for(page in allPages) {
            allUsers.addAll(page.results!!)
        }
        Assert.assertTrue(allUsers.size == page1Size + page2Size)
    }
    @Test
    fun theSamePageIdAddedTest() {
        randomUserDatabase.clearAllTables()
        randomUserDatabase.resultsDao.add(MockedPage1)
        val page2Size = MockedPage1_1.info.results
        randomUserDatabase.resultsDao.add(MockedPage1_1)
        val allPages = randomUserDatabase.resultsDao.findAll()
        val allUsers = mutableListOf<RandomUser>()
        for(page in allPages) {
            allUsers.addAll(page.results!!)
        }
        Assert.assertTrue(allUsers.size == page2Size)
    }
    @After
    fun after() {
    }
}