package com.example.devsochackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_fail_screen.*
import kotlinx.android.synthetic.main.activity_unlocked_screen.*
import kotlinx.android.synthetic.main.activity_unlocked_screen.main_menu

class unlocked_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_unlocked_screen)
        main_menu.visibility = View.GONE
        Handler(Looper.getMainLooper()).postDelayed({
            main_menu.visibility = View.VISIBLE
        },1500)
        //Animate the 3 dots at the end of the text
        main_menu.setOnClickListener{
            val intent: Intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
            finish()
        }
    }
}