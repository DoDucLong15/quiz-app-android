package dev.ddlong07.quizappv2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.ddlong07.quizappv2.R
import dev.ddlong07.quizappv2.databinding.ViewholderQuestionBinding
import dev.ddlong07.quizappv2.domain.QuestionModel

class QuestionAdapter(
    val correctAnswer: String,
    val users: MutableList<String> = mutableListOf(),
    val returnScore: score
): RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    private lateinit var binding: ViewholderQuestionBinding
    interface  score {
        fun amount(number: Int, clickedAnswer: String)
    }

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        binding = ViewholderQuestionBinding.inflate(inflate, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        val itemBinding = ViewholderQuestionBinding.bind(holder.itemView)
        itemBinding.answerTxt.text = differ.currentList[position]
        var correctPos = 0
        when(correctAnswer) {
            "a" -> {
                correctPos = 0
            }
            "b" -> {
                correctPos = 1
            }
            "c" -> {
                correctPos = 2
            }
            "d" -> {
                correctPos = 3
            }
        }
        if(differ.currentList.size == 5 && correctPos == position) {
            itemBinding.answerTxt.setBackgroundResource(R.drawable.green_background)
            itemBinding.answerTxt.setTextColor(ContextCompat.getColor(itemBinding.root.context, R.color.white))
            val drawable = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.tick)
            itemBinding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
        }
        if(differ.currentList.size == 5) {
            var clickedPos = 0;
            when(differ.currentList[4]) {
                "a" -> {
                    clickedPos = 0
                }
                "b" -> {
                    clickedPos = 1
                }
                "c" -> {
                    clickedPos = 2
                }
                "d" -> {
                    clickedPos = 3
                }
            }
            if(clickedPos == position && clickedPos != correctPos) {
                itemBinding.answerTxt.setBackgroundResource(R.drawable.red_background)
                itemBinding.answerTxt.setTextColor(ContextCompat.getColor(itemBinding.root.context, R.color.white))
                val drawable = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.thieves)
                itemBinding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
            }
        }
        if(position == 4) {
            binding.root.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            var str = ""
            when(position) {
                0 -> {
                    str = "a"
                }
                1 -> {
                    str = "b"
                }
                2 -> {
                    str = "c"
                }
                3 -> {
                    str = "d"
                }
            }
            users.add(4, str)
            notifyDataSetChanged()

            if(correctPos == position) {
                itemBinding.answerTxt.setBackgroundResource(R.drawable.green_background)
                itemBinding.answerTxt.setTextColor(ContextCompat.getColor(itemBinding.root.context, R.color.white))
                val drawable = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.tick)
                itemBinding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
                returnScore.amount(5, str)
            }
            else {
                itemBinding.answerTxt.setBackgroundResource(R.drawable.red_background)
                itemBinding.answerTxt.setTextColor(ContextCompat.getColor(itemBinding.root.context, R.color.white))
                val drawable = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.thieves)
                itemBinding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
                returnScore.amount(0, str)
            }
        }
        if(differ.currentList.size == 5) holder.itemView.setOnClickListener(null)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var differCallback = object: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}