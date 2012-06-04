package haw.ci.lib;

import java.util.Iterator;

import haw.ci.lib.nodes.*;

public class SymTaG {
	static public SymbolTable build(AbstractNode node) {
		SymbolTable table = new SymbolTable();

		if (node instanceof ActualParametersNode) { }
		if (node instanceof AssignmentNode) { }
		
		if (node instanceof ConstNode) {
			table.declare(((ConstNode) node).getIdent().toString(), null);
		}
		if (node instanceof ConstListNode) {
			for (ConstNode e : ((ConstListNode) node).getList()) {
				table.declare(((ConstNode) e).getIdent().toString(), null);
			}
		}
		if (node instanceof DeclarationNode) { }
		if (node instanceof ExpressionNode) { }
		if (node instanceof FieldListNode) { }
		if (node instanceof FormalParameterNode) { }
		if (node instanceof FormalParameterSectionNode) { }
		if (node instanceof IdentListNode) { }
		if (node instanceof IfStatementNode) { }
		if (node instanceof IntegerNode) { }
		if (node instanceof ModuleNode) { }
		if (node instanceof NegatedNode) { }
		if (node instanceof ProcedureBodyNode) { }
		if (node instanceof ProcedureCallNode) { }
		if (node instanceof ProcedureHeadingNode) { }
		if (node instanceof ProcedureNode) { }
		if (node instanceof RecordTypeNode) { }
		if (node instanceof SelectorNode) { }
		if (node instanceof StringNode) { }
		if (node instanceof VarListNode) { }
		if (node instanceof VarNode) { }
		if (node instanceof WhileStatementNode) { }
		
		return table;
	}
}
