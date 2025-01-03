package dev.ddlong07.quizappv2.activity

import android.app.DownloadManager.Request
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dev.ddlong07.quizappv2.R
import dev.ddlong07.quizappv2.adapter.QuestionAdapter
import dev.ddlong07.quizappv2.databinding.ActivityQuestionBinding
import dev.ddlong07.quizappv2.domain.QuestionModel

class QuestionActivity : AppCompatActivity(), QuestionAdapter.score {
    private lateinit var binding: ActivityQuestionBinding
    var position: Int = 0
    var receiveList: MutableList<QuestionModel> = mutableListOf()
    var allScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val window = this@QuestionActivity.window
        window.statusBarColor = ContextCompat.getColor(this@QuestionActivity, R.color.grey)

        receiveList = intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()

        binding.apply {
            backBtn.setOnClickListener { finish() }
            progressBar.progress = 1
            questionTxt.text = receiveList[position].question
            questionNumberTxt.text = "Question " + progressBar.progress + "/10"
            val drawableResourceId: Int = binding.root.resources.getIdentifier(
                receiveList[position].picPath,
                "drawable",
                binding.root.context.packageName
            )
            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                .into(pic)

            loadAnswers()

            rightArrow.setOnClickListener {
                if(progressBar.progress == 10) {
                    val intent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                    intent.putExtra("score", allScore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }
                position++
                progressBar.progress = progressBar.progress + 1
                questionNumberTxt.text = "Question " + progressBar.progress + "/10"
                questionTxt.text = receiveList[position].question.toString()

                val drawableResourceId: Int = binding.root.resources.getIdentifier(
                    receiveList[position].picPath,
                    "drawable",
                    binding.root.context.packageName
                )
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(pic)

                loadAnswers()
            }

            leftArrow.setOnClickListener {
                if(progressBar.progress == 1) {
                    return@setOnClickListener
                }
                position--
                progressBar.progress = progressBar.progress - 1
                questionNumberTxt.text = "Question " + progressBar.progress + "/10"
                questionTxt.text = receiveList[position].question.toString()

                val drawableResourceId: Int = binding.root.resources.getIdentifier(
                    receiveList[position].picPath,
                    "drawable",
                    binding.root.context.packageName
                )
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(pic)

                loadAnswers()
            }
        }
    }

    private fun loadAnswers() {
        val users: MutableList<String> = mutableListOf()
        users.add(receiveList[position].answer_1.toString())
        users.add(receiveList[position].answer_2.toString())
        users.add(receiveList[position].answer_3.toString())
        users.add(receiveList[position].answer_4.toString())

        if(receiveList[position].clickedAnswer != null) users.add(receiveList[position].clickedAnswer.toString())

        val questionAdapter by lazy {
            QuestionAdapter(
                receiveList[position].correctAnswer.toString(), users, this
            )
        }
        questionAdapter.differ.submitList(users)
        binding.questionList.apply {
            layoutManager = LinearLayoutManager(this@QuestionActivity)
            adapter = questionAdapter
        }
    }

    override fun amount(number: Int, clickedAnswer: String) {
        allScore += number
        receiveList[position].clickedAnswer = clickedAnswer
    }
}