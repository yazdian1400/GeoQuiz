package ir.homework.geoquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.homework.geoquiz.databinding.FragmentCheatBinding

class CheatFragment : Fragment() {
    lateinit var binding: FragmentCheatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}