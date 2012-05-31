package haw.ci.lib;

import static haw.ci.lib.Tokens.ARRAY;
import static haw.ci.lib.Tokens.ASSIGN;
import static haw.ci.lib.Tokens.BEGIN;
import static haw.ci.lib.Tokens.BRACE_ROUND_CLOSE;
import static haw.ci.lib.Tokens.BRACE_ROUND_OPEN;
import static haw.ci.lib.Tokens.BRACE_SQUARE_CLOSE;
import static haw.ci.lib.Tokens.BRACE_SQUARE_OPEN;
import static haw.ci.lib.Tokens.COLON;
import static haw.ci.lib.Tokens.COMMA;
import static haw.ci.lib.Tokens.CONST;
import static haw.ci.lib.Tokens.DO;
import static haw.ci.lib.Tokens.DOT;
import static haw.ci.lib.Tokens.ELSE;
import static haw.ci.lib.Tokens.ELSIF;
import static haw.ci.lib.Tokens.END;
import static haw.ci.lib.Tokens.EQUAL;
import static haw.ci.lib.Tokens.IDENTIFER;
import static haw.ci.lib.Tokens.IF;
import static haw.ci.lib.Tokens.LESS;
import static haw.ci.lib.Tokens.LESS_EQUAL;
import static haw.ci.lib.Tokens.MATH_ADD;
import static haw.ci.lib.Tokens.MATH_DIV;
import static haw.ci.lib.Tokens.MATH_MUL;
import static haw.ci.lib.Tokens.MATH_SUB;
import static haw.ci.lib.Tokens.MODULE;
import static haw.ci.lib.Tokens.MORE;
import static haw.ci.lib.Tokens.MORE_EQUAL;
import static haw.ci.lib.Tokens.NOT_EQUAL;
import static haw.ci.lib.Tokens.OF;
import static haw.ci.lib.Tokens.PRINT;
import static haw.ci.lib.Tokens.PROCEDURE;
import static haw.ci.lib.Tokens.READ;
import static haw.ci.lib.Tokens.RECORD;
import static haw.ci.lib.Tokens.REPEAT;
import static haw.ci.lib.Tokens.SEMICOLON;
import static haw.ci.lib.Tokens.STRING;
import static haw.ci.lib.Tokens.THEN;
import static haw.ci.lib.Tokens.TYPE;
import static haw.ci.lib.Tokens.UNTIL;
import static haw.ci.lib.Tokens.VAR;
import static haw.ci.lib.Tokens.WHILE;
import haw.ci.lib.nodes.AbstractNode;
import haw.ci.lib.nodes.ActualParametersNode;
import haw.ci.lib.nodes.ArrayTypeNode;
import haw.ci.lib.nodes.AssignmentNode;
import haw.ci.lib.nodes.ConstListNode;
import haw.ci.lib.nodes.ConstNode;
import haw.ci.lib.nodes.DeclarationNode;
import haw.ci.lib.nodes.EmptyNode;
import haw.ci.lib.nodes.ExpressionNode;
import haw.ci.lib.nodes.FieldListNode;
import haw.ci.lib.nodes.FormalParameterNode;
import haw.ci.lib.nodes.FormalParameterSectionNode;
import haw.ci.lib.nodes.IdentListNode;
import haw.ci.lib.nodes.IdentNode;
import haw.ci.lib.nodes.IdentifierTypeNode;
import haw.ci.lib.nodes.IfStatementNode;
import haw.ci.lib.nodes.IntegerNode;
import haw.ci.lib.nodes.ModuleNode;
import haw.ci.lib.nodes.NegatedNode;
import haw.ci.lib.nodes.PrintNode;
import haw.ci.lib.nodes.ProcedureBodyNode;
import haw.ci.lib.nodes.ProcedureCallNode;
import haw.ci.lib.nodes.ProcedureHeadingNode;
import haw.ci.lib.nodes.ProcedureNode;
import haw.ci.lib.nodes.ReadNode;
import haw.ci.lib.nodes.RecordTypeNode;
import haw.ci.lib.nodes.RepeatStatementNode;
import haw.ci.lib.nodes.SelectorNode;
import haw.ci.lib.nodes.StatementNode;
import haw.ci.lib.nodes.StatementSequenceNode;
import haw.ci.lib.nodes.StringNode;
import haw.ci.lib.nodes.TypeDeclarationNode;
import haw.ci.lib.nodes.TypeNode;
import haw.ci.lib.nodes.VarListNode;
import haw.ci.lib.nodes.VarNode;
import haw.ci.lib.nodes.WhileStatementNode;

