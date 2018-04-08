import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//shariq rasheed
//14832
public class BankTransaction {
	private final int EXITPROGRAM=0;
	private Account checkingAccount;
	private Account savingAccount;
	private Scanner console;
	private final String pin="A1234";
	
	public BankTransaction()
	{
		checkingAccount=savingAccount=null;
		console = new Scanner(System.in);
	}
	private void createAccount()
	{
		console.nextLine();
		String firstName=" ", lastName=" ";
		double checkAcc=0.0, savingAcc=0.0;
		System.out.print("Enter First Name: ");
		firstName=console.nextLine();
		System.out.print("Enter Last Name: ");
		lastName=console.nextLine();
		do {
			System.out.print("Enter Valid Initial Deposit of Current Account (Amount Should be greater than zero): ");
			checkAcc=console.nextDouble();
		}while(checkAcc<0.0);
		
		do {
			System.out.print("Enter Valid Initial Deposit of Saving Account (Amount Should be greater than zero): ");
			savingAcc=console.nextDouble();
		}while(savingAcc<0.0);
		System.out.print("Current ");
		checkingAccount=new Account(firstName,lastName,checkAcc);
		System.out.print("Savings ");
		savingAccount=new Account(firstName, lastName, savingAcc);
		generateAccNum(firstName,lastName);
	}
	private void generateAccNum(String firstName, String lastName)
	{
		char fname=firstName.charAt(0);
		char lname=lastName.charAt(0);
		
		int count1=0,count2=0;
		for(int i=65,j=97;i<91;i++,j++)
		{
			if((int)fname==i || (int)fname==j)
			{
				count1++;
				break;
			}
			else
				count1++;
				
		}
		for(int i=65,j=97;i<91;i++,j++)
		{
			if((int)lname==i || (int)lname==j)
			{
				count2++;
				break;
			}
			else
				count2++;
				
		}
		
		String accountNumber=""+firstName.charAt(0)+lastName.charAt(0)+"-"+(firstName.length()+lastName.length())+"-"+count1;
		createcustomerlist(firstName,lastName,accountNumber);
		
	}
	private void createcustomerlist(String firstName, String lastName, String accountNumber)
	{
		try {
			FileWriter file = new FileWriter("customers.txt",true);
			PrintWriter output=new PrintWriter(file);
			output.println(firstName+" "+lastName);
			
			output.println(accountNumber);
			
			output.close();
		}catch(IOException ex) {
			System.out.println(ex);
		}
	}
	private void Employee()
	{
		console.nextLine();
		String ePin=" ";
		do
		{
			System.out.println("Please Enter valid pin for login: ");
			ePin=console.nextLine();
		}while(!ePin.equals(pin));
		int response=-1;
		do {
			System.out.println("1: Create Account");
			System.out.println("2: Delete Account");
			System.out.println("3: Make Transaction");
			System.out.println("4: List Customers ( With Account Numbers )");
			System.out.println("5: Customer Accountdetails ( Ledger Entries )");
			System.out.println("0: Exit");
			response=console.nextInt();
			if(response!=EXITPROGRAM)
			{
				executeEmployeeSelection(response);
			}
		}while(response!=EXITPROGRAM);
	}
	private void executeEmployeeSelection(int pResponse)
	{
		switch(pResponse)
		{
		case 1:
			createAccount();
			displayMenu();
			break;
		case 2:
			deleteuser();
			break;
		case 3:
			displayMenu();
			break;
		case 4:
			listcustomers();
			break;
		case 5:
			Customer();
			default:
				System.out.println("Invalid Entery");
		}
	}
	private void deleteuser()
	{
		String filename="";
		console.nextLine();
		System.out.println("Enter users complete saving account file name or path: ");
		filename=console.nextLine();
		File file = new File(filename);
        
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        System.out.println("Enter users complete current account file name or path: ");
		filename=console.nextLine();
		file = new File(filename);
        
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
	}
	private void forEmpLedgerdetails()
	{
		String filename="customers.txt";
		File file = new File(filename);
		try {
			Scanner input = new Scanner(file);
			String line=input.nextLine();
			while(line!=null)
			{
				System.out.println(line);
				line=input.nextLine();
			}
			input.close();
		}catch(IOException ex) {
			System.out.println("Error"+ex);
		}
	}
	private void listcustomers()
	{
		String filename="customers.txt";
		File file = new File(filename);
		try {
			Scanner input = new Scanner(file);
			String line=input.nextLine();
			while(input.hasNextLine())
			{
				System.out.println(line);
				line=input.nextLine();
			}
			input.close();
		}catch(IOException ex) {
			System.out.println("Error"+ex);
		}
	}
	public void mainDisplay()
	{
		int response=-1;
		do
		{
			System.out.println("1: Employee");
			System.out.println("2: Customer");
			System.out.println("0: Exit");
			System.out.println("Enter a Selection from the menu: ");
			response=console.nextInt();
			switch(response)
			{
			case 1:
				Employee();
				break;
			case 2:
				Customer();
				break;
			case 0:
				break;
				default:
					System.out.println("Invalid Entry");
					break;
			}
		}while(response!=0);
	}
	private void Customer()
	{
		Account temp=new Account();
		String firstName=" ",lastName=" ";
		int pin=0,response=-1;
		int count1=0,count2=0;
		console.nextLine();
		System.out.println("Enter first name: ");
		firstName=console.nextLine();
		System.out.println("Enter Last name: ");
		lastName=console.nextLine();
		System.out.println("Enter pin: ");
		pin=console.nextInt();
		console.nextLine();
		temp.setName(firstName);
		temp.setlName(lastName);
		count1=temp.countAlphabet1();
		count2=temp.countAlphabet2();
		temp.setpin((count1*100)+count2);
		if(pin!=temp.getpin())
		{
			System.out.println("Invalid user......!!!");
			return;
		}
		temp.setAccountNumber(""+firstName.charAt(0)+lastName.charAt(0)+"-"+(firstName.length()+lastName.length())+"-"+count1);
		String filename=""+firstName.charAt(0)+lastName.charAt(0)+"-"+(firstName.length()+lastName.length())+"-"+count1+"-"+count2+"-"+"savings"+".txt";
		File f=new File(filename);
		if(f.isFile())
		{
			do {
				System.out.println("1: View Saving Account Transactions");
				System.out.println("2: View Current Account Transactions");
				System.out.println("0: Logout");
				System.out.println("Enter your choice: ");
				response=console.nextInt();
				switch(response)
				{
				case 1:
					temp.displayfile("savings");
					break;
				case 2:
					temp.displayfile("current");
					break;
				case 0:
					break;
					default:
						System.out.println("Invalid Entry");
						break;
				}
			}while(response!=EXITPROGRAM);
		}
		else
		{
			System.out.println("Invalid user");
			return;
		}
	}
	private void displayMenu()
	{
		int response=-1;
		do
		{
			try {
			System.out.println("1: Deposit Into Current");
			System.out.println("2: Withdraw From Current");
			System.out.println("3: Deposit Into Saving");
			System.out.println("4: Withdraw From Saving");
			System.out.println("5: Display Current Transactions");
			System.out.println("6: Display Saving Transactions");
			System.out.println("0: Exit");
			System.out.println("Enter a Selection from the menu: ");
			response=console.nextInt();
			if(response==0)
				break;
			else
				executeMenuSelection(response);
			}catch(Exception e) {
				System.out.println("Invalid Entry");
			}
		}while(response!=EXITPROGRAM);
	}
	
