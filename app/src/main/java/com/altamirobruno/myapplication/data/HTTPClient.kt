package co.tiagoaguiar.tutorial.jokerappdev.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BEARER_TOKEN =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMjNjODkyMjBiOTkzN2M1OWUwNjI5MTkxYmJhOWZhYSIsIm5iZiI6MTY0MTM5Mjc0Ny4wODgsInN1YiI6IjYxZDVhYTZiNTJkYzdmMDBhNGY5N2FmMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.L8VhzLhSH8x_L6ORZyP8vpjik9symAuGtnOotHQ7VQI"
    const val API_KEY = "b23c89220b9937c59e0629191bba9faa"
    const val API_LANGUAGE = "pt-BR"

    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", BEARER_TOKEN)
                .build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(headerInterceptor)
            .build()

    }

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient())
            .build()
    }
}