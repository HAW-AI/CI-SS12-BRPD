package haw.ci.lib.nodes;

public class TypeDeclarationNode extends AbstractNode {
	private static final long serialVersionUID = -1319709422716461088L;
	private IdentNode ident;
	private TypeNode type;
	
	public TypeDeclarationNode(IdentNode ident, TypeNode type) {
		this.ident = ident;
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
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
		TypeDeclarationNode other = (TypeDeclarationNode) obj;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TypeDeclarationNode [ident=" + ident + ", type=" + type + "]";
	}
	
}
