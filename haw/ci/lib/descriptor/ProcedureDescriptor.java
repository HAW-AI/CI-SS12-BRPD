package haw.ci.lib.descriptor;

import haw.ci.lib.SymbolTable;

public class ProcedureDescriptor implements Descriptor {

	private SymbolTable params;
	private int size;
	private String name;
	private int startAddress;
	private int lengthparblock;
	private int framesize;

	public ProcedureDescriptor(SymbolTable params, int size, String name,
			int startAddress, int lengthparblock, int framesize) {
		this.params = params;
		this.size = size;
		this.name = name;
		this.startAddress = startAddress;
		this.lengthparblock = lengthparblock;
		this.framesize = framesize;
	}

	@Override
	public int size() {
		return this.size;
	}
	
	public String name() {
		return this.name;
	}
	
	public int startAddress() {
		return this.startAddress;
	}
	
	public int lengthparblock() {
		return this.lengthparblock;
	}
	
	public int framesize() {
		return this.framesize;
	}
	
	public SymbolTable getParams() {
		return params;
	}

	@Override
	public SymbolTable symbolTable() {
		return getParams();
	}

}
