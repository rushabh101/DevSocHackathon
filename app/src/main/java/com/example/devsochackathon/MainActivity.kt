package com.example.devsochackathon

import android.app.AlertDialog
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var pipes = PipeLand()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..7) {
            for (j in 0..5) {
                setPipe(i, j)
            }
        }
    }

    fun pipeClick(view: View) {
//        if(pipes.first == null) {
//            pipes.first = findViewById("imageView");
//        }
//        ImageView iview = findViewById(getResources().getIdentifier("imageView11", "id", getPackageName()));
//        ImageView iview = findViewById(view.getId());
//        iview.setImageResource(R.drawable.ic_bentpipe1);
        val iview = findViewById<ImageView>(view.id)
        iview.setColorFilter(Color.argb(255, 85, 188, 223))
        if (pipes.swap(resources.getResourceEntryName(view.id))) swapPipes()
        if (pipes.checkForPath()) finishDialog()
    }

    fun setPipe(i: Int, j: Int) {

        val pipeViewId = "imageView" + (i + 1) + (j + 1)
        val iview = findViewById<ImageView>(resources.getIdentifier(pipeViewId, "id", packageName))
        when (pipes.layout[i]!![j]) {
//            -2 -> iview.setImageResource(R.drawable.ic_end)
//            -1 -> iview.setImageResource(R.drawable.ic_start)
            0 -> iview.setImageResource(R.drawable.ic_bentpipe1)
            1 -> iview.setImageResource(R.drawable.ic_bentpipe2)
            2 -> iview.setImageResource(R.drawable.ic_bentpipe3)
            3 -> iview.setImageResource(R.drawable.ic_bentpipe4)
            4 -> iview.setImageResource(R.drawable.ic_strpipe1)
            5 -> iview.setImageResource(R.drawable.ic_strpipe2)
        }
    }

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

    fun finishDialog() {
        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle("Congrats!")
        alertDialog.setMessage("You did it!!")
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }
}