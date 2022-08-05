package com.codewithyash.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KParameter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickOnBtn = findViewById<Button>(R.id.clickOnBtn)


        clickOnBtn.setOnClickListener {
            selectDate()

        }
    }

    fun selectDate() {

        val tvSelectedDate: TextView? = findViewById(R.id.tvSelectedDate)
        val tvAge: TextView? = findViewById(R.id.tvAge)


        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, selectedyear, selectedmonth, selecteddayOfMonth ->
                Toast.makeText(
                    this,
                    "You have selected $selectedyear year,${selectedmonth + 1} month,$selecteddayOfMonth day",
                    Toast.LENGTH_SHORT
                ).show()


                val selectedDate = "$selecteddayOfMonth/${selectedmonth + 1}/$selectedyear"

                tvSelectedDate?.text = selectedDate

                var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selctedDateInMilliseconds = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    val currentDateInMillisecond = currentDate.time / 60000

                    val difference = currentDateInMillisecond - selctedDateInMilliseconds

                    tvAge?.text = difference.toString()

                }


            }, year, month, day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}