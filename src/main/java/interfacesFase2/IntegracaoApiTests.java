package interfacesFase2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cedarsoftware.util.io.JsonWriter;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IntegracaoApiTests {
	
	
	private String baseURI = "http://10.104.29.102/QAFase2/QAWebAPIFase2/api/";
	static ExtentReports extent;
	static ExtentHtmlReporter report;

//	@BeforeClass
//	public static void statTest() {
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy-HHmmss");
//		Date date = new Date(System.currentTimeMillis());
//		
//		report = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/TestReport " + formatter.format(date) + ".html");
//		report.config().setDocumentTitle("Automation Report"); // Titulo da aba do relatorio
//		report.config().setReportName("API Testing"); // Nome do Relatorio
//		report.config().setTheme(Theme.DARK);
//
//		extent = new ExtentReports();
//		extent.attachReporter(report);
//		extent.setSystemInfo("Host Name", "localhost");
//		extent.setSystemInfo("Enviroment", "QA");
//		extent.setSystemInfo("user", "Edcandido");
//		
//	}
//	
//	@AfterClass
//	public static void endTest() {
//		extent.flush();
//		
//	}
	
//	public void logTestResult(Status status, String rest, Response resp,  String name, String descricao) {
//		ExtentTest teste;
//		teste = master.extent.createTest(name);
//		
//		if(descricao != "") 
//			teste.log(status, "Descricao: " + descricao);
//		
//		teste.log(status, "Status Code: " + resp.getStatusCode());
//		teste.log(status, "Rest: <pre>" + JsonWriter.formatJson(rest) + "</pre>");
//		teste.log(status, "Response: <pre>" + JsonWriter.formatJson(resp.getBody().asString()) + "</pre>");
//	}
	
	@Test
//	public void getPing() {
//		String resource = "PingService";
//
//		given().header("versao-api", 1).when().get(baseURI + resource).then().statusCode(200)
//				.contentType(ContentType.JSON).body(containsString("DB Time:"));
//	}
	
//	@Test
//	public void postConsultaEstoqueSapComEstoque() {
//		String resource = "ConsultaEstoqueSAP";
//	
//		String body = "{\"Matnr\": \"1000000058\", " 
//				+ "\"Werks\": \"10427805\", " 
//				+ "\"Lgort\": \"0219\", "
//				+ "\"Charg\": \"0000000069\"}"
//				+ "\"Licha\": \"\""
//				+ "\"Vfdat\": \"\""
//				+ "\"Z_mfrnr\": \"\""
//				+ "\"Ean11\": \"\""
//				+ "\"Zzdtmx\": \"\"";
//		
//		System.out.println(InterfaceMaster.baseURI);
//		
//		Response resp=given()
//				.header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(master.baseURI + resource).then()
////				.assertThat().statusCode(200).contentType(ContentType.JSON)
//				.extract()
//				.response();
//		if(resp.getStatusCode() == 200) {
//			
//			String lgort = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Lgort");
//			String licha = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Licha");
//			String clabs = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Clabs");
//			String labst = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Labst");
//			
//			if(lgort.equals("[1100]") && licha.equals("[PRCT0092019]") && 
//			   clabs.equals("[25.000 ]") && labst.equals("[0]") )
//			{
//				master.logTestResult(Status.PASS, body, resp, new Throwable().getStackTrace()[0].getMethodName(), "");
//			}else {
//				master.logTestResult(Status.FAIL, body, resp, new Throwable().getStackTrace()[0].getMethodName(), "");
//			}
//
////			System.out.println(resp.getStatusCode());
//		}else
//		{
//			
////			System.out.println(resp.getStatusCode());
//			master.logTestResult(Status.FAIL, body, resp, new Throwable().getStackTrace()[0].getMethodName(),"");
//
//			
////			System.out.println("erro");
//			
//		
//		}
//			
//		
////		
//////		System.out.println(baseURI + resource);
////		System.out.println(resp.getStatusCode());
//	}

//	@Test
//	public void postConsultaEstoqueSapComEstoqueMaisDeUmCodigoBarras() {	
//		String resource = "ConsultaEstoqueSAP";
//
//		String body = "{\"Matnr\": \"1000000058\", " + "\"Werks\": \"10427805\", " + "\"Lgort\": \"0219\"}";
//
//		Response resp =
//		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
//				.assertThat().statusCode(200).contentType(ContentType.JSON)
//				.body("MatConsEstoqResponse.TabMatReturn.Lgort", hasItems("1100", "1100"))
//				.body("MatConsEstoqResponse.TabMatReturn.Licha", hasItems("PRCT0092019", null))
//				.body("MatConsEstoqResponse.TabMatReturn.Clabs", hasItems("25.000 ", "100.000 "))
//				.body("MatConsEstoqResponse.TabMatReturn.Labst", hasItems("0", "0")).extract().response();
//		
//		logTestResult(Status.PASS, body, resp, new Throwable().getStackTrace()[0].getMethodName());
//	}

//	@Test
//	public void postConsultaEstoqueSapSemEstoque() {
//		String resource = "ConsultaEstoqueSAP";
//		String body = "{\"Matnr\": \"1000000051\"," + "\"Werks\": \"10427805\", " + "\"Lgort\": \"0219\"} ";
//
//    	 Response resp =
//		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
//				.assertThat().statusCode(200).contentType(ContentType.JSON)
//				.body("MatConsEstoqResponse.TabMatReturn.Lgort", hasItem("1100"))
////			.body("MatConsEstoqResponse.TabMatReturn.Licha", hasItem(null))
//				.body("MatConsEstoqResponse.TabMatReturn.Clabs", hasItem("0"))
//				.body("MatConsEstoqResponse.TabMatReturn.Labst", hasItem("0"))
//				.body("MatConsEstoqResponse.TabMatReturn.DescErro",
//						hasItem("Não há saldo de estoque para os dados informados."))
//				.extract()
//				.response();
//
//
//		logTestResult(Status.PASS, body, resp, new Throwable().getStackTrace()[0].getMethodName());
//
//	}

	@Test
	public void postConsultaPacienteBaixaEstoquePorNomeSap() {
		String resource = "ConsultaPacienteBaixaEstoqueSAP";
		String body = "{\"ConsultaPacienteReq\": {\"ConsultaTab\": [" + "{\"Zsist\": \"SISH\","
				+ "\"NOME\": \"Rodrigo Mora\", " + "\"WRK\": \"1150\"}]}}";

		Response resp =
		 given()
				.header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.ZZCODPAC", hasItem("H917150")).extract().response();
		System.out.println(baseURI + resource);
		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postConsultaPacienteBaixaEstoquePorCodExtSap() {
		String resource = "ConsultaPacienteBaixaEstoqueSAP";
		String body = "{\"ConsultaPacienteReq\": {\"ConsultaTab\": [" + "{\"Zsist\": \"SISH\"," + "\"NOME\": \"\", "
				+ "\"WRK\": \"1150\", " + "\"CODEXT\": \"H917150\"}]}}";

		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.ZZPACIENTE", hasItem(containsString("RODRIGO")))
			.extract()
			.response();
		System.out.println(baseURI + resource);
		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postConsultaPacienteBaixaEstoquePorNomeDoisItensSAP() {
		String resource = "ConsultaPacienteBaixaEstoqueSAP";
		String body = "{\"ConsultaPacienteReq\": {\"ConsultaTab\": [" + "{\"Zsist\": \"SISH\"," + "\"NOME\": \"MORA\", "
				+ "\"WRK\": \"1150\", " + "\"CODEXT\": \"\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.ZZCODPAC", hasItems("H917751", "H917150"));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postConsultaPacienteBaixaEstoquePorLeitoSAP() {
		String resource = "ConsultaPacienteBaixaEstoqueSAP";
		String body = "{\"ConsultaPacienteReq\": {\"ConsultaTab\": [" + "{\"Zsist\": \"SISH\"," + "\"NOME\": \"\", "
				+ "\"WRK\": \"1150\", " + "\"CODEXT\": \"\", " + "\"LEITO\": \"UT08\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.ZZPACIENTE", hasItem("7014 RODRIGO MORA"));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postConsultaPacienteBaixaEstoquePorSetorSAP() {
		String resource = "ConsultaPacienteBaixaEstoqueSAP";
		String body = "{\"ConsultaPacienteReq\": {\"ConsultaTab\": [" + "{\"Zsist\": \"SISH\","
				+ "\"NOME\": \"RODRIGO\", " + "\"WRK\": \"1150\", " + "\"CODEXT\": \"\", " + "\"LGORT\": \"3008\", "
				+ "\"LEITO\": \"\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.ZZCODPAC", hasItem("H917150")).body("CompletaTab.ZZLEITO", hasItem("UT08"));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postConsultaPacienteBaixaEstoqueCodigoErradoSAP() {
		String resource = "ConsultaPacienteBaixaEstoqueSAP";
		String body = "{\"ConsultaPacienteReq\": {\"ConsultaTab\": [" + "{\"Zsist\": \"SISH\"," + "\"NOME\": \"\", "
				+ "\"WRK\": \"1150\", " + "\"CODEXT\": \"H91715\", " + "\"LGORT\": \"\", " + "\"LEITO\": \"\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(500).contentType(ContentType.JSON)
				.body(containsString("Paciente não existe."));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postConsultaPacienteBaixaEstoqueNomeErradoSAP() {
		String resource = "ConsultaPacienteBaixaEstoqueSAP";
		String body = "{\"ConsultaPacienteReq\": {\"ConsultaTab\": [" + "{\"Zsist\": \"SISH\","
				+ "\"NOME\": \"Rodrygo Mora\", " + "\"WRK\": \"1150\", " + "\"CODEXT\": \"\", " + "\"LGORT\": \"\", "
				+ "\"LEITO\": \"\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(500).contentType(ContentType.JSON)
				.body(containsString("Paciente não existe."));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postConsultaPacienteBaixaEstoqueLeitoErradoSAP() {
		String resource = "ConsultaPacienteBaixaEstoqueSAP";
		String body = "{\"ConsultaPacienteReq\": {\"ConsultaTab\": [" + "{\"Zsist\": \"SISH\","
				+ "\"NOME\": \"Rodrigo Mora\", " + "\"WRK\": \"1150\", " + "\"CODEXT\": \"\", " + "\"LGORT\": \"\", "
				+ "\"LEITO\": \"99\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(500).contentType(ContentType.JSON)
				.body(containsString("Paciente não existe."));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postValidaMaterialBaixaEstoqueSAP() {
		String resource = "ValidaMaterialBaixaEstoqueSAP";
		String body = "{\"ValidaMaterialReq\": {\"CosultaTab\": [" + "{\"Zsist\": \"SISH\"," + "\"WERKS\": \"1150\","
				+ "\"CODEXT\": \"H917751\"," + "\"AUFNR\": \"\"," + "\"MATNR\": \"1000000058\","
				+ "\"LGORT\": \"3008\", " + "\"QUANT\": \"\"," + "\"SETOR\": \"0219\"," + "\"KOSTL\": \"\","
				+ "\"LGBER\": \"\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.AUFNR", hasItem("H00000000199")).body("CompletaTab.CODMSG", hasItem("03"))
				.body("CompletaTab.DESCMSG", hasItem("Item pode gerar comanda!"));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postValidaMaterialBaixaEstoqueSemMAterialSAP() {
		String resource = "ValidaMaterialBaixaEstoqueSAP";
		String body = "{\"ValidaMaterialReq\": {\"CosultaTab\": [" + "{\"Zsist\": \"SISH\"," + "\"WERKS\": \"1150\","
				+ "\"CODEXT\": \"H917751\"," + "\"AUFNR\": \"\"," + "\"MATNR\": \"\"," + "\"LGORT\": \"3008\", "
				+ "\"QUANT\": \"\"," + "\"SETOR\": \"0219\"," + "\"KOSTL\": \"\"," + "\"LGBER\": \"\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.AUFNR", hasItem("H00000000199")).body("CompletaTab.CODMSG", hasItem("02"))
				.body("CompletaTab.DESCMSG", hasItem("Não foi encontrado o material com os dados enviados."));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postValidaMaterialBaixaEstoqueOrdemDeOutroPacienteSAP() {
		String resource = "ValidaMaterialBaixaEstoqueSAP";
		String body = "{\"ValidaMaterialReq\": {\"CosultaTab\": [" + "{\"Zsist\": \"SISH\"," + "\"WERKS\": \"1150\","
				+ "\"CODEXT\": \"H917751\"," + "\"AUFNR\": \"H00000000175\"," + "\"MATNR\": \"1000000058\","
				+ "\"LGORT\": \"3008\", " + "\"QUANT\": \"\"," + "\"SETOR\": \"0219\"," + "\"KOSTL\": \"\","
				+ "\"LGBER\": \"\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.AUFNR", hasItem("H00000000175")).body("CompletaTab.CODMSG", hasItem("02"))
				.body("CompletaTab.DESCMSG", hasItem("Paciente não foi encontrado com os dados enviados."));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}

	@Test
	public void postValidaMaterialBaixaEstoqueConsultaComOrdemSAP() {
		String resource = "ValidaMaterialBaixaEstoqueSAP";
		String body = "{\"ValidaMaterialReq\": {\"CosultaTab\": [" + "{\"Zsist\": \"SISH\"," + "\"WERKS\": \"1150\","
				+ "\"CODEXT\": \"\"," + "\"AUFNR\": \"H00000000199\"," + "\"MATNR\": \"1000000058\","
				+ "\"LGORT\": \"3008\", " + "\"QUANT\": \"\"," + "\"SETOR\": \"0219\"," + "\"KOSTL\": \"\","
				+ "\"LGBER\": \"\"}]}}";

//		 Response resp =
		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("CompletaTab.AUFNR", hasItem("H00000000199")).body("CompletaTab.CODMSG", hasItem("03"))
				.body("CompletaTab.DESCMSG", hasItem("Item pode gerar comanda!"));
//			.extract()
//			.response();
//		System.out.println(baseURI + resource);
//		System.out.println(resp.getBody().asString());

	}
	
	
}
