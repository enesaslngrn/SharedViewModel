package com.example.sharedviewmodel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.sharedviewmodel.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,FirstFragment())
            commit()
        }
        binding.fabBottomSheet.setOnClickListener {
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(supportFragmentManager,BottomSheetFragment.TAG)
        }

    }
}