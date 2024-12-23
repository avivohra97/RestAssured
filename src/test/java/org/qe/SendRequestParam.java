package org.qe;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class SendRequestParam {
    RequestSpecBuilder reqSpecB;
    ResponseSpecBuilder resSpecB;
    ResponseSpecification resSpec;
   @BeforeClass
   public void specBuilder(){
       reqSpecB = new RequestSpecBuilder();
               reqSpecB.setBaseUri("https://graphql-api-ppql.onrender.com/graphql").
               setContentType(ContentType.JSON).
               log(LogDetail.HEADERS);
       resSpecB = new ResponseSpecBuilder();
       resSpecB.expectBody("data.getAllUsers.firstName",hasItems("Wilbur","Oriana","Brade","Sebastian")).
               log(LogDetail.ALL);
       resSpec = resSpecB.build();
       RestAssured.requestSpecification = reqSpecB.build();

   }

    //In below test method we will validate for multiple FirstName present in response and
    // in similar way we can validate other fields in response
    @Test
    public void singleParam()
    {
        given().
                baseUri("https://petstore.swagger.io").
                when().log().all().
                basePath("v2/pet/findByStatus").
                param("status","sold").
                get("").
                then().
                log().all().
                assertThat().
                statusCode(200);

    }
}
