package interfacesFase2;

import static io.restassured.RestAssured.given;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aventstack.extentreports.Status;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class InterfaceConsultaEstoqueSAP {
	
	@Test
	public void postConsultaEstoqueSapComEstoque() {
		String resource = "ConsultaEstoqueSAP";
	
		String body = "{\"Matnr\": \"1000000058\", " 
				+ "\"Werks\": \"10427805\", " 
				+ "\"Lgort\": \"0219\", "
				+ "\"Charg\": \"0000000069\"}"
				+ "\"Licha\": \"\""
				+ "\"Vfdat\": \"\""
				+ "\"Z_mfrnr\": \"\""
				+ "\"Ean11\": \"\""
				+ "\"Zzdtmx\": \"\"";
		
		Response resp=given()
				.header("versao-api", 1).body(body)
				.contentType(ContentType.JSON)
				.when()
				.post(InterfaceMaster.baseURI + resource).then()
				.extract()
				.response();
		if(resp.getStatusCode() == 200) {
			
			String lgort = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Lgort");
			String licha = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Licha");
			String clabs = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Clabs");
			String labst = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Labst");
			
			if(lgort.equals("[1100]") && licha.equals("[PRCT0092019]") && 
			   clabs.equals("[25.000 ]") && labst.equals("[0]") )
			{
				InterfaceMaster.logTestResult(Status.PASS, body, resp, new Throwable().getStackTrace()[0].getMethodName(), "");
			}else {
				InterfaceMaster.logTestResult(Status.FAIL, body, resp, new Throwable().getStackTrace()[0].getMethodName(), "");
			}
		}else
		{
			InterfaceMaster.logTestResult(Status.FAIL, body, resp, new Throwable().getStackTrace()[0].getMethodName(),"");

		
		}
	}

}
