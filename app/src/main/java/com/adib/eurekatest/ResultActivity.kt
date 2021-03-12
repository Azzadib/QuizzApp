package com.adib.eurekatest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private lateinit var tvScore: TextView
    private lateinit var tvRight: TextView
    private lateinit var tvWrong: TextView

    private  var expandableListView: ExpandableListView? = null
    private  var adapter: BaseExpandableListAdapter? = null
    private  var titleList: List<String> ? = null

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

    private var firstSelected: String? = null
    private var firstCorrect: String? = null
    private var secondSelected: String? = null
    private var secondCorrect: String? = null
    private var thirdSelected: String? = null
    private var thirdCorrect: String? = null
    private var fourthSelected: String? = null
    private var fourthCorrect: String? = null
    private var fifthSelected: String? = null
    private var fifthCorrect: String? = null

    private val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val resultOne = ArrayList<String>()
            firstSelected?.let { resultOne.add(it) }
            firstCorrect?.let { resultOne.add(it) }

            val resultTwo = ArrayList<String>()
            secondSelected?.let { resultTwo.add(it) }
            secondCorrect?.let { resultTwo.add(it) }

            //val resultThree = ArrayList<String>()
            //thirdSelected?.let { resultThree.add(it) }
            //thirdCorrect?.let { resultThree.add(it) }

            //val resultFour = ArrayList<String>()
            //fourthSelected?.let { resultFour.add(it) }
            //fourthCorrect?.let { resultFour.add(it) }

            //val resultFive = ArrayList<String>()
            //fifthSelected?.let { resultFive.add(it) }
            //fifthCorrect?.let { resultFive.add(it) }

            listData["Question 1"] = resultOne
            listData["Question 2"] = resultTwo
            //listData["Question 3"] = resultThree
            //listData["Question 4"] = resultFour
            //listData["Question 5"] = resultFive

            return listData
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvScore = findViewById(R.id.tv_score)
        tvRight = findViewById(R.id.tv_right)
        tvWrong = findViewById(R.id.tv_wrong)

        tvScore.text = intent.getIntExtra(SCORE, 0).toString()
        tvRight.text = intent.getIntExtra(RIGHT,0).toString()
        tvWrong.text = (5 - intent.getIntExtra(RIGHT,0)).toString()

        firstSelected = intent.getStringExtra(FIRST_SELECTED)
        firstCorrect = intent.getStringExtra(FIRST_CORRECT)
        secondSelected = intent.getStringExtra(SECOND_SELECTED)
        secondCorrect = intent.getStringExtra(SECOND_CORRECT)
        thirdSelected = intent.getStringExtra(THIRD_SELECTED)
        thirdCorrect = intent.getStringExtra(THIRD_CORRECT)
        fourthSelected = intent.getStringExtra(FOURTH_SELECTED)
        fourthCorrect = intent.getStringExtra(FOURTH_CORRECT)
        fifthSelected = intent.getStringExtra(FIFTH_SELECTED)
        fifthCorrect = intent.getStringExtra(FIFTH_CORRECT)

        expandableListView = findViewById(R.id.expandable_list_view)
        val dataList = data
        titleList = ArrayList(dataList.keys)
        adapter = ExpandableAdapter(this, titleList as ArrayList<String>, dataList)
        expandableListView!!.setAdapter(adapter)

        Log.d("Expandable_check", "result: $dataList")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val home = Intent(this@ResultActivity, MainActivity::class.java)
        startActivity(home)
    }
}