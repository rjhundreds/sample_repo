package stepdefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class StepDef_API {
	String baseUri = "https://api.tmsandbox.co.nz/v1/Categories/6329/Details.json?catalogue=false";
	RequestSpecification _REQ_SPEC;
	Response _RESP;
	ValidatableResponse _VALIDATABLE_RESP;
	
	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByName("js");

	Scenario scn;
	@Before
	public void BeforeHook(Scenario s) {
		this.scn = s;
	}
	
	@Given("I have API")
	public void i_have_API() {
		_REQ_SPEC = given().baseUri(baseUri);
		
	}

	@When("I send request at API")
	public void i_send_request_at_API() {
		_RESP = _REQ_SPEC.when().get();
		scn.write("Response returned as: " + _RESP.asString());
	}

	@Then("status code comes as {int}")
	public void status_code_comes_as(Integer int1) {
		_VALIDATABLE_RESP = _RESP.then();
		_VALIDATABLE_RESP.statusCode(int1);
	}

	@Then("json body contain value (.*?) in path (.*?)")
	public void json_body_contain_value_in_path(String value, String path) throws ScriptException {
		_VALIDATABLE_RESP.body(path, equalTo(engine.eval(value)));
		
	}
	
	@Then("I validate (.*?) element with (.*?) equals (.*?) has a (.*?) that contains the text (.*?)")
	public void i_validate_element_with_equals_has_a_that_contains_the_text(String obj, String obj2, String obj3, String obj4, String value) throws ScriptException {
		_VALIDATABLE_RESP.body(""+obj+".find {it."+obj2+" == '"+obj3+"'}."+obj4+"", equalTo(engine.eval(value)));
	}

}
