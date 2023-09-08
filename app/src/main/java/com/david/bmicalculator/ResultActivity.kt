package com.david.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.david.bmicalculator.MainActivity.Companion.IMC_KEY

class ResultActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvBMI: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponent()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        tvBMI.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.low_weight_title)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.yellow_low))
                tvDescription.text = getString(R.string.low_weight_description)
            }
            in 18.51..24.99 -> {
                tvResult.text = getString(R.string.normal_weight_title)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.green_normal))
                tvDescription.text = getString(R.string.normal_weight_description)
            }
            in 25.00..29.99 -> {
                tvResult.text = getString(R.string.high_weight_title)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.orange_high))
                tvDescription.text = getString(R.string.high_weight_description)
            }
            in 30.00..34.99 -> {
                tvResult.text = getString(R.string.obesity1_weight_title)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.red_obesity))
                tvDescription.text = getString(R.string.obesity1_weight_description)
            }
            in 35.00..39.99 -> {
                tvResult.text = getString(R.string.obesity2_weight_title)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.red_obesity))
                tvDescription.text = getString(R.string.obesity2_weight_description)
            }
            in 40.00..100.00 -> {
                tvResult.text = getString(R.string.obesity3_weight_title)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.red_obesity))
                tvDescription.text = getString(R.string.obesity3_weight_description)
            }
            else -> {
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.red_obesity))
                tvBMI.text = getString(R.string.error)
                tvBMI.setTextColor(ContextCompat.getColor(this, R.color.red_obesity))
                tvDescription.text = getString(R.string.error)
                tvDescription.setTextColor(ContextCompat.getColor(this, R.color.red_obesity))
            }
        }
    }

    private fun initComponent() {
        tvResult = findViewById(R.id.tvResult)
        tvBMI = findViewById(R.id.tvBMI)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}