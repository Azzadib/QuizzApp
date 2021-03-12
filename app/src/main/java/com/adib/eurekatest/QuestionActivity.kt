package com.adib.eurekatest

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.*
import java.util.*
import kotlin.collections.ArrayList

class QuestionActivity : AppCompatActivity(), Communicator {
    companion object {
        var timeLeft = 0L
        const val SELECTED_CATEGORY_ID = "SELECTED_CATEGORY_ID"
        const val SELECTED_CATEGORY_NAME = "SELECTED CATEGORY_NAME"
        var categorySelected = 0
        private var tabLayout: TabLayout? = null
        var viewPager: ViewPager? = null
    }
    private lateinit var items: ArrayList<Question>

    private lateinit var tvCategorySelected: TextView
    private lateinit var information: LinearLayout
    private lateinit var tvQuestionCount: TextView
    private lateinit var tvAnsweredCount: TextView
    private lateinit var tvTimeLeft: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var fabDone: FloatingActionButton
    private lateinit var tvNoQuestionAvailable: TextView

    private var correct:Int = 0
    private var score:Int = 0
    private var answered:Int = 0
    private var passed: Int = 0
    private var firstQuestionAnswered:Int = 0
    private var firstQuestionCorrect:Int = 0
    private var firstSelected: String = ""
    private var firstCorrect: String = ""
    private var secondQuestionAnswered:Int = 0
    private var secondQuestionCorrect:Int = 0
    private var secondSelected: String = ""
    private var secondCorrect: String =  ""
    private var thirdQuestionAnswered:Int = 0
    private var thirdQuestionCorrect:Int = 0
    private var thirdSelected: String = ""
    private var thirdCorrect: String = ""
    private var fourthQuestionAnswered:Int = 0
    private var fourthQuestionCorrect:Int = 0
    private var fourthSelected: String = ""
    private var fourthCorrect: String = ""
    private var fifthQuestionAnswered:Int = 0
    private var fifthQuestionCorrect:Int = 0
    private var fifthSelected: String = ""
    private var fifthCorrect: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        tabLayout = findViewById(R.id.tl_question)
        viewPager = findViewById(R.id.tl_question_viewpager)
        tvCategorySelected = findViewById(R.id.tv_category_selected)
        information = findViewById(R.id.information)
        tvQuestionCount = findViewById(R.id.tv_question_count)
        tvAnsweredCount = findViewById(R.id.tv_answered_count)
        tvTimeLeft = findViewById(R.id.tv_time_left)
        fabDone = findViewById(R.id.fab_done)
        tvNoQuestionAvailable = findViewById(R.id.tv_no_question_available)

        tvCategorySelected.text = intent.getStringExtra(SELECTED_CATEGORY_NAME)
        categorySelected = intent.getIntExtra(SELECTED_CATEGORY_ID, 0)

        fabDone.setOnClickListener {
            finishQuiz()
        }

        items = getQuestions()
        if(items.size > 0) {
            timeLeft = 120000L
            startTimer(timeLeft)
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 1"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 2"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 3"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 4"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 5"))
            tabLayout!!.tabGravity = GRAVITY_FILL

            val adapter = ViewPagerAdapter(this, supportFragmentManager, tabLayout!!.tabCount, items)
            viewPager!!.adapter = adapter

            viewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

            tabLayout!!.addOnTabSelectedListener(object: OnTabSelectedListener{
                override fun onTabSelected(tab: Tab) {
                    viewPager!!.currentItem = tab.position
                    tabLayout!!.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#000000"))
                    tvQuestionCount.text = (tab.position + 1).toString()
                    answered = firstQuestionAnswered + secondQuestionAnswered + thirdQuestionAnswered + fourthQuestionAnswered + fifthQuestionAnswered
                    tvAnsweredCount.text = answered.toString()
                }

                override fun onTabUnselected(tab: Tab) {
                    answered = firstQuestionAnswered + secondQuestionAnswered + thirdQuestionAnswered
                    + fourthQuestionAnswered + fifthQuestionAnswered
                    tvAnsweredCount.text = answered.toString()
                }

                override fun onTabReselected(tab: Tab) {
                    answered = firstQuestionAnswered + secondQuestionAnswered + thirdQuestionAnswered
                    + fourthQuestionAnswered + fifthQuestionAnswered
                    tvAnsweredCount.text = answered.toString()
                }
            })
        } else {
            viewPager!!.visibility = View.GONE
            tabLayout!!.visibility = View.GONE
            information.visibility = View.GONE
            fabDone.visibility = View.GONE
            tvNoQuestionAvailable.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (items.size > 0) finishQuiz()
    }

    override fun checkOne(oneAnswered: String) {
        firstSelected = oneAnswered
        when(firstSelected) {
            items[0].correctAnswer -> {
                firstQuestionAnswered = 1
                firstQuestionCorrect = 1
                firstCorrect = ""
            }
            "" -> {
                firstQuestionAnswered = 0
                firstQuestionCorrect = 0
                firstCorrect = items[0].correctAnswer
            }
            else -> {
                firstQuestionAnswered = 1
                firstQuestionCorrect = 0
                firstCorrect = items[0].correctAnswer
            }
        }
    }

