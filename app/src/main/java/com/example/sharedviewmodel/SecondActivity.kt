package com.example.sharedviewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodel.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        // Bu şekilde güncel değer gelmez -> number = 0 olarak gelir. Çünkü her activity kendi bağımsız viewmodel instance'ını yaratır!!
         */

        sharedViewModel = ViewModelProvider(this,SharedViewModelFactory(application)).get(SharedViewModel::class.java)
        binding.tvNumber.text = sharedViewModel.number.toString()
        binding.main.setBackgroundColor(ContextCompat.getColor(this, sharedViewModel.backgroundColor))


        // Timer
        sharedViewModel.seconds.observe(this, Observer {
            binding.tvCount.text = it.toString()
        })
        sharedViewModel.isCountFinished.observe(this, Observer {
            Toast.makeText(this,"Count finished", Toast.LENGTH_SHORT).show()
        })
    }
}