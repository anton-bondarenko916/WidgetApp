package com.eligon.widget.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class PriceWithTime(
    @SerializedName("price")
    val price: String,
    @SerializedName("timestamp")
    val timestamp: Long)