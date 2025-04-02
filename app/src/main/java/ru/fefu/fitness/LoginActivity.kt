package ru.fefu.fitness

import android.os.Bundle
import android.app.Activity
import android.widget.ImageView

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val backArrow = findViewById<ImageView>(R.id.Arrow)
        backArrow.setOnClickListener {
            finish()
        }

    }
}