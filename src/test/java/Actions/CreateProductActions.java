package Actions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateProductActions {

    Response response;
    CreateProductResponse createProductResponse;

    public CreateProductResponse createBooking(CreateProductRequest createProductRequest) {
        RestAssured.baseURI = Data.BASE_URL;
        RestAssured.basePath = Data.CREATE_BOOKING_BASEPATH;

        response = RestAssured
                .given()
                .body(createProductRequest)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .post().then().log().all().extract().response();

        createProductResponse = response.as(CreateProductResponse.class);
        return createProductResponse;
    }

    public boolean verifyStatusCodeAs(int expectedStatusCode) {
        int actualCode = response.getStatusCode();
        boolean flag = expectedStatusCode == actualCode;
/*        if(flag)
            test.pass("Status code is as expected as "+ expectedStatusCode);
        else
            test.fail("Status code is not as expected as "+expectedStatusCode); */
        return flag;
    }

    public boolean verifyBookingIdGenerated() {
        int bookingId = createProductResponse.getBookingid();
        boolean flag = bookingId > 0;
/*        if(flag)
            test.pass("Booking id is proper as "+ bookingId);
        else
            test.fail("Booking id is not proper as "+bookingId);*/
        System.out.println("ProductID=   " + bookingId);
        return flag;

    }
}
