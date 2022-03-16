package ir.homework.geoquiz

import androidx.lifecycle.ViewModel

class GeoQuizViewModel: ViewModel() {
    val questionList = mutableListOf<Question>()
    var num = 0

    init {
        GeoQuiz.setData()
    }
}