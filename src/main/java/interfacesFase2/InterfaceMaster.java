package interfacesFase2;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cedarsoftware.util.io.JsonWriter;

import io.restassured.response.Response;


public class InterfaceMaster {
	static String baseURI = "http://10.104.29.102/QAFase2/QAWebAPIFase2/api/";
	static ExtentReports extent;
	static ExtentHtmlReporter report;
	
	static void InicalizaTeste()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy-HHmmss");
		Date date = new Date(System.currentTimeMillis());
		
		report = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/TestReport " + formatter.format(date) + ".html");
		report.config().setDocumentTitle("Automation Report"); // Titulo da aba do relatorio
		report.config().setReportName("API Testing"); // Nome do Relatorio
		report.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("user", "Edcandido");
	}
	
	static void FechaTeste()
	{
		extent.flush();
	}
	
	static void logTestResult(Status status, String rest, Response resp,  String name, String descricao) {
		ExtentTest teste;
		teste = extent.createTest(name);
		
		if(descricao != "") 
			teste.log(status, "Descricao: " + descricao);
		
		teste.log(status, "Status Code: " + resp.getStatusCode());
		
		if(rest != "") 
			teste.log(status, "Rest: <pre>" + JsonWriter.formatJson(rest) + "</pre>");
		
		teste.log(status, "Response: <pre>" + JsonWriter.formatJson(resp.getBody().asString()) + "</pre>");
	}
	
}
