package com.example.lec5

import android.Manifest.permission_group.CALENDAR
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.text.DateFormat
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    val fmtDateAndTime = DateFormat.getTimeInstance();
    val calendar = Calendar.getInstance();
    val lblDateAndTime : TextView? = null;

    private fun updateLabel() {
        lblDateAndTime?.text = fmtDateAndTime.format(calendar.time)
    }

    val date = DatePickerDialog.OnDateSetListener{day,year,monthOfYear,dayOfMonth
    -> calendar.set(Calendar.YEAR,year)
        calendar.set(Calendar.MONTH,monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        updateLabel()
    }

    val time = TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
        calendar.set(Calendar.HOUR,i)
        calendar.set(Calendar.MINUTE,i2)
    }

    






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}