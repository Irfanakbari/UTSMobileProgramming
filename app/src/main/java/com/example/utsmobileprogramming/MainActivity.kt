package com.example.utsmobileprogramming

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private var auth = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Hide Status Bar
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        window.statusBarColor = this.resources.getColor(R.color.red)


        val divisorButton = findViewById<Button>(R.id.divisor)
        val operationMathButton = findViewById<Button>(R.id.operationMath)
        val callItEvenButton = findViewById<Button>(R.id.callItEven)
        val credit = findViewById<Button>(R.id.credit)

        credit.text = auth?.displayName.toString()


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
        credit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            recreate()
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth == null){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
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