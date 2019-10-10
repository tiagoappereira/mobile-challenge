package pt.unbabel.android.demo.network

import com.squareup.moshi.JsonClass

/**
 * These are responsible for parsing responses from the server.
 */
@JsonClass(generateAdapter = true)
data class NetworkPost(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)

@JsonClass(generateAdapter = true)
data class NetworkUser(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: NetworkAddress,
    val phone: String,
    val website: String,
    val company: NetworkCompany
){
    @JsonClass(generateAdapter = true)
    data class NetworkAddress(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: NetworkGeo
    ){
        @JsonClass(generateAdapter = true)
        data class NetworkGeo(
            val lat: String,
            val lng: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class NetworkCompany(
        val name: String,
        val catchPhrase: String,
        val bs: String
    )
}

@JsonClass(generateAdapter = true)
data class NetworkComment(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)