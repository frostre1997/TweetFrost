package com.frostre1997.tweetfrost.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id_str") val id: String,
    val name: String,
    @SerializedName("screen_name") val screenName: String,
    @SerializedName("profile_image_url_https") val profileImageUrl: String? = null,
    @SerializedName("followers_count") val followersCount: Int = 0,
    @SerializedName("friends_count") val followingCount: Int = 0,
    @SerializedName("statuses_count") val tweetCount: Int = 0,
    val description: String? = null
)
