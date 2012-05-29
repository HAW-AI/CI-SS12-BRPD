package haw.ci.lib.descriptor;

public class VariableDescriptor implements Descriptor {

	private TypeDescriptor type;

	public int address() {
		return 0;
	}
	
	// Dont know what this is supposed to mean
	public boolean isvarpar() {
		return false;
	}
	
	public TypeDescriptor type() {
		return type;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
