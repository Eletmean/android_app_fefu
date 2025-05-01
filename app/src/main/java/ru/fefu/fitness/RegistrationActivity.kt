package ru.fefu.fitness

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.text.SpannableString
import android.app.Activity
import android.content.Intent
import android.widget.Button

class RegistrationActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_page)

        val arrowButton = findViewById<ImageView>(R.id.Arrow)
        arrowButton.setOnClickListener {
            finish()
        }

        val agreementTextView = findViewById<TextView>(R.id.agreeText)
        val originalText = agreementTextView.text.toString()
        val spannableContent = SpannableString(originalText)

        val privacyPolicyPhrase = "политикой конфиденциальности и обработки персональных данных"
        val userAgreementPhrase = "пользовательское соглашение"

        val privacyStartIndex = originalText.indexOf(privacyPolicyPhrase)
        val privacyEndIndex = privacyStartIndex + privacyPolicyPhrase.length

        val agreementStartIndex = originalText.indexOf(userAgreementPhrase)
        val agreementEndIndex = agreementStartIndex + userAgreementPhrase.length

        if (privacyStartIndex != -1) {
            spannableContent.setSpan(
                CustomClickableSpan(this) {
                    // обработка нажатия на политику
                },
                privacyStartIndex, privacyEndIndex,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        if (agreementStartIndex != -1) {
            spannableContent.setSpan(
                CustomClickableSpan(this) {
                    // обработка нажатия на соглашение
                },
                agreementStartIndex, agreementEndIndex,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        agreementTextView.text = spannableContent


        val signUpButton = findViewById<Button>(R.id.Buttonregist)
        signUpButton.setOnClickListener {
            val intent = Intent(this, MainNavigationActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}
