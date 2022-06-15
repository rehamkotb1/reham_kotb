import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Specifications {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    String jsonString = "{\r\n" +
            "    \"firstname\" : \"reham\",\r\n" +
            "    \"lastname\" : \"Ahmed\",\r\n" +
            "    \"totalprice\" : 111,\r\n" +
            "    \"depositpaid\" : true,\r\n" +
            "    \"bookingdates\" : {\r\n" +
            "        \"checkin\" : \"2021-01-01\",\r\n" +
            "        \"checkout\" : \"2022-01-01\"\r\n" +
            "    },\r\n" +
            "    \"additionalneeds\" : \"Breakfast\"\r\n" +
            "}";

    @BeforeClass
    public void setup()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://restful-booker.herokuapp.com/");
        requestSpecBuilder.setBasePath("booking");
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectResponseTime(Matchers.lessThan(10000L));
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void createBookingAgain()
    {
        RestAssured
                .given(requestSpecification,responseSpecification)
                .post();

    }

    @Test
    public void createBookingAgain1()
    {
        RestAssured
                .given()
                .spec(requestSpecification)
                .body(jsonString)
                .when()
                .post()
                .then()
                .spec(responseSpecification);
    }
}
