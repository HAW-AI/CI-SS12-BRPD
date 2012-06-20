package haw.ci.lib.descriptor;

import haw.ci.lib.SymbolTable;

public class RecordDescriptor implements TypeDescriptor {

	private SymbolTable fields;

	public RecordDescriptor(SymbolTable symbolTable) {
		fields = symbolTable;
	}

	@Override
	public int size() {
		return fields.size();
	}
	
	public SymbolTable fields() {
		return fields;
	}

	@Override
	public SymbolTable symbolTable() {
		return fields();
	}

}