import java.util.ArrayList;
import java.util.List;

public class Parser {
	private final boolean DEBUG = true;
	private ITokenStream tokenStream;
	private Yytoken current;
	private Yytoken next;
	public Parser(ITokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}
//IdentList = ident {Õ,Õ ident}.
	private IdentListNode IdentList() throws ParserAcceptError {
		IdentListNode identList = new IdentListNode();
		if (test(IDENTIFER)) {
			identList.add(Ident());
		}
		while (test(COMMA)) {
			next();
			identList.add(Ident());
		}
		return identList;
	}
//ArrayType = ÕARRAYÕ Õ[Õ IndexExpression Õ]Õ ÕOFÕ Type.
	private TypeNode Arraytype() throws ParserAcceptError {
		require(ARRAY);
		require(BRACE_SQUARE_OPEN);
		AbstractNode node = IndexExpression();
		require(BRACE_SQUARE_CLOSE);
		require(OF);
		TypeNode type = Type();
		return new ArrayTypeNode(node, type);
	}
//FieldList = [IdentList Õ:Õ Type].
	private FieldListNode FieldList() throws ParserAcceptError {
		IdentListNode identList = IdentList();
		FieldListNode node = null;

		if (test(COLON)) {
			require(COLON);
			node = new FieldListNode(identList, Type());
		}
		return node;
	}
//RecordType = ÕRECORDÕ FieldList {Õ;Õ FieldList} ÕENDÕ.
	private TypeNode RecordType() throws ParserAcceptError {
		require(RECORD);
		List<FieldListNode> fieldLists = new ArrayList<FieldListNode>();
		FieldListNode fieldList = FieldList();
		if (fieldList != null) {
			fieldLists.add(fieldList);
		}

		while(test(SEMICOLON)) {
			require(SEMICOLON);
			fieldList = FieldList();
			if(fieldList != null) {
				fieldLists.add(FieldList());
			}
		}
		require(END);

		return new RecordTypeNode(fieldLists);
	}
//Type = ident | ArrayType | RecordType.
	private TypeNode Type() throws ParserAcceptError {
		TypeNode type = null;
		switch (current.getToken()) {
		case IDENTIFER:
			type = new IdentifierTypeNode(current.getValue());
			next();
			break;
		case ARRAY:
			type = Arraytype();
			break;
		case RECORD:
			type = RecordType();
			break;
		}
		return type;
	}
//FPSection = [ÕVARÕ] IdentList Õ:Õ Type.
	private FormalParameterSectionNode FPSection() throws ParserAcceptError {
		require(VAR);
		IdentListNode identList = IdentList();
		require(COLON);
		TypeNode type = Type();
		return new FormalParameterSectionNode(identList, type);
	}
//FormalParameters = FPSection {Õ;Õ FPSection}.
	private FormalParameterNode FormalParameters() throws ParserAcceptError {
		FormalParameterNode formalParameter = new FormalParameterNode();
		if (test(VAR)) {
			formalParameter.add(FPSection());
			while (test(SEMICOLON)) {
				next();
				formalParameter.add(FPSection());
			}
		}
		return formalParameter;
	}
//ProcedureHeading = ÕPROCEDUREÕ ident Õ(Õ [FormalParameters] Õ)Õ.
	private ProcedureHeadingNode ProcedureHeading() throws ParserAcceptError {
		debug("---------------------- ProcedureHeading");
		require(PROCEDURE);
		IdentNode ident = Ident();
		require(BRACE_ROUND_OPEN);
		FormalParameterNode formalParameter = FormalParameters();
		require(BRACE_ROUND_CLOSE);
		return new ProcedureHeadingNode(ident, formalParameter);
	}
//ProcedureBody = Declarations ÕBEGINÕ StatementSequence ÕENDÕ.
	private ProcedureBodyNode ProcedureBody() throws ParserAcceptError {
		debug("---------------------- ProcedureBody");
		debug("proc body declarations");
		DeclarationNode declaration = Declarations();
		require(BEGIN);
		debug("proc body statements");
		StatementSequenceNode statementSequence = StatementSequence();
		require(END);
		return new ProcedureBodyNode(declaration, statementSequence);
	}
//ProcedureDeclaration = ProcedureHeading Õ;Õ ProcedureBody ident.
	private ProcedureNode ProcedureDeclatation() throws ParserAcceptError {
		debug("---------------------- ProcedureDeclaration");
		debug("get proc head");
		ProcedureHeadingNode procedureHeading = ProcedureHeading();
		require(SEMICOLON);
		debug("get proc body");
		ProcedureBodyNode procedureBody = ProcedureBody();
		require(IDENTIFER);
		require(SEMICOLON);
		return new ProcedureNode(procedureHeading, procedureBody);
	}
//Declarations = 
//	[ÕCONSTÕ ident Õ=Õ Expression Õ;Õ {ident Õ=Õ Expression Õ;Õ}] 
//	[ÕTYPEÕ ident Õ=Õ Type Õ;Õ {ident Õ=Õ Type Õ;Õ}]
//	[ÕVARÕ IdentList Õ:Õ Type Õ;Õ {IdentList Õ:Õ Type Õ;Õ}] 
// 	{ProcedureDeclaration Õ;Õ}.
	private DeclarationNode Declarations() throws ParserAcceptError {
		DeclarationNode declaration = new DeclarationNode();
		if (test(CONST)) {
			declaration.add(Const());
		}
		if (test(TYPE)) {
			require(TYPE);
			while(test(IDENTIFER)) {
				IdentNode ident = Ident();
				require(EQUAL);
				TypeNode type = Type();
				require(SEMICOLON);
				declaration.add(new TypeDeclarationNode(ident, type));
			}
		}
		if (test(VAR)) {
			declaration.add(Var());
		}
		while (test(PROCEDURE)) {
			declaration.add(ProcedureDeclatation());
		}
		return declaration;
	}

//	[ÕCONSTÕ ident Õ=Õ Expression Õ;Õ {ident Õ=Õ Expression Õ;Õ}]
	private ConstListNode Const() throws ParserAcceptError {
		ConstListNode constList = new ConstListNode();
		require(CONST);
		IdentNode ident = Ident();
		require(EQUAL);
		AbstractNode expression = Expression();
		require(SEMICOLON);
		constList.add(new ConstNode(ident, expression));
		while (test(IDENTIFER)) {
			ident = Ident();
			require(EQUAL);
			expression = Expression();
			require(SEMICOLON);
			constList.add(new ConstNode(ident, expression));
		}
		return constList;
	}
// ÕVARÕ IdentList Õ:Õ Type Õ;Õ {IdentList Õ:Õ Type Õ;Õ}
	private VarListNode Var() throws ParserAcceptError {
		VarListNode varList = new VarListNode();
		require(VAR);
		IdentListNode identList = IdentList();
		require(COLON);
		TypeNode type = Type();
		require(SEMICOLON);
		varList.add(new VarNode(identList,type));
		while(test(IDENTIFER)) {
			identList = IdentList();
			require(COLON);
			type = Type();
			require(SEMICOLON);
			varList.add(new VarNode(identList,type));	
		}
		return varList;
	}
	
//Module = ÕMODULEÕ ident Õ;Õ Declarations ÕBEGINÕ StatementSequence ÕENDÕ ident Õ.Õ.
	private AbstractNode Module() throws ParserAcceptError {
		require(MODULE);
		debug("ident");
		IdentNode ident = Ident();
		require(SEMICOLON);
		debug("declaration");
		DeclarationNode declaration = Declarations();
		require(BEGIN);
		debug("statementSequence");
		StatementSequenceNode statementSequence = StatementSequence();
		require(END);
		require(IDENTIFER);
		require(DOT);
		return new ModuleNode(ident, declaration, statementSequence);
	}
//Assignment = ident Selector Õ:=Õ Expression.
	private AssignmentNode Assignment() throws ParserAcceptError {
		IdentNode ident = Ident();
		SelectorNode selector = Selector();
		require(ASSIGN);

		return new AssignmentNode(ident, selector, Expression());
	}
//ActualParameters = Expression {Õ,Õ Expression}.
	private ActualParametersNode ActualParameters() throws ParserAcceptError {
		ActualParametersNode expressions = new ActualParametersNode();
		expressions.add(Expression());
		while(test(COMMA)) {
			next();
			expressions.add(Expression());
		}
		return expressions;
	}
//ProcedureCall = ident Õ(Õ [ActualParameters] Õ)Õ.
	private AbstractNode ProcedureCall() throws ParserAcceptError {
		IdentNode ident = Ident();
		require(BRACE_ROUND_OPEN);
		ActualParametersNode actualParameters = ActualParameters();
		require(BRACE_ROUND_CLOSE);
		return new ProcedureCallNode(ident, actualParameters);
	}
//IfStatement = ÕIFÕ Expression ÕTHENÕ StatementSequence {ÕELSIFÕ Expression ÕTHENÕ StatementSequence} [ÕELSEÕ StatementSequence] ÕENDÕ.
	private IfStatementNode IfStatement() throws ParserAcceptError {
		AbstractNode expression = null, elsif = null;
		StatementSequenceNode statementSeq1 = null, statementSeq2 = null;
		if (require(IF)) {
			expression = Expression();
		}
		if (require(THEN)) {
			statementSeq1 = StatementSequence();
		}
		if (test(ELSIF)) {
			elsif = Elsif();
		}
		if (test(ELSE)) {
			require(ELSE);
			statementSeq2 = StatementSequence();
		}
		require(END);

		return new IfStatementNode(expression, statementSeq1, elsif, statementSeq2);
	}
	private IfStatementNode Elsif() throws ParserAcceptError {
		AbstractNode expression = null, elsif = null;
		StatementSequenceNode statementSeq = null;
		if (require(ELSIF)) {
			expression = Expression();
		}
		if (require(THEN)) {
			statementSeq = StatementSequence();
		}
		if (test(ELSIF)) {
			elsif = Elsif();
		}
		return new IfStatementNode(expression, statementSeq, elsif, null);
	}
	//WhileStatement = ÕWHILEÕ Expression ÕDOÕ StatementSequence ÕENDÕ.
	private AbstractNode WhileStatement() throws ParserAcceptError {
		AbstractNode node, expression;
		StatementSequenceNode statementSequence;
		node = null;
		if (require(WHILE)) {
			expression = Expression();
			if (require(DO)) {
				statementSequence = StatementSequence();
				if (require(END)) {
					node = new WhileStatementNode(expression, statementSequence);
				}
			}
		}
		return node;
	}
//RepeatStatement = ÕREPEATÕ StatementSequence ÕUNTILÕ Expression.
	private AbstractNode RepeatStatement() throws ParserAcceptError {
		StatementSequenceNode statementSequence;
		AbstractNode expression;
		require(REPEAT);
		statementSequence = StatementSequence();
		require(UNTIL);
		expression = Expression();
		return new RepeatStatementNode(statementSequence, expression);
	}
//Statement = [Assignment | ProcedureCall | IfStatement | ÕPRINTÕ Expression | WhileStatement | RepeatStatement].
	private AbstractNode Statement() throws ParserAcceptError {
		AbstractNode node = null;
		if (test(IDENTIFER)) {
			if (testNext(BRACE_ROUND_OPEN)) {
				return ProcedureCall();
			}
			else {
				return new StatementNode(Assignment());
			}
		}
		if (test(IF)) {
			return IfStatement();
		}
		if (test(PRINT)) {
			return Print();
		}
		if (test(WHILE)) {
			return WhileStatement();
		}
		if (test(REPEAT)) {
			return RepeatStatement();
		}
		return node; // Throw Statement Missing
	}
	private PrintNode Print() throws ParserAcceptError {
		require(PRINT);
		PrintNode node;
		node = new PrintNode(Expression());
		return node;
	}
	//StatementSequence = Statement {Õ;Õ Statement}.
	private StatementSequenceNode StatementSequence() throws ParserAcceptError {
		StatementSequenceNode statementSequence = new StatementSequenceNode();
		statementSequence.add(Statement());
		while (test(SEMICOLON)) {
			AbstractNode nextStatement = Statement();
			if (nextStatement != null) {
				statementSequence.add(nextStatement);
			}
		}
		return statementSequence;
	}
//Selector = {Õ.Õ ident | Õ[Õ Expression Õ]Õ}.
	private SelectorNode Selector() throws ParserAcceptError {
		SelectorNode selectorNode = null;
		if (test(DOT)) {
			require(DOT);
			selectorNode = new SelectorNode(Ident(), Selector());
		}
		if (test(BRACE_SQUARE_OPEN)) {
			require(BRACE_SQUARE_OPEN);
			AbstractNode expression = Expression();
			require(BRACE_SQUARE_CLOSE);
			selectorNode = new SelectorNode(expression, Selector());
		}
		return selectorNode;
	}
//Factor = ident Selector | integer | string | Read | Õ(Õ Expression Õ)Õ.
	private AbstractNode Factor() throws ParserAcceptError {
		AbstractNode node = new EmptyNode();
		switch(current.getToken()) {
		case IDENTIFER:
			node = Ident();
			break;
		case INTEGER:
			node = new IntegerNode(Integer.valueOf(current.getValue()));
			next();
			break;
		// TODO: String
		case READ:
			node = Read();
			break;
		case BRACE_ROUND_OPEN:
			next();
			Expression();
			require(BRACE_ROUND_CLOSE);
			break;
		}
		return node;
	}
//String
	private StringNode String() throws ParserAcceptError {
		StringNode node = null;
		if (test(STRING)) {
			node = new StringNode(current.getValue().substring(1, current.getValue().length() - 1));
			next();
		}
		return node;
	}
//Read = READ [Prompt].
	private ReadNode Read() throws ParserAcceptError {
		require(READ);
		ReadNode node;
		if (current.getToken() == STRING) {
			node = new ReadNode(String());
		} else {
			node = new ReadNode();
		}

		return node;
	}
//Prompt = string.
	private AbstractNode Prompt() {
		// TODO: implement
		return new EmptyNode();
	}
//Term = Factor {(Õ*Õ | Õ/Õ) Factor}.
	private AbstractNode Term() throws ParserAcceptError {
		AbstractNode node = Factor();

		if (test(MATH_MUL) || test(MATH_DIV)) {
			next();
			node = new ExpressionNode(current.getToken(), node, Factor());
		}

		return node;
	}
//SimpleExpression = [Õ-Õ] Term {(Õ+Õ | Õ-Õ) Term}.
	private AbstractNode SimpleExpression() throws ParserAcceptError {
		AbstractNode node;
		if (test(MATH_SUB)) {
			next();
			node = new NegatedNode(Term());
		} else {
			node = Term();
		}

		if (test(MATH_ADD) || test(MATH_SUB)) {
			// TODO: next() needed?
			Tokens token = current.getToken();
			next();
			node = new ExpressionNode(token, node, Term());
		}

		return node;
	}
//Expression = SimpleExpression [(Õ=Õ | Õ#Õ | Õ<Õ | Õ<=Õ | Õ>Õ | Õ>=Õ) SimpleExpression].
	private AbstractNode Expression() throws ParserAcceptError {
		AbstractNode node;
		node = SimpleExpression();
		while (test(EQUAL) || test(NOT_EQUAL) || test(LESS) || test(LESS_EQUAL) || test(MORE) || test(MORE_EQUAL)) {
			Tokens token = current.getToken();
			next();
			node = new ExpressionNode(token, node, SimpleExpression());
		}

		return node;
	}
//IndexExpression = integer | ConstIdent.
	private AbstractNode IndexExpression() {
		next();
		AbstractNode node = null;
		// Have to do it this way because calling Integer() or ConstIdent() will move to next Token
		switch (current.getToken()) {
		case INTEGER:
			node = new IntegerNode(Integer.getInteger(current.getValue()));
			break;
		case IDENTIFER:
			node = new IdentNode(current.getValue());
		default:
			break;
		}
		return node;
	}
//ConstIdent = ident.
	private IdentNode ConstIdent() throws ParserAcceptError {
		IdentNode node = null;
		if (test(IDENTIFER)) {
			node = new IdentNode(current.getValue());
		}
		return node;
	}

