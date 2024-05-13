package ru.tinkoff.fintech.meowle.screens.compose

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performClick
import io.github.kakaocup.compose.node.element.ComposeScreen

open class ComposeBaseScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<ComposeBaseScreen>(semanticsProvider){

    private val navigationBar = semanticsProvider.onAllNodesWithTag("navigation_bar")

    fun clickRatingTabBar() {
        navigationBar[1].performClick()
    }

    fun clickAddTabBar() {
        navigationBar[2].performClick()
    }
}