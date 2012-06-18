package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;
import haw.ci.lib.descriptor.SimpleTypeDescriptor;
import haw.ci.lib.descriptor.TypeDescriptor;
import haw.ci.lib.descriptor.SimpleTypeDescriptor.Type;

public class FieldListNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final IdentListNode identList;
	private final AbstractNode type;

	public FieldListNode(IdentListNode identList, AbstractNode type) {
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
		FieldListNode other = (FieldListNode) obj;
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
		    result += identList.toString(indentation+1) + "\n";
		}
		if(type != null) {
		    result += type.toString(indentation+1);
		}
	    return result;
	}
	
	@Override
	public Descriptor compile(SymbolTable symbolTable) {
		Descriptor typeDescriptor = null;
		if (type instanceof IdentNode) {
			if (((IdentNode) type).getIdentifierName().equals("boolean")) {
				typeDescriptor = new SimpleTypeDescriptor(Type.BOOLEAN);
			} else if (((IdentNode) type).getIdentifierName().equals("integer")) {
				typeDescriptor = new SimpleTypeDescriptor(Type.INTEGER);
			} else if (((IdentNode) type).getIdentifierName().equals("string")) {
				typeDescriptor = new SimpleTypeDescriptor(Type.STRING);
			} else {
				System.out.println("Error occured in VarNode compile.");
			}
		}
		identList.compile(symbolTable, typeDescriptor);
		return null;
    }
}
