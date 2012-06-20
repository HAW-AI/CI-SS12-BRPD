package haw.ci.lib.descriptor;

import haw.ci.lib.SymbolTable;

public class VariableDescriptor implements Descriptor {

	private TypeDescriptor type;
	private int address;

	public VariableDescriptor(TypeDescriptor type, int address) {
		this.type = type;
		this.address = address;
	}
	
	public int address() {
		return address;
	}
	
	public TypeDescriptor type() {
		return type;
	}
	
	@Override
	public int size() {
		return -1;
	}

	@Override
	public SymbolTable symbolTable() {
		return null;
	}

}
