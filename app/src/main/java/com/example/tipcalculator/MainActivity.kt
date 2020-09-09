package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        num0.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("0")
            }
        }
        num1.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("1")
            }
        }
        num2.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("2")
            }
        }
        num3.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("3")
            }
        }
        num4.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("4")
            }
        }
        num5.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("5")
            }
        }
        num6.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("6")
            }
        }
        num7.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("7")
            }
        }
        num8.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("8")
            }
        }
        num9.setOnClickListener {
            if (bill.hasFocus() || person.hasFocus()) {
                addText("9")
            }
        }
        dec.setOnClickListener {
            if (bill.hasFocus()) {
                if(bill.text.toString().contains(".")){

                        bill_amount.error = "Only one decimal allowed"
                        addText("")

                }else {
                    if (bill.text.toString() != "")
                        addText(".")
                    else {
                        addText("0.")
                    }
                }
            }
        }
        done.setOnClickListener {
            if(bill.text!!.isEmpty()){
                bill_amount.error="Required"
            }else{
                calculate()
            }
        }
        seekBar.progress = 15
        person.setText("1")
        bill.showSoftInputOnFocus = false
        person.showSoftInputOnFocus = false


        bill.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {


                if (bill.text!!.isNotEmpty()) {

                    calculate()
                }
                else{
                    total_text.text = null
                    tip_text.text = null
                    share_text.text = null
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                bill_amount.error = ""
                if (bill.text!!.isNotEmpty()) {
                    calculate()
                }


            }

        })

        person.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if (person.text!!.isEmpty()) {
                    num0.isEnabled = false

                    share_text.text = null
                } else {
                    num0.isEnabled=true
                    if (person.text!!.isNotEmpty() && bill.text!!.isNotEmpty()) {
                        var per = person.text.toString().toDouble()
                        if (per > 0) {
                            no_of_person.error = ""
                            calculate()
                        } else {

                            total_text.text = null
                            tip_text.text = null
                            share_text.text = null
                        }
                    }

                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var per = person.text.toString()
                if (person.text!!.isNotEmpty() && bill.text!!.isNotEmpty()) {

                    if (per.toDouble() > 0 && per.contains(".") == false) {
                        num0.isEnabled=true
                        calculate()
                    } else {
                        num0.isEnabled=false
                        total_text.text = null
                        tip_text.text = null
                        share_text.text = null
                    }
                }


            }

        })




        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var pos: String? = null
                pos = seekBar?.progress.toString()
                number.setText(pos)
                if (person.text?.isNotEmpty()!! && bill.text?.isNotEmpty()!!) {
                    var per = person.text.toString().toDouble()
                    if (per > 0) {
                        calculate()
                    }
                }


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })



        reset.setOnClickListener {
            bill.text = null
            person.setText("1")
            var len = person.length()
            person.setSelection(len)
            seekBar.progress = 15
            total_text.text = null
            tip_text.text = null
            share_text.text = null
        }
        clear.setOnClickListener {

            if (bill.hasFocus()) {
                var edit = bill.text
                val size = edit?.length
                if (size != null) {
                    if (size > 0) {
                        edit?.delete(size - 1, size)
                        bill.setSelection(size - 1)
                    } else {
                        var len = bill.length()
                        person.setSelection(len)
                    }
                }
            } else {
                var edit = person.text
                val size = edit?.length
                if (size != null) {
                    if (size > 0) {
                        edit?.delete(size - 1, size)
                        person.setSelection(size - 1)
                    } else {

                        var len = person.length()
                        person.setSelection(len)
                    }
                }
            }
        }
    }

    fun calculate() {
        var pers = person.length()
        if (pers == 0 || pers == null) {
            person.setText("1")
        }
        var bill_amt1 = bill.text.toString().toDouble()
        var tip = seekBar.progress.toString().toDouble()
        var persons = person.text.toString().toDouble()
        var tipamt = bill_amt1 * tip / 100

        var total_bill: Double = bill_amt1 + tipamt
        var share_per: Double = total_bill / persons

        tip_text.text = "$  "+"%.2f".format(tipamt)

        total_text.text = "$  "+ "%.2f".format(total_bill)
        share_text.text = "$  "+"%.2f".format(share_per)


    }

    fun addText(num: String) {
        if (bill.hasFocus()) {
            bill.append(num)
        } else {
            person.append(num)
        }
    }
}
