package pt.unbabel.android.demo.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://jsonplaceholder.typicode.com/"

/**
 * Moshi object that Retrofit will be using to parse JSON with Kotlin adapter for full
 * Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * A retrofit service to fetch posts, users and comments
 */
interface PostService {

    @GET("posts")
    fun getPostsAsync() : Deferred<List<NetworkPost>>

    @GET("users")
    fun getUsersAsync() : Deferred<List<NetworkUser>>

    @GET("comments")
    fun getCommentsAsync() : Deferred<List<NetworkComment>>
}

/**
* Main entry point for network access. Call like `Network.service.{operation}`
*/
object Network {
    /**
     * Retrofit object using a Moshi JSON converter
     * Configured to parse JSON and use coroutines
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val service: PostService = retrofit.create(PostService::class.java)
}