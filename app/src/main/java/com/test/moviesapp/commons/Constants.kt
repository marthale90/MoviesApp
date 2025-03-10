package com.test.moviesapp.commons

object Constants {
    object Remote {
        const val POPULAR = "popular"
        const val LANGUAGE_ES = "es-ES"
        const val LANGUAGE_EN = "en-US"
    }

    object UI {
        // Todo: get from https://developer.themoviedb.org/reference/genre-movie-list
        val GENRE_MOVIES = mapOf(
            28 to "Acción",
            12 to "Aventura",
            16 to "Animación",
            35 to "Comedia",
            80 to "Crimen",
            99 to "Documental",
            18 to "Drama",
            10751 to "Familia",
            14 to "Fantasía",
            36 to "Historia",
            27 to "Terror",
            10402 to "Música",
            9648 to "Misterio",
            10749 to "Romance",
            878 to "Ciencia ficción",
            10770 to "Película de TV",
            53 to "Suspense",
            10752 to "Bélica",
            37 to "Western"
        )
    }
}
