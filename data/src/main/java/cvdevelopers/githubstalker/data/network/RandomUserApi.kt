package cvdevelopers.githubstalker.data.network

import cvdevelopers.githubstalker.data.model.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("/api/")
    suspend fun getResults(@Query("page") page: String = "1", @Query("results") results: String = "15"): Response<Results>
}