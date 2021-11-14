package com.example.furortask.business.data.remote.model


import com.google.gson.annotations.SerializedName

data class PutProductRequstModel(
    @SerializedName("address")
    val address: String, // string
    @SerializedName("cost")
    val cost: Int, // 0
    @SerializedName("created_date")
    val createdDate: String, // 2021-11-14T11:03:00.471Z
    @SerializedName("id")
    val id: Int, // 0
    @SerializedName("name_uz")
    val nameUz: String, // string
    @SerializedName("product_type_id")
    val productTypeId: Int // 0
)