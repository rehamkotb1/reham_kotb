import io.restassured.RestAssured;
import org.testng.annotations.Test;


public class Services extends Specifications{



    //Get service
    @Test
    public void getProducts()
    {
        RestAssured
                .given()
                .log()
                .all()
                .baseUri("localhost:3030/products?$sort[price]=-1")
              //  .basePath("booking/{id}")
                //  .pathParam("id", 90)
                // Hit the request and get response
                .when()
                .get()
                // Validate the response
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void getwithRequestSpecification()
    {
        RestAssured
            //    .given(Specifications,responseSpecification)
                .post()
                // Validate the response
                .then()
                .log()
                .all();
    }
    }

