package com.vullpes.graphqlstarwars.data

import com.vullpes.AllMoviesQuery
import com.vullpes.MovieQuery
import com.vullpes.graphqlstarwars.domain.entity.Character
import com.vullpes.graphqlstarwars.domain.entity.Movie



fun AllMoviesQuery.Film.toMovie() = Movie(
    id = id,
    director = director?:"",
    title = title?:""
)

fun MovieQuery.Character.toCharacter() = Character(
    id = id,
    name = name?:"",
    skinColor = skinColor?:"",
    birthYear = birthYear?:""
)