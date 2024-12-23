package org.qe;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.*;
import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class GetTest {

    @Test
    public void getCallWithoutBody(){
        given().
                baseUri("https://petstore.swagger.io/v2").
        when().
                get("/store/inventory").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
    @Test
    public void getCallWithBody(){
            String response = given().header("content-type","application/json")
                    .body("{\"query\":\"query{\\n  getAllUsers{\\n    firstName,id\\n  }\\n}\",\"variables\":null}")
                    .when().post("https://graphql-api-ppql.onrender.com/graphql")
                    .then().extract().response().asString();
            System.out.println(response);
            // We will create JsonPath Object to fetch data
            JsonPath path = new JsonPath(response);
            Object firstName = path.get("data.getAllUsers.firstName");
            //Print All the First Name
            System.out.println(firstName);

    }

    @Test
    public void getCallWithBodyResp(){
        Response response = given().header("content-type","application/json")
                .body("{\"query\":\"query{\\n  getAllUsers{\\n    firstName,id\\n  }\\n}\",\"variables\":null}")
                .when().post("https://graphql-api-ppql.onrender.com/graphql")
                .then().extract().response();
        System.out.println(response);

        // We will create JsonPath Object to fetch data
        JsonPath path = new JsonPath(response.asString());
        Object firstName = path.getList("data.getAllUsers.firstName");
        //Print All the First Name
        System.out.println(firstName);

        System.out.println(JsonPath.from(response.asString()).getString("data.getAllUsers.firstName[0]"));

    }

    //In below test method we will validate for multiple FirstName present in response and
    // in similar way we can validate other fields in response
    @Test
    public void ValidateMultipleContent()
    {
        String response = given().header("content-type","application/json")
                .body("{\"query\":\"query{\\n  getAllUsers{\\n    firstName,id\\n  }\\n}\",\"variables\":null}")
                .when().log().all().post("https://graphql-api-ppql.onrender.com/graphql")
                .then().log().all()
                .assertThat()
                .body("data.getAllUsers.firstName",hasItems("Wilbur","Oriana","Brade","Sebastian"))
                .statusCode(200).toString();
        System.out.println(response);
    }

}
