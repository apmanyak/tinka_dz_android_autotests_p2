package ru.tinkoff.fintech.meowle.wiremock

import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching
import com.github.tomakehurst.wiremock.client.WireMock.equalToJson

class WireMockDetailScreen {

    fun stubMockLikeCatDetail() {
        val idCat = 15678
        stubFor(
            post("/api/likes/cats/$idCat/likes")
                .willReturn(
                    ok()
                )
        )
    }

    fun verifyMockCatLike() {
        val idCat = 15678
        verify(
            postRequestedFor(
                urlPathMatching("/api/likes/cats/$idCat/likes")
            )
                .withRequestBody(equalToJson("{\"dislike\":false,\"like\":true}"))
        )
    }

    fun stubMockDescriptionCatDetail() {
        stubFor(
            post("/api/core/cats/save-description")
                .willReturn(
                    ok()
                )
        )
    }

    fun verifyMockDescriptionCatDetail(id: Int, description: String) {
        verify(
            postRequestedFor(
                urlPathMatching("/api/core/cats/save-description")
            )
                .withRequestBody(equalToJson("{\"catDescription\":\"$description\",\"catId\":$id}"))
        )
    }
}