package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

import java.io.Serializable;

public abstract class AbstractNode implements Serializable {
	public static boolean DEBUG = false;
	private static final long serialVersionUID = 1L;
	private Object symbolTable;
	private static int stackSize=0;
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
	
	public Descriptor compile(Boolean debug) {
		DEBUG = debug;
		compile();
		return null;
	}
	
	public Descriptor compile() {
		return compile(new SymbolTable());
	}

//	public abstract Descriptor compile(SymbolTable symbolTable);
	public Descriptor compile(SymbolTable symbolTable){
		return null;
    }
    
    public Descriptor compile(SymbolTable symbolTable, Descriptor desct){
    	return null;
    }
    
    public void write(String code) {
    	if (DEBUG) {
    	System.out.println(String.format("S:%d [%30s]: %s",this.stackSize, this.getClass().getSimpleName(),code));
    	} else {
    		System.out.println(code);
    	}
    }
    public void debug(String msg) {
    	if (DEBUG) {
    		System.out.println(String.format("--- %s", msg));
    	}
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
    	this.stackSize++;
    }
    public void popReg(String register) {
    	write(String.format("POPREG, %s", register));
    	this.stackSize--;
    }
    public void pushI(int i) {
    	write(String.format("PUSHI, %d", i));
    	this.stackSize++;
	}
    public void pushS(String s) {
    	write(String.format("PUSHS, %s", s));
    	this.stackSize++;
	}
    public void assign(int l) {
    	write(String.format("ASSIGN, %d", l));
    	this.stackSize -= l;
	}
    public void cont(int l) {
    	write(String.format("CONT, %d", l));
    	this.stackSize += l;
	}
    public void sub() {
    	write("SUB");
    	this.stackSize--;
    }
    public void add() {
    	write("ADD");
    	this.stackSize--;
    }
    public void mul() {
    	write("MUL");
    	this.stackSize--;
    }
    public void div() {
    	write("DIV");
    	this.stackSize--;
    }
    public void get(String p) {
    	write(String.format("GET%s",p));
    	this.stackSize++;
    }
    public void set(String p) {
    	write(String.format("SET%s",p));
    	this.stackSize--;
    }
    public void print() {
    	write("PRINT");
    	this.stackSize--;
    }
    public void read(String msg) {
    	write("READ, "+msg);
    	this.stackSize++;
    }
}
