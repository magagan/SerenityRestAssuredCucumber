package com.testapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.testapp.model.StudentClass;
import com.testapp.utils.ReuseableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {
	
	@Step("Creating student with firstName:{0}, lastName:{1}, email:{2},programme{3} ,courses:{4}")
	public ValidatableResponse createStudent(String firstName,String lastName, String email, String programme,
			List<String> courses){
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
	
	return	SerenityRest.rest().given()
		.spec(ReuseableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();		
	}
	
	@Step("Getting the student information with firstName: {0}")
	public HashMap<String,Object> getStudentInfoByFirstName(String firstName){
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";

	return	SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()
		.path(p1+firstName+p2);
	}	
	
	@Step("Updating student information with studnetID: {0} firstName:{1}, lastName:{2}, email:{3},programme: {4} ,courses:{5}")
	public  ValidatableResponse updateStudent(int studentid, String firstName,
			String lastName, String email, String programme,
			List<String> courses) {

		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);

		return SerenityRest.rest().given()
				.spec(ReuseableSpecifications.getGenericRequestSpec()).log().all()
				.when().body(student).put("/" + studentid).then();
	}
	
	@Step("Deleting student information with ID: {0}")
	public  void deleteStudent(int studentId) {
		SerenityRest.rest().given().when().delete("/" + studentId);
	}
	

	@Step("Getting information of the student with ID: {0}")
	public ValidatableResponse getStudentById(int studentId){
		return 
		SerenityRest
		.rest()
		.given()
		.when()
		.get("/" + studentId).then();
		
	}
	
	@Step ("Getting information of the student Email: {0}")
	public HashMap<String, Object> getStudentInfoByEmailId(String email) {

		String p1 = "findAll{it.email=='";
		String p2 = "'}.get(0)";
		return SerenityRest
				.rest().given().when().get("/list").then().extract()
				.path(p1 + email + p2);
	}
	
	@Step("Getting Details name: {0}") 
	public String getDetailsByName(String Name) {
		//String p1 = "findAll{it.Name=='";
		//String p2 = "'}.get(0)";
		
		//String name = Name;
		System.out.println(SerenityRest.rest().given().when().get("/v1/Categories/6327/Details.json").then().extract().jsonPath().getString("Name"));
		//return SerenityRest
		//		.rest().given().when().get("/v1/Categories/6327/Details.json").then().extract()
		//		.path("findAll.it.Name=='Carbon credits'.get(0)");
		//System.out.println(SerenityRest.rest().given().when().get("/v1/Categories/6327/Details.json").then().extract().asString("{Name : 'Carbon credits'}.get(0)"));
		//return SerenityRest.rest().given().when().get("/v1/Categories/6327/Details.json").then().extract().path("{Name : 'Carbon credits'}.get()");
		
		return SerenityRest.rest().given().when().get("/v1/Categories/6327/Details.json").then().extract().jsonPath().getString("Name");
	}
	
	@Step("Getting Details CanReslit value: {0}") 
	public boolean getDetailsByCanRelist(Boolean param) {
//		String p1 = "findAll{it.CanRelist=='";
//		String p2 = "'}.get(0)";
//		
//		return SerenityRest
//				.rest().given().when().get("/v1/Categories/6327/Details.json").then().extract()
//				.path(p1 + param + p2);
		return SerenityRest.rest().given().when().get("/v1/Categories/6327/Details.json").then().extract().jsonPath().getBoolean("CanRelist");
	}
	

}
