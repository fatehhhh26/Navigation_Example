package com.example.navigationexample

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.navigationexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val launcherToThirdActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == RESULT_OK) {

            val data = result.data

            val nama = data?.getStringExtra("fatih")

            binding.txtHello.text = "update to second activity ${nama}"
        }
    }

    //bikin parameter binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //assign binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        //agar UI muncul
        setContentView(binding.root)


        //handel UI
        with(binding) {
            txtHello.text = "Selamat datang!!"

            btnNext.setOnClickListener {
                var intentToSecondActivity = Intent(this@MainActivity, SecondActivity::class.java)
                intentToSecondActivity.putExtra("name", "Fatih")
                startActivity(intentToSecondActivity)
            }
            btnToThird.setOnClickListener {
                val intentToThirdActivity = Intent(this@MainActivity, ThirdActivity::class.java)
                launcherToThirdActivity.launch(intentToThirdActivity)
            }
        }
    }
}