package com.e2e.utils;

import com.e2e.pages.BasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.e2e.utils.Base64EncryptionUtil.decryption;

public abstract class TwoFA extends BasePage {
    public static Response post;
    public static String smsCode;


    public static String authentication() throws InterruptedException {

        try {
            RestAssured.baseURI = "https://api.test.miles-and-more.com";
            RestAssured.useRelaxedHTTPSValidation();
            String mmg_Reset_PIN = "Nzc4ODE="; // Provide the actual key value here
            String mmg_Login_SCN1 = "OTkyMDA2MTEwNjUyMDUx";

            post = RestAssured.given().header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Origin", "https://net-uat2.miles-and-more.com").header("x-logintype", "USER_PWD")
                    .header("x-scope", "AUTHENTICATED")
                    .header("X-Authorization",
                            "Bearer U2FsdGVkX19OqWO1/Yr6B1otxCKaTc67UXUN6J56KYeO/0oz9uHZmEC6jcOy77P5RYXIU5pMaRGYMlDGgTsZArxw6auY7XaR0FvDkUktvhdXg84HSUFdcwldBUJDZb+j")
                    .contentType("application/x-www-form-urlencoded").formParam("username", decryption(mmg_Login_SCN1))
                    .formParam("password", decryption(mmg_Reset_PIN)).formParam("grant_type", "password").when()
                    .post("/oauth2/token");
            int StatusCode = post.statusCode();
            log.info(StatusCode);
            post.prettyPrint();
            String cookieValue = post.headers().toString().split("Path=")[0].split("Set-Cookie=")[1];
            String custNo = post.getBody().jsonPath().get("custNo");
            String cookieValueUpdated = "STATInfo=MAM_MEMBER|" + custNo + "; " + cookieValue
                    + " e4d78a09996271d7183ddcbc007b5167=ebe7a3b973f09d9096f6869f97141b1c";
            post = RestAssured.given().header("Content-Type", "text/plain")
                    .header("Origin", "https://net-uat2.miles-and-more.com").header("Cookie", cookieValueUpdated).when()
                    .post("/validation/v1/testing/" + custNo + "/sms");

            post.statusCode();
            post.prettyPrint();
            smsCode = post.getBody().jsonPath().get("sms_code").toString();
            if (smsCode.length() != 6) {
                smsCode = "0" + smsCode;
            }
            log.info(smsCode);


        } catch (Exception e) {
            e.printStackTrace();

        }
        return smsCode;
    }


}
