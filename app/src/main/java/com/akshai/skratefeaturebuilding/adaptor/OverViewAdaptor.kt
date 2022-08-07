package com.akshai.skratefeaturebuilding.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akshai.skratefeaturebuilding.databinding.FragmentHomeBinding
import com.akshai.skratefeaturebuilding.databinding.LayoutOverviewItemBinding
import com.akshai.skratefeaturebuilding.model.OverViewModel
import javax.inject.Inject

class OverViewAdaptor @Inject constructor() :
    RecyclerView.Adapter<OverViewAdaptor.OverViewHolder>() {

    var overViewList: List<OverViewModel> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverViewHolder {
        val binding =
            LayoutOverviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OverViewHolder, position: Int) {
        holder.setData(overViewList[position], position)
    }

    override fun getItemCount(): Int = overViewList.size


    /*  private val diffUtil = object : DiffUtil.ItemCallback<OverViewModel>() {
          override fun areItemsTheSame(oldItem: OverViewModel, newItem: OverViewModel): Boolean {

              return oldItem.count == newItem.count
          }

          override fun areContentsTheSame(oldItem: OverViewModel, newItem: OverViewModel): Boolean {
              return oldItem == newItem
          }

      }*/

    class OverViewHolder(val binding: LayoutOverviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: OverViewModel, position: Int) {
            binding.apply {
                model = data
            }
        }
    }
}