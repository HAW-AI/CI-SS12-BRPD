package haw.ci.lib;

public class ParserAcceptError extends Exception {
	private static final long serialVersionUID = -5506550732525950771L;

	public ParserAcceptError(Tokens token, Yytoken current) {
		super(String.format("Missing %s, got %s, at %d:%d",token,current,current.getLine(),current.getColumn()));
	}	
}