	private IdentNode Ident() {
		IdentNode node = null;
		if (test(IDENTIFER)) {
			node = new IdentNode(current.getValue());
			next();
		}
		return node;
	}
	
	public AbstractNode build() throws ParserAcceptError {
		init();
		return Module();
	}

	private void init() {
		next();next(); // accept, or die
	}
	
	private Yytoken next() {
		current = next;
		next = tokenStream.nextToken();
		return current;
	}
	
	/**
	 * Test if current token matchs required token and overread
	 * @param token
	 * @return
	 * @throws ParserAcceptError 
	 */
	private boolean require(Tokens token) throws ParserAcceptError {
		if (test(token)) {
			next();
			return true;
		}
		else {
			throw new ParserAcceptError(token, current);
		}
	}
	
	/**
	 * Test if current token matches required token
	 * @param token
	 * @return
	 */
	private boolean test(Tokens token) {
		return current.getToken() == token;
	}
	
	/**
	 * Test if next token matches required token
	 * @param token
	 * @return
	 */
	private boolean testNext(Tokens token) {
		return !next.equals(null) && next.getToken() == token;
	}
	
	private void debug(String string) {
		if (DEBUG) System.out.println("-- DEBUG -- "+string);
	}
	
	public Yytoken getCurrent() {
		return current;
	}
	public Yytoken getNext() {
		return next;
	}
}
