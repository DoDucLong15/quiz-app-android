package dev.ddlong07.quizappv2.activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.ddlong07.quizappv2.R
import dev.ddlong07.quizappv2.databinding.ActivityMainBinding
import dev.ddlong07.quizappv2.domain.QuestionModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val window: Window = this@MainActivity.window
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.grey)

        binding.apply {
            menu.setItemSelected(R.id.home)
            menu.setOnItemSelectedListener {
                if(it == R.id.board) {
                    startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                }
            }
            singleBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, QuestionActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
                startActivity(intent)
            }
        }
    }

    private fun questionList(): MutableList<QuestionModel> {
        val questions: MutableList<QuestionModel> = mutableListOf()
        questions.add(QuestionModel(
            1,
            "Which planet is the larget planet in the solar system?",
            "Earth",
            "Mars",
            "Neptune",
            "Jupiter",
            "d",
            5,
            "q_1",
            null))
        questions.add(QuestionModel(
            2,
            "Which country is the larget country in the world by land area?",
            "Russia",
            "Canada",
            "United States",
            "China",
            "a",
            5,
            "q_2",
            null))
        questions.add(QuestionModel(
            3,
            "Which of the following substances is used as an anti-cancer medication in the medical world?",
            "Cheese",
            "Lemon juice",
            "Cannabis",
            "Paspalum",
            "c",
            5,
            "q_3",
            null))
        questions.add(QuestionModel(
            4,
            "Which moon in the Earth's solar system has an atmosphere?",
            "Luna (Moon)",
            "Phobos (Mars' moon)",
            "Venus' moon",
            "None of the above",
            "d",
            5,
            "q_4",
            null))
        questions.add(QuestionModel(
            5,
            "Which of the following symbols represents the chemical element with the atomic number 6?",
            "O",
            "H",
            "C",
            "N",
            "c",
            5,
            "q_5",
            null))
        questions.add(QuestionModel(
            6,
            "Who is credited with inventing theater as we know it today?",
            "Shakespeare",
            "Arthur Miller",
            "Ashkouri",
            "Ancient Greeks",
            "d",
            5,
            "q_6",
            null))
        questions.add(QuestionModel(
            7,
            "Which ocean is the largest ocean in the world?",
            "Pacific Ocean",
            "Atlantic Ocean",
            "Indian Ocean",
            "Arctic Ocean",
            "a",
            5,
            "q_7",
            null))
        questions.add(QuestionModel(
            8,
            "Which religions are among the most practiced religions in the world?",
            "Islam, Christianity, Judaism",
            "Buddhism, Hinduism, Sikhism",
            "Zoroastrianism, Brahmanism, Yazdanism",
            "Taoism, Shintoism, Confucianism",
            "a",
            5,
            "q_8",
            null))
        questions.add(QuestionModel(
            9,
            "In which continent are the most independent countries located",
            "Asia",
            "Europe",
            "Africa",
            "Americas",
            "c",
            5,
            "q_9",
            null))
        questions.add(QuestionModel(
            10,
            "Which ocean has the greatest average depth?",
            "Pacific Ocean",
            "Atlantic Ocean",
            "Indian Ocean",
            "Southern Ocean",
            "d",
            5,
            "q_10",
            null))
        return questions
    }
}