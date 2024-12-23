package org.qe;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class DefaultReqResBuilder {
    RequestSpecBuilder reqSpecB;
    ResponseSpecBuilder resSpecB;

   @BeforeClass
   public void specBuilder(){
       reqSpecB = new RequestSpecBuilder();
               reqSpecB.setBaseUri("https://graphql-api-ppql.onrender.com/graphql").
               setContentType(ContentType.JSON).
               log(LogDetail.HEADERS);
       resSpecB = new ResponseSpecBuilder();
       resSpecB.
               log(LogDetail.ALL).
               expectBody("data.getAllUsers.firstName",hasItems("Wilbur","Oriana","Brade","Sebastian"));
       RestAssured.responseSpecification = resSpecB.build();
       RestAssured.requestSpecification = reqSpecB.build();

   }


    @Test
    public void ValidateMultipleContent()
    {
        String payload = "{\"query\":\"query{\\n  getAllUsers{\\n    firstName,id\\n  }\\n}\",\"variables\":null}";
        Response response = given().
                body(payload).post();
        System.out.println(response.toString());
    }

}
