package com.example.foodmarket.model.response.home


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @Expose
    @SerializedName("created_at")
    val createdAt: String,
    @Expose
    @SerializedName("data")
    val `data`: List<Data>,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("ingredients")
    val ingredients: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("picturePath")
    val picturePath: String,
    @Expose
    @SerializedName("price")
    val price: String,
    @Expose
    @SerializedName("rate")
    val rate: String,
    @Expose
    @SerializedName("types")
    val types: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String
)