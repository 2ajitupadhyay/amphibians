package com.ajidroid.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ajidroid.amphibians.AmphibiansApplication
import com.ajidroid.amphibians.data.AmphibiansRepository
import com.ajidroid.amphibians.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphiUiState {
    data class Success(val data : List<Amphibian>) : AmphiUiState
    object Loading : AmphiUiState
    object Error : AmphiUiState

}
class AmphiViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {

    var amphiUiState : AmphiUiState by mutableStateOf(AmphiUiState.Loading)
        private set

    init {
        getAmphiData()
    }

    fun getAmphiData() {
        viewModelScope.launch {
            amphiUiState = AmphiUiState.Loading
            amphiUiState = try {
//                val amphibiansRepository = NetworkAmphibianRepository()
                val listResult = amphibiansRepository.getAmphibians()
                AmphiUiState.Success(listResult)
            } catch (e : IOException){
                AmphiUiState.Error
            } catch (e : HttpException){
                AmphiUiState.Error
            }
        }
    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphiViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}

