package haw.ci.lib;

import java.util.List;

public class RecordTypeNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final List<FieldListNode> fieldsList;

	public RecordTypeNode(List<FieldListNode> fieldsList) {
		this.fieldsList = fieldsList;
	}

	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((fieldsList == null) ? 0 : fieldsList.hashCode());
        return result;
	}

	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof IdentNode) {
			RecordTypeNode otherNode = (RecordTypeNode) object;
			if (fieldsList == null && otherNode.fieldsList != null) {
				return false;
			} else if (!fieldsList.equals(otherNode.fieldsList)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
