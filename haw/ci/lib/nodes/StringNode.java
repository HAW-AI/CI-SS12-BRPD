package haw.ci.lib.nodes;

public class StringNode extends AbstractNode {
	private static final long serialVersionUID = 6088338929581231417L;
	private final String value;

	public StringNode(String value) {
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
		if (object instanceof StringNode) {
			StringNode otherNode = (StringNode) object;
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
