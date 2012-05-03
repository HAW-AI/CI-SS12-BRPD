package haw.ci.lib.nodes;

public class AssignmentNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final SelectorNode selector;
	private final AbstractNode expression;
	private IdentNode ident;

	public AssignmentNode(IdentNode ident, SelectorNode selector, AbstractNode expression) {
		this.ident = ident;
		this.selector = selector;
		this.expression = expression;
	}
	
	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((ident == null) ? 0 : ident.hashCode());
        result = prime * result + ((selector == null) ? 0 : selector.hashCode());
        result = prime * result + ((expression == null) ? 0 : expression.hashCode());
        return result;
	}
	
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof AssignmentNode) {
			AssignmentNode otherNode = (AssignmentNode) object;
			if (selector == null && otherNode.selector != null) {
				return false;
			} else if (!selector.equals(otherNode.selector)) {
				return false;
			} else if (expression == null && otherNode.expression != null) {
				return false;
			} else if (!expression.equals(otherNode.expression)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

}
