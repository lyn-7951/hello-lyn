package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化binding对象
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener { view, i, keyEvent -> handleKeyEvent(view,i) }
    }

     private fun calculateTip() {
         val stringInTextField = binding.costOfServiceEditText.text.toString()
         val cost = stringInTextField.toDoubleOrNull()//服务费
         if(cost==null){
             displayTip(0.0)
             return
         }


         val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {//小费百分比
             R.id.option_twenty_percent -> 0.20
             R.id.option_eighteen_percent -> 0.18
             else -> 0.15
         }
         var tip = cost * tipPercentage//小费
         if(binding.roundUpSwitch.isChecked){
             tip= ceil(tip)
         }

         displayTip(tip)

     }
    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // inputMethodManager:显示/隐藏键盘，显示哪一个软键盘
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}