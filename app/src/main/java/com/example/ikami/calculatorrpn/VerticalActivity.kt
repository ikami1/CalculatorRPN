package com.example.ikami.calculatorrpn

import android.app.Activity
import android.icu.math.BigDecimal
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_vertical.*
import kotlinx.android.synthetic.main.content_vertical.*
import android.content.Intent


class VerticalActivity : AppCompatActivity() {

    val calculator: Calculator = Calculator()
    var newNumber = String()
    val REQUEST_CODE = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical)
        setSupportActionBar(toolbar)

        newView()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun clickHandler(v: View) {
        when( v.id ){

            R.id.buttonEnter -> {
                if (newNumber != "") {
                    calculator.pushOnTop(BigDecimal(newNumber))
                    newNumber = ""
                    newView()
                }
            }
            R.id.buttonPower -> {
                if (calculator.stackSize() >= 2) {
                    calculator.powTop2()
                    newView()
                }
            }
            R.id.buttonSubtract -> {
                if (calculator.stackSize() >= 2) {
                    calculator.subtractTop2()
                    newView()
                }
            }
            R.id.buttonAdd -> {
                if (calculator.stackSize() >= 2) {
                    calculator.addTop2()
                    newView()
                }
            }
            R.id.buttonChangeSign -> {
                if (!calculator.isEmpty()){
                    calculator.changeSign()
                    newView()
                }
            }
            R.id.buttonDivide -> {
                if (calculator.stackSize() >= 2) {
                    calculator.divideTop2()
                    newView()
                }
            }
            R.id.buttonMultiply -> {
                if (calculator.stackSize() >= 2) {
                    calculator.multiplyTop2()
                    newView()
                }
            }
            R.id.buttonDrop -> {
                if (!calculator.isEmpty()){
                    calculator.dropTopNumber()
                    newView()
                }
            }
            R.id.buttonSwap -> {
                if (calculator.stackSize() >= 2) {
                    calculator.swapTop2()
                    newView()
                }
            }
            R.id.buttonAC -> {
                newNumber = ""
                newView()
            }

            else -> {
                val name: String = v.resources.getResourceEntryName(v.id)
                newNumber += name[name.lastIndex].toString()
                newView()
            }
        }
    }

    fun newView(){
        val numbers = calculator.getTop4()

        val text = "4: ${numbers[3]}\n3: ${numbers[2]}\n2: ${numbers[1]}\n1: ${numbers[0]}\n\tnew: $newNumber"
        editText.setText(text)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_vertical, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.getItemId() == R.id.action_settings){
            val i= Intent(this, SettingActivity::class.java)
            i.putExtra("curPrec", calculator.getPrecision())
            startActivityForResult(i, REQUEST_CODE)
            return true
        }else{
            return false
        }
    }

    fun loadOptions(prec: Int){
        calculator.setPrecision(prec)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if((requestCode==REQUEST_CODE)&&(resultCode== Activity.RESULT_OK)){
            if(data!=null){
                if(data.hasExtra("Precyzja")){
                    val nPrec = data.extras.getInt("Precyzja")
                    loadOptions(nPrec)
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}
