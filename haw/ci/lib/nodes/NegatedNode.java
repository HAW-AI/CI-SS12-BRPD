package haw.ci.lib.nodes;

public class NegatedNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final AbstractNode node;
	
	public NegatedNode(AbstractNode node) {
		this.node = node;
	}
	
	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((node == null) ? 0 : node.hashCode());
        return result;
	}
	
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof NegatedNode) {
			NegatedNode otherNode = (NegatedNode) object;
			if (node == null && otherNode.node != null) {
				return false;
			} else {
				return node.equals(otherNode.node);
			}
		} else {
			return false;
		}
	}
	
}
