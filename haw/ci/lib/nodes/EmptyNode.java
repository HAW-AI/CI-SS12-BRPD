package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

public class EmptyNode extends AbstractNode {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString(int indentation) {
		return toString(indentation, "EmptyNode");
	}

	@Override
	public Descriptor compile(SymbolTable symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
