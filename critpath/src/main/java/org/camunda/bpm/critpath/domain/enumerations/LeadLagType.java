package org.camunda.bpm.critpath.domain.enumerations;

public enum LeadLagType {
	
	LEAD(1), LAG(2);
	
	private final int valor;
	
    private LeadLagType(int valorOpcao){
        valor = valorOpcao;
    }
    
    public int getValor(){
    	
        return valor;
    }

}
