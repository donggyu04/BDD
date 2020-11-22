package com.training.bdd.tests;

import com.training.bdd.SpringIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
// TODO refer to https://thepracticaldeveloper.com/cucumber-tests-spring-boot-dependency-injection/
public class StepDefsIntegrationTest extends SpringIntegrationTest {

//    @When("^the client calls /baeldung$")
//    public void the_client_issues_POST_hello() throws Throwable {
//        executePost();
//    }

//    @Given("^the client calls /hello$")
//    public void the_client_issues_GET_hello() throws Throwable {
//        executeGet("http://localhost:8082/hello");
//    }

    @When("^the client calls /version$")
    public void the_client_issues_GET_version() {
        executeGet("http://localhost:8080/version");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getClientHttpResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) {
        assertThat(latestResponse.getBody(), is(version));
    }

}
