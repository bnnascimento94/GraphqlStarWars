package com.vullpes.graphqlstarwars.domain.usecases

import com.vullpes.graphqlstarwars.domain.StarWarsRepository
import javax.inject.Inject

class GetMoviesUsecase @Inject constructor(private val starWarsRepository: StarWarsRepository) {

    suspend fun execute() = starWarsRepository.fetchAllMovies()
}