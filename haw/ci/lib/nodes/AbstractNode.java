package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;

import java.io.Serializable;

public abstract class AbstractNode implements Serializable {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return this.getClass().getName();
	}
	
	public String compile() {
		SymbolTable symbolTable = symbolTable();
		return generateCode();
	}
	
	public abstract SymbolTable symbolTable();
	public abstract String generateCode();
	
}
