package com.example.furortask.presentation.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.furortask.business.data.remote.model.GetProductResponseData
import com.example.furortask.databinding.ItemCategoryBinding

class ProductPagingAdapter :
    PagingDataAdapter<GetProductResponseData, ProductPagingAdapter.SpecialityVH>(ItemDiffer()) {

    private val TAG = "TAG"
    private var clickListener: SpecialityClickListener? = null
    fun itemClickListener(clickListener: SpecialityClickListener) {
        this.clickListener = clickListener
    }

    class SpecialityVH(
        private val binding: ItemCategoryBinding,
        private val clickListener: SpecialityClickListener?
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var model: GetProductResponseData? = null
        fun onBind(model: GetProductResponseData) {
            binding.root.setOnClickListener(this)
            this.model = model
            binding.tvProductTypeId.text = model.productTypeId.toString()
            binding.tvNameUz.text = model.nameUz
            binding.tvCost.text = model.cost.toString()
            binding.tvAddress.text = model.address.toString()
            binding.tvCreatedDate.text = model.createdDate.toString()
        }

        override fun onClick(p0: View?) {
            clickListener?.onClicked(model!!)
        }
    }

    override fun onBindViewHolder(holder: SpecialityVH, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.onBind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityVH {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpecialityVH(binding, clickListener)
    }


}

class ItemDiffer : DiffUtil.ItemCallback<GetProductResponseData>() {
    override fun areItemsTheSame(
        oldItem: GetProductResponseData,
        newItem: GetProductResponseData
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: GetProductResponseData,
        newItem: GetProductResponseData
    ): Boolean {
        return oldItem.id == newItem.id
    }

}

interface SpecialityClickListener {
    fun onClicked(model: GetProductResponseData)
}