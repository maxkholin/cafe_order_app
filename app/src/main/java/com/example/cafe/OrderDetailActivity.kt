package com.example.cafe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Константы для ключей интента
private const val USER_NAME_INTENT = "userName"
private const val DRINK_TYPE_INTENT = "drinkType"
private const val DRINK_NAME_INTENT = "drinkName"
private const val EXTRAS_INTENT = "extras"

/**
 * Активити для отображения деталей заказа.
 * Получает данные из интента с предыдущего экрана.
 */
class OrderDetailActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewDrinkType: TextView
    private lateinit var textViewNameName: TextView
    private lateinit var textViewExtras: TextView

    companion object {
        /**
         * Создает новый интент для запуска этой активити.
         *
         * @param context Контекст приложения.
         * @param userName Имя пользователя.
         * @param drinkType Тип напитка.
         * @param drinkName Название напитка.
         * @param extras Добавки к напитку.
         * @return Новый интент для запуска активити.
         */
        fun newIntent(
            context: Context,
            userName: String,
            drinkType: String,
            drinkName: String,
            extras: String
        ): Intent {
            return Intent(context, OrderDetailActivity::class.java).apply {
                putExtra(USER_NAME_INTENT, userName)
                putExtra(DRINK_TYPE_INTENT, drinkType)
                putExtra(DRINK_NAME_INTENT, drinkName)
                putExtra(EXTRAS_INTENT, extras)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        initViews()
        setupViews()
    }

    /**
     * Инициализирует все вью данной активити.
     */
    private fun initViews() {
        textViewName = findViewById(R.id.Name)
        textViewDrinkType = findViewById(R.id.Drink)
        textViewNameName = findViewById(R.id.DrinkType)
        textViewExtras = findViewById(R.id.Extras)
    }

    /**
     * Настраивает содержимое вью на основе данных из интента.
     */
    private fun setupViews() {
        textViewName.text = intent.getStringExtra(USER_NAME_INTENT)
        textViewDrinkType.text = intent.getStringExtra(DRINK_TYPE_INTENT)
        textViewNameName.text = intent.getStringExtra(DRINK_NAME_INTENT)
        textViewExtras.text = intent.getStringExtra(EXTRAS_INTENT)
    }
}