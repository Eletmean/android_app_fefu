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

        val activityTypeTextView = findViewById<TextView>(R.id.titleText)
        val activityDistanceTextView = findViewById<TextView>(R.id.distanceText)
        val activityTimeAgoTextView = findViewById<TextView>(R.id.timeAgoText)
        val activityDurationTextView = findViewById<TextView>(R.id.durationText)
        val activityTimeRangeTextView = findViewById<TextView>(R.id.timeRangeText)
        val activityUsernameTextView = findViewById<TextView>(R.id.usernameText)
        val backButtonImageView = findViewById<ImageView>(R.id.Arrow)


        val activityData = intent.getParcelableExtra<ActivityItem.ActivityMain>("ACTIVITY_MAIN")

        activityData?.let { activity ->

            activityTypeTextView.text = activity.type
            activityDistanceTextView.text = activity.distance
            activityTimeAgoTextView.text = activity.timeAgo
            activityDurationTextView.text = activity.duration


            activityTimeRangeTextView.text = activity.timeRange ?: "Старт 14:49  |  Финиш 16:31"


            if (activity.isFromOtherUser && !activity.username.isNullOrEmpty()) {
                activityUsernameTextView.visibility = View.VISIBLE
                activityUsernameTextView.text = activity.username
            } else {
                activityUsernameTextView.visibility = View.GONE
            }
        }

        backButtonImageView.setOnClickListener {
            finish()
        }
    }
}