package ru.tinkoff.fintech.meowle.screens.noCompose

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.bottomnav.KBottomNavigationView
import ru.tinkoff.fintech.meowle.R

abstract class BaseScreen: KScreen<BaseScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val ratingButton = KBottomNavigationView { withId(R.id.tab_btn_rating) }

    fun clickRatingTabBar() {
        ratingButton.click()
    }

    private val addButton = KBottomNavigationView { withId(R.id.tab_btn_add) }

    fun clickAddTabBar() {
        addButton.click()
    }

}