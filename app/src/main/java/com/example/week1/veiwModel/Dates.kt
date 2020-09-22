package com.example.week1.veiwModel

import com.google.gson.annotations.SerializedName

data class Dates (@SerializedName("maximum")
                  val maximum : String?,
                  @SerializedName("minimum")
                  val minimum : String?)