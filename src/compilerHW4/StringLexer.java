package compilerHW4;

import java.util.List;

import compilerHW4.lexer.Lexer;
import compilerHW4.node.EOF;
import compilerHW4.node.TSpace;
import compilerHW4.node.Token;

import java.util.List;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * The purpose of this class is to wrap a lexer
 * to handle strings as the source of input.
 * 
 * @author gaffn
 *
 */

public class StringLexer {
	
	private Lexer lexer;
	private List<Token> tokens;
	
	/**
	 * Parses the string for tokens. 
	 * Ignores whitespace.
	 * 
	 * Use getTokens() to get the list of non white space
	 * tokens.
	 * @param source
	 */
	public StringLexer(String source){
		Token token;
		
		token = null;
		tokens = new ArrayList<Token>();
		lexer = new Lexer (new PushbackReader(new StringReader(source)));
		try{
			while ( !(token instanceof EOF)) {	// End of File token
				token = lexer.next();
				if(token instanceof TSpace == false)
					tokens.add(token);// ignore whitespace
		     }
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public List<Token> getTokens(){
		return tokens;
	}
}
