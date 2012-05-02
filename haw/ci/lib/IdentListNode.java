package haw.ci.lib;

import java.util.List;

public class IdentListNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final List<IdentNode> identNodes;

	public IdentListNode(List<IdentNode> identNodes) {
		this.identNodes = identNodes;
	}

	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((identNodes == null) ? 0 : identNodes.hashCode());
        return result;
	}

	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof IdentNode) {
			IdentListNode otherNode = (IdentListNode) object;
			if (identNodes == null && otherNode.identNodes != null) {
				return false;
			} else if (!identNodes.equals(otherNode.identNodes)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
