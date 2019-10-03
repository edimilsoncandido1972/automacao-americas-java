package dadosMestres;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

import org.junit.Test;

import io.restassured.http.ContentType;

public class IntegracaoApiTests {
	
	private String baseURI = "http://10.104.29.102/QAFase2/QAWebAPIFase2/api/";
	
	@Test 
	public void getPing() {
		String resource = "PingService";
		
		given()
			.header("versao-api",1)
		.when()
			.get(baseURI + resource)
		.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body(containsString("DB Time:"));
	}
	
	@Test
	public void postConsultaEstoqueSap() {		
		String resource = "ConsultaEstoqueSAP";
		
		String body = "{\"Matnr\": \"1000000058\", "
				+ "\"Werks\": \"10427805\", "
				+ "\"Lgort\": \"0219\", "
				+ "\"Charg\": \"0000000069\"}";

		// Response resp = given()
		given()
			.header("versao-api",1)
			.body(body).contentType(ContentType.JSON)
		.when()
			.post(baseURI + resource)
		.then()
			.assertThat()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("MatConsEstoqResponse.TabMatReturn.Cspem", hasItem("0.000 "))
			.body("MatConsEstoqResponse.TabMatReturn.Licha", hasItem("PRCT0092019"));
//			.extract()
//			.response();
		
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());
	}
	
	
}
