package ru.tinkoff.fintech.meowle.screens.compose

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode
import ru.tinkoff.fintech.meowle.presentation.compose.ui.LazyListItemPosition

class KComposeSearchScreen (
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeBaseScreen(semanticsProvider) {

    private val search = KNode(semanticsProvider) { hasTestTag("search") }

    fun findCat(catName: String) {
        search.performTextInput(catName)
        search.performImeAction()
    }

    private val catsList = KLazyListNode(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = {
            hasTestTag("searchCatsList")
            useUnmergedTree = true
        },
        itemTypeBuilder = {
            itemType(::ComposeCatCard)
        },
        positionMatcher = { position -> SemanticsMatcher.expectValue(LazyListItemPosition, position) }
    )

    @OptIn(ExperimentalTestApi::class)
    fun checkCatName(catName: String, position: Int) {
        catsList.childAt<ComposeCatCard>(position-1)  {
            name.assertTextContains(catName, true)
        }
    }
    @OptIn(ExperimentalTestApi::class)
    fun clickFirstCat() {
        catsList.firstChild<ComposeCatCard> {
            name.performClick()
        }
    }
}

private class ComposeCatCard(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<ComposeCatCard>(semanticsNode, semanticsProvider) {
    val name: KNode = child {
        hasTestTag("catName")
        useUnmergedTree = true
    }
}