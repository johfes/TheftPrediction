package biz.fesenmeyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Main {
	private static EPRuntime cepRT;
	private static String person;
	
	public static String getPerson() {
		return person;
	}

	public static void main(String[] args) {
		initCEP();

	    BufferedReader br = new BufferedReader
	    					(new InputStreamReader(System.in));
	    System.out.print("Welche Person soll überwacht werden?");
	    try {
			person = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Theft theft = new Theft(person);
		
		for(int i=0; i<5; i++){
			System.out.println(theft);
			cepRT.sendEvent(theft);
		}

	}
	
	public static void initCEP(){
	    Configuration cepConfig = new Configuration();
	    // register objects the engine will have to handle
	    cepConfig.addEventType("Theft",
	    		Theft.class.getName());
		// setup the engine
		EPServiceProvider cep = EPServiceProviderManager.
									getProvider("CEPEngine",cepConfig);
		cepRT = cep.getEPRuntime();
		//  register EPL statements
		EPAdministrator cepAdm = cep.getEPAdministrator();
		
		EPStatement cepStatement1 = cepAdm.createEPL("select * from " +
				"pattern [ [3] Theft ]");

		cepStatement1.addListener(new RepetitionListener());
	}

}
