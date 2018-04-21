package com.example.ikami.calculatorrpn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        val extras = intent.extras ?: return
        precisionEdit.setText(extras.getInt("curPrec").toString())

    }

    fun finish(v: View) {
        val data = Intent()

        val nP = precisionEdit.text.toString().toInt()
        data.putExtra("Precyzja", nP)
        setResult(Activity.RESULT_OK, data)

        super.finish()
    }

}