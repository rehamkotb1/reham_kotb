package TestScripts;


import Actions.BookingDates;
import Actions.CreateProductActions;
import Actions.CreateProductRequest;
import Utilities.ExcelUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CreateProduct {


    @DataProvider
    public Object[][] getDataForCreateBooking() throws EncryptedDocumentException, IOException {
        ExcelUtils excelUtils = new ExcelUtils("createProduct.xlsx");
        return excelUtils.getDataForDataProvider("Sheet1");
    }

    @Test(dataProvider = "getDataForCreateBooking")
    public void createBooking(String firstName, String lastName, String totalPrice, String depositPaid, String checkIn, String checkOut, String addNeeds, String scenarioName)
    {


        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setFirstname(firstName);
        createProductRequest.setLastname(lastName);
        createProductRequest.setAdditionalneeds(addNeeds);
        createProductRequest.setDepositpaid(Boolean.parseBoolean(depositPaid));
        createProductRequest.setTotalprice(Integer.parseInt(totalPrice));

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin(checkIn);
        bookingdates.setCheckout(checkOut);
        createProductRequest.setBookingdates(bookingdates);

        CreateProductActions createBookingActions = new CreateProductActions();
        createBookingActions.createBooking(createProductRequest);

        Assert.assertTrue(createBookingActions.verifyStatusCodeAs(200));
        Assert.assertTrue(createBookingActions.verifyBookingIdGenerated());

    }



}
