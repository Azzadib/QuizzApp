package com.adib.eurekatest

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class ResultActivity : AppCompatActivity() {
    private lateinit var firstSpinner: Spinner
    private lateinit var secondSpinner: Spinner
    private lateinit var thirdSpinner: Spinner
    private lateinit var fourthSpinner: Spinner
    private lateinit var fifthSpinner: Spinner

    var firstArray = arrayOf(intent.getStringExtra(FIRST_SELECTED), intent.getStringExtra(
        FIRST_CORRECT))
    var secondArray = arrayOf(intent.getStringExtra(SECOND_SELECTED), intent.getStringExtra(
        SECOND_CORRECT))
    var thirdArray = arrayOf(intent.getStringExtra(THIRD_SELECTED), intent.getStringExtra(
        THIRD_CORRECT))
    var fourthArray = arrayOf(intent.getStringExtra(FOURTH_SELECTED), intent.getStringExtra(
        FOURTH_CORRECT))
    var fifthArray = arrayOf(intent.getStringExtra(FIFTH_SELECTED), intent.getStringExtra(
        FIFTH_CORRECT))
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
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        firstSpinner = findViewById(R.id.first)
        secondSpinner = findViewById(R.id.second)
        thirdSpinner = findViewById(R.id.third)
        fourthSpinner = findViewById(R.id.fourth)
        fifthSpinner = findViewById(R.id.fifth)

        val firstAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, firstArray)
        val secondAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, secondArray)
        val thirdAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, thirdArray)
        val fourthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, fourthArray)
        val fifthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, fifthArray)

        firstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        thirdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fourthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fifthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        firstSpinner.adapter = firstAdapter
        secondSpinner.adapter = secondAdapter
        thirdSpinner.adapter = thirdAdapter
        fourthSpinner.adapter = fourthAdapter
        fifthSpinner.adapter = fifthAdapter

        if (intent.getStringExtra(FIRST_CORRECT) == "") firstSpinner.setBackgroundColor(Color.parseColor("#FF0000"))
        else firstSpinner.setBackgroundColor(Color.parseColor("#07DA63"))
        if (intent.getStringExtra(SECOND_CORRECT) == "") secondSpinner.setBackgroundColor(Color.parseColor("#FF0000"))
        else secondSpinner.setBackgroundColor(Color.parseColor("#07DA63"))
        if (intent.getStringExtra(THIRD_CORRECT) == "") thirdSpinner.setBackgroundColor(Color.parseColor("#FF0000"))
        else thirdSpinner.setBackgroundColor(Color.parseColor("#07DA63"))
        if (intent.getStringExtra(FOURTH_CORRECT) == "") fourthSpinner.setBackgroundColor(Color.parseColor("#FF0000"))
        else fourthSpinner.setBackgroundColor(Color.parseColor("#07DA63"))
        if (intent.getStringExtra(FIFTH_CORRECT) == "") fifthSpinner.setBackgroundColor(Color.parseColor("#FF0000"))
        else fifthSpinner.setBackgroundColor(Color.parseColor("#07DA63"))
    }
}