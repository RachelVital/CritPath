package org.camunda.bpm.critpath.domain.enumerations;

public enum DependenceType {

	FINISH_START(1), START_START(2), FINISH_FINISH(3),START_FINISH(4);
	
	private final int valor;
	
    DependenceType(int valorOpcao){
        valor = valorOpcao;
    }
    
    public int getValor(){
    	
        return valor;
    }
    
    
    public static DependenceType fromInteger(int x) {
        switch(x) {
        case 1:
            return DependenceType.FINISH_START;
        case 2:
            return DependenceType.START_START;
        case 3:
        	return DependenceType.FINISH_FINISH;
        case 4:
        	return DependenceType.START_FINISH;
        }
        return null;
    }

}
