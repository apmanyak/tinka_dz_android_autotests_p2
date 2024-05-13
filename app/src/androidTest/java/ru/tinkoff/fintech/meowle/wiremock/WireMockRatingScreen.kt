package ru.tinkoff.fintech.meowle.wiremock

import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.aResponse

class WireMockRatingScreen {

    fun stubMockRating() {
        stubFor(
            get("/api/likes/cats/rating")
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("rating_response.json"))
                )
        )
    }

    fun stubMockCatDetail(id: Int) {
        stubFor(
            get("/api/core/cats/get-by-id?id=$id")
                .willReturn(
                    ok()
                )
        )
        stubFor(
            get("/api/photos/cats/$id/photos")
                .willReturn(
                    ok()
                )
        )
    }
}