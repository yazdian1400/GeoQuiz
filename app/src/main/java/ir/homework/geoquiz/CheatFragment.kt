package ir.homework.geoquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import ir.homework.geoquiz.databinding.FragmentCheatBinding

class CheatFragment : Fragment() {
    lateinit var binding: FragmentCheatBinding
    var hasCheated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val answer = arguments?.getBoolean("answer")

        binding.btnShowAnswer.setOnClickListener{
            binding.tvCheatAnswer.text = answer.toString()
            binding.btnShowAnswer.setBackgroundColor(getColor(requireContext(),R.color.green_dark_A100))
            hasCheated = true
        }
    }

    override fun onResume() {
        super.onResume()
        val activity = activity as MainActivity?
        activity?.showUpButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                (activity as MainActivity?)?.onBackPressed()
//                val bundle = Bundle()
//                bundle.putBoolean("hasCheated",hasCheated)
//                findNavController().navigate(R.id.action_return_to_quiz)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}