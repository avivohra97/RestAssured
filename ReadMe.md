# Rest Assured

```java
 //In below test method we will validate for multiple FirstName present in response and
    // in similar way we can validate other fields in response
    @Test
    public void ValidateMultipleContent()
    {
        String response = given().header("content-type","application/json")
                .body("{\"query\":\"query{\\n  getAllUsers{\\n    firstName,id\\n  }\\n}\",\"variables\":null}")
                .when().post("https://graphql-api-ppql.onrender.com/graphql")
                .then()
                .assertThat()
                .body("data.getAllUsers.firstName",hasItems("Wilbur","Oriana","Brade","Sebastian"))
                        .statusCode(200).toString();

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

    
```
## logging
```java

given().log().all(). .. // Log all request specification details including parameters, headers and body
given().log().params(). .. // Log only the parameters of the request
given().log().body(). .. // Log only the request body
given().log().headers(). .. // Log only the request headers
given().log().cookies(). .. // Log only the request cookies
given().log().method(). .. // Log only the request method
given().log().path(). .. // Log only the request path
get("/x").then().log().ifError().
get("/x").then().log().ifStatusCodeIsEqualTo(302). .. // Only log if the status code is equal to 302
get("/x").then().log().ifStatusCodeMatches(matcher). .. // Only log if the status code matches the supplied Hamcrest matcher

given().log().ifValidationFails().


```

## blaclist header
```java
given().config(config().logConfig(logConfig().blacklistHeader("Accept"))).

Request method:   GET
Request URI:    http://localhost:8080/something
Proxy:          <none>
Request params: <none>
Query params:   <none>
Form params:    <none>
Path params:    <none>
Headers:        Accept=[ BLACKLISTED ]
Cookies:        <none>
Multiparts:     <none>
Body:           <none>
```

# headers
```java
given().header("MyHeader", "Something").and(). ..
given().headers("MyHeader", "Something", "MyOtherHeader", "SomethingElse").and(). ..
You can also specify a multi-value headers like this:

given().header("headerName", "value1", "value2"). ..

or create a hashmap or headers object

get("/x").then().assertThat().header("headerName", "headerValue"). ..
get("/x").then().assertThat().headers("headerName1", "headerValue1", "headerName2", "headerValue2"). ..
get("/x").then().assertThat().headers("headerName1", "headerValue1", "headerName2", containsString("Value2")). ..


```
# download file
```
InputStream is = respose.asInputStream
os = new fileos(path)
byte[] b = new byte[is.available()]
is.read(bytes)
os.write(bytes)
os.close()
```
```java
form wencoding

config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
```
