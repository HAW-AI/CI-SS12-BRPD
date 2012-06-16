package haw.ci.lib.nodes;

public class FormalParameterSectionNode extends AbstractNode {
	private static final long serialVersionUID = -4416688308390577830L;
	private IdentListNode identList;
	private AbstractNode type;
	
	public FormalParameterSectionNode(IdentListNode identList, AbstractNode type) {
		this.identList = identList;
		this.type = type;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identList == null) ? 0 : identList.hashCode());
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
		FormalParameterSectionNode other = (FormalParameterSectionNode) obj;
		if (identList == null) {
			if (other.identList != null)
				return false;
		} else if (!identList.equals(other.identList))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(identList != null) {
		    result += identList.toString() + "\n";
		}
		if(type != null) {
		    result += type.toString(indentation+1);
		}

	    return result;
	}
}
