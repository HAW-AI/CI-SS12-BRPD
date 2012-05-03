package haw.ci.lib.nodes;

public class IdentNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final String value;

	public IdentNode(String value) {
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
		if (object instanceof IdentNode) {
			IdentNode otherNode = (IdentNode) object;
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
	
	public String toString() {
		return this.value;
	}

}
