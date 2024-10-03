package com.example.cafe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val USERNAME_INTENT = "userName"

/**
 * Активити для создания заказа.
 * Пользователь выбирает тип напитка, отмечает добавки и выбирает подтип напитка.
 */
class MakeOrderActivity : AppCompatActivity() {
    companion object {
        /**
         * Создает новый интент для запуска этой активности.
         *
         * @param context Контекст приложения.
         * @param userName Имя пользователя, которое будет передано в активность.
         * @return Новый интент для запуска активности.
         */
        fun newIntent(context: Context, userName: String): Intent {
            val intent = Intent(context, MakeOrderActivity::class.java)
            intent.putExtra(USERNAME_INTENT, userName)

            return intent
        }
    }

    private lateinit var textViewGreeting: TextView
    private lateinit var textViewExtras: TextView

    private lateinit var radioGroupDrinks: RadioGroup
    private lateinit var radioTeaButton: RadioButton
    private lateinit var radioCoffeeButton: RadioButton

    private lateinit var checkBoxSugar: CheckBox
    private lateinit var checkBoxMilk: CheckBox
    private lateinit var checkBoxLemon: CheckBox

    private lateinit var spinnerTea: Spinner
    private lateinit var spinnerCoffee: Spinner

    private lateinit var buttonMakeOrder: Button

    private lateinit var userName: String
    private lateinit var drinkType: String
    private lateinit var drinkName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)

        initViews()
        setupGreeting()
        setupRadioGroupDrinks()
        setupMakeOrderButton()
    }

    /**
     * Инициализирует все вью данной активити.
     */
    private fun initViews() {
        textViewGreeting = findViewById(R.id.textViewGreeting)
        textViewExtras = findViewById(R.id.textViewDrinkExtras)
        radioGroupDrinks = findViewById(R.id.radioGroup)
        radioTeaButton = findViewById(R.id.radioButtonTea)
        radioCoffeeButton = findViewById(R.id.radioButtonCoffee)
        checkBoxSugar = findViewById(R.id.checkBoxSugar)
        checkBoxMilk = findViewById(R.id.checkBoxMilk)
        checkBoxLemon = findViewById(R.id.checkBoxLemon)
        spinnerTea = findViewById(R.id.spinner_tea)
        spinnerCoffee = findViewById(R.id.spinner_coffee)
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder)
    }

    /**
     * Настраивает вью с приветствием.
     * Получает имя пользователя с предыдущего экрана и отображает его в приветствии.
     */
    private fun setupGreeting() {
        userName = intent.getStringExtra(USERNAME_INTENT) ?: "Guest"
        val greetings = getString(R.string.greetings, userName)
        textViewGreeting.text = greetings
    }

    /**
     * Настраивает радио-группу выбора типа напитка.
     * В зависимости от выбранного типа настраивает дальнейшее представление.
     */
    private fun setupRadioGroupDrinks() {
        radioGroupDrinks.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == radioTeaButton.id) {
                onUserChoseTea()
            } else if (checkedId == radioCoffeeButton.id) {
                onUserChoseCoffee()
            }
        }

        radioTeaButton.isChecked = true //  Устанавливаем чай по умолчанию
    }

    /**
     * Настраивает дальнейшие вью при выборе Чая
     */
    private fun onUserChoseTea() {
        drinkType = getString(R.string.radio_button_tea)
        textViewExtras.text = getString(R.string.extra_drinks, drinkType)
        checkBoxLemon.visibility = View.VISIBLE
        spinnerTea.visibility = View.VISIBLE
        spinnerCoffee.visibility = View.INVISIBLE
    }

    /**
     * Настраивает дальнейшие вью при выборе Кофе
     */
    private fun onUserChoseCoffee() {
        drinkType = getString(R.string.radio_button_coffee)
        textViewExtras.text = getString(R.string.extra_drinks, drinkType)
        checkBoxLemon.visibility = View.INVISIBLE
        spinnerTea.visibility = View.INVISIBLE
        spinnerCoffee.visibility = View.VISIBLE
    }

    /**
     * Настраивает слушатель на кнопку "Сделать заказ"
     */
    private fun setupMakeOrderButton() {
        buttonMakeOrder.setOnClickListener {
            onUserMadeOrder()
        }
    }

    /**
     * Готовит данные для передачи на следующий экран.
     * Собирает информацию о выбранных добавках и подтипе напитка.
     */
    private fun onUserMadeOrder() {
        val extras = arrayListOf<String>()

        if (checkBoxSugar.isChecked) {
            extras.add(checkBoxSugar.text.toString())
        }
        if (checkBoxMilk.isChecked) {
            extras.add(checkBoxMilk.text.toString())
        }
        if (radioTeaButton.isChecked && checkBoxLemon.isChecked) {
            extras.add(checkBoxLemon.text.toString())
        }

        if (radioTeaButton.isChecked) {
            drinkName = spinnerTea.selectedItem.toString()
        } else if (radioCoffeeButton.isChecked) {
            drinkName = spinnerCoffee.selectedItem.toString()
        }

        launchNextScreen(extras)
    }

    /**
     * Запускает следующий экран (OrderDetailActivity).
     *
     * @param extras Список добавок, выбранных пользователем.
     *
     * Юзернейм, Тип напитка, Название, Добавки - эти параметры будут переданы на следующий экран.
     */
    private fun launchNextScreen(extras: ArrayList<String>) {
        val intent = OrderDetailActivity.newIntent(
            this,
            userName,
            drinkType,
            drinkName,
            extras.joinToString(", ")
        )

        startActivity(intent)
    }
}