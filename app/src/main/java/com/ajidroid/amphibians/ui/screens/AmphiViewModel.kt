package com.ajidroid.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajidroid.amphibians.network.AmphiApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphiUiState {
    data class Success(val data : String) : AmphiUiState
    object Loading : AmphiUiState
    object Error : AmphiUiState

}
class AmphiViewModel : ViewModel() {

    var amphiUiState : AmphiUiState by mutableStateOf(AmphiUiState.Loading)
        private set

    init {
        getAmphiData()
    }

    fun getAmphiData() {
        viewModelScope.launch {
            amphiUiState = AmphiUiState.Loading
            amphiUiState = try {
                val listResult = AmphiApi.retrofitService.getData()
                AmphiUiState.Success("Success : ${listResult.size} Amphibian data recieved")
            } catch (e : IOException){
                AmphiUiState.Error
            } catch (e : HttpException){
                AmphiUiState.Error
            }
        }
    }
}

