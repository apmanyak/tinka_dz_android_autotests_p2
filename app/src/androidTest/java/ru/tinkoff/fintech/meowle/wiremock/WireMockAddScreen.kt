package ru.tinkoff.fintech.meowle.wiremock

import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching
import com.github.tomakehurst.wiremock.client.WireMock.equalToJson

class WireMockAddScreen {

    fun stubMockAddCat() {
        stubFor(
            post("/api/core/cats/add")
                .willReturn(
                    ok()
                )
        )
    }

    fun verifyMockAddCat(name: String, gender: String, description: String) {
        var genderInText = ""
        when (gender){
            "Муж." ->   genderInText = "male"
            "Жен." ->  genderInText = "female"
            "Универс." ->  genderInText = "unisex"
        }
        verify(
            postRequestedFor(
                urlPathMatching("/api/core/cats/add")
            )
                .withRequestBody(
                    equalToJson("{\"cats\":[{\"description\":\"$description\",\"gender\":\"$genderInText\",\"name\":\"$name\"}]}")
                )
        )
    }
}