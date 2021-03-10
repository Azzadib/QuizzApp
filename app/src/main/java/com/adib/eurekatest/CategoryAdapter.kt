package com.adib.eurekatest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CategoryAdapter(private val context: Context, private val items: ArrayList<Category>):
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategoryName: TextView = view.findViewById(R.id.tv_category_name)
        val ivCategoryImage: ImageView = view.findViewById(R.id.iv_category_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(context).inflate(R.layout.card_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val item = items[position]
        val tag = item::class.simpleName
        Log.d(tag, "data type")
        Glide.with(holder.itemView.context)
            .load(item.image)
            .apply(RequestOptions().override(150, 150))
            .into(holder.ivCategoryImage)
        holder.tvCategoryName.text = item.name

        holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(items[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = items.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Category)
    }
}