package com.example.devsochackathon

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MazeActivity : AppCompatActivity() {
    var pipes = PipeLand()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maze)

        // Sets all the pipe images at the start
        for (i in 0..7) {
            for (j in 0..5) {
                setPipe(i, j)
            }
        }
    }

    // Called when a pipe is clicked
    fun pipeClick(view: View) {

        val iview = findViewById<ImageView>(view.id)
        iview.setColorFilter(Color.argb(255, 85, 188, 223))

        val pipeId = pipes.swap(resources.getResourceEntryName(view.id))
        if (pipeId == 0) {
            swapPipes()
        }
        else if(pipeId == 2) {
            errorPipes()
        }
        if (pipes.checkForPath()) finishDialog()
    }

    // Re/sets the pipe image
    fun setPipe(i: Int, j: Int) {

        val pipeViewId = "imageView" + (i + 1) + (j + 1)
        val iview = findViewById<ImageView>(resources.getIdentifier(pipeViewId, "id", packageName))
        when (pipes.layout[i]!![j]) {
            0 -> iview.setImageResource(R.drawable.ic_bentpipe1)
            1 -> iview.setImageResource(R.drawable.ic_bentpipe2)
            2 -> iview.setImageResource(R.drawable.ic_bentpipe3)
            3 -> iview.setImageResource(R.drawable.ic_bentpipe4)
            4 -> iview.setImageResource(R.drawable.ic_strpipe1)
            5 -> iview.setImageResource(R.drawable.ic_strpipe2)
        }
    }

    // Visuals for pipe swap
    fun swapPipes() {
        val pipeString1 = "imageView" + (pipes.i1 + 1) + (pipes.j1 + 1)
        val iview1 = findViewById<ImageView>(resources.getIdentifier(pipeString1, "id", packageName))
        iview1.clearColorFilter()

        val pipeString2 = "imageView" + (pipes.i2 + 1) + (pipes.j2 + 1)
        val iview2 = findViewById<ImageView>(resources.getIdentifier(pipeString2, "id", packageName))
        iview2.clearColorFilter()

        val animLength: Long = 250

        iview1.animate().apply {
            duration = animLength
            scaleX(0.1f)
            scaleY(0.1f)
        }.withEndAction {
            setPipe(pipes.i1, pipes.j1)
            iview1.animate().apply {
                duration = animLength
                scaleX(1f)
                scaleY(1f)
            }
        }.start()

        iview2.animate().apply {
            duration = animLength
            scaleX(0.1f)
            scaleY(0.1f)
        }.withEndAction {
            setPipe(pipes.i2, pipes.j2)
            iview2.animate().apply {
                duration = animLength
                scaleX(1f)
                scaleY(1f)
            }

        }.start()
    }

    fun errorPipes() {
        val pipeString1 = "imageView" + (pipes.i1 + 1) + (pipes.j1 + 1)
        val iview1 = findViewById<ImageView>(resources.getIdentifier(pipeString1, "id", packageName))

        val pipeString2 = "imageView" + (pipes.i2 + 1) + (pipes.j2 + 1)
        val iview2 = findViewById<ImageView>(resources.getIdentifier(pipeString2, "id", packageName))

        val animLength: Long = 250

        iview1.animate().apply {
            duration = animLength
            iview1.setColorFilter(Color.argb(255, 255, 0, 0))
        }.withEndAction {
            iview1.clearColorFilter()
        }.start()

        iview2.animate().apply {
            duration = animLength
            iview2.setColorFilter(Color.argb(255, 255, 0, 0))
        }.withEndAction {
            iview2.clearColorFilter()
        }.start()
    }

    // Runs when pipe puzzle completed
    fun finishDialog() {
        val alertDialog = AlertDialog.Builder(this@MazeActivity).create()
        alertDialog.setTitle("Congrats!")
        alertDialog.setMessage("You did it!!")
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which ->
            dialog.dismiss()
            val intent: Intent = Intent(this@MazeActivity,MainActivity::class.java)
            startActivity(intent)
            }
        alertDialog.show()


    }

    fun helpButton(view: View) {
        val alertDialog = AlertDialog.Builder(this@MazeActivity).create()
        alertDialog.setTitle("Cat")
        alertDialog.setMessage("You are an all-powerful cat who can bend space to reach your yarn.\nBut, you're still a cat so your powers are limited to only being able to swap 2 objects of a similar shape and size.")
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "I am now a cat"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }
}