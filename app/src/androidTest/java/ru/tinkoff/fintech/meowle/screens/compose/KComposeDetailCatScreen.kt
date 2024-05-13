package ru.tinkoff.fintech.meowle.screens.compose

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.KNode

class KComposeDetailCatScreen (
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeBaseScreen(semanticsProvider) {

    private val getLike = KNode(semanticsProvider) { hasTestTag("getLike") }
    private val numberLikes = KNode(semanticsProvider) { hasTestTag("numberLike") }
    private val nameCat = KNode(semanticsProvider) { hasTestTag("nameCat") }
    private val descriptionCat = KNode(semanticsProvider) { hasTestTag("descriptionCat") }
    private val editButton = KNode(semanticsProvider) { hasTestTag("editButton") }
    private val catDescription = KNode(semanticsProvider) { hasTestTag("editCatDescription") }
    private val saveButton = KNode(semanticsProvider) { hasTestTag("saveButton") }


    fun checkDataInCatDetail() {
        val nameCatDefault = "Гречка"
        val descriptionCatDefault = "Описание кота"
        val likeCatDefault = "3"
        nameCat.assertTextContains(nameCatDefault,true)
        descriptionCat.assertTextContains(descriptionCatDefault,true)
        numberLikes.assertTextContains(likeCatDefault,true)
    }

    fun clickLikeButton() {
        getLike.performClick()
    }

    fun clickEditButton() {
        editButton.performClick()
    }

    fun editDescription(textDescription: String) {
        catDescription.performTextInput(textDescription)
    }

    fun clickSaveButton() {
        saveButton.performClick()
    }
}