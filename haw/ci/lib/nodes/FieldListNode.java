package haw.ci.lib.nodes;

public class FieldListNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final IdentListNode identList;
	private final TypeNode type;

	public FieldListNode(IdentListNode identList, TypeNode type) {
		this.identList = identList;
		this.type = type;
	}

	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((identList == null) ? 0 : identList.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
	}
	
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof FieldListNode) {
			FieldListNode otherNode = (FieldListNode) object;
			if (identList == null && otherNode.identList != null) {
				return false;
			} else if (!identList.equals(otherNode.identList)) {
				return false;
			} else if (type == null && otherNode.type != null) {
				return false;
			} else if (!type.equals(otherNode.type)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
