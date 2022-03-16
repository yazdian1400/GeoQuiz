package ir.homework.geoquiz

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.homework.geoquiz.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    lateinit var binding: FragmentQuizBinding
    val questionList = mutableListOf<Question>()
    var num = 0
    var hasCheated = false

    companion object{
        const val NUM_OF_QUESTIONS = 10
        const val NUM = "num"
        const val CHEATING_ARRAY = "cheatingArray"
        const val USER_ANSWER_ARRAY = "UserAnswerArray"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getBoolean("hasCheated") != null) {
            hasCheated = arguments?.getBoolean("hasCheated") as Boolean
            if (hasCheated) questionList[num].hasCheated = true
        }
        setData()
        initViews()
        onClickListeners()
    }

    override fun onResume() {
        super.onResume()
        val activity = activity as MainActivity?
        activity?.hideUpButton()
    }

    private fun setData(){
        questionList.add(Question(getString(R.string.question1),(getString(R.string.answer1)).toBoolean()))
        questionList.add(Question(getString(R.string.question2),getString(R.string.answer2).toBoolean()))
        questionList.add(Question(getString(R.string.question3),getString(R.string.answer3).toBoolean()))
        questionList.add(Question(getString(R.string.question4),getString(R.string.answer4).toBoolean()))
        questionList.add(Question(getString(R.string.question5),getString(R.string.answer5).toBoolean()))
        questionList.add(Question(getString(R.string.question6),getString(R.string.answer6).toBoolean()))
        questionList.add(Question(getString(R.string.question7),getString(R.string.answer7).toBoolean()))
        questionList.add(Question(getString(R.string.question8),getString(R.string.answer8).toBoolean()))
        questionList.add(Question(getString(R.string.question9),getString(R.string.answer9).toBoolean()))
        questionList.add(Question(getString(R.string.question10),getString(R.string.answer10).toBoolean()))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun onClickListeners() {
        binding.btnTrue.setOnClickListener {
            questionList[num].userAnswer = UserAnswer.TRUE
            binding.btnTrue.isEnabled = false
            binding.btnTrue.setBackgroundColor(getColor(requireContext(),R.color.pink_dark_A100))
            binding.btnFalse.isEnabled = false
            binding.btnFalse.setBackgroundColor(getColor(requireContext(),R.color.blue_dark_A100))
            binding.btnCheat.isEnabled = false
            binding.btnCheat.setBackgroundColor(getColor(requireContext(),R.color.red_dark_A100))
            messageAnswer(questionList[num].answer)
        }
        binding.btnFalse.setOnClickListener {
            questionList[num].userAnswer = UserAnswer.FALSE
            binding.btnTrue.isEnabled = false
            binding.btnTrue.setBackgroundColor(getColor(requireContext(),R.color.pink_dark_A100))
            binding.btnFalse.isEnabled = false
            binding.btnFalse.setBackgroundColor(getColor(requireContext(),R.color.blue_dark_A100))
            binding.btnCheat.isEnabled = false
            binding.btnCheat.setBackgroundColor(getColor(requireContext(),R.color.red_dark_A100))
            messageAnswer(!questionList[num].answer)
        }
        binding.btnNext.setOnClickListener {
            num++
            changePage()
        }
        binding.btnPrev.setOnClickListener {
            num--
            changePage()
        }
        binding.btnCheat.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("answer",questionList[num].answer)
            findNavController().navigate(R.id.cheating_action,bundle)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initViews(){
        changePage()
    }

    fun messageAnswer(boolean: Boolean){
        if (boolean){
            Toast.makeText(requireContext(), "Correct", Toast.LENGTH_LONG).show()
            if (questionList[num].hasCheated == true) Toast.makeText(requireContext(), "Cheating is Wrong.",
                Toast.LENGTH_LONG).show()
        }
        else    Toast.makeText(requireContext(), "Incorrect", Toast.LENGTH_LONG).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun changePage() {
        binding.tvQuestion.text = questionList[num].question
        binding.btnPrev.isEnabled = num != 0
        binding.btnNext.isEnabled = num != (NUM_OF_QUESTIONS - 1)
        binding.btnFalse.isEnabled = questionList[num].userAnswer == UserAnswer.NO_ANSWER
        binding.btnTrue.isEnabled = questionList[num].userAnswer == UserAnswer.NO_ANSWER
        binding.btnCheat.isEnabled = questionList[num].userAnswer == UserAnswer.NO_ANSWER
        if (num != 0)   binding.btnPrev.setBackgroundColor(getColor(requireContext(),R.color.green_A100))
        else    binding.btnPrev.setBackgroundColor(getColor(requireContext(),R.color.green_dark_A100))
        if (num != NUM_OF_QUESTIONS - 1)  binding.btnNext.setBackgroundColor(getColor(requireContext(),R.color.green_A100))
        else    binding.btnNext.setBackgroundColor(getColor(requireContext(),R.color.green_dark_A100))
        if (questionList[num].userAnswer == UserAnswer.NO_ANSWER) {
            binding.btnFalse.setBackgroundColor(getColor(requireContext(),R.color.blue_A100))
            binding.btnTrue.setBackgroundColor(getColor(requireContext(),R.color.pink_A100))
            binding.btnCheat.setBackgroundColor(getColor(requireContext(),R.color.red_A100))
        }
        else {
            binding.btnFalse.setBackgroundColor(getColor(requireContext(),R.color.blue_dark_A100))
            binding.btnTrue.setBackgroundColor(getColor(requireContext(),R.color.pink_dark_A100))
            binding.btnCheat.setBackgroundColor(getColor(requireContext(),R.color.red_dark_A100))
        }
        if ((questionList[num].userAnswer == UserAnswer.TRUE && questionList[num].answer
                    || questionList[num].userAnswer == UserAnswer.FALSE && !questionList[num].answer) && questionList[num].hasCheated)
            Toast.makeText(requireContext(), "Cheating is wrong.", Toast.LENGTH_LONG).show()
    }
}