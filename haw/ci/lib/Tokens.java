package haw.ci.lib;

public enum Tokens {
	// Math Operations
	MATH_MUL, MATH_ADD, MATH_SUB, MATH_DIV,
	// Variable hanlding
	ASSIGN, 
	// conditionals
	EQUAL, NOT_EQUAL, LESS, LESS_EQUAL, MORE, MORE_EQUAL,
	// tbw
	DOT, COMMA, COLON, SEMICOLON,
	BRACE_ROUND_OPEN, BRACE_ROUND_CLOSE,
	BRACE_SQUARE_OPEN, BRACE_SQUARE_CLOSE,
	// reserved words
	IF, THEN, ELSIF, ELSE, 
	WHILE, REPEAT, UNTIL,
	BEGIN, END, OF, DO, 
	PRINT, READ,
	ARRAY, RECORD, 
	CONST, TYPE,
	VAR, PROCEDURE, MODULE,
	// literals
	INTEGER, IDENTIFER,
	STRING
}

/*

* + - / :=
= # < <= > >= . , : ( ) [ ] ;
ident = letter {letter | digit}.
integer = digit {digit}.
OF THEN DO PRINT READ
END ELSE ELSIF IF WHILE REPEAT UNTIL
ARRAY RECORD CONST TYPE
VAR PROCEDURE BEGIN MODULE

 */
