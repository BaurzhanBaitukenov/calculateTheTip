package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip();
        }
    }


    private fun calculateTip() {
        val stringInTextField = binding.editTextID.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.amazingID -> 0.20
            R.id.goodID -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if(binding.roundUpSwitchID.isChecked)
            tip = kotlin.math.ceil(tip);

        displayTip(tip)
    }

    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

}