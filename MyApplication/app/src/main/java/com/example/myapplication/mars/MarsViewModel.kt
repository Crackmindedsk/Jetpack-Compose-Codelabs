package com.example.myapplication.mars

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.MarsPhotosApplication
import com.example.myapplication.data.MarsPhotosRepository
import com.example.myapplication.network.MarsPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState{
    data class Success(val photo:List<MarsPhoto>):MarsUiState
    object Loading:MarsUiState
    object Error:MarsUiState
}
class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState=MarsUiState.Loading
            marsUiState=try {
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            }catch (e:IOException){
                MarsUiState.Error
            }catch (e:HttpException){
                MarsUiState.Error
            }

        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotoRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}



