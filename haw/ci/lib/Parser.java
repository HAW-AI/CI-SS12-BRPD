package haw.ci.lib;

import static haw.ci.lib.Tokens.*;

import java.util.Arrays;
import java.util.List;

public class Parser {
	private ITokenStream tokenStream;
	private Yytoken current;
	public Parser(ITokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}
//IdentList = ident {Õ,Õ ident}.
	private void IdentList() {
		accept(IDENTIFER);
		while (accept(COMMA)) {
			accept(IDENTIFER);
		}
	}
//ArrayType = ÕARRAYÕ Õ[Õ IndexExpression Õ]Õ ÕOFÕ Type.
	private void Arraytype() {
		accept(ARRAY);
		accept(BRACE_SQUARE_OPEN);
		IndexExpression();
		accept(OF);
		Type();
	}
//FieldList = [IdentList Õ:Õ Type].
	private void FieldList() {
		if (accept(IDENTIFER)) {
			accept(COLON);
			Type();
		}
	}
//RecordType = ÕRECORDÕ FieldList {Õ;Õ FieldList} ÕENDÕ.
	private void RecordType() {
		accept(RECORD);
		FieldList();
		while(accept(SEMICOLON)) {
			FieldList();
		}
		accept(END);
	}
//Type = ident | ArrayType | RecordType.
	private void Type() {
		switch (current.getToken()) {
		case IDENTIFER:
			// was hier?
			break;
		case ARRAY:
			Arraytype();
			break;
		case RECORD:
			RecordType();
			break;
		}
	}
//FPSection = [ÕVARÕ] IdentList Õ:Õ Type.
	private void FPSection() {
		accept(VAR);
		IdentList();
		accept(COLON);
		Type();
	}
//FormalParameters = FPSection {Õ;Õ FPSection}.
	private void FormalParameters() {
		FPSection();
		while (accept(SEMICOLON)) {
			FPSection();
		}
	}
//ProcedureHeading = ÕPROCEDUREÕ ident Õ(Õ [FormalParameters] Õ)Õ.
	private void ProcedureHeading() {
		accept(PROCEDURE);
		accept(IDENTIFER);
		accept(BRACE_ROUND_OPEN);
		FormalParameters();
		accept(BRACE_ROUND_CLOSE);
	}
//ProcedureBody = Declarations ÕBEGINÕ StatementSequence ÕENDÕ.
	private void ProcedureBody() {
		Declarations();
		accept(BEGIN);
		StatementSequence();
		accept(END);
	}
//ProcedureDeclaration = ProcedureHeading Õ;Õ ProcedureBody ident.
	private void ProcedureDeclatation() {
		ProcedureHeading();
		accept(SEMICOLON);
		ProcedureBody();
		accept(IDENTIFER);
	}
//Declarations = 
//	[ÕCONSTÕ ident Õ=Õ Expression Õ;Õ {ident Õ=Õ Expression Õ;Õ}] 
//	[ÕTYPEÕ ident Õ=Õ Type Õ;Õ {ident Õ=Õ Type Õ;Õ}]
//	[ÕVARÕ IdentList Õ:Õ Type Õ;Õ {IdentList Õ:Õ Type Õ;Õ}] 
// 	{ProcedureDeclaration Õ;Õ}.
	private void Declarations() {
		// TODO: implement
	}
//Module = ÕMODULEÕ ident Õ;Õ Declarations ÕBEGINÕ StatementSequence ÕENDÕ ident Õ.Õ.
	private void Module() {
		accept(MODULE);
		accept(IDENTIFER);
		accept(SEMICOLON);
		Declarations();
		accept(BEGIN);
		StatementSequence();
		accept(END);
		accept(IDENTIFER);
		accept(DOT);
	}
//Assignment = ident Selector Õ:=Õ Expression.
	private void Assignment() {
		accept(IDENTIFER);
		accept(ASSIGN);
		Expression();
	}
//ActualParameters = Expression {Õ,Õ Expression}.
	private void ActualParameters() {
		Expression();
		while(accept(COMMA)) {
			Expression();
		}
	}
//ProcedureCall = ident Õ(Õ [ActualParameters] Õ)Õ.
	private void ProcedureCall() {
		accept(IDENTIFER);
		accept(BRACE_ROUND_OPEN);
		ActualParameters();
		accept(BRACE_ROUND_CLOSE);
	}
//IfStatement = ÕIFÕ Expression ÕTHENÕ StatementSequence {ÕELSIFÕ Expression ÕTHENÕ StatementSequence} [ÕELSEÕ StatementSequence] ÕENDÕ.
	private void IfStatement() {
		accept(IF);
		Expression();
		accept(THEN);
		StatementSequence();
		while (accept(ELSIF)) {
			Expression();
			accept(THEN);
			StatementSequence();
		}
		if (accept(ELSE)) {
			StatementSequence();
		}
		accept(END);
	}
//WhileStatement = ÕWHILEÕ Expression ÕDOÕ StatementSequence ÕENDÕ.
	private AbstractNode WhileStatement() {
		AbstractNode node, expression;
		StatementSequenceNode statementSequence;
		node = null;
		if (accept(WHILE)) {
			expression = Expression();
			if (accept(DO)) {
				statementSequence = StatementSequence();
				if (accept(END)) {
					node = new WhileStatementNode(expression, statementSequence);
				}
			}
		}
		return node;
	}
//RepeatStatement = ÕREPEATÕ StatementSequence ÕUNTILÕ Expression.
	private void RepeatStatement() {
		accept(REPEAT);
		StatementSequence();
		accept(UNTIL);
		Expression();
	}
//Statement = [Assignment | ProcedureCall | IfStatement | ÕPRINTÕ Expression | WhileStatement | RepeatStatement].
	private AbstractNode Statement() {
		// TODO: implement
		return null;
	}
//StatementSequence = Statement {Õ;Õ Statement}.
	private StatementSequenceNode StatementSequence() {
		List<AbstractNode> list = Arrays.asList(Statement());

		while (accept(SEMICOLON)) {
			list.add(Statement());
		}

		return new StatementSequenceNode(list);
	}
//Selector = {Õ.Õ ident | Õ[Õ Expression Õ]Õ}.
	private SelectorNode Selector() {
		SelectorNode node = null;
		// TODO: while
		switch(current.getToken()) {
		case DOT:
			next();
			if (accept(IDENTIFER)) {
				IdentNode ident = new IdentNode(current.getValue());
				node = new SelectorNode(ident);
			}
			break;
		case BRACE_SQUARE_OPEN:
			// TODO
			next();
			Expression();
			accept(BRACE_SQUARE_CLOSE);
			break;
		}
		return node;
	}
//Factor = ident Selector | integer | string | Read | Õ(Õ Expression Õ)Õ.
	private AbstractNode Factor() {
		AbstractNode node = null;
		switch(current.getToken()) {
		case IDENTIFER:
			break;
		case INTEGER:
			node = new IntegerNode(Integer.getInteger(current.getValue()));
			break;
		// TODO: String
		case READ:
			Read();
			break;
		case BRACE_ROUND_OPEN:
			next();
			Expression();
			accept(BRACE_ROUND_CLOSE);
			break;
		}
		return null;
	}
//Read = READ [Prompt].
	private void Read() {
		accept(READ);
		Prompt();
	}
//Prompt = string.
	private void Prompt() {
		// TODO: implement
	}
//Term = Factor {(Õ*Õ | Õ/Õ) Factor}.
	private AbstractNode Term() {
		AbstractNode node = Factor();

		if (accept(MATH_MUL) || accept(MATH_DIV)) {
			node = new BinaryOperationNode(current.getToken(), node, Factor());
		}

		return node;
	}
//SimpleExpression = [Õ-Õ] Term {(Õ+Õ | Õ-Õ) Term}.
	private AbstractNode SimpleExpression() {
		AbstractNode node;
		if (accept(MATH_SUB)) {
			node = new NegatedNode(Term());
		} else {
			node = Term();
		}

		if (accept(MATH_ADD) || accept(MATH_SUB)) {
			node = new BinaryOperationNode(current.getToken(), node, Term());
		}

		return node;
	}
//Expression = SimpleExpression [(Õ=Õ | Õ#Õ | Õ<Õ | Õ<=Õ | Õ>Õ | Õ>=Õ) SimpleExpression].
	private AbstractNode Expression() {
		AbstractNode node;
		node = SimpleExpression();
		while (accept(EQUAL) || accept(LESS) || accept(LESS_EQUAL) || accept(MORE) || accept(MORE_EQUAL)) {
			node = new BinaryOperationNode(current.getToken(), node, SimpleExpression());
		}

		return node;
	}
//IndexExpression = integer | ConstIdent.
	private void IndexExpression() {
		ConstIdent();
	}
//ConstIdent = ident.
	private IdentNode ConstIdent() {
		IdentNode node = null;
		if (accept(IDENTIFER)) {
			node = new IdentNode(current.getValue());
		}
		return node;
	}

	public String build() {
		next();
		Module();
		return "yeah";
	}

	private Yytoken next() {
		return this.current = tokenStream.nextToken();
	}
	
	private boolean accept(Tokens token) {
		if (current.getToken() == token) {
			next();
			return true;
		}
		return false;
	}
	
	private boolean except(Tokens token) {
		if (accept(token)) {
			System.out.println(String.format("%s not accepted at line %d", token, current.getLine()));
			return false;
		}
		return true;
	}
}
