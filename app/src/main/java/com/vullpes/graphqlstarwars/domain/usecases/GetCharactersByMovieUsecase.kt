package com.vullpes.graphqlstarwars.domain.usecases

import com.vullpes.graphqlstarwars.domain.StarWarsRepository
import javax.inject.Inject

class GetCharactersByMovieUsecase @Inject constructor(private val starWarsRepository: StarWarsRepository) {

    suspend fun execute(movieID: String) = starWarsRepository.fetchAllCharactersByMovie(movieID)
}