package ru.tinkoff.fintech.meowle.screens.compose

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performClick
import io.github.kakaocup.compose.node.element.KNode

class KComposeAddScreen (
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeBaseScreen(semanticsProvider) {

    private val nameInput = KNode(semanticsProvider) { hasTestTag("addNameCat") }
    private val descriptionInput = KNode(semanticsProvider) { hasTestTag("addDescription") }
    private val addButton = KNode(semanticsProvider) { hasTestTag("addButton") }
    private val genderList = semanticsProvider.onAllNodesWithTag("genderList")
    private val genderSelect = KNode(semanticsProvider) { hasTestTag("clickGender") }

     fun addNameCat(catName: String) {
         nameInput.performTextInput(catName)
    }

    fun addDescriptionCat(textDescription: String) {
        descriptionInput.performTextInput(textDescription)
    }

    fun clickSelectGenderCat() {
        genderSelect.performClick()
    }

    fun clickGenderCat(gender: String) {
        when (gender){
            "Муж." ->  genderList[0].performClick()
            "Жен." ->  genderList[1].performClick()
            "Универс." ->  genderList[2].performClick()
        }
    }

    fun clickAddButton() {
        addButton.performClick()
    }

}