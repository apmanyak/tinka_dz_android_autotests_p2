package ru.tinkoff.fintech.meowle.screens.noCompose

import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.tinkoff.fintech.meowle.R


class KDetailCatScreen: BaseScreen() {
    override val layoutId: Int = R.layout.details_fragment_redesign
    private val catNameText = KTextView { withId(R.id.cat_name) }
    private val catDescriptionText = KTextView { withId(R.id.cat_description) }
    private val catLikesNumber = KTextView { withId(R.id.tw_likes) }
    private val catLikeButton = KButton { withId(R.id.ib_like) }
    private val catEditButton = KButton { withId(R.id.btn_edit) }
    private val catEditDescription = KTextInputLayout { withId(R.id.til_desc) }
    private val saveButton = KView { withId(R.id.confirm_button) }

    fun checkDataInCatDetail() {
        val nameCatDefault = "Гречка"
        val descriptionCatDefault = "Описание кота"
        val likeCatDefault = "3"
        catNameText.hasText(nameCatDefault)
        catDescriptionText.hasText(descriptionCatDefault)
        catLikesNumber.hasText(likeCatDefault)
    }

    fun clickLikeButton() {
        catLikeButton.click()
    }

    fun clickEditButton() {
        catEditButton.click()
    }

    fun editDescription(textDescription: String) {
        catEditDescription.edit.replaceText(textDescription)
    }

    fun clickSaveButton() {
        saveButton.click()
    }
}