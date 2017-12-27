package it.test

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.common.ConsoleNotifier

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

class GMock {
    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(options()
                .port(8089).notifier(new ConsoleNotifier(true))
                .extensions(Transformer.class))
        wireMockServer.start()

        WireMock wireMock = new WireMock(wireMockServer.port())

        wireMock.register(WireMock.get("/api")
                .willReturn(WireMock.aResponse()))

        wireMock.register(
                WireMock.post("/api")
                        .willReturn(WireMock.aResponse()))
    }
}