    override fun checkTwo(twoAnswered: String) {
        secondSelected = twoAnswered
        when(secondSelected) {
            items[1].correctAnswer -> {
                secondQuestionAnswered = 1
                secondQuestionCorrect = 1
                secondCorrect = ""
            }
            "" -> {
                secondQuestionAnswered = 0
                secondQuestionCorrect = 0
                secondCorrect = items[1].correctAnswer
            }
            else -> {
                secondQuestionAnswered = 1
                secondQuestionCorrect = 0
                secondCorrect = items[1].correctAnswer
            }
        }

    }

    override fun checkThree(threeAnswered: String) {
        thirdSelected = threeAnswered
        when(thirdSelected) {
            items[2].correctAnswer -> {
                thirdQuestionAnswered = 1
                thirdQuestionCorrect = 1
                thirdCorrect = ""
            }
            "" -> {
                thirdQuestionAnswered = 0
                thirdQuestionCorrect = 0
                thirdCorrect = items[2].correctAnswer
            }
            else -> {
                thirdQuestionAnswered = 1
                thirdQuestionCorrect = 0
                thirdCorrect = items[2].correctAnswer
            }
        }
    }

    override fun checkFour(fourAnswered: String) {
        fourthSelected = fourAnswered
        when(fourthSelected) {
            items[3].correctAnswer -> {
                fourthQuestionAnswered = 1
                fourthQuestionCorrect = 1
                fourthCorrect = ""
            }
            "" -> {
                fourthQuestionAnswered = 0
                fourthQuestionCorrect = 0
                fourthCorrect = items[3].correctAnswer
            }
            else -> {
                fourthQuestionAnswered = 1
                fourthQuestionCorrect = 0
                fourthCorrect = items[3].correctAnswer
            }
        }
    }
    override fun checkFive(fiveAnswered: String) {
        fifthSelected = fiveAnswered
        when(fifthSelected) {
            items[4].correctAnswer -> {
                fifthQuestionAnswered = 1
                fifthQuestionCorrect = 1
                fifthCorrect = ""
            }
            "" -> {
                fifthQuestionAnswered = 0
                fifthQuestionCorrect = 0
                fifthCorrect = items[4].correctAnswer
            }
            else -> {
                fifthQuestionAnswered = 1
                fifthQuestionCorrect = 0
                fifthCorrect = items[4].correctAnswer
            }
        }
    }

    private fun startTimer(time_left: Long) {
        countDownTimer = object: CountDownTimer(time_left, 1000) {
            override fun onFinish() {
                finishQuiz()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateTimerShow()
            }
        }
        countDownTimer.start()
    }

    private fun updateTimerShow() {
        val minutes = (timeLeft/1000)/60
        val minutesText: String  = if (minutes <= 9) "0$minutes" else "$minutes"
        val seconds = (timeLeft/1000)%60
        val secondsText: String = if (seconds <= 9) "0$seconds" else "$seconds"
        val count = "$minutesText:$secondsText"

        tvTimeLeft.text = count
        if (minutes < 1 && seconds <= 30) tvTimeLeft.setTextColor(Color.parseColor("#FF0000"))
    }

    private fun getQuestions(): ArrayList<Question> {
        val databaseHandler = DatabaseHandler(this)
        return databaseHandler.viewQuestion(categorySelected)
    }

    private fun finishQuiz() {
        if (thirdSelected == "") checkThree("")
        if (fourthSelected == "") checkFour("")
        if (fifthSelected == "") checkFive("")
        correct = firstQuestionCorrect + secondQuestionCorrect + thirdQuestionCorrect + fourthQuestionCorrect + fifthQuestionCorrect
        score = correct * 2
        if (score >= 6) passed = 1
        Toast.makeText(this, "Score: $score", Toast.LENGTH_SHORT).show()
        val showResult = Intent(this@QuestionActivity, ResultActivity::class.java)
        Log.d("TAG", "result is $firstSelected")
        showResult.putExtra(ResultActivity.FIRST_SELECTED, firstSelected)
        showResult.putExtra(ResultActivity.FIRST_CORRECT, firstCorrect)
        showResult.putExtra(ResultActivity.SECOND_SELECTED, secondSelected)
        showResult.putExtra(ResultActivity.SECOND_CORRECT, secondCorrect)
        showResult.putExtra(ResultActivity.THIRD_SELECTED, thirdSelected)
        showResult.putExtra(ResultActivity.THIRD_CORRECT, thirdCorrect)
        showResult.putExtra(ResultActivity.FOURTH_SELECTED, fourthSelected)
        showResult.putExtra(ResultActivity.FOURTH_CORRECT, fourthCorrect)
        showResult.putExtra(ResultActivity.FIFTH_SELECTED, fifthSelected)
        showResult.putExtra(ResultActivity.FIFTH_CORRECT, fifthCorrect)
        showResult.putExtra(ResultActivity.SCORE, score)
        showResult.putExtra(ResultActivity.RIGHT, correct)
        startActivity(showResult)
        countDownTimer.cancel()
        score = 0
        correct = 0
        passed = 0
    }
}

interface Communicator {
    fun checkOne(oneAnswered: String)
    fun checkTwo(twoAnswered: String)
    fun checkThree(threeAnswered: String)
    fun checkFour(fourAnswered: String)
    fun checkFive(fiveAnswered: String)
}