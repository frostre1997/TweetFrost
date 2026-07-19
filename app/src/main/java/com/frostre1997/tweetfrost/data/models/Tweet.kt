package com.frostre1997.tweetfrost.data.models

import com.google.gson.annotations.SerializedName

data class Tweet(
    @SerializedName("id_str") val id: String,
    @SerializedName("full_text") val text: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("user") val user: User?,
    @SerializedName("retweet_count") val retweetCount: Int = 0,
    @SerializedName("favorite_count") val likeCount: Int = 0,
    @SerializedName("entities") val entities: Entities? = null
)

data class Entities(
    val urls: List<UrlEntity>? = null,
    val hashtags: List<Hashtag>? = null,
    val media: List<Media>? = null
)

data class UrlEntity(
    val url: String,
    @SerializedName("expanded_url") val expandedUrl: String? = null,
    val display_url: String? = null
)

data class Hashtag(
    val text: String
)

data class Media(
    @SerializedName("media_url_https") val mediaUrl: String,
    val type: String
)
