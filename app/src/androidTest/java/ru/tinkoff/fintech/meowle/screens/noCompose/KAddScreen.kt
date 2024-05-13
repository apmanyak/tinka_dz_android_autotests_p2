package ru.tinkoff.fintech.meowle.screens.noCompose

import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.tinkoff.fintech.meowle.R

class KAddScreen: BaseScreen() {
    override val layoutId: Int = R.layout.fragment_add
    private val catNameInput = KEditText { withId(R.id.et_name) }
    private val catDescriptionInput = KEditText { withId(R.id.cat_description) }
    private val addButton = KButton { withId(R.id.btn_add) }
    private val addGender = KTextInputLayout { withId(R.id.til_gender) }

    fun addNameCat(catName: String) {
        catNameInput.replaceText(catName)
    }

    fun addDescriptionCat(textDescription: String) {
        catDescriptionInput.replaceText(textDescription)
    }

    fun clickSelectGenderCat() {
        addGender.click()
    }

    fun clickGenderCat(gender: String) {
        val genderInput = KTextView { withText(gender) }
        genderInput{
            inRoot{isPlatformPopup()}
            click()
        }
    }

    fun clickAddButton() {
        addButton.click()
    }
}