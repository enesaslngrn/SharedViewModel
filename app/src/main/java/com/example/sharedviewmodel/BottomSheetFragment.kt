package com.example.sharedviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.sharedviewmodel.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetBinding
    private val testSharedViewModel: TestSharedViewModel by activityViewModels()
    companion object{
        val TAG = "BottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testSharedViewModel.language.observe(viewLifecycleOwner, Observer {
            binding.etLanguage.setText(it)
        })

        // Timer
        if (!testSharedViewModel.isCountRunning){
            testSharedViewModel.startTimer()
        }
        testSharedViewModel.seconds.observe(viewLifecycleOwner, Observer {
            binding.tvCount.text = it.toString()
        })

        binding.btnSave.setOnClickListener {
            testSharedViewModel.passLanguageData(binding.etLanguage.text.toString())
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView,FirstFragment())
                commit()
            }
            dismiss()
        }
    }
}