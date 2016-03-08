package compilerHW4;

import java.util.List;
import compilerHW4.node.*;

/**
 * @author Cory Finch
 * @author John Gaffney
 * @author Donald Aufiero
 */



public class MainParser
{
	private StringLexer lexer;
	private List<Token> tokens;
	
	/**
	 * Main method.
	 * 
	 * Runs through three examples using the parser.
	 * 
	 * First two are syntactically correct. Third will throw an exception
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		StringBuilder sbExample1, sbExample2, sbExample3;
		
		/**
		 * begin
		 * 	declare int x
		 *   
		 *   while(x < 10)
		 *   	x = x + 1
		 *   	print(x)
		 *   wend
		 *   
		 * end
		 */
		sbExample1 = new StringBuilder();
		sbExample1.append("alpha							");
		sbExample1.append("	declare abacus x				");
		sbExample1.append("									");
		sbExample1.append("	under_contract( x serfTo 10 ) 	");
		sbExample1.append("		x peer x loot 1			    ");
		sbExample1.append("		announce(x)				    ");
		sbExample1.append("								    ");
		sbExample1.append(" end_contract 				    ");
		sbExample1.append("                                 ");
		sbExample1.append("omega						    ");
		
		/**
		 * begin
		 * declare int x
		 * declare int y
		 * declare boolean isEvenX
		 * declare boolean isEvenY
		 * 
		 * x = 6
		 * x = 1
		 * isEvenX = false
		 * isEvenY = false
		 * 
		 * if(x/2 == 0)
		 * 	isEvenX = true
		 * fi
		 * 
		 * if(y/2 ==0)
		 * 	isEvenY = false
		 * fi
		 * 
		 * print(isEvenX)//should be true
		 * print(isEvenY)//should be false
		 * end
		 */
		sbExample2 = new StringBuilder();
		sbExample2.append("alpha							");
		sbExample2.append("declare abacus x               ");
		sbExample2.append("declare abacus y               ");
		sbExample2.append("declare dichotomy isEvenX      ");
		sbExample2.append("declare dichotomy isEvenY      ");
        sbExample2.append("                               ");
		sbExample2.append("x peer 6                       ");
		sbExample2.append("y peer 1                       ");
		sbExample2.append("isEvenX peer nay               ");
		sbExample2.append("isEvenY peer nay               ");
        sbExample2.append("                               ");
		sbExample2.append("consider(x nerf 2)      	      ");
		sbExample2.append("	isEvenX peer aye              ");
		sbExample2.append("end_consider                   ");
        sbExample2.append("                               ");
		sbExample2.append("consider(Y nerf 2)      	      ");
		sbExample2.append("	isEvenY peer aye              ");
		sbExample2.append("end_consider                   ");
        sbExample2.append("                               ");
		sbExample2.append("announce(isEvenX)              ");
		sbExample2.append("announce(isEvenY)              ");
		sbExample2.append("omega        					");
		
		/**
		 * syntax errors
		 *  VarDecl after Stm
		 *  Missing omega keyword
		 *  
		 *  x = 10
		 *  declare int x
		 *  while(x > 1)
		 *  	print(x)
		 *  end
		 */
		sbExample3 = new StringBuilder();
		sbExample3.append("alpha			");
		sbExample3.append("x peer 10			");
		sbExample3.append("declare abacus x		");
		sbExample3.append("consider(x lordTo 1)	");
		sbExample3.append("		announce(x) 	");
		sbExample3.append("end_consider			");
		
