package dev.ddlong07.quizappv2.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.ddlong07.quizappv2.R
import dev.ddlong07.quizappv2.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding

    private var score: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val window = this@ScoreActivity.window
        window.statusBarColor = ContextCompat.getColor(this@ScoreActivity, R.color.grey)

        score = intent.getIntExtra("score", 0)

        binding.apply {
            scoreTxt.text = score.toString()
            backBtn.setOnClickListener {
                startActivity(Intent(this@ScoreActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}