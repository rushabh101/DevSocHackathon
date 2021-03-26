package com.example.devsochackathon

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var pipes = PipeLand()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var but = findViewById<Button>(R.id.button)
        but.setOnClickListener{
            val intent: Intent = Intent(this, MazeActivity::class.java)
            startActivity(intent)
        }

    }

//    fun setMaze(view: View) {
//        val intent = Intent(this, MazeActivity::class.java)
//        startActivity(intent)
//    }
}