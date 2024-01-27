package com.vullpes.graphqlstarwars.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.vullpes.AllMoviesQuery
import com.vullpes.MovieQuery
import com.vullpes.graphqlstarwars.domain.StarWarsRepository
import com.vullpes.graphqlstarwars.domain.entity.Character
import com.vullpes.graphqlstarwars.domain.entity.Movie
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
): StarWarsRepository {

    override suspend fun fetchAllMovies(): List<Movie> {
        return try {
            apolloClient
                .query(AllMoviesQuery())
                .execute()
                .data
                ?.allFilms
                ?.films
                ?.map {
                    it!!.toMovie()
                }?: emptyList()
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun fetchAllCharactersByMovie(movieID: String): List<Character> {
        return try {
            apolloClient
                .query(MovieQuery(filmId = Optional.present(movieID)))
                .execute()
                .data
                ?.film
                ?.characterConnection
                ?.characters
                ?.map {
                    it!!.toCharacter()
                }?: emptyList()
        }catch (e:Exception){
            throw e
        }
    }

}