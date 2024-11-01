package com.products.products.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.products.products.Util.NetworkResult
import com.products.products.data.Api
import com.products.products.data.Repository
import com.products.products.model.Product
import com.products.products.model.ProductDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _productResponse: MutableLiveData<NetworkResult<Product>> = MutableLiveData()
    val productResponse: LiveData<NetworkResult<Product>> = _productResponse


    fun getProductList(
        limit: String?,
        skip: String?
    ) = viewModelScope.launch {
        repository.getProductList(limit,skip).collect { values ->
            _productResponse.value = values
        }
    }


}