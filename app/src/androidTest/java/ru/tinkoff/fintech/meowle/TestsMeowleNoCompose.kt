package ru.tinkoff.fintech.meowle

import androidx.test.ext.junit.rules.activityScenarioRule
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.fintech.meowle.elements.BaseClassTestCaseForMeowleNoCompose
import ru.tinkoff.fintech.meowle.screens.noCompose.KSearchScreen
import ru.tinkoff.fintech.meowle.presentation.view.AuthActivity
import ru.tinkoff.fintech.meowle.rules.PreferenceRuleNoCompose
import ru.tinkoff.fintech.meowle.screens.noCompose.KAddScreen
import ru.tinkoff.fintech.meowle.screens.noCompose.KDetailCatScreen
import ru.tinkoff.fintech.meowle.screens.noCompose.KRatingScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockAddScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockDetailScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockRatingScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockSearchScreen

class TestsMeowleNoCompose : BaseClassTestCaseForMeowleNoCompose() {
    private val port = 5000

    @get:Rule
    val prefs = PreferenceRuleNoCompose()

    @get: Rule
    val mock = WireMockRule(port)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<AuthActivity>()

    @Test
    fun test1SearchCat() = run {
        val nameCat = "Гречка"
        val numberPositionIsSearch = 1

        WireMockSearchScreen().stubMockSearch()

        KSearchScreen().apply{
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

        KSearchScreen().apply {
            findCat(nameCat)
            clickFirstCat()
        }

        KDetailCatScreen().checkDataInCatDetail()
    }

    @Test
    fun test3LikeTopCat() = run {
        val numberPositionInTopDislikes = 1
        val idCatInPositionInTopDislikes = 15678

        WireMockRatingScreen().apply {
            stubMockRating()
            stubMockCatDetail(idCatInPositionInTopDislikes)
        }

        KRatingScreen().apply {
            clickRatingTabBar()
            clickTopDislikesButton()
            checkDislikesRating()
            clickCatInRating(numberPositionInTopDislikes)
        }

        WireMockDetailScreen().stubMockLikeCatDetail()
        KDetailCatScreen().clickLikeButton()
        WireMockDetailScreen().verifyMockCatLike()
    }

    @Test
    fun test4AddCat() = run {
        val catName = "Пипинска"
        val gender = "Муж."
        val textDescription = "Это мое описание"

        WireMockAddScreen().stubMockAddCat()

        KAddScreen().apply {
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

        KRatingScreen().apply {
            clickRatingTabBar()
            clickCatInRating(numberPositionInTopLikes)
        }

        WireMockDetailScreen().stubMockDescriptionCatDetail()

        KDetailCatScreen().apply{
            clickEditButton()
            editDescription(textDescription)
            clickSaveButton()
        }

        WireMockDetailScreen().verifyMockDescriptionCatDetail(
            idCatInPositionInTopLikes,
            textDescription
        )
    }
}