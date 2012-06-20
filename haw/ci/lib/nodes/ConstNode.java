package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.Tokens;
import haw.ci.lib.descriptor.ConstDescriptor;
import haw.ci.lib.descriptor.Descriptor;
import haw.ci.lib.descriptor.TypeDescriptor;

public class ConstNode extends AbstractNode implements IntegerValue {
	private static final long serialVersionUID = 4386388009177664496L;

	private IdentNode ident;
	private AbstractNode expression;

	public ConstNode(IdentNode ident, AbstractNode expression) {
		this.ident = ident;
		this.expression = expression;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
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
		ConstNode other = (ConstNode) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		return true;
	}

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if (expression != null) {
			result += expression.toString(indentation + 1);
		}
		return result;
	}

	public Descriptor compile(SymbolTable symbolTable) {
		expression.compile(symbolTable);
		symbolTable.declareConst(ident.getIdentifierName(), new ConstDescriptor(this));
		return null;
	}

	@Override
	public int getValue() {
		// TODO: Fix me, should we evaluate the full expression stack? (needed for Array declaration)
		return 0;
	}
}
