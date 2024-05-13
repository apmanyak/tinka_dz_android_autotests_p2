package ru.tinkoff.fintech.meowle.wiremock

import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.aResponse

class WireMockSearchScreen {
    fun stubMockSearch() {
        stubFor(
            post("/api/core/cats/search")
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("search_response.json"))
                )
        )
    }
    fun stubMockFirstCatDetail() {
        val idCat = 14096
        stubFor(
            get("/api/core/cats/get-by-id?id=$idCat")
                .willReturn(
                    ok()
                )
        )

        stubFor(
            get("/api/photos/cats/$idCat/photos")
                .willReturn(
                    ok()
                )
        )
    }
}