package ru.fefu.fitness


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class NewestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_newest)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, StartFragment())
                .commit()
        }

        val buttonBack = findViewById<ImageView>(R.id.Arrow)
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainNavigationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
