package com.adib.eurekatest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private lateinit var firstSelected: TextView
    private lateinit var secondSelected: TextView
    private lateinit var thirdSelected: TextView
    private lateinit var fourthSelected: TextView
    private lateinit var fifthSelected: TextView
    private lateinit var firstCorrect: TextView
    private lateinit var secondCorrect: TextView
    private lateinit var thirdCorrect: TextView
    private lateinit var fourthCorrect: TextView
    private lateinit var fifthCorrect: TextView

    private lateinit var tvScore: TextView
    private lateinit var tvRight: TextView
    private lateinit var tvWrong: TextView

    companion object{
        const val FIRST_SELECTED = "FIRST_SELECTED"
        const val FIRST_CORRECT = "FIRST_CORRECT"
        const val SECOND_SELECTED = "SECOND_SELECTED"
        const val SECOND_CORRECT = "SECOND_CORRECT"
        const val THIRD_SELECTED = "THIRD_SELECTED"
        const val THIRD_CORRECT = "THIRD_CORRECT"
        const val FOURTH_SELECTED = "FOURTH_SELECTED"
        const val FOURTH_CORRECT = "FOURTH_CORRECT"
        const val FIFTH_SELECTED = "FIFTH_SELECTED"
        const val FIFTH_CORRECT = "FIFTH_CORRECT"

        const val SCORE = "SCORE"
        const val RIGHT = "RIGHT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        firstSelected = findViewById(R.id.first_selected)
        firstCorrect = findViewById(R.id.first_correct)
        secondSelected = findViewById(R.id.second_selected)
        secondCorrect = findViewById(R.id.second_correct)
        thirdSelected = findViewById(R.id.third_selected)
        thirdCorrect = findViewById(R.id.third_correct)
        fourthSelected = findViewById(R.id.fourth_selected)
        fourthCorrect = findViewById(R.id.fourth_correct)
        fifthSelected = findViewById(R.id.fifth_selected)
        fifthCorrect = findViewById(R.id.fifth_correct)

        tvScore = findViewById(R.id.tv_score)
        tvRight = findViewById(R.id.tv_right)
        tvWrong = findViewById(R.id.tv_wrong)

        firstSelected.text = intent.getStringExtra(FIRST_SELECTED)
        firstCorrect.text = intent.getStringExtra(FIRST_CORRECT)
        secondSelected.text = intent.getStringExtra(SECOND_SELECTED)
        secondCorrect.text = intent.getStringExtra(SECOND_CORRECT)
        thirdSelected.text = intent.getStringExtra(THIRD_SELECTED)
        thirdCorrect.text = intent.getStringExtra(THIRD_CORRECT)
        fourthSelected.text = intent.getStringExtra(FOURTH_SELECTED)
        fourthCorrect.text = intent.getStringExtra(FOURTH_CORRECT)
        fifthSelected.text = intent.getStringExtra(FIFTH_SELECTED)
        fifthCorrect.text = intent.getStringExtra(FIFTH_CORRECT)

        tvScore.text = intent.getIntExtra(SCORE, 0).toString()
        tvRight.text = intent.getIntExtra(RIGHT,0).toString()
        tvWrong.text = (5 - intent.getIntExtra(RIGHT,0)).toString()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val home = Intent(this@ResultActivity, MainActivity::class.java)
        startActivity(home)
    }
}