package com.example.furortask.business.data.remote.model
import com.google.gson.annotations.SerializedName

data class GetProductResponseData(
    @SerializedName("address")
    val address: String?, // string
    @SerializedName("cost")
    val cost: Double?, // 0.0
    @SerializedName("created_date")
    val createdDate: Long?, // 1636817429000
    @SerializedName("id")
    val id: Int?, // 3850
    @SerializedName("name_uz")
    val nameUz: String?, // string
    @SerializedName("product_type_id")
    val productTypeId: Int? // 0
)