		MainParser parser1, parser2, parser3;
		parser1 = new MainParser(sbExample1.toString());
		parser2 = new MainParser(sbExample2.toString());
		parser3 = new MainParser(sbExample3.toString());
		System.out.println("Start program 1");
		parser1.Program();
		System.out.println("\n Start program 2");
		parser2.Program();
		System.out.println("\n Start program 3");
		parser3.Program();
	}
	
	/**
	 * Constructor for the parsing class.
	 * Takes the source code as a string and
	 * attempts to parse it. 
	 * 
	 * Checks for any syntax errors.
	 * @param sourceCode
	 */
	public MainParser(String sourceCode){
		lexer = new StringLexer(sourceCode);
		tokens = lexer.getTokens();
//		//print tokens for debugging
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
			while(peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || peekNextToken().equals("TPrint") || peekNextToken().equals("TIdentifier")){
				Stm();
			}
			eat("TOmega");
			System.out.println("Program Success!");
		}
		else
			error("Missing alpha at the start of the program");
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
			error("Missing declare in variable declaration");
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
			error("Type():: invalid type!");
	}
	
	//6.	Stm -> under_contract(Exp) Stmt* end_contract
	//7.   		-> consider(Exp) Stm* end_consider
	//8.    	-> print(Exp)
	//9.    	-> id Assign
	public void Stm() throws Exception
	{
		if(peekNextToken().equals("TWhile")){//rule 5
			eat("TWhile");
			eat("TLparen");
			Exp();
			eat("TRparen");
			while(peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || peekNextToken().equals("TPrint") || peekNextToken().equals("TIdentifier")){
				Stm();
			}
			eat("TWend");
		}
		else if(peekNextToken().equals("TIf")){//rule 6
			eat("TIf");
			eat("TLparen");
			Exp();
			eat("TRparen");
			while(peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || peekNextToken().equals("TPrint") || peekNextToken().equals("TIdentifier")){
				Stm();
			}
			eat("TFi");
		}
		else if(peekNextToken().equals("TPrint")){// rule 7
			eat("TPrint");
			eat("TLparen");
			Exp();
			eat("TRparen");
		}
		else if(peekNextToken().equals("TIdentifier")){// rule 8
			eat("TIdentifier");
			Assign();
		}
		else
			error("Invalid statement start. Statement must start with: under_contract, consider, announce, or an identifier");
	}
	
	//10.	Assign -> peer Exp
	public void Assign() throws Exception
	{
		if(peekNextToken().equals("TAssign")){// rule 9.
			eat("TAssign");
			Exp();
		}
		else
			error("Missing keyword \"peer\".");
	}
	
	//11.	Exp -> And Elist
	public void Exp() throws Exception
	{
		if(peekNextToken().equals("TMultiply") || peekNextToken().equals("TDivide") || peekNextToken().equals("TNot") || 
				peekNextToken().equals("TNumber") || peekNextToken().equals("TTrue") || peekNextToken().equals("TFalse") ||
				peekNextToken().equals("TIdentifier") || peekNextToken().equals("TPlus") || peekNextToken().equals("TMinus")){// rule 10
			And();
			EList();
		}
		else
			error("invalid expression for token: " + peekNextToken());
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
		else if(peekNextToken().equals("TRparen") || peekNextToken().equals("TOmega") || 
				peekNextToken().equals("TWend") || peekNextToken().equals("TFi") ||
				peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || 
				peekNextToken().equals("TPrint")|| peekNextToken().equals("TIdentifier"))
		{// rule 13
			;
		}
		else
			error("invalid Elist for token:" + peekNextToken());
	}
	
	//14.	And -> Less Alist
	public void And() throws Exception
	{
		if(peekNextToken().equals("TMultiply") || peekNextToken().equals("TDivide") || peekNextToken().equals("TNot") || 
				peekNextToken().equals("TNumber") || peekNextToken().equals("TTrue") || peekNextToken().equals("TFalse") ||
				peekNextToken().equals("TIdentifier") || peekNextToken().equals("TPlus") || peekNextToken().equals("TDrop")){// rule 14
			Less();
			AList();
		}
		else
			error("invalid And for token: " + peekNextToken());
	}
	
	//15.	AList -> serfTo Less AList
	//16.    -> null
	public void AList() throws Exception
	{

		
		if(peekNextToken().equals("TLessthan"))
		{//rule 15
			eat(peekNextToken());
			Less();
			AList();
		}
		else if(peekNextToken().equals("TRparen") || 
				peekNextToken().equals("TAnd") || peekNextToken().equals("TOmega") ||
				peekNextToken().equals("TWend") || peekNextToken().equals("TFi") ||
				peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || 
				peekNextToken().equals("TPrint")|| peekNextToken().equals("TIdentifier"))
		{//rule 16
			;
		}
		else
		{
			throw new Exception("Invalid token for " + peekNextToken());
		}
	}
	
	//17.	Less -> Term LList
	public void Less() throws Exception
	{
		if(peekNextToken().equals("TMultiply") || peekNextToken().equals("TDivide") || peekNextToken().equals("TNot") || peekNextToken().equals("TNumber") ||
				peekNextToken().equals("TTrue") || peekNextToken().equals("TFalse") || peekNextToken().equals("TIdentifier") || peekNextToken().equals("TPlus") || peekNextToken().equals("TMinus"))
		{
			Term();
			LList();
		}
		else
		{
			throw new Exception("Invalid token for " + peekNextToken());
		}
	}
	
	//18.	Llist -> loot Term Llist
	//19.    -> drop Term Llist
	//20.    -> null
	public void LList() throws Exception
	{
		//rule 18
		if(peekNextToken().equals("TPlus"))
		{
			eat(peekNextToken());
			Term();
			LList();
		}
		//rule 19
		else if(peekNextToken().equals("TMinus"))
		{
			eat(peekNextToken());
			Term();
			LList();
		}
		//rule 20
		else if(peekNextToken().equals("TLessthan") || peekNextToken().equals("TPlus") || peekNextToken().equals("TRparen") || peekNextToken().equals("TOmega") || peekNextToken().equals("TWend") ||
				peekNextToken().equals("TFi")||
				peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || peekNextToken().equals("TPrint")|| peekNextToken().equals("TIdentifier"))
		{
			;
		}
		else
		{
			throw new Exception("Invalid token for " + peekNextToken());
		}

	}
	
	//21.	Term -> Not TList
	public void Term() throws Exception
	{
		if(peekNextToken().equals("TMultiply") || peekNextToken().equals("TDivide") || peekNextToken().equals("TNot") || peekNextToken().equals("TNumber") || peekNextToken().equals("TTrue") || peekNextToken().equals("TFalse") ||
				peekNextToken().equals("TIdentifier"))
		{
			Not();
			TList();
		}
		else
		{
			throw new Exception("Invalid token for " + peekNextToken());
		}
	}
	
	//22.	TList -> buff Not TList
	//23.    -> nerf Not TList
	//24.   -> null
	public void TList() throws Exception
	{
		//rule 22 && rule 23
		if(peekNextToken().equals("TMultiply") || peekNextToken().equals("TDivide"))
		{
			eat(peekNextToken());
			Not();
			TList();
		}
		//rule 24
		else if(peekNextToken().equals("TPlus") || peekNextToken().equals("TMinus") || peekNextToken().equals("TLessthan") || peekNextToken().equals("TRparen") ||
				peekNextToken().equals("TOmega") || peekNextToken().equals("TWend") || peekNextToken().equals("TFi") ||
				peekNextToken().equals("TWhile") || peekNextToken().equals("TIf") || peekNextToken().equals("TPrint")|| peekNextToken().equals("TIdentifier"))
		{
			;
		}
		else
		{
			throw new Exception("Invalid token for " + peekNextToken());
		}
	}
	
	
	//25.	Not -> dah Not 
	//26.    -> Factor
	public void Not() throws Exception
	{
		//rule 25
		if(peekNextToken().equals("TNot"))
		{
			eat(peekNextToken());
			Not();
		}
		//rule 26 {number, ‘aye’, ‘nay’, identifer}
		else if(peekNextToken().equals("TNumber") || peekNextToken().equals("TTrue") || peekNextToken().equals("TFalse") || peekNextToken().equals("TIdentifier"))
		{
			Factor();
		}
		else
		{
			throw new Exception("Invalid token for " + peekNextToken());
		}
	}
	
	//27.	Factor -> number
	//28.    -> aye
	//29.    -> nay
	//30.    -> id
	public void Factor() throws Exception
	{
		if(peekNextToken().equals("TTrue") || peekNextToken().equals("TFalse") || peekNextToken().equals("TIdentifier") || peekNextToken().equals("TNumber"))
		{
			eat(peekNextToken());
		}
		else
		{
			throw new Exception("Invalid token for " + peekNextToken());
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
			error("Invalid input: " + input);
		else
			System.out.println("Ate token: " + input);
		
		//get next input
		if(!tokens.isEmpty())
			tokens.remove(0);
		else
			error("eat():: No more tokens!");
		
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
			error("peekNextToken():: No more tokens!");
		return null;
	}
	
	/**
	 * Prints an error to the console.
	 * 
	 * @param errorMessage
	 * @throws Exception
	 */
	public void error(String errorMessage) throws Exception{
		System.err.println(errorMessage);
	}
}