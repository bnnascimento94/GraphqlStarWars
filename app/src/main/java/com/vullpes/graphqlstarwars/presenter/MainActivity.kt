package com.vullpes.graphqlstarwars.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.vullpes.graphqlstarwars.presenter.ui.theme.GraphqlStarWarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphqlStarWarsTheme {
                val viewModel = hiltViewModel<MovieViewModel>()
                val state by viewModel.uiStateMovies.collectAsState()
                MoviesScreen(
                    uiStateMovies = state,
                    onSelectMovie = {movieID->
                        viewModel.getCharacteres(movieID, onSucess = {
                            viewModel.openViewModelDialog(openDialog = true)
                        })
                    }
                )

                if(state.openDialog){
                    ModalBottomCharacteres(characters = state.characters) {
                        viewModel.openViewModelDialog(openDialog = false)
                    }
                }
            }
        }
    }
}

