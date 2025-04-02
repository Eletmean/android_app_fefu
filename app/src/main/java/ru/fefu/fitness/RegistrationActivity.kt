package ru.fefu.fitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import CustomClickableSpan
import android.widget.ImageView
import android.text.SpannableString
import android.widget.TextView

class RegistrationActivity : AppCompatActivity() {

    companion object {
        private const val PRIVACY_POLICY_START_INDEX = 37
        private const val PRIVACY_POLICY_END_INDEX = 65
        private const val USER_AGREEMENT_START_INDEX = 119
        private const val USER_AGREEMENT_END_INDEX = 146
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_page)

        val backButton = findViewById<ImageView>(R.id.BackButton)
        backButton.setOnClickListener {
            finish()
        }


        val privacyPolicySpan = CustomClickableSpan(this, { onPrivacyPolicyClick() })
        val userAgreementSpan = CustomClickableSpan(this, { onUserAgreementClick() })


        val agreementTextView = findViewById<TextView>(R.id.Agreement)
        val spannableString = SpannableString(agreementTextView.text)

        spannableString.setSpan(privacyPolicySpan, PRIVACY_POLICY_START_INDEX, PRIVACY_POLICY_END_INDEX, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(userAgreementSpan, USER_AGREEMENT_START_INDEX, USER_AGREEMENT_END_INDEX, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)

        agreementTextView.text = spannableString
    }

    private fun onPrivacyPolicyClick() {
    }

    private fun onUserAgreementClick() {
    }
}
