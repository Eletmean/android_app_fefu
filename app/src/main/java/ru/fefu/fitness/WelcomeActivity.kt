package ru.fefu.fitness

import android.app.Activity
import com.google.android.material.button.MaterialButton
import android.content.Intent
import android.os.Bundle

class WelcomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)

        setupNavigationButtons()
    }

    private fun setupNavigationButtons() {
        with(findViewById<MaterialButton>(R.id.buttonregist)) {
            setOnClickListener { navigateTo(RegistrationActivity::class.java) }
        }

        with(findViewById<MaterialButton>(R.id.buttonlogin)) {
            setOnClickListener { navigateTo(LoginActivity::class.java) }
        }
    }

    private fun navigateTo(target: Class<*>) {
        startActivity(Intent(this, target))
    }
}