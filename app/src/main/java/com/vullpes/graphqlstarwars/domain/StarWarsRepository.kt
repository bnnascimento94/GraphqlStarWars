package com.vullpes.graphqlstarwars.domain

import com.vullpes.graphqlstarwars.domain.entity.Character
import com.vullpes.graphqlstarwars.domain.entity.Movie

interface StarWarsRepository {

    suspend fun fetchAllMovies(): List<Movie>

    suspend fun fetchAllCharactersByMovie(movieID:String):List<Character>

}