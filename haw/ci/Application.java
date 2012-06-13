package haw.ci;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import haw.ci.lib.ITokenStream;
import haw.ci.lib.Parser;
import haw.ci.lib.ParserAcceptError;
import haw.ci.lib.SymTaG;
import haw.ci.lib.SymbolTable;
import haw.ci.lib.Tokenizer;
import haw.ci.lib.nodes.AbstractNode;

public class Application {
	public static void main(String[] args) throws IOException {
		Reader fileReader = new FileReader(new File("./simple-program2.ob"));
		ITokenStream tokenStream = new Tokenizer(fileReader);
		Parser parser = new Parser(tokenStream);
		AbstractNode node;
		try {
			node = parser.build();
			System.out.println(node.toString());
			
			System.out.println("Code:");
			node.compile();
		} catch (ParserAcceptError e) {
			System.out.println(String.format("Failure (cur: %s, nex: %s)", parser.getCurrent(),parser.getNext()));
			e.printStackTrace();
		}
	}
}
