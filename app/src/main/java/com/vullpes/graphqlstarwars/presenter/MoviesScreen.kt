package com.vullpes.graphqlstarwars.presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    uiStateMovies: UiStateMovies,
    onSelectMovie: (String) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Starwars Movies")
            })
        }
    ) { paddingValues ->

        if (uiStateMovies.loading) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )
            {
                items(uiStateMovies.movies) { movie ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(6.dp))
                            .clickable { onSelectMovie(movie.id) }
                            .padding(6.dp)
                    ) {
                        Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = movie.director, style = MaterialTheme.typography.titleSmall)
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider()
                    }
                }

            }
        }

    }

}
