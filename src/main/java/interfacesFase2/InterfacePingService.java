package interfacesFase2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.aventstack.extentreports.Status;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class InterfacePingService {
	
	@Test
	public void getPing() {
		String resource = "PingService";

		Response resp = 
			given()
				.header("versao-api", 1)
			.when()
				.get(InterfaceMaster.baseURI + resource)
			.then()
				.contentType(ContentType.JSON)
//				.body(containsString("DB Time:"))
				.extract()
				.response();
		
		
		if(resp.getStatusCode()==200)
		{
			if (resp.getBody().asString().contains("DB Time:"))
				InterfaceMaster.logTestResult(Status.PASS, "", resp, new Throwable().getStackTrace()[0].getMethodName(), "");
			else
				InterfaceMaster.logTestResult(Status.FAIL, "", resp, new Throwable().getStackTrace()[0].getMethodName(), "");
		}
		else
		{
			InterfaceMaster.logTestResult(Status.FAIL, "", resp, new Throwable().getStackTrace()[0].getMethodName(), "");
		}
	}
}
