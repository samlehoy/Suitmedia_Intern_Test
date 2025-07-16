package com.suitmedia.interntest.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.suitmedia.interntest.R
import com.suitmedia.interntest.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // untuk tombol back
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvWelcome.text = "Welcome"
        binding.tvName.text = args.name

        binding.btnChooseUser.setOnClickListener {
            findNavController().navigate(R.id.thirdFragment)
        }

        // default selected user
        setFragmentResultListener("selectedUser") { _, bundle ->
            val selectedName = bundle.getString("userName")
            binding.tvSelectedUser.text = selectedName
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
