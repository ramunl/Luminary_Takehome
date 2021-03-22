package cvdevelopers.githubstalker.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cvdevelopers.githubstalker.domain.entities.RandomUserEntity
import cvdevelopers.githubstalker.domain.usecases.RandomUserUseCase
import cvdevelopers.githubstalker.util.SingleLiveEvent
import cvdevelopers.githubstalker.utils.AppResult
import cvdevelopers.githubstalker.utils.TAG
import cvdevelopers.githubstalker.view.UsersAdapter
import cvdevelopers.githubstalker.viewmodel.observable.RandomUserObservable
import cvdevelopers.githubstalker.viewmodel.observable.RecyclerConfigurationObservable
import kotlinx.coroutines.launch

class RandomUsersViewModel(private val useCase: RandomUserUseCase, application: Application) : AndroidViewModel(application) {

    var userList: List<RandomUserObservable>? = null
    val showLoading = ObservableBoolean()
    val showError = SingleLiveEvent<String?>()
    private val usersAdapter = UsersAdapter().also {
        recyclerConfigurationObservable.adapter = it
        getAllUsers()
    }

    companion object {
        val recyclerConfigurationObservable = RecyclerConfigurationObservable()
    }

    fun onRefresh() {
        showLoading.set(true)
        viewModelScope.launch {
            onResultReady(useCase.getFreshRandomUsers())
        }
    }

    fun getAllUsers() {
        showLoading.set(true)
        viewModelScope.launch {
            onResultReady(useCase.getRandomUsers())
        }
    }

    private fun onResultReady(result: AppResult<List<RandomUserEntity>>) {
        when (result) {
            is AppResult.Success -> {
                userList = result.successData.map {
                    RandomUserObservable(it.name, it.avatarUrl)
                }
                Log.d(TAG,"onResultReady ${userList?.size}")
                showError.value = null
            }
            is AppResult.Error -> {
                userList = ArrayList(0)
                showError.value = result.exception.message
                Log.d(TAG,"onResultReady ${userList?.size} err = ${result.exception}")
            }
        }
        usersAdapter.setUsers(userList!!)
        showLoading.set(false)
    }
}