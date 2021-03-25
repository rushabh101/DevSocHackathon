package com.example.devsochackathon

import android.util.Log
import java.util.*

class PipeLand internal constructor() {
    var i1 = 0
    var j1 = 0
    var i2 = 0
    var j2 = 0
    var swapActive = false
    var layout = Array<IntArray?>(8) { IntArray(6) }
    fun swap(id: String): Boolean {
        if (!swapActive) {
            i1 = id[9].toString().toInt() - 1
            j1 = id[10].toString().toInt() - 1
        } else {
            i2 = id[9].toString().toInt() - 1
            j2 = id[10].toString().toInt() - 1

            val temp = layout[i1]!![j1]
            layout[i1]!![j1] = layout[i2]!![j2]
            layout[i2]!![j2] = temp
        }
        swapActive = !swapActive
        return !swapActive
    }

    fun checkForPath(): Boolean {
        return (checkPath(2) || checkPath(1))
    }

    fun checkPath(pOut: Int): Boolean {
        var pout = pOut
        var i = 0
        var j = 0
        while (i != 7 || j != 5) {
            if (pout == 0) i-- else if (pout == 1) j++ else if (pout == 2) i++ else if (pout == 3) j--
            if (i < 0 || i > 7 || j < 0 || j > 5) return false
            pout = pipeMove((pout + 2) % 4, layout[i]!![j])
            if (pout == -1) return false
        }
        return true
    }

    fun pipeMove(`in`: Int, pipeType: Int): Int {
        if (pipeType == -2) {
            return if (`in` != 0 && `in` != 3) -1 else -2
        }
        val moveList = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 0), intArrayOf(0, 2), intArrayOf(1, 3))
        if (moveList[pipeType][0] != `in` && moveList[pipeType][1] != `in`) return -1
        var temp = -1
        for (i in 0..1) {
            if (moveList[pipeType][i] != `in`) temp = moveList[pipeType][i]
        }
        return temp
    }

    companion object {
        private const val TAG = "PipeLand"
    }

    init {
        val rand = Random()
        val pipes = intArrayOf(8, 8, 8, 8, 6, 8)

        // Randomly allocating the pipe positions, while the start and end pipes are fixed on corners
        for (i in 0..7) {
            for (j in 0..5) {
                if (i == 0 && j == 0) {
                    layout[i]!![j] = -1
                    continue
                } else if (i == 7 && j == 5) {
                    layout[i]!![j] = -2
                    continue
                }
                var t = rand.nextInt(6)
                while (pipes[t] == 0) t = rand.nextInt(6)
                layout[i]!![j] = t
                pipes[t]--
            }
        }
    }
}