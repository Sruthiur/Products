package com.products.products.viewmodel

import androidx.lifecycle.*
import com.products.products.data.Api

import com.products.products.data.Repository
import com.products.products.model.ProductDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(var api: Api): ViewModel() {

    val productLiveData: MutableLiveData<ProductDetails?>? =  MutableLiveData()

    fun getProductDetailList(id: String) {
        val url = "https://dummyjson.com/products/"+id
        val call = api.getProductDetailList(url)

        call.enqueue(object : Callback<ProductDetails> {
            override fun onResponse(
                call: Call<ProductDetails>,
                response: Response<ProductDetails>
            ) {
                if (response.isSuccessful) {
                    productLiveData?.value = response.body()
                } else {
                    productLiveData?.value = null

                }

            }

            override fun onFailure(call: Call<ProductDetails>, t: Throwable) {


            }

        })
    }
}