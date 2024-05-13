package ru.tinkoff.fintech.meowle.screens.compose

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performClick
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode
import ru.tinkoff.fintech.meowle.presentation.compose.ui.LazyListItemPosition

class KComposeRatingScreen (
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeBaseScreen(semanticsProvider) {

    private val likesDislikesTab = semanticsProvider.onAllNodesWithTag("top_dislikes_button")

    private val catsList = KLazyListNode(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = {
            hasTestTag("listRatingCat")
            useUnmergedTree = true
        },
        itemTypeBuilder = {
            itemType(::ComposeCatCardRating)
        },
        positionMatcher = { position -> SemanticsMatcher.expectValue(LazyListItemPosition, position) }
    )

    fun clickTopDislikesButton() {
        likesDislikesTab[1].performClick()
    }

    @OptIn(ExperimentalTestApi::class)
    fun clickCatInRating(position: Int) {
        catsList.childAt<ComposeCatCardRating>(position-1) {
            name.performClick()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    fun checkDislikesRating() {
        val nameTopDislikesList = listOf(
            "Оаапп",
            "Быжълтгнмк",
            "Здчёедлжьшздчёедлжьш",
            "Некий",
            "Елюуузшёою",
            "Щнпмяшочтз",
            "Валерий",
            "Ууууууууу",
            "Жжж",
            "Майонез")

        for (position in 0..9) {
            catsList.childAt<ComposeCatCardRating> (position) {
                name.assertTextContains(nameTopDislikesList[position],true)
            }
        }
    }
}

private class ComposeCatCardRating(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<ComposeCatCardRating>(semanticsNode, semanticsProvider) {
    val name: KNode = child {
        hasTestTag("catName")
        useUnmergedTree = true
    }
}