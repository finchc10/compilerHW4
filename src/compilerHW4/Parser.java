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
	public void AList()
	{
		
	}
	
	//16.	Less -> Term Llist
	public void Less()
	{
		
	}
	
	//17.	Llist -> loot Term Llist
	//18.    -> drop Term Llist
	//19.    -> null
	public void LList()
	{
		
	}
	
	//20.	Term -> Not TList
	public void Term()
	{
		
	}
	
	//21.	TList -> buff Not TList
	//22.    -> nerf Not TList
	//23.   -> null
	public void TList()
	{
		
	}
	
	
	//24.	Not -> dah Not 
	//25.    -> Factor
	public void Not()
	{
		
	}
	
	//26.	Factor -> number
	//27.    -> aye
	//28.    -> nay
	//29.    -> id
	public void Factor()
	{
		
	}
	
	public void eat()
	{
		// dunno what eat does!
	}
}
