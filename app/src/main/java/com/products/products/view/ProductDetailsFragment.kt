package com.products.products.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.products.products.R
import com.products.products.databinding.FragmentProductDetailsBinding
import com.products.products.databinding.FragmentProductsBinding
import com.products.products.view.adapter.ProductListItemAdapter
import com.products.products.viewmodel.ProductDetailViewModel
import com.products.products.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    var binding: FragmentProductDetailsBinding? = null
    var id: String? = null
    val productDetailViewModel: ProductDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_details, container, false
        )
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = getArguments()?.getString("id")

        id?.let { productDetailViewModel.getProductDetailList(it) }

        productDetailViewModel.productLiveData?.observe(viewLifecycleOwner) { response ->

            binding?.description?.text = response?.description
            binding?.stockval?.text = response?.stock.toString()
            binding?.titles?.text = response?.title.toString()
            binding?.discountval?.text = response?.discountPercentage.toString()
            binding?.ratingval?.text = response?.rating.toString()
            binding?.priceval?.text = response?.price.toString()
            binding?.categoryval?.text = response?.category.toString()

            binding?.template?.let {
                Glide.with(requireContext())
                    .load(response?.thumbnail)
                    .into(it)
            }
        }

    }
}