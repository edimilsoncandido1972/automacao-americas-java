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

// private só pode ser utilizado dentro da mesma classe
// public pode ser utilizado em todas as classe do projeto, precisa ser instanciado
// static pode ser utilizado em todas as classe do projeto, e não precisa ser instanciado
// void passa paramentro e não retorna valor
// string quando o função tipo string, int, double ela tem que retorna um valor 

public class InterfaceMaster {
	static String baseURI = "http://10.104.29.102/QAFase2/QAWebAPIFase2/api/";
	static ExtentReports extent;
	static ExtentHtmlReporter report;
	
	static void InicializaTeste()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy-HHmmss");
		Date date = new Date(System.currentTimeMillis()); // criamos uma variavel simple data forma para utilizarmos o formato data,dia e hora
		
		report = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/TestReport " + formatter.format(date) + ".html");
		report.config().setDocumentTitle("Automation Report"); //Para colocar o titulo da aba do relatorio
		report.config().setReportName("API Testing"); // Para colocar nome do Relatorio
		report.config().setTheme(Theme.DARK); // Para colocar a cor no relatorio 

		extent = new ExtentReports(); //Quando instanciamos o metodo ExtentReport para informar no relatorio os dados que achamos importames
		extent.attachReporter(report); 
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("user", "Edcandido");
	}
	
	static void FechaTeste()
	{
		extent.flush();
	}
	
	static void logTestResult(Status status, String rest, Response resp,  String name, Integer statusEsperado, String msgSucesso, String msgFalha) {
		ExtentTest teste;
		teste = extent.createTest(name);
		
		if(msgSucesso != "") 
			teste.log(Status.PASS, "<html> Sucesso: " + "<br />" + msgSucesso + "</html>");
			// para quebrar a linha foi trocado o \n e utilizamos os comandos do html <html>, <br /> e  </html>
		
		if(msgFalha != "") 
			teste.log(status, "<html> Falha: " + "<br />" + msgFalha + "</html>");
		
		if(statusEsperado == resp.getStatusCode()) {
			teste.log(Status.PASS, "Status Code: " + resp.getStatusCode());
			if(rest != "") 
				teste.log(Status.PASS, "Rest: <pre>" + JsonWriter.formatJson(rest) + "</pre>");
		}
		else {
			teste.log(Status.FAIL, "Status Code: " + resp.getStatusCode());
			if(rest != "") 
				teste.log(Status.FAIL, "Rest: <pre>" + JsonWriter.formatJson(rest) + "</pre>");
		}
		
		teste.log(status, "Response: <pre>" + JsonWriter.formatJson(resp.getBody().asString()) + "</pre>");
	}
	
	static String deParaDepositoPaulistano(String depositoRest) {
		
		String depositoResp = "";

		Integer intDepositoRest = Integer.parseInt(depositoRest); // Converte string para double"uma variavel que aceita virgula","
		
		
		
		switch(intDepositoRest) {
			case 142:
				depositoResp = "1000";
				break;
			case 219:
				depositoResp = "1100";
				break;
			case 1006:
				depositoResp = "1101";
				break;
			case 2002:
				depositoResp = "1102";
				break;
			case 253:
				depositoResp = "1103";
				break;
			case 220:
				depositoResp = "1200";
				break;
			case 168:
				depositoResp = "1204";
				break;
			case 15:
				depositoResp = "1104";
				break;
			case 273:
			case 240:
			case 241:
			case 228:
			case 229:
			case 239:
			case 230:
			case 232:
			case 233:
			case 245:
			case 235:
			case 236:
			case 237:
			case 243:
			case 244:
			case 44:
			case 247:
			case 246:
			case 248:
			case 271:
			case 90:
			case 28:
			case 29:
			case 249:
			case 251:
				depositoResp = "2000";
				break;
			case 6021:
			case 9005:
			case 9001:
			case 89:
			case 169:
				depositoResp = "6000";
				break;
			case 255:
			case 258:
			case 259:
			case 260:
			case 261:
			case 48:
			case 263:
			case 264:
			case 265:
				depositoResp = "5000";
				break;
			case 266:
			case 107:
			case 55:
			case 268:
			case 269:
			case 30:
			case 32:
			case 33:
			case 35:
			case 36:
			case 37:
			case 46:
			case 127:
			case 65:
			case 69:
			case 61:
			case 158:
			case 159:
			depositoResp = "3000";
				break;
			case 117:
			case 116:
			case 118:
				depositoResp = "4000";
				break;
			default:
				depositoResp = "Deposito nao encontrado";
			
		}
		
		return depositoResp;
	}
	
	static String deParaCentro(String centroRest) {
		
		String centroResp = "";

		Integer intCentroRest = Integer.parseInt(centroRest); // Converte string para double"uma variavel que aceita virgula","
		
		
		
		switch(intCentroRest) {
			case 10427805:
				centroResp = "1150";
				break;
			default:
				centroResp = "Centro nao encontrado";
		}
		
		return centroResp;
	}

	
	static String TratarTexto(String texto) {
		String textoTratado = texto;
		
		textoTratado = textoTratado.replace("[", ""); // Trocar [ "Cochetes" para vazio campo
		textoTratado = textoTratado.replace("]", ""); // Trocar [ "Cochetes" para vazio no campo
		textoTratado = textoTratado.trim(); // Tira o espaço que não são necessarios e adicionais no campo
		//System.out.println("teste" + textoTratado);
		return textoTratado;
	}
	
	
	
	
}
