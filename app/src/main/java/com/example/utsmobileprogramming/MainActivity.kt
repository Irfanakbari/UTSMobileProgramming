package com.example.utsmobileprogramming

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.utsmobileprogramming.utility.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random


class MainActivity : BaseActivity() {
    private var auth = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inisialisasi tombol
        val divisorButton = findViewById<Button>(R.id.divisor)
        val operationMathButton = findViewById<Button>(R.id.operationMath)
        val callItEvenButton = findViewById<Button>(R.id.callItEven)
        val credit = findViewById<Button>(R.id.credit)

        credit.text = auth?.displayName.toString()


        // Button Click Listener
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
//            FirebaseAuth.getInstance().signOut()
//            recreate()
            val r = Random.nextInt(1,100)
            val skorDB = FirebaseService()
            skorDB.saveSkor(r,"Divisor")

            // To show the dialog fragment
//            val fragment = UsernameModal()
//            fragment.show(supportFragmentManager, "username_dial")
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
}