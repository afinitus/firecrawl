package com.firecrawl;

import com.firecrawl.client.FirecrawlClient;
import com.firecrawl.models.CreditUsage;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class CreditUsageMockTest {

    @Test
    void testGetCreditUsageReadsDataEnvelope() throws Exception {
        byte[] body = "{\"success\":true,\"data\":{\"remainingCredits\":73,\"planCredits\":100}}"
                .getBytes(StandardCharsets.UTF_8);
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/v2/team/credit-usage", exchange -> {
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.start();
        try {
            CreditUsage usage = FirecrawlClient.builder()
                    .apiKey("fc-test-key")
                    .apiUrl("http://127.0.0.1:" + server.getAddress().getPort())
                    .maxRetries(0)
                    .build()
                    .getCreditUsage();

            assertEquals(73, usage.getRemainingCredits());
            assertEquals(100, usage.getPlanCredits());
        } finally {
            server.stop(0);
        }
    }
}
