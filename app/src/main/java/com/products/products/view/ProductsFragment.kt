package com.products.products.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.products.products.ProductApp
import com.products.products.R
import com.products.products.Util.NetworkResult
import com.products.products.databinding.FragmentProductsBinding
import com.products.products.model.ProductX
import com.products.products.view.adapter.ProductListItemAdapter
import com.products.products.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ProductsFragment : Fragment(), ProductListItemAdapter.ProductsItemClicked {

    var binding: FragmentProductsBinding? = null
    private var productDataAdapter: ProductListItemAdapter?=null
    val productsViewModel: ProductsViewModel by viewModels()
    var skip = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_products, container, false
        )
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadNextPage("10", "0")

        }

    private fun loadNextPage(limit: String, skip: String) {
        productsViewModel.getProductList(limit,skip)

        productsViewModel.productResponse.observe(viewLifecycleOwner) {
                response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        setProduct(it.products, binding?.rvProducts)
                    }
                }

                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {

                }
                else -> {
                    Toast.makeText(ProductApp.instance, "No response found", Toast.LENGTH_SHORT).show()}
            }
        }
    }


    private fun setProduct(
        productItems: List<ProductX?>?,
        rv_product: RecyclerView?
    ) {
        rv_product?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        productDataAdapter = ProductListItemAdapter(context, productItems,this)
        rv_product?.adapter = productDataAdapter
    }

    override fun onProductsItemClicked(selected: String) {
        val bundle = Bundle()
        bundle.putString("id", selected)
        findNavController().navigate(R.id.action_productsFragment_to_productDetailsFragment,bundle)
    }

    override fun onScrollChange(selected: Boolean) {
        if (selected){
            loadNextPage("10", skip++.toString())
        }

    }
}