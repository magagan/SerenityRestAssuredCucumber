package com.testapp.cucumber.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.testapp.cucumber.serenity.StudentSerenitySteps;
import com.testapp.utils.TestUtils;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class StudentSteps {
	static String email=null;
	
	@Steps
	StudentSerenitySteps steps;
	
	@When("^User sends a GET request in the list endpoint, they must get a valid status code 200$")
	public void verify_status_code_200_for_listendpoint(){
		SerenityRest.rest()
		.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	
	@When("^I create a new student by providing the information firstName (.*) lastName (.*) email (.*) programme (.*) courses (.*)$")
	public void createStudent(String firstName,String lastName,String _email,String programme,String course){
		List<String> courses = new ArrayList<>();
		courses.add(course);
		 email =TestUtils.getRandomValue()+ _email;
		
		 steps.createStudent(firstName, lastName, email, programme, courses)
		 .assertThat()
		 .statusCode(201);
		
	}
	
	@Then("^I verify that the student with (.*) is created$")
	public void verifyStudent(String emailId){
		HashMap<String, Object> actualValue=	steps.getStudentInfoByEmailId(email);
		assertThat(actualValue, hasValue(email));
	}
	
	@When("^User sends a GET request in the category details endpoint, they must get a valid status code 200$")
	public void verify_status_code_200_for_category_details(){
		SerenityRest.rest()
		.given()
		.when()
		.get("/v1/Categories/6327/Details.json")
		.then()
		.statusCode(200);
	}
	
	@Then("^I verify that the name is (.*)$")
	public void verify_name_details(String name){
		String actualValue = steps.getDetailsByName(name);
		assertEquals(actualValue, name);
	}
	
	@And("^I verify the CanRelist is (.*)$")
	public void verify_canreslit_boolean_value_true(Boolean canrelist){
		boolean actualValue = steps.getDetailsByCanRelist(canrelist);
		assertEquals(actualValue, canrelist);
	}
}
