package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;
import haw.ci.lib.descriptor.ProcedureDescriptor;

public class ProcedureNode extends AbstractNode {
	private static final long serialVersionUID = -8384343829441859287L;
	private ProcedureHeadingNode procedureHeading;
	private ProcedureBodyNode procedureBody;

	public ProcedureNode(ProcedureHeadingNode procedureHeading,
			ProcedureBodyNode procedureBody) {
		this.procedureHeading = procedureHeading;
		this.procedureBody = procedureBody;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((procedureBody == null) ? 0 : procedureBody.hashCode());
		result = prime
				* result
				+ ((procedureHeading == null) ? 0 : procedureHeading.hashCode());
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
		ProcedureNode other = (ProcedureNode) obj;
		if (procedureBody == null) {
			if (other.procedureBody != null)
				return false;
		} else if (!procedureBody.equals(other.procedureBody))
			return false;
		if (procedureHeading == null) {
			if (other.procedureHeading != null)
				return false;
		} else if (!procedureHeading.equals(other.procedureHeading))
			return false;
		return true;
	}

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(procedureHeading != null) {
		    result += procedureHeading.toString() + "\n";
		}
		if(procedureBody != null) {
		    result += procedureBody.toString() + "\n";
		}

	    return result;
	}
	
	public Descriptor compile(SymbolTable symbolTable) {
		Descriptor descriptor = null;
		SymbolTable localSymboleTable = new SymbolTable(symbolTable);
		procedureHeading.compile(localSymboleTable);
		String name = procedureHeading.getName();
		int startAddress = getNextLabelNumber();
		int lengthparblock = localSymboleTable.size();
		int framesize = localSymboleTable.size() + 3 +2;// 3 saved registers
		int size = framesize; //TODO same as framesize??

		label(startAddress);

		pushReg("RK");
		pushReg("FP");
		pushI(1);
		pushReg("SL");
		get("SP");
		set("FP");
		get("FP");
		pushI(1);
		set("SL");
		get("SP");
		pushI(3);
		add();
		set("SP");
		
		debug("");
		procedureBody.compile(localSymboleTable);
		debug("");
		
		//TODO new Label??
		get("FP");
		set("SP");
		pushI(1);
		popReg("SL");
		popReg("FP");
		popReg("RK");
		
		get("SP");
		pushI(3);
		sub();
		set("SP");
		
		write("REDUCE, " + framesize);
		write("RET");
		debug("--------------------------------------------------------------");
		
		descriptor = new ProcedureDescriptor(localSymboleTable, size, name, startAddress, lengthparblock, framesize);
		symbolTable.declare(procedureHeading.getName(), descriptor);
		return null;
	}


}
