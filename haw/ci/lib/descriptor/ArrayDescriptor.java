package haw.ci.lib.descriptor;

public class ArrayDescriptor implements TypeDescriptor {

	private TypeDescriptor type;
	private int numberOfElements;
	
	public ArrayDescriptor(int numberOfElements, TypeDescriptor type) {
		this.numberOfElements = numberOfElements;
		this.type  = type;
	}

	@Override
	public int size() {
		return numberOfElements() * type.size();
	}
	
	public int numberOfElements() {
		return numberOfElements;
	}
	
	public TypeDescriptor type() {
		return type;
	}

}
