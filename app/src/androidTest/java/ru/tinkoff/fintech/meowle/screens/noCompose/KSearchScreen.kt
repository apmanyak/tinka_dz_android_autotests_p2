package ru.tinkoff.fintech.meowle.screens.noCompose

import android.view.View
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import io.qameta.allure.kotlin.Allure
import org.hamcrest.Matcher
import ru.tinkoff.fintech.meowle.R

class KSearchScreen: BaseScreen() {

    override val layoutId: Int = R.layout.search_fragment

    private val searchInput = KEditText { withId(R.id.et_search) }

    private val catsList = KRecyclerView(
        builder = { withId(R.id.rv_search_result_list) },
        itemTypeBuilder = { itemType(::CatCards) }
    )

    fun findCat(catName: String) {
        Allure.step("Поиск кота") {
            searchInput.replaceText(catName)
            searchInput.pressImeAction()
        }
    }

    fun checkCatName(catName: String, position: Int) {
        catsList.childAt<CatCards>(position-1) {
            this.catName{hasText("$catName,")}
        }
    }

    fun clickFirstCat() {
        catsList.childAt<CatCards>(0) {
            this.catName.click()
        }
    }
}

private class CatCards(matcher: Matcher<View>) : KRecyclerItem<CatCards>(matcher) {
    val catName = KTextView(matcher) { withId(R.id.cat_name) }
}