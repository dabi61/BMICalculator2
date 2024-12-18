package com.example.bmicalculator2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    { result: ActivityResult ->
        // Xử lý kết quả trả về từ SecondActivity
        if(result.resultCode == Activity.RESULT_OK)
        {
            val resultFinal = result.data?.getStringExtra("result_data")
            binding.edResult.setText(resultFinal)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btResult.setOnClickListener {
            val height = binding.etHeight.text.toString()
            val weight = binding.etWidth.getText().toString()
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            resultLauncher.launch(intent)
        }
    }
}