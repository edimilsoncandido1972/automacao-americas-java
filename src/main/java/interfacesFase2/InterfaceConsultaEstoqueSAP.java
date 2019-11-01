package interfacesFase2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import org.apache.commons.lang3.ObjectUtils.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aventstack.extentreports.Status;
import org.json.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class InterfaceConsultaEstoqueSAP { // Nome da classe 
	
	private JSONObject JsonConsultaEstoqueSAP(String matnr, String werks, // Uma função com os campos do json.
										  String lgort, String charg, 
										  String licha, String vfdat,
										  String z_mfrnr, String ean11,
										  String zzdtmx)
	{ // Uma função com os campos do json, dentro parêntese () esta as variáveis, que são os campos.
//		String ConsultaEstoque = "{\"Matnr\": \"" +  matnr + "\", " 
//				+ "\"Werks\": \""+ werks +"\", " // centro "codigo do hospital"
//				+ "\"Lgort\": \""+ lgort +"\", "// deposito
//				+ "\"Charg\": \""+ charg +"\", "// lote interno SAP
//				+ "\"Licha\": \""+ licha +"\", "//num.lote fornecedor
//				+ "\"Vfdat\": \""+ vfdat +"\", "// data de vencimento
//				+ "\"Z_mfrnr\": \""+ z_mfrnr +"\", " // fabricante
//				+ "\"Ean11\": \""+ ean11 +"\", " // codigo de barras
//				+ "\"Zzdtmx\": \""+ zzdtmx +"\"}"; // codigo data matrix "QR code"
		JSONObject body = new JSONObject();
		body.put("Matnr", matnr);
		body.put("Werks", werks);
		body.put("Lgort", lgort);
		body.put("Charg", charg);
		body.put("Licha", licha);
		body.put("Vfdat", vfdat);
		body.put("Z_mfrnr", z_mfrnr);
		body.put("Ean11", ean11);
		body.put("Zzdtmx", zzdtmx);		
		
		
		
		return body;
	}
	
	
//	Json Response - Consulta estoque
//	  "Matnr": "1000000051", - Codigo do produto
//      "Werks": "1150", - Centro código do hospital
//      "Lgort": "1100", - Depósito - OBRIGATORIO EM CASO DE ESTOQUE POSITIVO
//      "Charg": "null", - lote interno SAP
//      "Clabs": "0", - Quantidade de estoque
//      "Cspem": "0", - Estoque bloqueado
//      "Licha": "null", - num.lote fornecedor - OBRIGATORIO EM CASO DE ESTOQUE POSITIVO
//      "Vfdat": "0001-01-01T00:00:00+00:00", - Data de validade - OBRIGATORIO EM CASO DE ESTOQUE POSITIVO
//      "Z_mfrnr": "null", - Fabricante - OBRIGATORIO EM CASO DE ESTOQUE POSITIVO
//      "Labst": "0", - Estoque de utilização livre avaliado
//      "Speme": "0", - Estoque bloqueado
//      "CodErro": "I", - Código de erro
//      "DescErro": "Não há saldo de estoque para os dados informados." - Mensagem de erro
	
	@Test
	public void postConsultaEstoqueSapComEstoque() {
		String resource = "ConsultaEstoqueSAP";
		
		String restMatnr = "1000000058";
		String restWerks = "10427805";
		String restLgort = "0219";
		String restCharg = "0000000069";
		String restLicha = "";
		String restVfdat = "";
		String restZ_mfrnr = "";
		String restEan11 = "";
		String restZzdtmx = "";
		
	
		
		JSONObject body = JsonConsultaEstoqueSAP(restMatnr, restWerks, restLgort,
												restCharg, restLicha, restVfdat, restZ_mfrnr, restEan11, restZzdtmx);
		Response resp = given()
				.header("versao-api", 1).body(body.toString())
				.contentType(ContentType.JSON)
				.when()
				.post(InterfaceMaster.baseURI + resource).then()
				.extract()
				.response();
	    // mecher tambem edimilson
		String msgSucesso = "";
		String msgFalha = "";
		
		// teste git edimilson
		if(resp.getStatusCode() == 200) {
			String lgort = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Lgort"));
			
			String deParaLgort = InterfaceMaster.deParaDepositoPaulistano(restLgort);
			
			String licha = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Licha"));
					
			String clabs = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Clabs"));
			Double dblClabs = Double.parseDouble(clabs); // Converte string para double"uma variavel que aceita virgula","
			
			String labst = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Labst"));
			Double dblLabst = Double.parseDouble(labst); // Converte string para double"uma variavel que aceita virgula","
			
			String z_mfrnr = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Z_mfrnr"));
						
			String vfdat = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Vfdat"));
			
			String charg = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Charg"));
			
			String werks = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Werks"));
			String deParaWerks = InterfaceMaster.deParaCentro(restWerks);

			
			if(lgort.equals(deParaLgort)) {
				msgSucesso = msgSucesso + "LGORT: Valor Lgort Correto: " + lgort + "<br />";
			}
			else {
				msgFalha = msgFalha + "LGORT: Lote em branco " + lgort + "<br />";
			}

			if(werks.equals(deParaWerks)) {
				msgSucesso = msgSucesso + "WERKS: Valor Lgort Correto: " + werks + "<br />";
			}
			else {
				msgFalha = msgFalha + "WERKS: Lote em branco " + werks + "<br />";
			}
							
			if(dblClabs > 0 && dblLabst > 0) {
				msgFalha = msgFalha + "CLABS e LABST: Estao preenchidos CLABS: " + dblClabs + " LABST: " + dblLabst + "<br />";
			}
			else {
				if(dblClabs > 0) {
					msgSucesso = msgSucesso + "CLABS: Estoque existente " + dblClabs + "<br />";
					
					if(!licha.equals("null")) {
						msgSucesso = msgSucesso + "LICHA: Preenchido: " + licha + "<br />";
					}
					else {
						msgFalha = msgFalha + "LICHA: Lote em branco " + licha + "<br />";
					}
					
					if(!z_mfrnr.equals("null")) {
						msgSucesso = msgSucesso + "Z_MFRNR: Preenchido: " + z_mfrnr + "<br />";
					}
					else {
						msgFalha = msgFalha + "Z_MFRNR: Lote em branco " + z_mfrnr + "<br />";
					}
					
					if(!vfdat.equals("0001-01-01T00:00:00+00:00")) {
						msgSucesso = msgSucesso + "VFDAT: Preenchido: " + vfdat + "<br />";
					}
					else {
						msgFalha = msgFalha + "VFDAT: Lote em branco " + vfdat + "<br />";
					}
					
					if(!charg.equals("null")) {
						msgSucesso = msgSucesso + "CHARG: Preenchido: " + charg + "<br />";
					}
					else {
						msgFalha = msgFalha + "CHARG: Lote em branco " + charg + "<br />";
					}
				}
				else if(dblLabst > 0) {
					msgSucesso = msgSucesso + "LABST: Estoque existente " + dblLabst + "<br />";
					
					if(licha.equals("null")) {
						msgSucesso = msgSucesso + "LICHA: Em Branco: " + licha + "<br />";
					}
					else {
						msgFalha = msgFalha + "LICHA: Preenchido " + licha + "<br />";
					}
					
					if(z_mfrnr.equals("null")) {
						msgSucesso = msgSucesso + "Z_MFRNR: Em branco: " + z_mfrnr + "<br />";
					}
					else {
						msgFalha = msgFalha + "Z_MFRNR: Preenchido: " + z_mfrnr + "<br />";
					}
					
					if(vfdat.equals("0001-01-01T00:00:00+00:00")) {
						msgSucesso = msgSucesso + "VFDAT: Em Branco: " + vfdat + "<br />";
					}
					else {
						msgFalha = msgFalha + "VFDAT: Preenchido: " + vfdat + "<br />";
					}
					
					if(charg.equals ("null")) {
						msgSucesso = msgSucesso + "CHARG: Em branco: " + charg + "<br />";
					}
					else {
						msgFalha = msgFalha + "CHARG: Preenchido: " + charg + "<br />";
					}
					
				}
				else {
					msgFalha = msgFalha + "CLABS e LABST: Sem Estoque Clabs : " + dblClabs + "Labst: " + dblLabst + "<br />";
				}
			}
						
			if (!msgFalha.equals(""))
				InterfaceMaster.logTestResult(Status.FAIL, body.toString(), resp, new Throwable().getStackTrace()[0].getMethodName(),200, msgSucesso, msgFalha); 
				// interfacemaster é uma classe master que contem variaveis globais , esta sendo instaciada com função da classe master,
				// que esta sendo instanciada, que recebe o paramentro.
				// Os metodos Throwable().getStackTrace()[0].getMethodName() é metodos java que estão sendo instanciados para utilizar o nome do função corrente
			else
				InterfaceMaster.logTestResult(Status.PASS, body.toString(), resp, new Throwable().getStackTrace()[0].getMethodName(),200, msgSucesso, msgFalha);
		}else
		{
			InterfaceMaster.logTestResult(Status.FAIL, body.toString(), resp, new Throwable().getStackTrace()[0].getMethodName(),200,"","");	
		}
	}

	@Test
	public void postConsultaEstoqueSapComEstoqueMaisDeUmCodigoBarras() {	
		String resource = "ConsultaEstoqueSAP";
		
		String restMatnr = "1000000058";
		String restWerks = "10427805";
		String restLgort = "0219";
		String restCharg = "";
		String restLicha = "";
		String restVfdat = "";
		String restZ_mfrnr = "";
		String restEan11 = "";
		String restZzdtmx = "";
		
		
		
		JSONObject body = JsonConsultaEstoqueSAP(restMatnr, restWerks, restLgort,
												restCharg, restLicha, restVfdat, restZ_mfrnr, restEan11, restZzdtmx);
		
		Response resp = given()
				.header("versao-api", 1).body(body.toString())
				.contentType(ContentType.JSON)
				.when()
				.post(InterfaceMaster.baseURI + resource).then()
				.extract()
				.response();
		String msgSucesso = "";
		String msgFalha = "";
		
		if(resp.getStatusCode() == 200) {
			
			String lgort = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Lgort"));
			
			String deParaLgort = InterfaceMaster.deParaDepositoPaulistano(restLgort);
			
			String licha = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Licha"));
					
			String clabs = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Clabs"));
			Double dblClabs;
			
			String labst = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Labst"));
			Double dblLabst; 
			
			String z_mfrnr = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Z_mfrnr"));
						
			String vfdat = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Vfdat"));
			
			String charg = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Charg"));
			
			String werks = InterfaceMaster.TratarTexto(resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Werks"));
			String deParaWerks = InterfaceMaster.deParaCentro(restWerks);

			String vtLgort[] = lgort.split(",");
			
			String vtLicha[] = licha.split(","); // o split serve para separar os campos quando ele encontrar uma virgula
		
			String vtClabs[] = clabs.split(","); 
						
			String vtLabst[] = labst.split(",");
			
			String vtZ_mfrnr[] = z_mfrnr.split(",");
			
			String vtVfdat[] = vfdat.split(",");
			
			String vtCharg[] = charg.split(",");
			
			String vtWerks[] = werks.split(",");
			
			for(Integer i = 0; i < vtLabst.length; i++) {
				vtClabs[i] = vtClabs[i].trim(); // retira os espaços extras que tiver dentro do campo
				vtLabst[i] = vtLabst[i].trim();
				vtLgort[i] = vtLgort[i].trim();
				vtLicha[i] = vtLicha[i].trim();
				vtZ_mfrnr[i] = vtZ_mfrnr[i].trim();
				vtVfdat[i] = vtVfdat[i].trim();
				vtCharg[i] = vtCharg[i].trim();
				vtWerks[i] = vtWerks[i].trim();
			
				dblClabs = Double.parseDouble(vtClabs[i]);
				dblLabst = Double.parseDouble(vtLabst[i]);
				
				
				if(vtLgort[i].equals(deParaLgort)) {
					msgSucesso = msgSucesso + "LGORT "+ i +": Valor Lgort Correto: " + vtLgort[i] + "<br />";
				}
				else {
					msgFalha = msgFalha + "LGORT "+ i +": Lote em branco " + vtLgort[i] + "<br />";
				}

				if(vtWerks[i].equals(deParaWerks)) {
					msgSucesso = msgSucesso + "WERKS "+ i +": Valor Lgort Correto: " + vtWerks[i] + "<br />";
				}
				else {
					msgFalha = msgFalha + "WERKS " + i + ": Lote em branco " + vtWerks[i] + "<br />";
				}
								
				if(dblClabs > 0 && dblLabst > 0) {
					msgFalha = msgFalha + "CLABS e LABST " + i + ": Estao preenchidos CLABS: " + dblClabs + " LABST: " + dblLabst + "<br />";
				}
				else {
					if(dblClabs > 0) {
						msgSucesso = msgSucesso + "CLABS "+ i +": Estoque existente " + dblClabs + "<br />";
						if(!vtLicha[i].equals("null")) {              // o conteudo que tem "null" esta como string por causa da conversao
							msgSucesso = msgSucesso + "LICHA: Preenchido: " + vtLicha[i] + "<br />";
						}
						else {
							msgFalha = msgFalha + "LICHA: Lote em branco " + vtLicha[i] + "<br />";
						}
						
						if(!vtZ_mfrnr[i].equals("null")) {
							msgSucesso = msgSucesso + "Z_MFRNR: Preenchido: " + vtZ_mfrnr[i] + "<br />";
						}
						else {
							msgFalha = msgFalha + "Z_MFRNR: Lote em branco " + vtZ_mfrnr[i] + "<br />";
						}
						
						if(!vtVfdat[i].equals("0001-01-01T00:00:00+00:00")) {
							msgSucesso = msgSucesso + "VFDAT: Preenchido: " + vtVfdat[i] + "<br />";
						}
						else {
							msgFalha = msgFalha + "VFDAT: Lote em branco " + vtVfdat[i] + "<br />";
						}
						
						if(!vtCharg[i].equals("null")) {
							msgSucesso = msgSucesso + "CHARG: Preenchido: " + vtCharg[i] + "<br />";
						}
						else {
							msgFalha = msgFalha + "CHARG: Lote em branco " + vtCharg[i] + "<br />";
						}
					}
					else if(dblLabst > 0) {
						msgSucesso = msgSucesso + "LABST: Estoque existente " + dblLabst + "<br />";
						
						if(vtLicha[i].equals("null")) {
							msgSucesso = msgSucesso + "LICHA: Em Branco: " + vtLicha[i] + "<br />";
						}
						else {
							msgFalha = msgFalha + "LICHA: Preenchido " + vtLicha[i] + "<br />";
						}
						
						if(vtZ_mfrnr[i].equals("null")) {
							msgSucesso = msgSucesso + "Z_MFRNR: Em branco: " + vtZ_mfrnr[i] + "<br />";
						}
						else {
							msgFalha = msgFalha + "Z_MFRNR: Preenchido: " + vtZ_mfrnr[i] + "<br />";
						}
						
						if(vtVfdat[i].equals("0001-01-01T00:00:00+00:00")) {
							msgSucesso = msgSucesso + "VFDAT: Em Branco: " + vtVfdat[i] + "<br />";
						}
						else {
							msgFalha = msgFalha + "VFDAT: Preenchido: " + vtVfdat[i] + "<br />";
						}
						
						if(vtCharg[i].equals ("null")) {
							msgSucesso = msgSucesso + "CHARG: Em branco: " + vtCharg[i] + "<br />";
						}
						else {
							msgFalha = msgFalha + "CHARG: Preenchido: " + vtCharg[i] + "<br />";
						}
						
					}
					else {
						msgFalha = msgFalha + "CLABS e LABST: Sem Estoque Clabs : " + dblClabs + "Labst: " + dblLabst + "<br />";
					}
				}
			}
			
			if (!msgFalha.equals(""))
				InterfaceMaster.logTestResult(Status.FAIL, body.toString(), resp, new Throwable().getStackTrace()[0].getMethodName(),200, msgSucesso, msgFalha); 
				// interfacemaster é uma classe master que contem variaveis globais , esta sendo instaciada com função da classe master,
				// que esta sendo instanciada, que recebe o paramentro.
				// Os metodos Throwable().getStackTrace()[0].getMethodName() é metodos java que estão sendo instanciados para utilizar o nome do função corrente
			else
				InterfaceMaster.logTestResult(Status.PASS, body.toString(), resp, new Throwable().getStackTrace()[0].getMethodName(),200, msgSucesso, msgFalha);
			
		}else
		{
			InterfaceMaster.logTestResult(Status.FAIL, body.toString(), resp, new Throwable().getStackTrace()[0].getMethodName(),200,"","");	
		}

		
	

//		String body = "{\"Matnr\": \"1000000058\", " + "\"Werks\": \"10427805\", " + "\"Lgort\": \"0219\"}";
//
//		Response resp =
//		given().header("versao-api", 1).body(body).contentType(ContentType.JSON).when().post(baseURI + resource).then()
//				.assertThat().statusCode(200).contentType(ContentType.JSON)
//				.body("MatConsEstoqResponse.TabMatReturn.Lgort", hasItems("1100", "1100"))
//				.body("MatConsEstoqResponse.TabMatReturn.Licha", hasItems("PRCT0092019", "null"))
//				.body("MatConsEstoqResponse.TabMatReturn.Clabs", hasItems("25.000 ", "100.000 "))
//				.body("MatConsEstoqResponse.TabMatReturn.Labst", hasItems("0", "0")).extract().response();
//		
//		logTestResult(Status.PASS, body, resp, new Throwable().getStackTrace()[0].getMethodName());
	}
	
	
//	@Test
//	public void postConsultaEstoqueSapSemEstoque() {
//		String resource = "ConsultaEstoqueSAP";
//		
//		String body = JsonConsultaEstoqueSAP("1000000051","10427805","0219",
//											 "","","","","","");
//		
//		Response resp = given()
//				.header("versao-api", 1).body(body)
//				.contentType(ContentType.JSON)
//				.when()
//				.post(InterfaceMaster.baseURI + resource).then()
//				.extract()
//				.response();
//		
//		String msgSucesso = "";
//		String msgFalha = "";
//		
//		
//		if(resp.getStatusCode() == 200) {
//			String lgort = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Lgort");
//			lgort = lgort.replace("[", "") // Trocar [ "Cochetes" para vazio campo
//					.replace("]", "") // Trocar [ "Cochetes" para vazio no campo
//					.trim(); // Tira o espaço que não são necessarios e adicionais no campo
//			String licha = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Licha");
//			licha = licha.replace("[", "")
//					.replace("]", "")
//					.trim();
//			String clabs = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Clabs");
//			clabs = clabs.replace("[", "")
//					.replace("]", "")
//					.trim();
//			Double dblClabs = Double.parseDouble(clabs); // Converte string para double"uma variavel que aceita virgula","
//			String labst = resp.jsonPath().getString("MatConsEstoqResponse.TabMatReturn.Labst");
//			labst = labst.replace("[", "")
//					.replace("]", "")
//					.trim();
//			
//			
//			if(lgort.equals("1100")) { 
//				msgSucesso = "LGORT: Esta com mesmo conteudo do REST" + "<br />";
//			}
//			else {
//				msgFalha = "LGORT: Esta com conteudo diferente. Deveria ser: "+ lgort + "<br />";
//			}
//			
//			if(licha.equals("") && licha.equals("null") && labst.equals("0")) {
//				msgSucesso = msgSucesso + "LICHA e LABST: Retornou o numero do LICHA " + licha + " Quando LICHA tiver conteudo o LABST eh obrigatorio" + "<br />";  
//			}
//			else { 
//					if(!licha.equals("") || !licha.equals("null")) { 
//						msgFalha = msgFalha + "LICHA: Lote em branco " + licha + "<br />";
//			
//					}
//					if(!labst.equals("0")) {
//						msgFalha = msgFalha + "LABST: Estoque de utilização esta com conteudo " + labst + "<br />";
//					}
//			}
//			
//			if(dblClabs > 0) {
//				msgSucesso = msgSucesso + "CLABS: Estoque existente " + dblClabs + "<br />";
//			}
//			else {
//				msgFalha = msgFalha + "CLABS: Produto sem estoque " + dblClabs + "<br />";
//			}
//					
//			if (!msgFalha.equals(""))
//				InterfaceMaster.logTestResult(Status.FAIL, body, resp, new Throwable().getStackTrace()[0].getMethodName(),200, msgSucesso, msgFalha); 
//				// interfacemaster é uma classe master que contem variaveis globais , esta sendo instaciada com função da classe master,
//				// que esta sendo instanciada, que recebe o paramentro.
//				// Os metodos Throwable().getStackTrace()[0].getMethodName() é metodos java que estão sendo instanciados para utilizar o nome do função corrente
//			else
//				InterfaceMaster.logTestResult(Status.PASS, body, resp, new Throwable().getStackTrace()[0].getMethodName(),200, msgSucesso, msgFalha);
//		}else
//		{
//			InterfaceMaster.logTestResult(Status.FAIL, body, resp, new Throwable().getStackTrace()[0].getMethodName(),200,"","");	
//		}
//	}
	
	
	
}
