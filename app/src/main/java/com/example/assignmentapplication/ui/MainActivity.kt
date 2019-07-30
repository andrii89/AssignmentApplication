package com.example.assignmentapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmentapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentById(R.id.main_activity) == null)
            supportFragmentManager.beginTransaction().replace(R.id.main_activity,
                MainFragment()
            ).commit()
    }
}
