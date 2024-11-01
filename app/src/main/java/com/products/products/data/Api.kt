package com.products.products.data

import com.products.products.Util.Constants
import com.products.products.model.Product
import com.products.products.model.ProductDetails
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @GET(Constants.PRODUCT)
    suspend fun getProductList(
        @Query("limit") limit: String?,
        @Query("skip") skip: String?
    ): Response<Product>

    @GET
    fun getProductDetailList(
        @Url url: String
    ): Call<ProductDetails>
}