package com.example.sharedviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodel.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var testSharedViewModel: TestSharedViewModel
    // Ya da -> private val testSharedViewModel : TestSharedViewModel by activityViewModels() diyerek delegate ederiz ve aşağıda ViewModelProvider(requireActivity()) demeye gerek kalmaz.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // testSharedViewModel = ViewModelProvider(this).get(TestSharedViewModel::class.java) Eğer owner : this olursa bu fragmana özel olur ve kendi instance'ı olur.
        // Ama requireActivity() verirsek bulunduğu activity yani TestActivity'den ortak tek bir instance'ı alır.

        testSharedViewModel = ViewModelProvider(requireActivity()).get(TestSharedViewModel::class.java)

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
                replace(R.id.fragmentContainerView,SecondFragment())
                commit()
            }
        }
    }
}