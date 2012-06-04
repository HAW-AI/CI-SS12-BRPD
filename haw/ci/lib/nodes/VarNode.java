package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;
import haw.ci.lib.descriptor.SimpleTypeDescriptor;
import haw.ci.lib.descriptor.SimpleTypeDescriptor.Type;

public class VarNode extends AbstractNode {
	private static final long serialVersionUID = -8257259901064314426L;
	private IdentListNode identList;
	private AbstractNode type;
	
	public VarNode(IdentListNode identList, AbstractNode type) {
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
		VarNode other = (VarNode) obj;
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
		    result += type.toString() + "\n";
		}

	    return result;
	}
	
	public Descriptor compile(SymbolTable symbolTable) {
		Descriptor descriptor = null;
		
		if (type instanceof IdentNode) {
			String identifierName = ((IdentNode) type).getIdentifierName();
			if (identifierName.equals("boolean")) {
				descriptor = new SimpleTypeDescriptor(Type.BOOLEAN);
			} else if (identifierName.equals("integer")) {
				descriptor = new SimpleTypeDescriptor(Type.INTEGER);
			} else if (identifierName.equals("string")) {
				descriptor = new SimpleTypeDescriptor(Type.STRING);
			} else {
				System.out.println("Error occured in VarNode compile.");
			}
		} else {
			descriptor = type.compile(symbolTable);
		}
		identList.compile(symbolTable, descriptor);
		
		return null;
	}

}
