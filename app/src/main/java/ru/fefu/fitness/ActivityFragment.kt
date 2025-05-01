package ru.fefu.fitness

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ActivityFragment : Fragment() {

    companion object {
        const val TAG = "ActivityFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.activity_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            val tabLayout = findViewById<TabLayout>(R.id.tablayout)
            val viewPager = findViewById<ViewPager2>(R.id.view_pager)

            viewPager.adapter = ViewPagerAdapter(this@ActivityFragment)

            val tabTitles = listOf("Моя", "Пользователей")

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabTitles.getOrNull(position) ?: "Вкладка ${position + 1}"
            }.attach()
        }
    }
}

// Адаптер для ViewPager2
class ViewPagerAdapter(fragment: Fragment) : androidx.viewpager2.adapter.FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MyActivitiesFragment()
        1 -> UsersActivitiesFragment()
        else -> throw IndexOutOfBoundsException("Нет вкладки с позицией $position")
    }
}

// Фрагмент для отображения моих активностей
class MyActivitiesFragment : Fragment() {
    private lateinit var adapter: ActivityAdapter
    private val activitiesRepository = ActivityDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_sportik_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        adapter = ActivityAdapter(emptyList()) { activity ->
            val intent = Intent(requireContext(), ActivityDetails::class.java).apply {
                putExtra("ACTIVITY_MAIN", activity)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadMyActivities()

        return view
    }

    private fun loadMyActivities() {
        val items = activitiesRepository.fetchActivities(ActivityType.OWN)
        adapter.updateActivities(items)
    }
}

// Фрагмент для отображения активностей пользователей
class UsersActivitiesFragment : Fragment() {
    private lateinit var adapter: ActivityAdapter
    private val activitiesRepository = ActivityDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_sportik_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        adapter = ActivityAdapter(emptyList()) { activity ->
            val intent = Intent(requireContext(), ActivityDetails::class.java).apply {
                putExtra("ACTIVITY_MAIN", activity)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadUsersActivities()

        return view
    }

    private fun loadUsersActivities() {
        val items = activitiesRepository.fetchActivities(ActivityType.OTHERS)
        adapter.updateActivities(items)
    }
}
