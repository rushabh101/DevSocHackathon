package com.example.devsochackathon

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.devsochackathon.R
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val b1:Boolean=intent.getBooleanExtra("statuseasy",false)
        val b2:Boolean=intent.getBooleanExtra("statusmed",false)
        val b3:Boolean=intent.getBooleanExtra("statushard",false)
        reward.visibility=View.GONE
        var c = 0
        power.setBackgroundResource(R.drawable.powerbuttonoff)
        Toast.makeText(this, "Power Off", Toast.LENGTH_SHORT).show()
        textView.visibility = View.GONE
        textView2.visibility = View.GONE
        layout1.visibility = View.GONE
        layout.setBackgroundResource(R.color.black)
        power.setOnClickListener {
            if (c % 2 == 0) {
                power.setBackgroundResource(R.drawable.powerbutton)
                Toast.makeText(this, "Power On", Toast.LENGTH_SHORT).show()
                textView.visibility = View.VISIBLE
                textView2.visibility = View.VISIBLE
                layout1.visibility = View.VISIBLE
                layout.setBackgroundResource(R.drawable.bg_black)
                c++
            } else {
                power.setBackgroundResource(R.drawable.powerbuttonoff)
                Toast.makeText(this, "Power Off", Toast.LENGTH_SHORT).show()
                textView.visibility = View.GONE
                textView2.visibility = View.GONE
                layout1.visibility = View.GONE
                layout.setBackgroundResource(R.color.black)
                c++
            }
            terminal1.setOnClickListener{
                val intent:Intent=Intent(this@MainMenu,Easy::class.java)
                startActivity(intent)
                finish()
            }
            terminal2.setOnClickListener{
                val intent:Intent=Intent(this@MainMenu,Medium::class.java)
                startActivity(intent)
                finish()
            }
            terminal3.setOnClickListener{
                val intent:Intent=Intent(this@MainMenu,Hard::class.java)
                startActivity(intent)
                finish()
            }
        }
        if (b1&&b2&&b3){
            reward.visibility=View.VISIBLE
            reward.setOnClickListener{
                val intentt:Intent= Intent(this,Reward::class.java)
                startActivity(intentt)
                finish()
            }
        }
    }
}