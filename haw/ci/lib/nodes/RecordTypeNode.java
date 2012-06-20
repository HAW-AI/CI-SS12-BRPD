package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;
import haw.ci.lib.descriptor.RecordDescriptor;

import java.util.List;

public class RecordTypeNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final List<FieldListNode> fieldsList;

	public RecordTypeNode(List<FieldListNode> fieldsList) {
		this.fieldsList = fieldsList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fieldsList == null) ? 0 : fieldsList.hashCode());
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
		RecordTypeNode other = (RecordTypeNode) obj;
		if (fieldsList == null) {
			if (other.fieldsList != null)
				return false;
		} else if (!fieldsList.equals(other.fieldsList))
			return false;
		return true;
	}

	@Override
	public String toString(int indentation) {
		String output = toString(indentation, this.getClass().getName() + "\n");
		if (fieldsList != null) {
			for (AbstractNode node : fieldsList) {
				if (node != null) {
					output += node.toString(indentation+1) + "\n";
				}
			}
		}
		return  output;
	}

	@Override
	public Descriptor compile(SymbolTable parentSymbolTable) {
		SymbolTable symbolTable = new SymbolTable(parentSymbolTable);
		for (AbstractNode e : fieldsList) {
			e.compile(symbolTable);
		}
		return new RecordDescriptor(symbolTable);
    }
}
