package com.adib.eurekatest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FirstQuestion(val item: Question): Fragment() {
    private lateinit var tvQuestionText: TextView
    private lateinit var ivQuestionImage: ImageView
    private lateinit var rgQuestion: RadioGroup
    private lateinit var rbAnswerA: RadioButton
    private lateinit var rbAnswerB: RadioButton
    private lateinit var rbAnswerC: RadioButton
    private lateinit var rbAnswerD: RadioButton
    private var selectedAnswer: String = ""
    lateinit var com: Communicator
    private var isChecked = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvQuestionText = view.findViewById(R.id.tv_question_text)
        tvQuestionText.text = item.question
        ivQuestionImage = view.findViewById(R.id.iv_question_image)
        if (item.isImageQuestion == 1) {
            ivQuestionImage.visibility = View.VISIBLE
            Glide.with(this)
                    .load(item.qImage)
                    .apply(RequestOptions().override(600, 600))
                    .into(ivQuestionImage)
        }
        val optionA = "A. ${item.answerA}"
        val optionB = "B. ${item.answerB}"
        val optionC = "C. ${item.answerC}"
        val optionD = "D. ${item.answerD}"
        rbAnswerA = view.findViewById(R.id.rb_answer_a)
        rbAnswerA.text = optionA
        rbAnswerB = view.findViewById(R.id.rb_answer_b)
        rbAnswerB.text = optionB
        rbAnswerC = view.findViewById(R.id.rb_answer_c)
        rbAnswerC.text = optionC
        rbAnswerD = view.findViewById(R.id.rb_answer_d)
        rbAnswerD.text = optionD

        rgQuestion = view.findViewById(R.id.rg_question)
        rgQuestion.setOnCheckedChangeListener{ _, checkedId ->
            isChecked = 1
            when(checkedId) {
                R.id.rb_answer_a ->
                    selectedAnswer = item.answerA
                R.id.rb_answer_b ->
                    selectedAnswer = item.answerB
                R.id.rb_answer_c ->
                    selectedAnswer = item.answerC
                R.id.rb_answer_d ->
                    selectedAnswer = item.answerD
            }
            showResult(selectedAnswer)
        }
        if (isChecked == 0) com.checkOne("")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        com = context as Communicator
    }

    private fun showResult(answer: String) {
            if (answer == item.correctAnswer) com.checkOne(answer)
            else com.checkOne(answer)
    }
}