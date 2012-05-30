package haw.ci.lib.nodes;

public class ArrayTypeNode extends AbstractNode {
	private static final long serialVersionUID = -104260727543163557L;
	private AbstractNode indexExpression;
	private TypeNode type;

	public ArrayTypeNode(AbstractNode indexExpression, TypeNode type) {
		this.indexExpression = indexExpression;
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((indexExpression == null) ? 0 : indexExpression.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayTypeNode other = (ArrayTypeNode) obj;
		if (indexExpression == null) {
			if (other.indexExpression != null)
				return false;
		} else if (!indexExpression.equals(other.indexExpression))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
