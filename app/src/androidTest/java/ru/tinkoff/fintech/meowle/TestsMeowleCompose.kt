package ru.tinkoff.fintech.meowle

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.fintech.meowle.elements.BaseCaseTestCaseForMeowleCompose
import ru.tinkoff.fintech.meowle.presentation.view.AuthActivity
import ru.tinkoff.fintech.meowle.rules.PreferenceRuleCompose
import ru.tinkoff.fintech.meowle.screens.compose.KComposeAddScreen
import ru.tinkoff.fintech.meowle.screens.compose.KComposeDetailCatScreen
import ru.tinkoff.fintech.meowle.screens.compose.KComposeRatingScreen
import ru.tinkoff.fintech.meowle.screens.compose.KComposeSearchScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockAddScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockDetailScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockRatingScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockSearchScreen


class TestsMeowleCompose : BaseCaseTestCaseForMeowleCompose() {

    private val port = 5000

    @get:Rule
    val prefs = PreferenceRuleCompose()

    @get: Rule
    val mock = WireMockRule(port)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<AuthActivity>()

    @Test
    fun test1SearchCat() = run {
        val nameCat = "Гречка"
        val numberPositionIsSearch = 1

        WireMockSearchScreen().stubMockSearch()
        KComposeSearchScreen(composeTestRule).apply {
            findCat(nameCat)
            checkCatName(nameCat,numberPositionIsSearch)
        }
    }

    @Test
    fun test2FromSearchCatToDetailCat()  {
        val nameCat = "Гречка"

        WireMockSearchScreen().apply {
            stubMockSearch()
            stubMockFirstCatDetail()
        }

        KComposeSearchScreen(composeTestRule).apply {
            findCat(nameCat)
            clickFirstCat()
        }

        KComposeDetailCatScreen(composeTestRule).checkDataInCatDetail()
    }

    @Test
    fun test3LikeTopCat() = run {
        val numberPositionInTopDislikes = 1
        val idCatInPositionInTopDislikes = 15678

        WireMockRatingScreen().apply {
            stubMockRating()
            stubMockCatDetail(idCatInPositionInTopDislikes)
        }

        KComposeRatingScreen(composeTestRule).apply {
            clickRatingTabBar()
            clickTopDislikesButton()
            checkDislikesRating()
            clickCatInRating(numberPositionInTopDislikes)
        }

        WireMockDetailScreen().stubMockLikeCatDetail()
        KComposeDetailCatScreen(composeTestRule).clickLikeButton()
        WireMockDetailScreen().verifyMockCatLike()
    }

    @Test
    fun test4AddCat() = run {
        val catName = "Пипинска"
        val gender = "Универс."
        val textDescription = "Это мое описание"

        WireMockAddScreen().stubMockAddCat()

        KComposeAddScreen(composeTestRule).apply {
            clickAddTabBar()
            addNameCat(catName)
            clickSelectGenderCat()
            clickGenderCat(gender)
            addDescriptionCat(textDescription)
            clickAddButton()
        }

        WireMockAddScreen().verifyMockAddCat(
            catName,
            gender,
            textDescription
        )
    }

    @Test
    fun test5EditCat() = run {
        val numberPositionInTopLikes = 5
        val idCatInPositionInTopLikes = 15480
        val textDescription = "ААААА"

        WireMockRatingScreen().apply {
            stubMockRating()
            stubMockCatDetail(idCatInPositionInTopLikes)
        }

        flakySafely(7_000) {
            KComposeRatingScreen(composeTestRule).clickRatingTabBar()
        }
        flakySafely(7_000) {
            KComposeRatingScreen(composeTestRule).clickCatInRating(numberPositionInTopLikes)
        }
        flakySafely(7_000) {
            WireMockDetailScreen().stubMockDescriptionCatDetail()
        }

        KComposeDetailCatScreen(composeTestRule).apply{
            clickEditButton()
            editDescription(textDescription)
            clickSaveButton()
        }

        flakySafely(10_000) {
        WireMockDetailScreen().verifyMockDescriptionCatDetail(
            idCatInPositionInTopLikes,
            textDescription
        )
        }
    }
}