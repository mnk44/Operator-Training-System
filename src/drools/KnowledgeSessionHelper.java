package drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Es una clase para interactuar con el motor de inferencia del sistema experto
 * 
 * **/

public class KnowledgeSessionHelper {

	//para interactuar con el motor
	public static KieContainer createRuleBase(){
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		return kc;
	}

	//para cargar el fichero con la definicion de reglas
	public static KieSession getStatefulKnowledgeSession(KieContainer kc, String sessionName){
		KieSession kSession = kc.newKieSession(sessionName);
		return kSession;
	} 
}
