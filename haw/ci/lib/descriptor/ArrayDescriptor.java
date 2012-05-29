package haw.ci.lib.descriptor;

import haw.ci.lib.nodes.TypeNode;

public class ArrayDescriptor implements TypeDescriptor {

	private TypeNode type;

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int numberOfElements() {
		return 0;
	}
	
	public TypeNode type() {
		return type;
	}

}
