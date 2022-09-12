package com.example.enews.ui.inf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enews.R
import com.example.enews.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private var _binding: FragmentInformationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.collectionText.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_informationFragment_to_collectTextFragment)
        }
        binding.collectionVideo.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_informationFragment_to_collectVideoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}