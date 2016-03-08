/**
 * @author Cory Finch
 * @author John Gaffney
 * @author Donald Aufiero
 */

package compilerHW4;

public class Parser
{
	String input = "";
	
	//1.	Program -> alpha VarDecl* Stm* omega
	public void Program()
	{
		
	}
	
	//	VarDecl -> declare Type id
	public void VarDecl()
	{
		
	}
	
	//2.	Type -> abacus
	//3.    -> tome
	//4.    -> dichotomy
	public void Type()
	{
		
	}
	
	//5.	Stm -> under_contract(Exp) Stmt* end_contract
	//6.   -> consider(Exp) Stm* end_consider
	//7.    -> print(Exp)
	//8.    -> id Assign
	public void Stm()
	{
		
	}
	
	//9.	Assign -> peer Exp
	public void Assign()
	{
		
	}
	
	//10.	Exp -> And Elist
	public void Exp()
	{
		
	}
	
	//11.	Elist -> fus And Elist
	//12.    -> null
	public void EList()
	{
		
	}
	
	//13.	And -> Less Alist
	public void And()
	{
		
	}
	
	//14.	AList -> serfTo Less AList
	//15.    -> null
	public void AList() throws Exception
	{

		//rule 14
		if(input.equals("serfTo"))
		{
			eat(input);
			Less();
			AList();
		}
		//rule 15
		else if(input == null)
		{
			;
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	//16.	Less -> Term Llist
	public void Less() throws Exception
	{
		Term();
		LList();
	}
	
	//17.	Llist -> loot Term Llist
	//18.    -> drop Term Llist
	//19.    -> null
	public void LList() throws Exception
	{
		//rule 17
		if(input.equals("loot"))
		{
			eat(input);
			Term();
			LList();
		}
		//rule 18
		else if(input.equals("drop"))
		{
			eat(input);
			Term();
			LList();
		}
		//rule 19
		else if(input == null)
		{
			;
		}
		else
		{
			throw new Exception("Syntax error");
		}

	}
	
	//20.	Term -> Not TList
	public void Term() throws Exception
	{
		Not();
		TList();
	}
	
	//21.	TList -> buff Not TList
	//22.    -> nerf Not TList
	//23.   -> null
	public void TList() throws Exception
	{
		//rule 21
		if(input.equals("buff"))
		{
			eat(input);
			Not();
			TList();
		}
		//rule 22
		else if(input.equals("nerf"))
		{
			eat(input);
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
	
	
	//24.	Not -> dah Not 
	//25.    -> Factor
	public void Not()
	{
		if(input.equals("dah"))
		{
			eat(input);
			Not();
		}
		else
		{
			Factor();
		}
	}
	
	//26.	Factor -> number
	//27.    -> aye
	//28.    -> nay
	//29.    -> id
	public void Factor()
	{
		if(input.equals("aye") || input.equals("nay") || input.equals(TokenType.ID) || input.equals(TokenType.NUMBER)
		{
			eat(input);
		}
		else
		{
			throw new Exception("Syntax error");
		}
	}
	
	public void eat(String input)
	{
		// dunno what eat does!
	}
}
