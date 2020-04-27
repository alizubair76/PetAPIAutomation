package io.swagger.pet.Action;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.swagger.pet.Model.Pet;

public class PetAction {
	public static Response uploadImage(String petId, File fileToUpload) {
		String api = "/pet/{petId}/uploadImage";

		api = api.replace("{petId}​", petId);

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryGSbciUbQw2H99PfT");

		return request.multiPart(fileToUpload).when().post(api, petId);
	}

	public static Response save(String body) {
		String api = "/pet";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		return request.body(body).post(api);
	}

	public static Response update(String body) {
		String api = "/pet";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		return request.body(body).put(api);
	}

	public static Response findByStatus(String status) {
		String api = "/pet/findByStatus";
		return get(api + "?" + "status=" + status);
	}

	public static Response findById(String petId) {
		String api = "/pet";
		return get(api + "/" + petId);
	}

	public static Response deleteById(String petId) {
		String api = "/pet";
		return delete(api + "/" + petId);
	}

	public static Pet[] getRandomPets() {
		Response response = PetAction.findByStatus("available");
		return response.as(Pet[].class);
	}

	public static Pet getRandomPet() {
		return PetAction.getRandomPets()[0];
	}

	public static Long getRandomPetId() {
		return PetAction.getRandomPets()[0].getId();
	}

	public static Response updateNameAndStatus(String existingPetId, String params) {
		String api = "/pet";
		api = api + "/" + existingPetId;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/x-www-form-urlencoded");

		return request.body(params).post(api);
	}
}
