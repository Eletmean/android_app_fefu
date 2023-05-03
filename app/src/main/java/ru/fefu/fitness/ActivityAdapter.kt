package ru.fefu.fitness

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater

class ActivityAdapter(
    private var activities: List<ActivityItem>,
    private val onActivityClicked: (ActivityItem.ActivityMain) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ACTIVITY = 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateActivities(newActivities: List<ActivityItem>) {
        activities = newActivities.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (activities[position]) {
            is ActivityItem.ActivityHeader -> VIEW_TYPE_HEADER
            is ActivityItem.ActivityMain -> VIEW_TYPE_ACTIVITY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val headerView = inflater.inflate(R.layout.activity_iheader, parent, false)
                HeaderViewHolder(headerView)
            }
            VIEW_TYPE_ACTIVITY -> {
                val activityView = inflater.inflate(R.layout.activity_imain, parent, false)
                ActivityViewHolder(activityView, onActivityClicked)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val currentItem = activities[position]) {
            is ActivityItem.ActivityHeader -> (holder as HeaderViewHolder).bind(currentItem)
            is ActivityItem.ActivityMain -> (holder as ActivityViewHolder).bind(currentItem)
        }
    }

    override fun getItemCount(): Int = activities.size

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerDateTextView: TextView = itemView.findViewById(R.id.dateText)

        fun bind(header: ActivityItem.ActivityHeader) {
            headerDateTextView.text = header.date
        }
    }

    class ActivityViewHolder(
        itemView: View,
        private val onActivityClick: (ActivityItem.ActivityMain) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val activityDistanceTextView: TextView = itemView.findViewById(R.id.distanceText)
        private val activityDurationTextView: TextView = itemView.findViewById(R.id.durationText)
        private val activityTimeAgoTextView: TextView = itemView.findViewById(R.id.timeAgoText)
        private val activityTypeTextView: TextView = itemView.findViewById(R.id.typeText)
        private val activityUsernameTextView: TextView = itemView.findViewById(R.id.usernameText)

        fun bind(activity: ActivityItem.ActivityMain) {
            activityDistanceTextView.text = activity.distance
            activityDurationTextView.text = activity.duration
            activityTimeAgoTextView.text = activity.timeAgo
            activityTypeTextView.text = activity.type

            if (activity.isFromOtherUser && !activity.username.isNullOrEmpty()) {
                activityUsernameTextView.visibility = View.VISIBLE
                activityUsernameTextView.text = activity.username
            } else {
                activityUsernameTextView.visibility = View.GONE
            }

            itemView.setOnClickListener { onActivityClick(activity) }
        }
    }
}