package com.frostre1997.tweetfrost.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Field
import retrofit2.http.Query
import retrofit2.Response

interface ApiService {
    // Home timeline (tweets from people you follow)
    // This is a reverse-engineered endpoint used by the official app.
    @GET("/1.1/statuses/home_timeline.json")
    suspend fun getHomeTimeline(
        @Query("count") count: Int = 20,
        @Query("tweet_mode") tweetMode: String = "extended"
    ): Response<List<Tweet>>

    // Post a new tweet
    @FormUrlEncoded
    @POST("/1.1/statuses/update.json")
    suspend fun postTweet(
        @Field("status") status: String
    ): Response<Tweet>

    // Get user details (for profile screen)
    @GET("/1.1/users/show.json")
    suspend fun getUserDetails(
        @Query("screen_name") screenName: String
    ): Response<User>

    // Optional: Like a tweet
    @FormUrlEncoded
    @POST("/1.1/favorites/create.json")
    suspend fun likeTweet(
        @Field("id") tweetId: String
    ): Response<Tweet>
}
