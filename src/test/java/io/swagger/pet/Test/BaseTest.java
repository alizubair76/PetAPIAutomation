package io.swagger.pet.Test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseTest {
	String baseUrl = "https://petstore.swagger.io";

	@BeforeSuite(alwaysRun = true)
	public void initialize() {
		RestAssured.baseURI = baseUrl;

	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
	}

}
