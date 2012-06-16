package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.ArrayDescriptor;
import haw.ci.lib.descriptor.Descriptor;
import haw.ci.lib.descriptor.SimpleTypeDescriptor;
import haw.ci.lib.descriptor.TypeDescriptor;
import haw.ci.lib.descriptor.SimpleTypeDescriptor.Type;

public class ArrayTypeNode extends AbstractNode {
	private static final long serialVersionUID = -104260727543163557L;
	private AbstractNode indexExpression;
	private AbstractNode type;

	public ArrayTypeNode(AbstractNode indexExpression, AbstractNode type) {
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

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(indexExpression != null)
		         result += indexExpression.toString(indentation+1) + "\n";
		if(type != null)
		result += type.toString(indentation+1) + "\n";
		
		return result;
	}

	@Override
	public Descriptor compile(SymbolTable symbolTable) {
		int numberOfElements = ((IntegerValue)indexExpression).getValue(); // TODO: Fix me!

		TypeDescriptor descriptor = null;
		if (((IdentNode) type).getIdentifierName().equals("boolean")) {
			descriptor = new SimpleTypeDescriptor(Type.BOOLEAN);
		} else if (((IdentNode) type).getIdentifierName().equals("integer")) {
			descriptor = new SimpleTypeDescriptor(Type.INTEGER);
		} else if (((IdentNode) type).getIdentifierName().equals("string")) {
			descriptor = new SimpleTypeDescriptor(Type.STRING);
		} else {
			System.out.println("Error occured in VarNode compile.");
		}
		return new ArrayDescriptor(numberOfElements, descriptor);
	}
}
