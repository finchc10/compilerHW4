package compilerHW4;
import compilerHW4.lexer.*;
import compilerHW4.node.*;
import java.io.*;

class Lexing
{  static Lexer lexer;
   static Object token;

   // Read from stdin.
   public static void main (String [] args)
   {  	lexer = new Lexer (new PushbackReader
				(new InputStreamReader (System.in), 1024));
	token = null;

	try
	{  while ( ! (token instanceof EOF))	// End of File token
	     {	token = lexer.next();
		if (token instanceof TIf)
		   System.out.print ("keyword ");
		if (token instanceof TFi)
		   System.out.print ("keyword ");
		if (token instanceof TWhile)
		   System.out.print ("keyword " );
		if (token instanceof TWhile )
		   System.out.print ("keyword ");
		if (token instanceof TWend )
		   System.out.print ("keyword ");
		if (token instanceof TAssign)
		   System.out.print ("Assignment  ");
		if (token instanceof TSpace )
		   System.out.print ("white space ");
		if (token instanceof TIdentifier)
		   System.out.print ("Identifier  ");
		if (token instanceof TDecrement)
		   System.out.print ("Decrement ");


		System.out.println (token) ;	// uses toString()
		
	    }
 	}
	catch (Exception exc)
	{	System.err.println (exc);  }
   }

}
