package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

import java.io.Serializable;

public abstract class AbstractNode implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object symbolTable;
	static int labelCount = 1;
	
	/*
	 * Zu klärende Fragen:
	 * 1) Muss die SymbolTabelle vollständig aufgebaut sein, bevor der codeGen prozess begonnen wird?
	 * 2) Sollte jeder Node in ein Descriptor aufgehen? 
	 * 3) Die Descriptoren werden in die SymbolTabelle geschrieben?
	 */
	
	protected void symbolTable(SymbolTable symbolTable) {
		if (this.symbolTable != null) return;
		this.symbolTable = symbolTable;
//		buildSymbolTable();
	}
	/*
	 * declare variables or create child table
	 */
//	protected abstract void buildSymbolTable();
	
	protected String toString(int indentation, String result) {
        String indentedString = "";
        for (int i = 0; i < indentation; ++i) {
            indentedString += " ";
        }    
        return result.replaceAll("^", indentedString);
    }
	
	@Override
    public String toString() {
        return toString(0);
    }

	public abstract String toString(int indentation);

	public static int getNextLabelNumber(){
    	return labelCount++;
    }
	
	public Descriptor compile() {
		return compile(new SymbolTable());
	}

	public abstract Descriptor compile(SymbolTable symbolTable);
//	public Descriptor compile(SymbolTable symbolTable){
//		return null;
//    }
    
    public Descriptor compile(SymbolTable symbolTable, Descriptor desct){
    	return null;
    }
    
    public void write(String code){
    	System.out.println(code);
    }
	
    public void label(int n) {
    	write(String.format("LABEL, %d", n));
    }
    public void jump(int n) {
    	write(String.format("JMP, %d", n));
    }
    public void branchFalse(int n) {
    	write(String.format("BF, %d", n));
    }
    public void pushReg(String register) {
    	write(String.format("PUSHREG, %s", register));
    }
    public void popReg(String register) {
    	write(String.format("POPREG, %s", register));
    }
    public void pushI(int i) {
    	write(String.format("PUSHI, %d", i));
	}
}
