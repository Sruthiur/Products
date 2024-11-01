package com.products.products.data
import com.products.products.Util.BaseApiResponse
import com.products.products.Util.NetworkResult
import com.products.products.model.Product
import com.products.products.model.ProductDetails
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource) : BaseApiResponse() {


    suspend fun getProductList(
        limit: String?,
        skip: String?
    ): Flow<NetworkResult<Product>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getProductList(limit,skip) })
        }.flowOn(Dispatchers.IO)
    }


}
