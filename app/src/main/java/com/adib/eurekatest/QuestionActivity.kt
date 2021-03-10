package com.adib.eurekatest

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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
    companion object {
        var timeLeft = 0L
        const val SELECTED_CATEGORY_ID = "SELECTED_CATEGORY_ID"
        const val SELECTED_CATEGORY_NAME = "SELECTED CATEGORY_NAME"
        var categorySelected = 0

        private var tabLayout: TabLayout? = null
        var viewPager: ViewPager? = null
    }
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
            correct = firstQuestionCorrect + secondQuestionCorrect + thirdQuestionCorrect + fourthQuestionCorrect + fifthQuestionCorrect
            score = correct * 2
            if (score >= 6) passed = 1
            Toast.makeText(this, "Score: $score", Toast.LENGTH_SHORT).show()
            if (getResult().size > 0) {
                updateResultRecord()
            }
            else {
                addResultRecord()
            }
            countDownTimer.cancel()
            score = 0
            correct = 0
            passed = 0
            val showResult = Intent(this@QuestionActivity, ResultActivity::class.java)
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
            startActivity(showResult)
        }

        if(getQuestions().size > 0) {
            timeLeft = 120000L
            startTimer(timeLeft)
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 1"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 2"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 3"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 4"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Soal 5"))
            tabLayout!!.tabGravity = GRAVITY_FILL

            val adapter = ViewPagerAdapter(this, supportFragmentManager, tabLayout!!.tabCount, getQuestions())
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

    override fun checkOne(
        answeredOne: Int,
        correctOne: Int,
        oneAnswered: String,
        oneCorrect: String
    ) {
        firstQuestionAnswered = answeredOne
        firstQuestionCorrect = correctOne
        firstSelected = oneAnswered
        firstCorrect = oneCorrect
    }

    override fun checkTwo(
        answeredTwo: Int,
        correctTwo: Int,
        twoAnswered: String,
        twoCorrect: String
    ) {
        secondQuestionAnswered =  answeredTwo
        secondQuestionCorrect = correctTwo
        secondSelected = twoAnswered
        secondCorrect = twoCorrect
    }

    override fun checkThree(
        answeredThree: Int,
        correctThree: Int,
        threeAnswered: String,
        threeCorrect: String
    ) {
        thirdQuestionAnswered =  answeredThree
        thirdQuestionCorrect = correctThree
        thirdSelected = threeAnswered
        thirdCorrect = threeCorrect
    }

    override fun checkFour(
        answeredFour: Int,
        correctFour: Int,
        fourAnswered: String,
        fourCorrect: String
    ) {
        fourthQuestionAnswered =  answeredFour
        fourthQuestionCorrect = correctFour
        fourthSelected = fourAnswered
        fourthCorrect = fourCorrect
    }
    override fun checkFive(
        answeredFive: Int,
        correctFive: Int,
        fiveAnswered: String,
        fiveCorrect: String
    ) {
        fifthQuestionAnswered = answeredFive
        fifthQuestionCorrect = correctFive
        fifthSelected = fiveAnswered
        fifthCorrect = fiveCorrect
    }

    private fun startTimer(time_left: Long) {
        countDownTimer = object: CountDownTimer(time_left, 1000) {
            override fun onFinish() {
                val home = Intent(this@QuestionActivity, MainActivity::class.java)
                startActivity(home)
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

    private fun getResult(): ArrayList<Result> {
        val databaseHandler = DatabaseHandler(this)
        return databaseHandler.viewResult(categorySelected)
    }

    private fun addResultRecord(): Long {
        val result = Result()
        val databaseHandler = DatabaseHandler(this)
        result.passed = passed
        result.resultCategoryId = categorySelected
        result.score = score
        return databaseHandler.addResult(result)
    }

    private fun updateResultRecord(): Int {
        val result = Result()
        val databaseHandler = DatabaseHandler(this)
        result.passed = passed
        result.resultCategoryId = categorySelected
        result.score = score

        return databaseHandler.updateResult(result)
    }

    override fun onPause() {
        super.onPause()
        if (getQuestions().size > 0) {
            countDownTimer.cancel()
            correct = 0
            score = 0
            val home = Intent(this@QuestionActivity, MainActivity::class.java)
            startActivity(home)

        }
    }
}

interface Communicator {
    fun checkOne(answeredOne: Int, correctOne: Int, oneAnswered: String, oneCorrect: String)
    fun checkTwo(answeredTwo: Int, correctTwo:Int, twoAnswered: String, twoCorrect: String)
    fun checkThree(answeredThree: Int, correctThree: Int, threeAnswered: String, threeCorrect: String)
    fun checkFour(answeredFour: Int, correctFour: Int, fourAnswered: String, fourCorrect: String)
    fun checkFive(answeredFive: Int, correctFive: Int, fiveAnswered: String, oneCorrect: String)
}