package com.vullpes.graphqlstarwars.presenter

import com.vullpes.graphqlstarwars.domain.entity.Character
import com.vullpes.graphqlstarwars.domain.entity.Movie

data class UiStateMovies (
    val loading:Boolean = false,
    val openDialog:Boolean = false,
    val movies:List<Movie> = emptyList(),
    val characters:List<Character> = emptyList()
)