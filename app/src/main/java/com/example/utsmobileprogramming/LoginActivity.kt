package com.example.utsmobileprogramming

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity()  {

    private lateinit var auth: FirebaseAuth

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        window.statusBarColor = this.resources.getColor(R.color.red)

        auth = FirebaseAuth.getInstance()

        val buttonGoogle = findViewById<SignInButton>(R.id.sign_in_button)

        if (auth.currentUser != null) {
            // User is already signed in, proceed to the main activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        buttonGoogle.setOnClickListener {
            createSignInIntent()
        }

    }

    private fun createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build(),
//            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())


        // Create and launch sign-in intent
        signInLauncher.launch(AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .build())

    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise, check
            // response.getError().getErrorCode() and handle the error.
            // ...
            if (response == null) {
                // User cancelled the sign-in flow
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
                return
            }

            if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                // No network connection
                Toast.makeText(this, "This is error", Toast.LENGTH_SHORT).show()
                return
            }
            Toast.makeText(this, "This is error anonim", Toast.LENGTH_SHORT).show()

        }
    }
}