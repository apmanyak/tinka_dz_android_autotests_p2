package ru.tinkoff.fintech.meowle.rules

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class PreferenceRuleCompose : TestRule {
    override fun apply(base: Statement, p1: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                putAuthParam()
                putUrl()
                mode()
                base.evaluate()
                cleanPrefs()
            }
        }
    }

    private fun putAuthParam() {
        InstrumentationRegistry.getInstrumentation().targetContext
            .getSharedPreferences("meowle", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("auth", true)
            .commit()
    }

    private fun mode() {
        InstrumentationRegistry.getInstrumentation().targetContext
            .getSharedPreferences("meowle", Context.MODE_PRIVATE)
            .edit()
            .putString("launch_mode", "COMPOSE")
            .commit()
    }

    private fun putUrl() {
        InstrumentationRegistry.getInstrumentation().targetContext
            .getSharedPreferences("meowle", Context.MODE_PRIVATE)
            .edit()
            .putString("url", "http://localhost:5000")
            .commit()
    }

    private fun cleanPrefs() {
        InstrumentationRegistry.getInstrumentation().targetContext
            .getSharedPreferences("meowle", Context.MODE_PRIVATE)
            .edit()
            .clear()
            .commit()
    }
}

