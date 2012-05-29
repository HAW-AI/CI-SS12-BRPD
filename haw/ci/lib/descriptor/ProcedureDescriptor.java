package haw.ci.lib.descriptor;

import haw.ci.lib.SymbolTable;

public class ProcedureDescriptor implements Descriptor {

	private SymbolTable params;

	public ProcedureDescriptor(SymbolTable params){
		this.params = params;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String name() {
		return "foo";
	}
	
	public int startAddress() {
		return 0;
	}
	
	public int lengthparblock() {
		return 0;
	}
	
	public int framesize() {
		return 0;
	}
	
	public SymbolTable getParams() {
		return params;
	}

}
