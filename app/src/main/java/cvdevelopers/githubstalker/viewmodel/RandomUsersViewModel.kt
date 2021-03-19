package cvdevelopers.githubstalker.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cvdevelopers.githubstalker.domain.entities.RandomUserEntity
import cvdevelopers.githubstalker.domain.usecases.RandomUserUseCase
import cvdevelopers.githubstalker.util.SingleLiveEvent
import cvdevelopers.githubstalker.utils.AppResult
import kotlinx.coroutines.launch

class RandomUsersViewModel(private val useCase: RandomUserUseCase) : ViewModel() {
    val showLoading = ObservableBoolean()
    val userList = MutableLiveData<List<RandomUserObservable>>()
    val showError = SingleLiveEvent<String?>()

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
        showLoading.set(false)
        when (result) {
            is AppResult.Success -> {
                userList.value = result.successData.map {
                    RandomUserObservable(it.name, it.avatarUrl)
                }
                showError.value = null //we clean recycler view
            }
            is AppResult.Error -> {
                userList.value = ArrayList(0)
                showError.value = result.exception.message
            }
        }
    }
}