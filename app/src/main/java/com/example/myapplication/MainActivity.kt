package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var activePlayer: Int = 0
    var winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img1.setOnClickListener(this)
        img2.setOnClickListener(this)
        img3.setOnClickListener(this)
        img4.setOnClickListener(this)
        img5.setOnClickListener(this)
        img6.setOnClickListener(this)
        img7.setOnClickListener(this)
        img8.setOnClickListener(this)
        img9.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {

        p0 as ImageView
        val tappedCounter: Int = p0.getTag().toString().toInt()
        if (gameState[tappedCounter] == 2 && gameActive == true) {
            gameState[tappedCounter] = activePlayer
            p0!!.translationY = -1500f
            if (activePlayer == 0) {

                p0.setImageResource(R.drawable.ic_baseline_close_24)
                activePlayer = 1
            } else {

                p0.setImageResource(R.drawable.ic_baseline_adjust_24)
                activePlayer = 0
            }
            p0.animate().translationYBy(1500f).setDuration(300)
            winningPositions.forEach {
                if (gameState[it[0]] == gameState[it[1]] && gameState[it[1]] == gameState[it[2]] && gameState[it[0]] != 2) {
                    gameActive = false
                    var winner: String? = null
                    winner = if (activePlayer == 1) {
                        "Player 1"
                    } else {
                        "Player 2"
                    }
                    winnertvw.text = winner + "  WON"
                    restart.visibility = VISIBLE
                    restart.setOnClickListener {
                        restart()

                    }
                }
            }
        }
        if(!gameState.contains(2)) {
            winnertvw.text = "MATCH TIED"
            restart.visibility = VISIBLE
            restart.setOnClickListener {
                restart()


            }
        }
    }

    private fun restart() {
        img1.setImageDrawable(null)
        img2.setImageDrawable(null)
        img3.setImageDrawable(null)
        img4.setImageDrawable(null)
        img5.setImageDrawable(null)
        img6.setImageDrawable(null)
        img7.setImageDrawable(null)
        img8.setImageDrawable(null)
        img9.setImageDrawable(null)
        winnertvw.text = ""
        activePlayer = 0
        gameActive = true
        for (i in 0 until gameState.size) {
            gameState[i] = 2
        }
        restart.visibility = GONE
    }
}
