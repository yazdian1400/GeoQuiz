package ir.homework.geoquiz

object GeoQuiz {
    val questionList = mutableListOf<Question>()
    var num = 0

    fun setData() {
        questionList.add(Question("Birjand is the capital of North Khorasan.", false))
        questionList.add(Question("Tehran is the capital of Iran.", true))
        questionList.add(Question("There is no sea in Yazd.", true))
        questionList.add(Question("Himalaya is in Iran.", false))
        questionList.add(Question("Persian Gulf is in Iran.", true))
        questionList.add(Question("Tehran and Qom are neighbours.", true))
        questionList.add(Question("There is enough water for many years.", false))
        questionList.add(Question("Shiraz is the capital of Fars.", true))
        questionList.add(Question("Ahvaz and Esfahan are neighbours.", false))
        questionList.add(Question("Shomal is the capital of Iran.", false))
    }
}