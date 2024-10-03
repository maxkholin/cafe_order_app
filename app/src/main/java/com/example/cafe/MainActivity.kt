package com.example.cafe

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Основное активити приложения, которое отображает экран входа.
 * Пользователь вводит имя и пароль.
 * Если поля не пусты, переходит на следующий экран.
 * Если пусты - выходит предупреждение
 */
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

    /**
     * Инициализирует все вью данной активити.
     */
    private fun initViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSignIn = findViewById(R.id.buttonSignIn)
    }

    /**
    * Настраивает слушатель на кнопку "Войти".
    * При нажатии проверяет поля ввода и переходит на следующий экран, если данные не пустые.
    */
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

    /**
     * Показывает сообщение об ошибке, если имя пользователя или пароль не введены.
     */
    private fun showErrorMessage() {
        Toast.makeText(
            this,
            R.string.error_message,
            Toast.LENGTH_LONG
        ).show()
    }

    /**
     * Запускает следующий экран (MakeOrderActivity).
     *
     * @param userName Имя пользователя, которое будет передано на следующий экран.
     */
    private fun launchNextScreen(userName: String) {
        val intent = MakeOrderActivity.newIntent(this, userName)

        startActivity(intent)
    }
}