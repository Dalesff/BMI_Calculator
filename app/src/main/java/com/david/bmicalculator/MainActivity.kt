package com.david.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentHeight = 120
    private var currentWeight = 50
    private var currentAge = 25

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnCalculate: Button

    companion object{
        const val IMC_KEY = "IMC Result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initListeners()
        initUI()
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.male)
        viewFemale = findViewById(R.id.female)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        tvAge = findViewById(R.id.tvAge)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        rsHeight.addOnChangeListener{_, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight" + " cm"
        }

        btnAddWeight.setOnClickListener{
            currentWeight += 1
            setWeight()
        }

        btnSubtractWeight.setOnClickListener{
            currentWeight -= 1
            setWeight()
        }

        btnAddAge.setOnClickListener{
            currentAge += 1
            setAge()
        }

        btnSubtractAge.setOnClickListener{
            currentAge -= 1
            setAge()
        }

        btnCalculate.setOnClickListener{
            //Log.i("David" , calculte(currentWeight, currentHeight).toString())
            resultNavigate(calculte(currentWeight, currentHeight))
        }
    }

    private fun resultNavigate(result: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculte(weight: Int, height: Int): Double {
        val df = DecimalFormat("#.##")
        val bmi = weight / (height.toDouble()/100 * height.toDouble()/100)
        return df.format(bmi).toDouble()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getMaleBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getFemaleBackgroundColor(isFemaleSelected))
    }

    private fun getMaleBackgroundColor(isSelectedComponent: Boolean): Int {
        val color = if (isSelectedComponent) {
            R.color.blue01
        } else {
            R.color.unselected
        }
        return ContextCompat.getColor(this, color)
    }

    private fun getFemaleBackgroundColor(isSelectedComponent: Boolean): Int {
        val color = if (isSelectedComponent) {
            R.color.red01
        } else {
            R.color.unselected
        }
        return ContextCompat.getColor(this, color)
    }
}