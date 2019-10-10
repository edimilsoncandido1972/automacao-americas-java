package interfacesFase2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	InterfacePingService.class,
//	InterfaceConsultaEstoqueSAP.class
})
public class SuiteTestes {
	@BeforeClass
	public static void statTest() {
		InterfaceMaster.InicalizaTeste();
	}
	
	@AfterClass
	public static void endTest() {
		InterfaceMaster.FechaTeste();
		
	}
}
