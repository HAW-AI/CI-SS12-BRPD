package haw.ci.lexer;

import static haw.ci.lib.Tokens.*;
import static haw.ci.lib.Yytoken.*;
import haw.ci.lib.Yytoken;
import java.io.IOException;

%%
%public
%class Tokenizer
%implements ITokenStream
%char
%line
%column

%{
	public Yytoken nextToken() {
		try {
			return yylex();
		} catch (IOException e) {
			return null;
		}
	}
%}

whitespace   = [ \n\t]
letter       = [A-Za-z]*
digit        = [0-9]
alphanumeric = {letter}|{digit}
other_identifer = "_"

integer   = {digit}+
real      = {integer}\.{integer}
identifer = {letter}({alphanumeric}|{other_identifer})*
comment_line = "#"[^\r\n]*
comment = {comment_line}

math_add = "+"
math_mul = "*"
math_sub = "-"
math_div = "/"

assign = ":="

cond_equal  = "="
cond_less   = "<"
cond_less_or_equal = "<="
cond_more   = ">"
cond_less_or_more  = ">="

sym_dot    = "."
sym_comma  = ","
sym_colon  = ":"
sym_semicolon = ";"
brace_round_open = "("
brace_round_close =")"
brace_square_open = "["
brace_square_close = "]"

cmd_array  = [Aa][Rr][Rr][Aa][Yy]
cmd_begin  = [Bb][Ee][Gg][Ii][Nn]
cmd_const  = [Cc][Oo][Nn][Ss][Tt]
cmd_do     = [Dd][Oo]
cmd_else   = [Ee][Ll][Ss][Ee]
cmd_elsif  = [Ee][Ll][Ss][Ii][Ff]
cmd_end    = [Ee][Nn][Dd]
cmd_if     = [Ii][Ff]
cmd_module = [Mm][Oo][Dd][Uu][Ll][Ee]
cmd_of     = [Oo][Ff]
cmd_print  = [Pp][Rr][Ii][Nn][Tt]
cmd_procedure = [Pp][Rr][Oo][Cc][Ee][Dd][Uu][Rr][Ee]
cmd_read   = [Rr][Ee][Aa][Dd]
cmd_record = [Rr][Ee][Cc][Oo][Rr][Dd]
cmd_repeat = [Rr][Ee][Pp][Ee][Aa][Tt]
cmd_then   = [Tt][Hh][Ee][Nn]
cmd_type   = [Tt][Yy][Pp][Ee]
cmd_until  = [Uu][Nn][Tt][Ii][Ll]
cmd_var    = [Vv][Aa][Rr]
cmd_while  = [Ww][Hh][Ii][Ll][Ee]

%%

{whitespace}*	{}

{comment}       { return token(COMMENT, yyline, yycolumn, yytext()); }

{math_add}      { return token(MATH_ADD, yyline, yycolumn); }
{math_mul}      { return token(MATH_MUL, yyline, yycolumn); }
{math_sub}      { return token(MATH_SUB, yyline, yycolumn); }
{math_div}      { return token(MATH_DIV, yyline, yycolumn); }

{assign}        { return token(ASSIGN, yyline, yycolumn); }

{cond_equal}    { return token(EQUAL, yyline, yycolumn); }
{cond_less_or_equal} { return token(LESS_EQUAL, yyline, yycolumn); }
{cond_less_or_more}  { return token(MORE_EQUAL, yyline, yycolumn); }
{cond_less}     { return token(LESS, yyline, yycolumn); }
{cond_more}     { return token(MORE, yyline, yycolumn); }

{sym_dot}       { return token(DOT, yyline, yycolumn); }
{sym_comma}     { return token(COMMA, yyline, yycolumn); }
{sym_colon}     { return token(COLON, yyline, yycolumn); }
{sym_semicolon} { return token(SEMICOLON, yyline, yycolumn); }
{brace_round_open}   { return token(BRACE_ROUND_OPEN, yyline, yycolumn); }
{brace_round_close}  { return token(BRACE_ROUND_CLOSE, yyline, yycolumn); }
{brace_square_open}  { return token(BRACE_SQUARE_OPEN, yyline, yycolumn); }
{brace_square_close} { return token(BRACE_SQUARE_CLOSE, yyline, yycolumn); }

{cmd_array}     { return token(ARRAY, yyline, yycolumn); }
{cmd_begin}     { return token(BEGIN, yyline, yycolumn); }
{cmd_const}     { return token(CONST, yyline, yycolumn); }
{cmd_do}        { return token(DO, yyline, yycolumn); }
{cmd_else}      { return token(ELSE, yyline, yycolumn); }
{cmd_elsif}     { return token(ELSIF, yyline, yycolumn); }
{cmd_end}       { return token(END, yyline, yycolumn); }
{cmd_if}        { return token(IF, yyline, yycolumn); }
{cmd_module}    { return token(MODULE, yyline, yycolumn); }
{cmd_of}        { return token(OF, yyline, yycolumn); }
{cmd_print}     { return token(PRINT, yyline, yycolumn); }
{cmd_procedure} { return token(PROCEDURE, yyline, yycolumn); }
{cmd_read}      { return token(READ, yyline, yycolumn); }
{cmd_record}    { return token(RECORD, yyline, yycolumn); }
{cmd_repeat}    { return token(REPEAT, yyline, yycolumn); }
{cmd_then}      { return token(THEN, yyline, yycolumn); }
{cmd_type}      { return token(TYPE, yyline, yycolumn); }
{cmd_until}     { return token(UNTIL, yyline, yycolumn); }
{cmd_var}       { return token(VAR, yyline, yycolumn); }
{cmd_while}     { return token(WHILE, yyline, yycolumn); }

{integer}       { return token(INTEGER, yyline, yycolumn, yytext()); }
{identifer}     { return token(IDENTIFER, yyline, yycolumn, yytext()); }

. { System.out.println("Illegal char, '" + yytext() + "' line: " + yyline + ", column: " + yycolumn); }
