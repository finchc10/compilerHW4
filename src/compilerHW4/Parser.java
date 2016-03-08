/**
 * @author Cory Finch
 * @author John Gaffney
 * @author Donald Aufiero
 */

package compilerHW4;

public class Parser
{
	String input = "";
	
	//	Program -> alpha VarDecl* Stm* omega
	public void Program()
	{
		
	}
	
	//	VarDecl -> declare Type id
	public void VarDecl()
	{
		
	}
	
	//	Type -> abacus
	//    -> tome
	//    -> dichotomy
	public void Type()
	{
		
	}
	
	//	Stm -> under_contract(Exp) Stmt* end_contract
	//    -> consider(Exp) Stm* end_consider
	//    -> print(Exp)
	//    -> id Assign
	public void Stm()
	{
		
	}
	
	//	Assign -> peer Exp
	public void Assign()
	{
		
	}
	
	//	Exp -> And Elist
	public void Exp()
	{
		
	}
	
	//	Elist -> fus And Elist
	//    -> null
	public void EList()
	{
		
	}
	
	//	And -> Less Alist
	public void And()
	{
		
	}
	
	//	AList -> serfTo Less AList
	//    -> null
	public void AList()
	{
		
	}
	
	//	Less -> Term Llist
	public void Less()
	{
		
	}
	
	//	Llist -> loot Term Llist
	//    -> drop Term Llist
	//    -> null
	public void LList()
	{
		
	}
	
	//	Term -> Not TList
	public void Term()
	{
		
	}
	
	//	TList -> buff Not TList
	//    -> nerf Not TList
	//   -> null
	public void TList()
	{
		
	}
	
	
	//	Not -> dah Not 
	//    -> Factor
	public void Not()
	{
		
	}
	
	//	Factor -> number
	//    -> aye
	//    -> nay
	//    -> id
	public void Factor()
	{
		
	}
	
	public void eat()
	{
		// dunno what eat does!
	}
}
