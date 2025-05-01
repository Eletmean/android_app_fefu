package ru.fefu.fitness

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val activityTitle = findViewById<TextView>(R.id.titleText)
        val activityDistance = findViewById<TextView>(R.id.distance)
        val activityTimeAgo = findViewById<TextView>(R.id.timeAgo)
        val activityDuration = findViewById<TextView>(R.id.duration)
        val activityTimeRange = findViewById<TextView>(R.id.timeRange)
        val authorName = findViewById<TextView>(R.id.username)
        val backButton = findViewById<ImageView>(R.id.Arrow)

        val receivedActivity = intent.getParcelableExtra<ActivityItem.ActivityMain>("ACTIVITY_MAIN")

        receivedActivity?.let { activityData ->
            activityTitle.text = activityData.type
            activityDistance.text = activityData.distance
            activityTimeAgo.text = activityData.timeAgo
            activityDuration.text = activityData.duration
            activityTimeRange.text = activityData.timeRange ?: "Старт 14:49  |  Финиш 16:31"

            if (activityData.isFromOtherUser && !activityData.username.isNullOrEmpty()) {
                authorName.visibility = View.VISIBLE
                authorName.text = activityData.username
            } else {
                authorName.visibility = View.GONE
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
