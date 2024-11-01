package com.products.products.data

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: Api) {

    suspend fun getProductList(
        limit: String?,
        skip: String?
    ) =
        api.getProductList(limit,skip)



}