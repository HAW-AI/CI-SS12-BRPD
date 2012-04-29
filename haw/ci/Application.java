package haw.ci;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import haw.ci.lib.ITokenStream;
import haw.ci.lib.Parser;
import haw.ci.lib.Tokenizer;
import haw.ci.lib.Yytoken;

public class Application {
	public static void main(String[] args) throws IOException {
		Reader fileReader = new FileReader(new File("./example.ob"));
		ITokenStream tokenStream = new Tokenizer(fileReader);
		Parser parser = new Parser(tokenStream);
	}
}
