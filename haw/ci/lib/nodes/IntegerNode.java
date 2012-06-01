package haw.ci.lib.nodes;

public class IntegerNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final Integer value;
	
	public IntegerNode(Integer value) {
		this.value = value;
	}
	
	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
	}

	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof IntegerNode) {
			IntegerNode otherNode = (IntegerNode) object;
			if (value == null && otherNode.value != null) {
				return false;
			} else if (!value.equals(otherNode.value)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(value != null) {
		    result += value.toString() + "\n";
		}

	    return result;
	}

}
