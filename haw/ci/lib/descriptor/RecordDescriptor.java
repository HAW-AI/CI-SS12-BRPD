package haw.ci.lib.descriptor;

import haw.ci.lib.SymbolTable;

public class RecordDescriptor implements TypeDescriptor {

	private SymbolTable fields;

	@Override
	public int size() {
		return fields.size();
	}
	
	public SymbolTable fields() {
		return fields;
	}

}
