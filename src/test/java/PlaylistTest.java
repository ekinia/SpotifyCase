
import io.restassured.RestAssured;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;


public class PlaylistTest {

    String authToken = "BQCNVgZDyY9ck6aLq_kitaQC1py2kjsHgZfViaDfJQDDLaoxvSKUDgXwSclc6kFXqFKrHXD_tASvXZmjm7qVUOdsZ1vq9NufLuvDZdnEwGjufRo5c-B2BwikXaWvQYQP5s8pLOJblAkUCelAs4_aPU9sSzkS5CsA6rdsVcFtSF8H1qnOxxpzQ2qrVjKsSt2q0JKoXs8Dr5HpwhQhJ4dau1L8yMHQZffXUBSV2xEisERzDAeYcNsniWmR9Qmsg57jHY9QAPPGQ0YIB7dMwPasX98";


    @BeforeMethod
    public void StartUp() {
        RestAssured.baseURI = "https://api.spotify.com/v1";

    }

    @Test
    public void SpotifyApiTest() throws IOException {
        String name = "Teoman";
        Assert.assertEquals(searchArtistAPI(name), name);

        //make an assertion to check name


    }

    public String searchArtistAPI(String name) {
        Response response =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .accept("application/json")
                        .header("Authorization", "Bearer " + authToken)
                        .queryParam("q", name)
                        .queryParam("market", "US")
                        .queryParam("limit", "1")
                        .queryParam("type", "artist")

                        .when()
                        .get("/search")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        ArrayList idList = response.path("artists.items.id");
        ArrayList nameList = response.path("artists.items.name");
        return nameList.get(0).toString();


    }
}
