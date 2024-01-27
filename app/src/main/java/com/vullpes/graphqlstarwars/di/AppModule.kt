package com.vullpes.graphqlstarwars.di

import com.apollographql.apollo3.ApolloClient
import com.vullpes.graphqlstarwars.data.StarWarsRepositoryImpl
import com.vullpes.graphqlstarwars.domain.StarWarsRepository
import com.vullpes.graphqlstarwars.domain.usecases.GetCharactersByMovieUsecase
import com.vullpes.graphqlstarwars.domain.usecases.GetMoviesUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
            .build()
    }

    @Provides
    @Singleton
    fun providesStarWarsRepository(apolloClient: ApolloClient): StarWarsRepository {
        return StarWarsRepositoryImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun providesGetCharactersByMovieUsecase(starWarsRepository: StarWarsRepository): GetCharactersByMovieUsecase{
        return GetCharactersByMovieUsecase(starWarsRepository)
    }

    @Provides
    @Singleton
    fun providesGetMoviesUsecase(starWarsRepository: StarWarsRepository): GetMoviesUsecase{
        return GetMoviesUsecase(starWarsRepository)
    }

}