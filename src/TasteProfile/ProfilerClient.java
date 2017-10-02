package TasteProfile;

import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class ProfilerClient {
	   public static void main(String[] args) {
		     try {
			    ORB orb = ORB.init(args, null);
			    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
			    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			    Profiler addobj = (Profiler) ProfilerHelper.narrow(ncRef.resolve_str("ABC"));

		           Scanner c=new Scanner(System.in);
		           System.out.println("Welcome to the Profiler system:");          		    
				    for(;;){
//				      System.out.println("Enter a:");
//				      String aa = c.nextLine();
//				      System.out.println("Enter b:");
//				      String bb = c.nextLine();
				    	
				      int r=addobj.getTimesPlayed("asdf");
				      System.out.println("The result for Profiler is : "+r);
				      System.out.println("-----------------------------------");
		           }
		      }
		      catch (Exception e) {
		         System.out.println("Hello Client exception: " + e);
			  e.printStackTrace();
		      }

		   }
}
