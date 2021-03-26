package com.example.devsochackathon

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_medium.*
import kotlinx.android.synthetic.main.eventlog.*
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil
import java.util.*
import kotlin.random.Random


class Medium : AppCompatActivity() {

    private var tmedstatus:Boolean=false
    var attemptcounter = 3
    var hmdr = 0
    val brackets = arrayOf<String>("()", "<>", "[]", "{}")
    private lateinit var bsb: BottomSheetBehavior<LinearLayout>
    private lateinit var bsb_start: TextView
    private lateinit var wordsfinal: Vector<String>
    private lateinit var duds: Array<String>
    private var wordsfinala = arrayOf<String>("", "", "", "", "", "", "")
    private lateinit var attempt: Array<ImageView>
    var idattempt = IntArray(4) { R.id.at1;R.id.at2;R.id.at3;R.id.at4 }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium)

        tmedstatus=false
        duds = arrayOf<String>("", "", "", "")
        attempt = arrayOf<ImageView>(at1, at2, at3, at4)
        enterb.setText("<enter>")

        col1.setText("")
        pre_text.setText("")
        var hex: String = col1.text.toString()
        var hex1: String = pre_text.text.toString()
        var allwords = resources.getStringArray(R.array.words2)
        val fix: String = "0xH"
        val post4 = arrayOf<String>("A", "9")
        val post5 = arrayOf<String>(
                "A",
                "B",
                "C",
                "D",
                "E",
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9"
        )

        //hex
        //var eventlog: TextView = findViewById(R.id.event_log)
        val garbage = arrayOf<String>(
                ":",
                ";",
                "%",
                "/",
                "!",
                "^",
                "#",
                "+",
                "*",
                "_",
                "|",
                "=",
                "-",
                "\\",
                "\$",
                "\'",
                "\"",
                "?",
                "@",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z"
        )
        var row = 1
        var comp_string: String = ""
        var post6 =
                Array<Int>(4) { Random.nextInt(9);Random.nextInt(9);Random.nextInt(9);Random.nextInt(9) }
        var j: Int = 0
        var k: Int = 1

        for (i in allwords.size - 1 downTo 1) {
            val j = Random.nextInt(i + 1)
            val temp = allwords[i]
            allwords[i] = allwords[j]
            allwords[j] = temp
        }
        var words = Vector<String>(7)
        for (i in 0..6) {
            words.add(allwords[i])
        }
        var password: String = words[Random.nextInt(0, 7)]

        wordsfinal = createdud(words)
        for (i in wordsfinal.size - 1 downTo 1) {
            val j = Random.nextInt(i + 1)
            val temp = wordsfinal[i]
            wordsfinal[i] = wordsfinal[j]
            wordsfinal[j] = temp
        }

        var startpost = getstartpost()
        //printing 1st column
        for (i in 0..34) {
            //hex1+="0xffff\n"
            var post5r: Int = Random.nextInt(32767) % 15
            if (post5r % 2 == 0) {
                k = 1
            } else {
                k = 0
            }
            hex1 += fix + post4[k] + post5[post5r] + post6[j].toString() + "\n"
            if (j == 3)
                j = -1
            j++
        }
        pre_text.setText(hex1)
        var stuvwxyz = Random.nextInt()
        if (stuvwxyz % 2 == 0) {
            garbage.set(19, "[")
            garbage.set(20, "[")
            garbage.set(21, "<")
            garbage.set(22, "<")
            garbage.set(23, "(")
            garbage.set(24, "(")
            garbage.set(25, "{")
            garbage.set(26, "{")
        } else if (stuvwxyz % 2 != 0) {
            garbage.set(19, "]")
            garbage.set(20, "]")
            garbage.set(21, ">")
            garbage.set(22, ">")
            garbage.set(23, ")")
            garbage.set(24, ")")
            garbage.set(25, "}")
            garbage.set(26, "}")
        }

        //printing 2nd column
        var counter = 0
        var loop = true
        var i = 0
        while (loop) {
            if (startpost.contains(i)) {
                comp_string += wordsfinal[counter]
                i += wordsfinal[counter].length
                counter++
                continue
            }
            var g_index: Int = Random.nextInt(32767) % 27
            comp_string += garbage[g_index]
            i++
            if (i == 630)
                loop = false
        }
        for (i in 0..629) {
            if (i % 18 == 0 && i != 0) {
                hex += "\n"
            }
            hex += comp_string.elementAt(i)

        }
        col1.setText(hex)

        //bottom sheet
        bsb = BottomSheetBehavior.from(event_log)
        bsb_start = findViewById(R.id.tv1)
        bsb_start.setOnClickListener {
            val state =
                    if (bsb.state == BottomSheetBehavior.STATE_EXPANDED) BottomSheetBehavior.STATE_COLLAPSED else BottomSheetBehavior.STATE_EXPANDED
            bsb.state = state
        }
        //highlighting user input
        /* userinput1.addTextChangedListener(object : TextWatcher {
             override fun afterTextChanged(s: Editable?) {
                 TODO("Not yet implemented")
             }

             override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                 TODO("Not yet implemented")
             }

             override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 if (s.toString().length != 0) {
                     tvt.setText("Change is coming")
                 }
             }
         })*/


        //highlight text example

        /*val str = SpannableStringBuilder("Highlighted. Not highlighted.")
        str.setSpan(BackgroundColorSpan(Color.parseColor("#59ff82")), 0, 12, 0)
        str.setSpan(ForegroundColorSpan(Color.parseColor("#0e281b")),0,12,0)
        eventlog.setText(str)*/
        var tv8: TextView = findViewById(R.id.tv8)
        //matching input with user and hiding keyboard
        var tv7v = findViewById<TextView>(R.id.tv7)
        var result = ""

        var printable: String = ""
        var c = 0
        for (element in wordsfinal) {
            if (element.startsWith("<") || element.startsWith("[") || element.startsWith("(") || element.startsWith(
                            "{"
                    )
            ) {
                duds[c] = element
                c++
            }

        }
        var it = 0
        for (i in 0..10) {
            if (!wordsfinal[i].startsWith("<") && !wordsfinal[i].startsWith("[") && !wordsfinal[i].startsWith(
                            "("
                    ) && !wordsfinal[i].startsWith(
                            "{"
                    )
            ) {
                wordsfinala[it] = wordsfinal[i]
                it++
            }
        }
        //testing begins
        for (element in duds) {
            printable += element + "\n"
        }
        tv8.setText(printable+"\' Single \" Double {}NOT[]")
        //testing ends
        //add blinking animation to warning
        enterb.setOnClickListener {
            tv8.setTextAppearance(R.style.tv8fontchange) //changing font for warning msg
            UIUtil.hideKeyboard(this) //hiding keyboard
            if (attemptcounter == 1) {
                tv8.setText("WARNING LOCKOUT IMMINENT!")

            } else {
                tv8.setText("")
            }
            if (user_input.text.toString() != "") {
                result += matchingpassword(password)
                tv7v.setText(result)
            }
        }
    }

    private fun matchingpassword(password: String): String {
        var resultc = ""
        var userinput = user_input.text.toString()
        if (duds.any { it == userinput }) {
            resultc += "> $userinput\n> DUD REMOVED\n"
            duds.set(duds.indexOf(userinput), "")
            hmdr++
            if (hmdr == 2) {
                hmdr = 0
                attemptcounter = 3
                resultc += "> ATTEMPTS REPLENISHED\n"
                tv8.text = ""
                for (elements in attempt) {
                    elements.setVisibility(View.VISIBLE)
                }
            }

        } else if (wordsfinala.contains(userinput)) {
            if (userinput == password) {
                resultc += "> " + userinput + "\n> Access Granted\n"
                val intent: Intent = Intent(this@Medium, unlocked_screen::class.java)
                tmedstatus=true
                val intent2:Intent=Intent(this,MainMenu::class.java)
                intent2.putExtra("statusmed",tmedstatus)
                startActivity(intent)
                finish()
            } else {
                var c = 0
                for (i in 0..10) {
                    if (userinput.elementAt(i) == password.elementAt(i)
                    ) {
                        c++
                    }
                }
                resultc += "> " + userinput + "\n> $c/7 Entries Matched \n"
                if (attemptcounter >= 0) {
                    attempt[attemptcounter].setVisibility(View.GONE)
                    attemptcounter--
                }
            }
        } else if (!wordsfinala.contains(userinput)&&!duds.contains(userinput)) {
            resultc += "> " + userinput + "\n> INVALID ENTRY\n"
            Log.d("look here",userinput)
            if (attemptcounter >= 0) {
                attempt[attemptcounter].setVisibility(View.GONE)
                attemptcounter--
            }
        }
        //exiting to fail screen
        if (attemptcounter == -1) {
            val intent: Intent = Intent(this@Medium, fail_screen::class.java)
            startActivity(intent)
            finish()
        }

        return resultc
    }


    private fun createdud(words: Vector<String>): Vector<String> {
        val garbagev2 = arrayOf<String>(
                ":",
                ";",
                "%",
                "/",
                "!",
                "^",
                "#",
                "+",
                "*",
                "_",
                "|",
                "=",
                "-",
                "\\",
                "\$",
                "\'",
                "\"",
                "?",
                "@"
        )
        for (h in 0..3) {
            var bracselected = brackets[Random.nextInt(4)]
            var randlength = Random.nextInt(10)
            var dud: String = ""
            if (randlength != 0) {
                for (i in 0..randlength - 1) {
                    dud += garbagev2[Random.nextInt(30000) % 19]
                }
                words.add(bracselected.elementAt(0) + dud + bracselected.elementAt(1))
                dud = ""
            } else if (randlength == 0) {
                words.add(bracselected)
            }
        }
        return words
    }

    private fun getstartpost(): IntArray {
        var a1 = IntArray(11) { 0 }
        var c1 = 0
        var c2 = 45
        for (i in 0..10) {
            if (c2 > 618)
                c2 = 618
            a1[i] = Random.nextInt(c1, c2)
            c1 += 52
            c2 += 52
        }
        return a1
    }
}
