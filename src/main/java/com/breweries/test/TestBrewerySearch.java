package com.breweries.test;


import com.breweries.model.Brewery;
import com.breweries.util.Utils;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Test
public class TestBrewerySearch {

    private String FILE_PATH = "/testData/brewery/%s.json";


    @BeforeClass
    public void configureRestAssured() {
        RestAssured.baseURI = "https://api.openbrewerydb.org/breweries";
        RestAssured.responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @DataProvider(name = "breweriesSearch")
    public Object[][] createTestDataRecords() {
        return new Object[][]{
                {"diving_dog"},
                {"micro"},
                {"1802"},
                {"oakland"},
                {"-2110"},
                {"5103061914"},
                {"http://www.divingdogbrew.com"},
                {"united states"},
        };
    }


    @Test(dataProvider = "breweriesSearch")
    public void searchBreweries(String queryParam) {
        List<Brewery> searchedBreweries = Utils.parseJsonIntoObjectsList(given().
                queryParams("query", queryParam).
                when().
                get("/search").
                then().
                extract().
                asString(), Brewery.class);
        Brewery expectedBrewery = Utils.parseJsonIntoObject(Utils.getJsonStringFromFile(String.format(FILE_PATH, "brewery")), Brewery.class);
        assert searchedBreweries != null;
        assert searchedBreweries.contains(expectedBrewery);
    }
}
