package com.akshai.skratefeaturebuilding.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akshai.skratefeaturebuilding.databinding.FragmentHomeBinding
import com.akshai.skratefeaturebuilding.databinding.LayoutOverviewItemBinding
import com.akshai.skratefeaturebuilding.databinding.LayoutSessionItemBinding
import com.akshai.skratefeaturebuilding.model.OverViewModel
import com.akshai.skratefeaturebuilding.reponse.UpcomingSessionsItem
import javax.inject.Inject

class UpCommingSessionAdaptor @Inject constructor() :
    RecyclerView.Adapter<UpCommingSessionAdaptor.OverViewHolder>() {

    var list: List<UpcomingSessionsItem> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverViewHolder {
        val binding =
            LayoutSessionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OverViewHolder, position: Int) {
        holder.setData(list[position], position)
    }

    override fun getItemCount(): Int = list.size


    /*  private val diffUtil = object : DiffUtil.ItemCallback<OverViewModel>() {
          override fun areItemsTheSame(oldItem: OverViewModel, newItem: OverViewModel): Boolean {

              return oldItem.count == newItem.count
          }

          override fun areContentsTheSame(oldItem: OverViewModel, newItem: OverViewModel): Boolean {
              return oldItem == newItem
          }

      }*/

    class OverViewHolder(val binding: LayoutSessionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: UpcomingSessionsItem, position: Int) {
            binding.apply {
                model = data
               // remote.stateListAnimator = null
            }
        }
    }
}