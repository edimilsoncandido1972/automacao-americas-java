package dadosMestres;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class IntegracaoApiTests {
	
	private String baseURI = "http://10.104.29.102/qa/SAP/api/";
	
	@Test 
	public void getPing() {
		String path = "PingService";
		
		given()
			.header("versao-api",1)
		.when()
			.get(baseURI + path)
		.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body(contains("DB Time:"));
		
		
		
		 
	}
	
	@Test
	public void b() {
		
	}
}
