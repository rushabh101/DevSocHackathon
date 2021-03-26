package com.example.devsochackathon

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_fail_screen.*

class fail_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fail_screen)
        //main menu button should appear after 2 seconds
        main_menu.visibility = View.GONE
        Handler(Looper.getMainLooper()).postDelayed({
            main_menu.visibility = View.VISIBLE
        },1500)
        main_menu.setOnClickListener{
            val intent: Intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
            finish()
        }
    }
}