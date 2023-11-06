package com.example.practice21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secText: TextView = findViewById(R.id.Sec)
        val btnST: Button = findViewById(R.id.button)
        val btnC: Button = findViewById(R.id.button2)
        var start: Boolean = false

        val mainViewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getValue()?.observe(this) {
            value -> secText.text = value
        }
        btnST.setOnClickListener {
            if (!start){
                start = true
            mainViewModel.execute()
            btnST.setText(R.string.btn_stop)
        }
        else {
            start = false
            btnST.setText(R.string.btn_start)
            mainViewModel.stop()
        }
    }
        btnC.setOnClickListener {
            if(start){
                start = false
                btnST.setText(R.string.btn_start)
            }
            mainViewModel.reset()
            secText.setText(R.string.timer)
        }
    }
}