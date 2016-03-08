package compilerHW4;

import java.util.List;
import compilerHW4.node.*;

/**
 * @author Cory Finch
 * @author John Gaffney
 * @author Donald Aufiero
 */



public class Parser
{
	Token  token;
	String input = "";
	private StringLexer lexer;
	private List<Token> tokens;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb;
		sb = new StringBuilder();
		sb.append("alpha						");
		sb.append("	declare abacus x			");
		sb.append("								");
		sb.append("	under_contract( x < 10 ) 	");
		sb.append("		x peer x loot 1			");
		sb.append("		print(x)				");
		sb.append("								");
		sb.append(" end_contract 				");
		sb.append("                             ");
		sb.append("                             ");
		sb.append("                             ");
		sb.append("omega						");
		
		Parser parser = new Parser(sb.toString());
		parser.Program();
	}
	
	/**
	 * Constructor for the parsing class.
	 * Takes the source code as a string and
	 * attempts to parse it. 
	 * 
	 * Checks for any syntax errors.
	 * @param sourceCode
	 */
	public Parser(String sourceCode){
		lexer = new StringLexer(sourceCode);
		tokens = lexer.getTokens();
//		for(Token tok: tokens){
//			System.out.println(tok + ": " + tok.getClass().getSimpleName());
//		}
	}
	
	
	
	//1.	Program -> alpha VarDecl* Stm* omega
	public void Program() throws Exception
	{
		if(peekNextToken().equals("TBegin")){
			eat("TBegin");
			while(peekNextToken().equals("TDeclare")){
				VarDecl();
			}
			while(peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || peekNextToken().equals("TPrint") || peekNextToken().equals("TIdentifer")){
				Stm();
			}
			eat("TEnd");
			System.out.println("Program Success!");
		}
		else
			throwError("Missing alpha at the start of the program");
	}
	
	//2.	VarDecl -> declare Type id
	public void VarDecl() throws Exception
	{
		if(peekNextToken().equals("TDeclare")){
			eat("TDeclare");
			Type();
			eat("TIdentifier");
		}
		else
			throwError("Missing declare in variable declaration");
	}
	
	//3.	Type -> abacus
	//4.    -> tome
	//5.    -> dichotomy
	public void Type() throws Exception
	{
		if(peekNextToken().equals("TInt")){//rule 3
			eat("TInt");
		}
		else if(peekNextToken().equals("TString")){
			eat("TString");
		}
		else if(peekNextToken().equals("TBoolean")){
			eat("TBoolean");
		}
		else
			throwError("Type():: invalid type!");
	}
	
	//6.	Stm -> under_contract(Exp) Stmt* end_contract
	//7.   		-> consider(Exp) Stm* end_consider
	//8.    	-> print(Exp)
	//9.    	-> id Assign
	public void Stm() throws Exception
	{
		if(peekNextToken().equals("TWhile")){//rule 5
			eat("TWhile");
			eat("LParen");
			Exp();
			eat("RParen");
			while(peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || peekNextToken().equals("TPrint") || peekNextToken().equals("TIdentifier")){
				Stm();
			}
			eat("TWend");
		}
		else if(input.equals("TIf")){//rule 6
			eat("TIf");
			eat("LParen");
			Exp();
			eat("RParen");
			while(peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || peekNextToken().equals("TPrint") || peekNextToken().equals("TIdentifier")){
				Stm();
			}
			eat("TFi");
		}
		else if(peekNextToken().equals("TPrint")){// rule 7
			eat("TPrint");
			eat("LParen");
			Exp();
			eat("RParen");
		}
		else if(peekNextToken().equals("TIdentifier")){// rule 8
			eat("TIdentifier");
			Assign();
		}
		else
			throwError("invalid statement start. Statement must start with: under_contract, consider, print, or an identifier");
	}
	
	//10.	Assign -> peer Exp
	public void Assign() throws Exception
	{
		if(peekNextToken().equals("TAssign")){// rule 9.
			eat("TAssign");
			Exp();
		}
		else
			throwError("Missing keyword \"peer\".");
	}
	
	//11.	Exp -> And Elist
	public void Exp() throws Exception
	{
		if(peekNextToken().equals("TMultiply") || peekNextToken().equals("TDivide") || peekNextToken().equals("TNot") || 
				peekNextToken().equals("TNumber") || peekNextToken().equals("TTrue") || peekNextToken().equals("TFalse") ||
				peekNextToken().equals("TIdentifier") || peekNextToken().equals("TAdd") || peekNextToken().equals("TMinus")){// rule 10
			And();
			EList();
		}
		else
			throwError("invalid expression for token: " + peekNextToken());
	}
	
	//12.	Elist -> fus And Elist
	//13.    -> null
	public void EList() throws Exception
	{
		if(peekNextToken().equals("TAnd")){// rule 12
			eat("TAnd");
			And();
			EList();
		}
		else if(peekNextToken().equals("TRparen") || peekNextToken().equals("TEnd") || peekNextToken().equals("TWend") || peekNextToken().equals("TFi")){// rule 13
			;
		}
		else
			throwError("invalid Elist for token:" + peekNextToken());
	}
	
	//14.	And -> Less Alist
	public void And() throws Exception
	{
		if(peekNextToken().equals("TMultiply") || peekNextToken().equals("TDivide") || peekNextToken().equals("TNot") || 
				peekNextToken().equals("TNumber") || peekNextToken().equals("TTrue") || peekNextToken().equals("TFalse") ||
				peekNextToken().equals("TIdentifier") || peekNextToken().equals("TAdd") || peekNextToken().equals("TDrop")){// rule 14
			Less();
			AList();
		}
		else
			throwError("invalid And for token: " + peekNextToken());
	}
	
	//15.	AList -> serfTo Less AList
	//16.    -> null
	public void AList() throws Exception
	{

		//rule 15
		if(input.equals("serfTo"))
		{
			eat(input);
			Less();
			AList();
		}
		//rule 16
		else if(input == null || input.equals("fus") || input.equals(")") || input.equals("omega") || input.equals("end_contract") || input.equals("end_consider"))
		{
			eat(input);
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	//17.	Less -> Term LList
	public void Less() throws Exception
	{
		if(input.equals("buff") || input.equals("nerf") || input.equals("dah") || token instanceof TNumber ||
				input.equals("aye") || input.equals("nay") || token instanceof TIdentifier || input.equals("loot") || input.equals("drop"))
		{
			Term();
			LList();
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	//18.	Llist -> loot Term Llist
	//19.    -> drop Term Llist
	//20.    -> null
	public void LList() throws Exception
	{
		//rule 18
		if(input.equals("loot"))
		{
			eat(input);
			Term();
			LList();
		}
		//rule 19
		else if(input.equals("drop"))
		{
			eat(input);
			Term();
			LList();
		}
		//rule 20
		else if(input == null || input.equals("serfTo") || input.equals("fus") || input.equals(")") || input.equals("omega") || input.equals("end_contract") ||
				input.equals("end_consider"))
		{
			eat(input);
		}
		else
		{
			throw new Exception("Syntax error");
		}

	}
	
	//21.	Term -> Not TList
	public void Term() throws Exception
	{
		if(input.equals("buff") || input.equals("nerf") || input.equals("dah") || token instanceof TNumber || input.equals("aye") || input.equals("nay") ||
				token instanceof TIdentifier)
		{
			Not();
			TList();
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	//22.	TList -> buff Not TList
	//23.    -> nerf Not TList
	//24.   -> null
	public void TList() throws Exception
	{
		//rule 22 && rule 23
		if(input.equals("buff") || input.equals("nerf"))
		{
			eat(input);
			Not();
			TList();
		}
		//rule 24
		else if(input == null || input.equals("loot") || input.equals("drop") || input.equals("serfTo") || input.equals("fus") || input.equals(")") ||
				input.equals("omega") || input.equals("end_contract") || input.equals("end_consider"))
		{
			eat(input);
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	
	//25.	Not -> dah Not 
	//26.    -> Factor
	public void Not() throws Exception
	{
		//rule 25
		if(input.equals("dah"))
		{
			eat(input);
			Not();
		}
		//rule 26 {number, ‘aye’, ‘nay’, identifer}
		else if(token instanceof TNumber || input.equals("aye") || input.equals("nay") || token instanceof TIdentifier)
		{
			Factor();
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	//27.	Factor -> number
	//28.    -> aye
	//29.    -> nay
	//30.    -> id
	public void Factor() throws Exception
	{
		if(input.equals("aye") || input.equals("nay") || token instanceof TIdentifier || token instanceof TNumber)
		{
			eat(input);
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	/**
	 * Eats the next token in the token list and
	 * throws an error if it an unexpected token
	 * is eaten.
	 * @param input
	 * @throws Exception 
	 */
	public void eat(String input) throws Exception
	{
		if(peekNextToken().equals(input)==false)
			throwError("Invalid input: " + input);
		
		//get next input
		if(!tokens.isEmpty())
			tokens.remove(0);
		else
			throwError("eat():: No more tokens!");
		
	}
	
	/**
	 * Returns the token type as a String of the
	 * next token in the input.
	 * @return
	 * @throws Exception
	 */
	public String peekNextToken() throws Exception{
		if(!tokens.isEmpty())
			return tokens.get(0).getClass().getSimpleName();
		else
			throwError("peekNextToken():: No more tokens!");
		return null;
	}
	
	public void throwError(String errorMessage) throws Exception{
		throw new Exception(errorMessage);
	}
}