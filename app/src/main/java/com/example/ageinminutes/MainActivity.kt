package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var vIdate :TextView?= null
    private var minutes :TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonDate :Button = findViewById(R.id.dateBt)

        vIdate = findViewById(R.id.sLdate)
        minutes = findViewById(R.id.timeMin)
        buttonDate.setOnClickListener {
            selectDate()
        }

    }

    private fun selectDate(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, sLyear, sLmonth, sLday->
                val sLdate = "$sLday/${sLmonth+1}/$sLyear"
                vIdate?.text = sLdate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(sLdate)
                theDate?.let {
                    val cuDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    cuDate?.let { val min =( cuDate.time - theDate.time)/60000

                        minutes?.text = min.toString() }

                }

            },
            year,
            month,
            day
        )
        dpDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpDialog.show()

    }

}