package com.example.furortask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.furortask.databinding.ItemCategoryBinding
import com.example.furortask.presentation.ui.catalog.model.GetProductTypeModelLocal

class CategoryAdapter :
    ListAdapter<GetProductTypeModelLocal, CategoryAdapter.SpecialityVH>(ItemDiffer())
{
    private val TAG = "TAG"
    private var clickListener: SpecialityClickListener? = null
    fun itemClickListener(clickListener: SpecialityClickListener) {
        this.clickListener = clickListener
    }
    class SpecialityVH(
        private val binding: ItemCategoryBinding,
        private val clickListener: SpecialityClickListener?
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var model: GetProductTypeModelLocal? = null
        fun onBind(model: GetProductTypeModelLocal) {
            binding.root.setOnClickListener(this)
            this.model = model
            binding.tvCategoryNameUz.text = model.nameUz
            binding.tvCategoryNameEn.text = model.address
            binding.tvCategoryNameRu.text = model.cost.toString()
            binding.tvCategoryDesc.text = model.createdDate.toString()
        }

        override fun onClick(p0: View?) {
            clickListener?.onClicked(model!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityVH {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpecialityVH(binding, clickListener)
    }

    override fun onBindViewHolder(holder: SpecialityVH, position: Int) {
        holder.onBind(currentList[position])
    }

}

class ItemDiffer : DiffUtil.ItemCallback<GetProductTypeModelLocal>() {
    override fun areItemsTheSame(
        oldItem: GetProductTypeModelLocal,
        newItem: GetProductTypeModelLocal
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: GetProductTypeModelLocal,
        newItem: GetProductTypeModelLocal
    ): Boolean {
        return oldItem.id == newItem.id
    }

}

interface SpecialityClickListener {
    fun onClicked(model: GetProductTypeModelLocal)
}
