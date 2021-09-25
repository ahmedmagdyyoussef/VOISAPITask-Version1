package VOISAPITest;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest {
    @Test  //Verify that API return all Posts
    public void VerifyReturnAllPosts() {
    	Response resposne = RestAssured.given().get("https://jsonplaceholder.typicode.com/posts");
    	String jsonString = resposne.asString();
    	System.out.println(jsonString);
    	SoftAssert softAssertion= new SoftAssert();
		System.out.println("softAssert Method Was Started");
		softAssertion.assertEquals(200, resposne.getStatusCode());
		System.out.println("softAssert Method Was Executed");
		softAssertion.assertEquals(jsonString.contains("laboriosam dolor voluptates"), true);
		softAssertion.assertAll();

    }
    
    @Test // verify that API return specific post
    public void VerifyPostByID() {
    	Response resposne = RestAssured.given().get("https://jsonplaceholder.typicode.com/posts/2");
    	String jsonString = resposne.body().asString();
    	System.out.println(jsonString);
    	SoftAssert softAssertion= new SoftAssert();
		System.out.println("softAssert Method Was Started");
		softAssertion.assertEquals(200, resposne.getStatusCode());
		System.out.println("softAssert Method Was Executed");
		softAssertion.assertEquals(jsonString.contains("est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla"), true);
		softAssertion.assertAll();

    }
    @Test // verify that API return return 404
    public void VerifyPostByIDWithNegativeNumber() {
    	Response resposne = RestAssured.given().get("https://jsonplaceholder.typicode.com/posts/-2");
    	String jsonString = resposne.body().asString();
    	System.out.println(jsonString);
    	SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertEquals(404, resposne.getStatusCode());
		softAssertion.assertEquals(jsonString.contains(""), true);
		softAssertion.assertAll();
    }
    @Test //post new one
    public void CreatNewPost(){
    	  RestAssured.baseURI  = "https://jsonplaceholder.typicode.com/posts"; 
    	  Response res = RestAssured.given()
    	     .contentType("application/json")
    	     .body("{\"title\": \"foo\",\n"
    	     		+ "    \"body\": \"bar\",\n"
    	     		+ "    \"userId\": 213123123123}")
    	     .when()
    	     .post("");
    	  String body = res.getBody().asString();
  	   	  assertEquals(201, res.getStatusCode());
    	  System.out.println(body + " " + res.statusCode());
    	}

    @Test //post new one without data -should fail and return no 204: no content
    public void CreatNewPostWithOutData(){
	  RestAssured.baseURI  = "https://jsonplaceholder.typicode.com/posts"; 
	  Response res = RestAssured.given()
	     .contentType("application/json")
	     .when()
	     .post("");
	  String body = res.getBody().asString();
	   	  assertEquals(204, res.getStatusCode());
	  System.out.println(body + " " + res.statusCode());
	}
}
