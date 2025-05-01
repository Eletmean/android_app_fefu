package ru.fefu.fitness

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActivityAdapter(
    activities: List<ActivityItem>,
    private val onActivityClick: (ActivityItem.ActivityMain) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_MAIN = 1
    }

    private val activityList = activities.toMutableList()

    @SuppressLint("NotifyDataSetChanged")
    fun updateActivities(newActivities: List<ActivityItem>) {
        activityList.clear()
        activityList.addAll(newActivities)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (activityList[position]) {
            is ActivityItem.ActivityHeader -> VIEW_TYPE_HEADER
            is ActivityItem.ActivityMain -> VIEW_TYPE_MAIN
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val headerView = inflater.inflate(R.layout.activity_iheader, parent, false)
                HeaderViewHolder(headerView)
            }
            VIEW_TYPE_MAIN -> {
                val mainView = inflater.inflate(R.layout.activity_imain, parent, false)
                MainViewHolder(mainView, onActivityClick)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val currentItem = activityList[position]) {
            is ActivityItem.ActivityHeader -> (holder as HeaderViewHolder).bindHeader(currentItem)
            is ActivityItem.ActivityMain -> (holder as MainViewHolder).bindMain(currentItem)
        }
    }

    override fun getItemCount(): Int = activityList.size

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerDateText: TextView = itemView.findViewById(R.id.dateText)

        fun bindHeader(headerItem: ActivityItem.ActivityHeader) {
            headerDateText.text = headerItem.date
        }
    }

    class MainViewHolder(
        itemView: View,
        private val onItemClicked: (ActivityItem.ActivityMain) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val distanceLabel: TextView = itemView.findViewById(R.id.distance)
        private val durationLabel: TextView = itemView.findViewById(R.id.duration)
        private val timeLabel: TextView = itemView.findViewById(R.id.timeAgo)
        private val typeLabel: TextView = itemView.findViewById(R.id.type)
        private val authorLabel: TextView = itemView.findViewById(R.id.username)

        fun bindMain(mainItem: ActivityItem.ActivityMain) {
            distanceLabel.text = mainItem.distance
            durationLabel.text = mainItem.duration
            timeLabel.text = mainItem.timeAgo
            typeLabel.text = mainItem.type

            if (mainItem.isFromOtherUser && !mainItem.username.isNullOrEmpty()) {
                authorLabel.visibility = View.VISIBLE
                authorLabel.text = mainItem.username
            } else {
                authorLabel.visibility = View.GONE
            }

            itemView.setOnClickListener { onItemClicked(mainItem) }
        }
    }
}
