import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ExcelConfg;
import utilities.ExcelReader;

import java.io.IOException;

public class createBooking {

   /* @BeforeClass
    public void setup()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://restful-booker.herokuapp.com/");
        requestSpecBuilder.setBasePath("booking");
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
    }*/

    @DataProvider(name="createBooking")
    public static Object[][] loginValidationData() throws IOException {
        return new ExcelReader().getExcelData(ExcelConfg.Path_ExcelSheets + ExcelConfg.createbooking, 0, true);
    }

    @Test(dataProvider = "getDataForCreateBooking")
    public void createBooking(String firstName, String lastName, String totalPrice, String depositPaid, String checkIn, String checkOut, String addNeeds, String scenarioName) {


    }}
