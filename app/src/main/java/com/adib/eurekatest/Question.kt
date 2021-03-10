package com.adib.eurekatest

data class Question(
        var qId: Int = 0,
        var question: String = "",
        var qImage: String = "",
        var answerA: String = "",
        var answerB: String = "",
        var answerC: String = "",
        var answerD: String = "",
        var correctAnswer: String = "",
        var isImageQuestion: Int = 0,
        var categoryId: Int = 0
)
