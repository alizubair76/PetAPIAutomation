package io.swagger.pet.Test;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.swagger.pet.Action.PetAction;
import io.swagger.pet.Helper.Util;
import io.swagger.pet.Model.Pet;

public class SmokeTest extends BaseTest {
	String basePath = "/v2";

	@BeforeClass
	public void setBasePath() {
		RestAssured.basePath = basePath;
	}

	@Test
	public void testFindByStatus() {
		String status = "sold";

		Response response = PetAction.findByStatus(status);

		response.then().statusCode(200);
	}

	@Test
	public void testFindById() {
		String existingPetId = PetAction.getRandomPetId().toString();

		Response response = PetAction.findById(existingPetId);

		response.then().statusCode(200);
	}

	@Test
	public void testAddNewPet() {
		String status = "available";

		String body = "{\"id\":%d,\"category\":{\"id\":2,\"name\":\"%s\"},\"name\":\"doggie\",\"photoUrls\":[\"%s\"],\"tags\":[{\"id\":1,\"name\":\"%s\"}],\"status\":\"%s\"}";
		body = String.format(body, Util.getRandomNumber(), Util.getRandomString(), Util.getRandomString(),
				Util.getRandomString(), status);
		System.out.println(body);

		Response response = PetAction.save(body);

		response.then().statusCode(200);

		Assert.assertEquals(response.asString(), body);
	}

	@Test
	public void testUpdatePet() {

		Pet randomPet = PetAction.getRandomPets()[0];

		String status = "available";
		String body = "{\"id\":%s,\"category\":{\"id\":2,\"name\":\"%s\"},\"name\":\"doggie\",\"photoUrls\":[\"%s\"],\"tags\":[{\"id\":1,\"name\":\"%s\"}],\"status\":\"%s\"}";
		body = String.format(body, randomPet.getId(), Util.getRandomString(), Util.getRandomString(),
				Util.getRandomString(), status);
		System.out.println(body);

		Response response = PetAction.update(body);

		response.then().statusCode(200);

		Assert.assertEquals(response.asString(), body);
	}

	@Test
	public void testUploadImage() {
		File testUploadFile = new File(getClass().getClassLoader().getResource("test.txt").getFile());

		String existingPetId = PetAction.getRandomPetId().toString();

		Response response = PetAction.uploadImage(existingPetId, testUploadFile);

		response.then().statusCode(200);
	}

	@Test
	public void updateName() {
		String existingPetId = PetAction.getRandomPetId().toString();

		String newName = Util.getRandomString();
		String params = "name=" + newName;

		Response response = PetAction.updateNameAndStatus(existingPetId, params);

		response.then().statusCode(200);
	}

	@Test
	public void updateStatus() {
		String existingPetId = PetAction.getRandomPetId().toString();

		String newStatus = "sold";
		String params = String.format("status=%s", newStatus);

		Response response = PetAction.updateNameAndStatus(existingPetId, params);

		response.then().statusCode(200);
	}

	@Test
	public void updateNameAndStatus() {
		String existingPetId = PetAction.getRandomPetId().toString();

		String newName = Util.getRandomString();
		String newStatus = "available";
		String params = String.format("name=%s&status=%s", newName, newStatus);

		Response response = PetAction.updateNameAndStatus(existingPetId, params);

		response.then().statusCode(200);
	}

	@Test
	public void testDeleteById() {
		String existingPetId = PetAction.getRandomPetId().toString();

		Response response = PetAction.deleteById(existingPetId);

		response.then().statusCode(200);
	}
}
