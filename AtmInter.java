package jAtmproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AtmInter
{
	
   
     static int pn,p;
     
	static String usname;
		Scanner sc = new Scanner(System.in);
 void enter() 
	{
		System.out.println("ENTER USERNAME:  ");
		usname=sc.next();
		System.out.println("ENTER pin:  ");
		 p=sc.nextInt();  
   }
	void validate(String username,int pin) throws ClassNotFoundException, SQLException 
	{
		try 
		{ 
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmtransaction?autoReconnect=true&useSSL=false","root","root");
			PreparedStatement ps = con.prepareStatement("select * from user");
    		ResultSet rs = ps.executeQuery();
    		while(rs.next())
	    while(rs.getInt("pin")==p &&rs.getString("username").equals(usname)) 
	    {
	    	    System.out.println("chosse any one operation:");
	            System.out.println("Choose 1 for Withdraw");
	            System.out.println("Choose 2 for Deposit");
	            System.out.println("Choose 3 for Check Balance");
	            System.out.println("Choose 4 for to change pin");
	            System.out.println("Choose 5  to exit");
	            System.out.print("Choose the operation you want to perform:");
	            int n = sc.nextInt();
	            switch(n)
	            {
	                case 1:
	                	int  withdraw,bal;
	                System.out.print("Enter money to be withdrawn:");
	                withdraw = sc.nextInt();
	   	        		if(rs.getInt(3)>= withdraw)
	                {
	                    bal = rs.getInt(3) - withdraw;
	                    System.out.println("YOUR TRANSACTION IS BEING PROCESSED");
	                    System.out.println("Please collect your money Thankyou!!");
	                    PreparedStatement smt = con.prepareStatement("UPDATE user SET balance=? WHERE username=?");
                		smt.setInt(1,bal);
                	    smt.setString(2, usname);
                		smt.executeUpdate();
	                    System.out.println("Available balance: "+bal);
	                    System.out.println("THANKYOU HAVE A GOOD DAY");
	                    System.exit(0);
	                    
	                }
	                else
	                {
	                    System.out.println("Insufficient Balance");
	                    System.exit(0);
	                }
	              
	                break;
	                
	                case 2:
	                int depo,baal;	
	                Scanner sca = new Scanner(System.in);
	                System.out.print("Enter money to be deposited:");
	            	depo = sca.nextInt();
	            	 baal= depo + rs.getInt(3);
	                 PreparedStatement ssmtt = con.prepareStatement("UPDATE user SET balance=? WHERE username=?");
            		ssmtt.setInt(1,baal);
            	    ssmtt.setString(2, usname);
            		ssmtt.executeUpdate();
	                System.out.println("Your Money has been successfully depsited");
	                System.out.println("Available balance: "+baal);
	                System.out.println("THANKYOU HAVE A GOOD DAY");
	                System.exit(0);
	               
	                break;
	 
	                case 3:
	             	 System.out.println("Balance : "+rs.getInt(3));
	             	 System.out.println("THANKYOU HAVE A GOOD DAY");
	             	System.exit(0);
	                break;
	                
	                case 4:
	                	System.out.println("Enter USERNAME: ");
	                	usname=sc.next();
	                	System.out.println("Enter CURRENT PIN NUMBER: ");
	                	p=sc.nextInt();
	                	System.out.println("Enter NEW PIN NUMBER: ");
	                	pn=sc.nextInt();
	                	
	                	Class.forName("com.mysql.cj.jdbc.Driver");
	        			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmtransaction?autoReconnect=true&useSSL=false","root","root");
	        			
	            		if(rs.getInt("pin")==p && rs.getString("username" ).equals(usname))
	            		{
	            			
	            			PreparedStatement smtt = conn.prepareStatement("UPDATE user SET Pin=? WHERE username=?");
	                		smtt.setInt(1,pn);
	                	    smtt.setString(2, usname);
	                		smtt.executeUpdate();
		            	 System.out.println("PIN NUMBER SUCCESSFULLY CHANGED");
		            	 System.out.println("THANKYOU HAVE A GOOD DAY");
		            	System.exit(0);
	            		} 
	            		
	            		else {
	            		
	            		 System.out.println("YOU ENTERED CURRENT PIN OR USERNAME WRONGLY TRY AGAIN!!!");
	            	     System.exit(0);
	            	     }
	            			                      		         		
	                	break;
	                	
	                case 5:
	                	 System.out.println("Thankyou!!!!");
	                	break;
	                
	            }
	            }
	       {
	               System.out.println("SORRY,YOU ENTERED WRONG PIN OR USERNAME TRY AGAIN!! ");
	               System.exit(0);
	            
    	}}
	    catch( SQLException e)
    	{
	    
    	    e.printStackTrace();}
    	}
	
	


	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		AtmInter am = new AtmInter();
		System.out.println("WELCOME TO SMART WAY TO GET CASH");
		System.out.println("PLESASE INSERT YOUR CARD");
		am.enter();
	   am.validate(usname,p);
	  
		       
		    }
		
	}


