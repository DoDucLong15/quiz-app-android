package dev.ddlong07.quizappv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ddlong07.quizappv2.databinding.ViewholderLeadersBinding
import dev.ddlong07.quizappv2.domain.UserModel

class LeaderAdapter: RecyclerView.Adapter<LeaderAdapter.ViewHolder>() {
    private lateinit var binding: ViewholderLeadersBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        binding = ViewholderLeadersBinding.inflate(inflate, parent, false)
        return ViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemBinding = ViewholderLeadersBinding.bind(holder.itemView)
        itemBinding.titleTxt.text = differ.currentList[position].name

        val drawableResourceId: Int = itemBinding.root.resources.getIdentifier(
            differ.currentList[position].pic,
            "drawable",
            itemBinding.root.context.packageName
        )
        Glide.with(itemBinding.root.context)
            .load(drawableResourceId)
            .into(itemBinding.pic)

        itemBinding.rowTxt.text = (position + 4).toString()
        itemBinding.scoreTxt.text = differ.currentList[position].score.toString()
    }

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root) {

    }

    private val differCallback = object: DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}