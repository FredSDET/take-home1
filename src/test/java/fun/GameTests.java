package fun;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class GameTests {
    @Test
    void testDefaultGame() {
        RestAssured.baseURI = "http://localhost:8080/game";
        Response response = given().when().get("/game").then().extract().response();
        assertEquals(200, response.getStatusCode());
        assertEquals("Playing Sudoku is fun!", response.jsonPath().getString("text"));
        assertNotNull(response.jsonPath().get("id"));
        assertTrue(response.jsonPath().get("id") instanceof Integer);
    }

    @Test
    void testSpecificGame() {
        RestAssured.baseURI = "http://localhost:8080";
        Response response = given().queryParam("name", "Chess").when().get("/game").then().extract().response();
        assertEquals(200, response.getStatusCode());
        assertEquals("Playing Chess is fun!", response.jsonPath().getString("text"));
        assertNotNull(response.jsonPath().get("id"));
        assertTrue(response.jsonPath().get("id") instanceof Integer);
    }
}
