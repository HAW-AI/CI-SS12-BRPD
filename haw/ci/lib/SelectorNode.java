package haw.ci.lib;

public class SelectorNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final IdentNode ident;
	
	public SelectorNode(IdentNode ident) {
		this.ident = ident;
	}
	
	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((ident == null) ? 0 : ident.hashCode());
        return result;
	}

	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof SelectorNode) {
			SelectorNode otherNode = (SelectorNode) object;
			if (ident == null && otherNode.ident != null) {
				return false;
			} else if (!ident.equals(otherNode.ident)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

}
