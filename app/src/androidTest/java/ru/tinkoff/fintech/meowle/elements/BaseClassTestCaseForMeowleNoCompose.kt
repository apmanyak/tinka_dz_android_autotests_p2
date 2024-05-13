package ru.tinkoff.fintech.meowle.elements

import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

open class BaseClassTestCaseForMeowleNoCompose: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 6_000, intervalMs = 250)
        }
    ).apply {
        stepWatcherInterceptors.addAll(listOf(AllureMapperStepInterceptor()))
        testRunWatcherInterceptors.addAll(listOf(DumpViewsInterceptor(viewHierarchyDumper)))
    }
)