package org.example.utils;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.annotations.BeforeMethod;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class MockInterceptor {
    private WireMockServer wireMockServer;
    private final int MOCK_PORT = 8081;
    private final String baseUrl = "http://localhost:" + MOCK_PORT;

    @BeforeMethod // TestNG annotation to setup the mock before every test run
    @Test
    public void startMockServer() {
        // 1. Initialize and start the local WireMock server
        wireMockServer = new WireMockServer(MOCK_PORT);
        wireMockServer.start();
        WireMock.configureFor("localhost", MOCK_PORT);

        // 2. Define your expected request and fixed response payload
        String expectedRequestPayload = "{\"name\": \"John Doe\", \"role\": \"QA\"}";
        String exactResponsePayload = "{\"status\": \"Success\", \"id\": 101, \"createdAt\": \"2026-10-06\"}";

        String a = WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/users"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.equalToJson(expectedRequestPayload))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(exactResponsePayload))).toString();
System.out.println("Mock response "+a);
    }

}
