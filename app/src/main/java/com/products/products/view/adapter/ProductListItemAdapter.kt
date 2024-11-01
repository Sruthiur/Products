package com.products.products.view.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.products.products.R
import com.products.products.model.ProductX

class ProductListItemAdapter
    (mcontext: Context?,
  private var mList: List<ProductX?>?,
  private val listener: ProductsItemClicked
) : RecyclerView.Adapter<ProductListItemAdapter.ViewHolder>() {

    val context = mcontext
    private var selectedCategories = mutableListOf<String>()
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.productitemlist, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList?.get(position)
        val categoryId = mList?.get(position)?.id

        holder.titles.text = ItemsViewModel?.title
        holder.description.text = ItemsViewModel?.description
        holder.categoryval.text = ItemsViewModel?.category
        holder.discountval.text = ItemsViewModel?.discountPercentage.toString()
        holder.priceval.text = ItemsViewModel?.price.toString()
        holder.stockval.text = ItemsViewModel?.stock.toString()
        holder.ratingval.text = ItemsViewModel?.rating.toString()
//        holder.imgInfo.setImageResource(R.drawable.ic_launcher_background)

        if (context != null) {
            Glide.with(context)
                .load(ItemsViewModel?.thumbnail)
                .into(holder.imgInfo)
        }
        holder.itemView.setOnClickListener {
            listener.onProductsItemClicked(ItemsViewModel?.id.toString())
        }


            if (position == mList?.size!! - 1) {
                listener.onScrollChange(true)
            }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList!!.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val titles: TextView = itemView.findViewById(R.id.titles)
        val description: TextView = itemView.findViewById(R.id.description)
        val imgInfo: ImageView = itemView.findViewById(R.id.template)
        val categoryval: TextView = itemView.findViewById(R.id.categoryval)
        val discountval: TextView = itemView.findViewById(R.id.discountval)
        val priceval: TextView = itemView.findViewById(R.id.priceval)
        val stockval: TextView = itemView.findViewById(R.id.stockval)
        val ratingval: TextView = itemView.findViewById(R.id.ratingval)

    }

    interface ProductsItemClicked {
        fun onProductsItemClicked(selected: String) // Define callback method
        fun onScrollChange(selected: Boolean)
    }


}