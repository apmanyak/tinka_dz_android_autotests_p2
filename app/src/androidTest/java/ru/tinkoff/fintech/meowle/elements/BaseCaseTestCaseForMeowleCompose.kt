package ru.tinkoff.fintech.meowle.elements

import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

open class BaseCaseTestCaseForMeowleCompose: TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
)  {
}