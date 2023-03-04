package de.markushimmel;

import static io.restassured.RestAssured.given;

import java.util.Base64;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ResourceTest {

    @ParameterizedTest
    @ValueSource(ints = { 5_000, 5_000_000 })
    void sendingRequests_SHOULD_work(int size) {
        given() //
                .body(String.format("{\"data\":\"%s\"}", getBase64String(size)))
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when().post("/resource/create") //
                .then()
                .statusCode(200);
    }

    private static String getBase64String(int size) {
        return Base64.getEncoder().encodeToString(new byte[size]);
    }
}
