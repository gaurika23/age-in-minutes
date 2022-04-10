package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var TvSelectedDate: TextView? = null
    private var tvageinminutes:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button =findViewById(R.id.btnDatePicker)

        TvSelectedDate= findViewById(R.id.TvSelectedDate)
        tvageinminutes=findViewById(R.id.tvageinminutes)


        btnDatePicker.setOnClickListener {

            clickDatePicker()
        }
    }
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year= myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val day= myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, year, month,dayOfMonth ->
                Toast.makeText(this,
                "Year was $year,month was ${month+1},day of month $dayOfMonth",Toast.LENGTH_LONG).show()

                val selectedDate = "$dayOfMonth/${month+1}/$year"
                TvSelectedDate?.text=selectedDate
                val sdf= SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                val selectedDateInMinutes=theDate.time / 60000
                val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes=currentDate.time/60000
                val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
                tvageinminutes?.text=differenceInMinutes.toString()

        },

            year,
            month,
            day
    )
        dpd.datePicker.maxDate= System.currentTimeMillis()- 86400000
        dpd.show()

        }

    }
