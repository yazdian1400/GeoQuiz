package ir.homework.geoquiz

class Question(val question: String, val answer: Boolean) {
    var userAnswer = UserAnswer.NO_ANSWER
    var hasCheated = false
}