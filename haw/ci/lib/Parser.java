package haw.ci.lib;

public class Parser {
	private ITokenStream tokenStream;
	public Parser(ITokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}
	
	public String build() {
		Yytoken token = null;
		while ((token = tokenStream.nextToken()) != null) {
			
		}
		return "";
	}
}
