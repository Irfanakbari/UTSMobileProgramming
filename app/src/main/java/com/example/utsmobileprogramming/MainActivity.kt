package com.example.utsmobileprogramming

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Hide Status Bar
        supportActionBar?.hide()
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.red)
        val divisorButton = findViewById<Button>(R.id.divisor)
        val operationMathButton = findViewById<Button>(R.id.operationMath)
        val callItEvenButton = findViewById<Button>(R.id.callItEven)

//        Button Click Listener
        val intent = Intent(this, GameActivity::class.java)
        divisorButton.setOnClickListener {
            intent.putExtra("game", "divisor")
            startActivity(intent)
        }
        operationMathButton.setOnClickListener {
            intent.putExtra("game", "operationMath")
            startActivity(intent)
        }
        callItEvenButton.setOnClickListener {
            intent.putExtra("game", "callItEven")
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, R.string.back_menu_alert, Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}