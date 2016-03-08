package compilerHW4;

/**
 * @author Cory Finch
 * @author John Gaffney
 * @author Donald Aufiero
 */



public class Parser
{
	String input = "";
	
	//1.	Program -> alpha VarDecl* Stm* omega
	public void Program() throws Exception
	{
		if(input.equals("alpha")){
			eat("alpha");
			while(input.equals("abacus") || input.equals("tome") || input.equals("dichotomy")){
				VarDecl();
			}
			while(input.equals("under_contract") || input.equals("consider") || input.equals("print") || input.equals("identifier")){
				Stm();
			}
			eat("omega");
		}
		else
			throwError("Missing alpha at the start of the program");
	}
	
	//2.	VarDecl -> declare Type id
	public void VarDecl()
	{
		if(input.equals("declare")){
			Type();
			eat("id");
		}
		else
			throwError("Missing declare in variable declaration");
	}
	
	//3.	Type -> abacus
	//4.    -> tome
	//5.    -> dichotomy
	public void Type()
	{
		if(input.equals("abacus") || input.equals("tome") || input.equals("dichotomy")){
			eat("token");
		}
	}
	
	//6.	Stm -> under_contract(Exp) Stmt* end_contract
	//7.   		-> consider(Exp) Stm* end_consider
	//8.    	-> print(Exp)
	//9.    	-> id Assign
	public void Stm() throws Exception
	{
		if(input.equals("under_contract")){//rule 5
			eat("under_contract");
			eat("(");
			Exp();
			eat(")");
			while(input.equals("under_contract") || input.equals("consider") || input.equals("print") || input.equals("identifier")){
				Stm();
			}
			eat("end_contract");
		}
		else if(input.equals("consider")){//rule 6
			eat("consider");
			eat("(");
			Exp();
			eat(")");
			eat("end_consider");
		}
		else if(input.equals("print")){// rule 7
			eat("print");
			eat("(");
			Exp();
			eat(")");
		}
		else if(input.equals("identifier")){// rule 8
			eat("identifier");
			Assign();
		}
		else
			throwError("invalid statement start. Statement must start with: under_contract, consider, print, or an identifier");
	}
	
	//10.	Assign -> peer Exp
	public void Assign() throws Exception
	{
		if(input.equals("peer")){// rule 9.
			eat("peer");
			Exp();
		}
		else
			throwError("Missing keyword \"peer\".");
	}
	
	//11.	Exp -> And Elist
	public void Exp() throws Exception
	{
		if(input.equals("buff") || input.equals("nerf") || input.equals("dah") || 
				input.equals("number") || input.equals("aye") || input.equals("nay") ||
				input.equals("identifier") || input.equals("loot") || input.equals("drop")){// rule 10
			And();
			EList();
		}
		else
			throwError("invalid expression for token: " + input);
	}
	
	//12.	Elist -> fus And Elist
	//13.    -> null
	public void EList() throws Exception
	{
		if(input.equals("fus")){// rule 12
			eat("fus");
			And();
			EList();
		}
		else if(input.equals(")") || input.equals("omega") || input.equals("end_contrat") || input.equals("end_consider")){// rule 13
			;
		}
		else
			throwError("invalid Elist for token:" + input);
	}
	
	//14.	And -> Less Alist
	public void And() throws Exception
	{
		if(input.equals("buff") || input.equals("nerf") || input.equals("dah") || 
				input.equals("number") || input.equals("aye") || input.equals("nay") ||
				input.equals("identifier") || input.equals("loot") || input.equals("drop")){// rule 14
			Less();
			AList();
		}
		else
			throwError("invalid And for token: " + input);
	}
	
	//15.	AList -> serfTo Less AList
	//16.    -> null
	public void AList() throws Exception
	{

		//rule 15
		if(input.equals("serfTo"))
		{
			eat("serfTo");
			Less();
			AList();
		}
		//rule 16
		else if(input == null)
		{
			;
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	//17.	Less -> Term Llist
	public void Less() throws Exception
	{
		Term();
		LList();
	}
	
	//18.	Llist -> loot Term Llist
	//19.    -> drop Term Llist
	//20.    -> null
	public void LList() throws Exception
	{
		//rule 18
		if(input.equals("loot"))
		{
			eat("drop");
			Term();
			LList();
		}
		//rule 19
		else if(input.equals("drop"))
		{
			eat("loot");
			Term();
			LList();
		}
		//rule 20
		else if(input == null)
		{
			;
		}
		else
		{
			throw new Exception("Syntax error");
		}

	}
	
	//21.	Term -> Not TList
	public void Term() throws Exception
	{
		Not();
		TList();
	}
	
	//22.	TList -> buff Not TList
	//23.    -> nerf Not TList
	//24.   -> null
	public void TList() throws Exception
	{
		//rule 22
		if(input.equals("buff"))
		{
			eat("buff");
			Not();
			TList();
		}
		//rule 23
		else if(input.equals("nerf"))
		{
			eat("nerf");
			Not();
			TList();
		}
		else if(input == null)
		{
			;
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	
	//25.	Not -> dah Not 
	//26.    -> Factor
	public void Not()
	{
		if(input.equals("dah"))
		{
			eat("dah");
			Not();
		}
		else
		{
			Factor();
		}
	}
	
	//27.	Factor -> number
	//28.    -> aye
	//29.    -> nay
	//30.    -> id
	public void Factor()
	{
		
	}
	
	public void eat(String input)
	{
		if(this.input.equals(input)==false)
			throwError("Invalid input: " + input);
		
		//get next input
	}
	
	public void throwError(String errorMessage){
		System.err.println(errorMessage);
	}
}