	private void executeMenuSelection(int pResponse)
	{
		double depAmt=0.0;
		double withdraw=0.0;
		String reason="";
		switch(pResponse)
		{
		case 1:
			System.out.print("How much to Deposit into Current? Amount must be greater than zero: ");
			depAmt=console.nextDouble();
			while(depAmt < 0.0)
			{
				System.out.print("Please enter an amount greater than zero: ");
				depAmt= console.nextDouble();
			}
			checkingAccount.deposit(depAmt,true);
			System.out.println("");
			System.out.println("\t\t Transaction Done... ");
			System.out.println("");
			break;
		case 2:
			System.out.print("How much to withdraw from current? Amount must be greater than zero: ");
			withdraw= console.nextDouble();
			while(withdraw < 0.0)
			{
				System.out.print("Please enter an amount greater than zero: ");
				withdraw=console.nextDouble();
			}
			String clearEOL= console.nextLine();
			System.out.print("Please enter a reason for withdrawl: ");
			reason=console.nextLine();
			checkingAccount.Withdraw(withdraw, reason,true);
			System.out.println("");
			System.out.println("\t\t Transaction Done... ");
			System.out.println("");
			break;
		case 3:
			System.out.print("How much do deposit into Savings? Amount should be greater than zero: ");
			depAmt=console.nextDouble();
			while(depAmt < 0.0)
			{
				System.out.print("Please enter an amount greater than zero: ");
				depAmt=console.nextDouble();
			}
			savingAccount.deposit(depAmt,false);
			System.out.println("");
			System.out.println("\t\t Transaction Done... ");
			System.out.println("");
			break;
		case 4:
			System.out.print("How much to withdraw from savings? Amount must be greater than zero: ");
			withdraw=console.nextDouble();
			
			while(withdraw < 0.0)
			{
				System.out.print("Please enter an amount greater than zero: ");
				withdraw=console.nextDouble();
			}
			clearEOL= console.nextLine();
			System.out.print("Please enter a reason for withdrawl: ");
			reason=console.nextLine();
			savingAccount.Withdraw(withdraw, reason,false);
			System.out.println("");
			System.out.println("\t\t Transaction Done... ");
			System.out.println("");
			break;
		case 5:
			checkingAccount.displayLedgerEntries();
			break;
		case 6:
			savingAccount.displayLedgerEntries();
			break;
		default:
			System.out.println("Invalid Entry");
			break;
		}
		
	}
	public static void main(String[] args)
	{
		BankTransaction bank=new BankTransaction();
		bank.mainDisplay();
	}
}