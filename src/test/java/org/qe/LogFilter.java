package org.qe;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class LogFilter {
    @Test
    public void getCallWithoutBody() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File("restAssured.log"));
        given().
                baseUri("https://petstore.swagger.io/v2").
                filter(new RequestLoggingFilter(ps)).
//                filter(new ResponseLoggingFilter(ps)).
        /*
        * can also be added to req res spec builder
        * resBuilder = new builder().
        * addFilter(new ResponseLoggingFilter(LogDetail.ALL,ps))
        *
        * */
                filter(new ResponseLoggingFilter(LogDetail.ALL,ps)).
                when().
                get("/store/inventory").
                then().
//                log().all().

                assertThat().
                statusCode(200);
    }
}
