package ru.fefu.fitness

enum class ActivityType {
    OWN,
    OTHERS
}

class ActivityDataSource {

    private val ownActivityList = listOf(
        ActivityItem.ActivityHeader("Вчера"),
        ActivityItem.ActivityMain(
            1, "14.32 км", "2 часа 46 минут", "14 часов назад", "Серфинг", false
        ),
        ActivityItem.ActivityHeader("Май 2022 года"),
        ActivityItem.ActivityMain(
            2, "1000 м", "60 минут", "29.05.2022", "Велосипед", false
        )
    )

    private val othersActivityList = listOf(
        ActivityItem.ActivityHeader("Вчера"),
        ActivityItem.ActivityMain(
            3, "14.32 км", "2 часа 46 минут", "14 часов назад", "Серфинг", true, "@van_darkholme"
        ),
        ActivityItem.ActivityMain(
            4, "228 м", "14 часов 48 минут", "14 часов назад", "Качели", true, "@techniquepasha"
        ),
        ActivityItem.ActivityMain(
            5, "10 км", "1 час 10 минут", "14 часов назад", "Езда на кадиллаке", true, "@morgen_shtern"
        )
    )

    fun fetchActivities(activityType: ActivityType): List<ActivityItem> {
        return when (activityType) {
            ActivityType.OWN -> ownActivityList
            ActivityType.OTHERS -> othersActivityList
        }
    }
}
