package com.suitmedia.interntest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.suitmedia.interntest.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private fun isPalindrome(input: String): Boolean {
        val normalized = input.replace("\\s".toRegex(), "").lowercase()
        return normalized == normalized.reversed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCheck.setOnClickListener {
            val text = binding.etPalindrome.text.toString()
            if (text.isBlank()) {
                Toast.makeText(requireContext(), "palindrome is empty", Toast.LENGTH_SHORT).show()
            } else {
                if (isPalindrome(text)) {
                    Toast.makeText(requireContext(), "is palindrome", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "not palindrome", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isNotBlank()) {
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(name)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}