package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;

import java.io.Serializable;

public abstract class AbstractNode implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object symbolTable;

	public String toString() {
		return this.getClass().getName();
	}
	
	/*
	 * Zu klärende Fragen:
	 * 1) Muss die SymbolTabelle vollständig aufgebaut sein, bevor der codeGen prozess begonnen wird?
	 * 2) Sollte jeder Node in ein Descriptor aufgehen? 
	 * 3) Die Descriptoren werden in die SymbolTabelle geschrieben?
	 */
	
	
	/*
	 * 1) trigger sym. tab. generation.
	 * 2) genrate code based on symboltable
	 */
	public String compile() {
		symbolTable(new SymbolTable());
//		return generateCode();
		return "";
	}
	
	protected void symbolTable(SymbolTable symbolTable) {
		if (this.symbolTable != null) return;
		this.symbolTable = symbolTable;
//		buildSymbolTable();
	}
	/*
	 * declare variables or create child table
	 */
//	protected abstract void buildSymbolTable();
	
//	protected abstract String generateCode();
	
}
