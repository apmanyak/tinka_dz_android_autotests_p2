package ru.tinkoff.fintech.meowle.screens.noCompose

import android.view.View
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.tinkoff.fintech.meowle.R
import ru.tinkoff.fintech.meowle.wiremock.WireMockHelper

class KRatingScreen : BaseScreen() {
    override val layoutId: Int = R.layout.rating_fragment
    private val topDislikesButton = KView { withText(R.string.rating_tab_dislikes_title) }

    private val catsList = KRecyclerView(
        builder = { withId(R.id.rv_cats_list)},
        itemTypeBuilder = { itemType(::CatCard) }
    )

    fun clickTopDislikesButton() {
        topDislikesButton.click()
    }

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
            catsList.childAt<CatCard>(position) {
                this.catName { hasText(nameTopDislikesList[position]) }
            }
        }
    }

    fun clickCatInRating(positionInRating: Int) {
        catsList.childAt<CatCard>(positionInRating-1) {
            this.catName.click()
        }
    }
}

private class CatCard(matcher: Matcher<View>) : KRecyclerItem<CatCard>(matcher) {
    val catName = KTextView(matcher) { withId(R.id.cat_name) }
}
