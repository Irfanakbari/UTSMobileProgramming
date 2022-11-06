package com.example.utsmobileprogramming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Toast
import com.example.utsmobileprogramming.fragments.CallItEvenFragment
import com.example.utsmobileprogramming.fragments.DivisorFragment
import com.example.utsmobileprogramming.fragments.OperationMathFragment

class GameActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

//        Hide Status Bar
        supportActionBar?.hide()
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.red)

//        Get Intent
        val gameType = intent.getStringExtra("game")
        when (gameType) {
            "divisor" -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.gameContainer, DivisorFragment())
                    commit()
                }
            }
            "operationMath" -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.gameContainer, OperationMathFragment())
                    commit()
                }
            }
            "callItEven" -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.gameContainer, CallItEvenFragment())
                    commit()
                }
            }
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