package ru.fefu.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.collections.getOrNull

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

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> Tab1Fragment()
        1 -> Tab2Fragment()
        else -> throw IndexOutOfBoundsException("Нет вкладки с позицией $position")
    }
}
