package ru.fefu.fitness

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val backButton = findViewById<ImageView>(R.id.BackButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}
