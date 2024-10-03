package com.example.cafe

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignIn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupSignInButton()
    }


    private fun initViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSignIn = findViewById(R.id.buttonSignIn)
    }


    private fun setupSignInButton() {
        buttonSignIn.setOnClickListener {
            val userName = editTextName.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (userName.isBlank() || password.isBlank()) {
                showErrorMessage()
            } else {
                launchNextScreen(userName)
            }
        }
    }


    private fun showErrorMessage() {
        Toast.makeText(
            this,
            R.string.error_message,
            Toast.LENGTH_LONG
        ).show()
    }


    private fun launchNextScreen(userName: String) {
        val intent = MakeOrderActivity.newIntent(this, userName)

        startActivity(intent)
    }
}