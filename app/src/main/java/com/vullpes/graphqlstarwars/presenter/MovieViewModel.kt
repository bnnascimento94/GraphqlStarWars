package com.vullpes.graphqlstarwars.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.graphqlstarwars.domain.usecases.GetCharactersByMovieUsecase
import com.vullpes.graphqlstarwars.domain.usecases.GetMoviesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUsecase: GetMoviesUsecase,
    private val getCharactersByMovieUsecase: GetCharactersByMovieUsecase
): ViewModel() {
    val uiStateMovies = MutableStateFlow(UiStateMovies())
    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        val result = getMoviesUsecase.execute()
        withContext(Dispatchers.Main){
            uiStateMovies.update {
                it.copy(movies = result)
            }
        }
    }

    fun getCharacteres(movieID: String, onSucess:() -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        val result = getCharactersByMovieUsecase.execute(movieID)
        withContext(Dispatchers.Main){
            uiStateMovies.update {

                it.copy(characters = result)
            }
            onSucess()
        }
    }

    fun openViewModelDialog(openDialog:Boolean = false){
        uiStateMovies.update {
            it.copy(openDialog= openDialog)
        }
    }


}