import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//shariq rasheed
//14832
public class Account {
	private String firstName;
	private String lastName;
	private String accountNumber;
	private double currentAmount;
	private int pin;
	private LedgerEntry[] accountLedger;
	
	private int ledgerEntryCount;
	private final int MAX_ENTRIES=100;
	
	public Account()
	{
		firstName=" ";
		lastName=" ";
		accountNumber=" ";
		currentAmount=0.0;
		ledgerEntryCount=0;
		initLedger();
	}
	public Account(String pName, String lName, double pAmt)
	{
		firstName=pName;
		lastName=lName;
		generateAccNum();
		currentAmount=pAmt;
		ledgerEntryCount=0;
		System.out.println("Account has been Successfully created.......!!");
		displayAccDetails();
		initLedger();
	}
	private void displayAccDetails()
	{
		System.out.println("First Name: "+firstName);
		System.out.println("Last Name: "+lastName);
		System.out.println("Account Number: "+accountNumber);
		System.out.println("Current Balance: "+currentAmount);
		System.out.println("Pin is: "+pin);
	}
	private void generateAccNum()
	{
		char fname=firstName.charAt(0);
		int count1=0;
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
		int count2=0;
		char lname=lastName.charAt(0);
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
		
		accountNumber=""+firstName.charAt(0)+lastName.charAt(0)+"-"+(firstName.length()+lastName.length())+"-"+count1;
		pin=(count1*100)+count2;
		customersfiles(count1,count2);
		
	}
	public int countAlphabet1()
	{
		char fname=firstName.charAt(0);
		int count1=0;
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
		return count1;
	}
	public int countAlphabet2()
	{
		int count2=0;
		char lname=lastName.charAt(0);
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
		return count2;
	}
	private void customersfiles(int count1,int count2)
	{
		String checkAcc=""+firstName.charAt(0)+lastName.charAt(0)+"-"+(firstName.length()+lastName.length())+"-"+count1+"-"+count2+"-checking"+".txt";
		String savingAcc=""+firstName.charAt(0)+lastName.charAt(0)+"-"+(firstName.length()+lastName.length())+"-"+count1+"-"+count2+"-savings"+".txt";
		try {
			FileWriter file = new FileWriter(checkAcc,true);
			PrintWriter output=new PrintWriter(file);
			output.close();
		}catch(IOException ex) {
			System.out.println(ex);
		}
		try {
			FileWriter file = new FileWriter(savingAcc,true);
			PrintWriter output=new PrintWriter(file);
			output.close();
		}catch(IOException ex) {
			System.out.println(ex);
		}
	}
	private void initLedger()
	{
		accountLedger= new LedgerEntry[MAX_ENTRIES];
		accountLedger[ledgerEntryCount]=new LedgerEntry();
		accountLedger[ledgerEntryCount].addEntry("Deposit","Intial Deposit",currentAmount, currentAmount);
		ledgerEntryCount+=1;
	}
	public void deposit(double pDepAmt,boolean flag)
	{
		currentAmount= currentAmount + pDepAmt;
		accountLedger[ledgerEntryCount]= new LedgerEntry();
		
		accountLedger[ledgerEntryCount].addEntry("Deposit",null,pDepAmt,currentAmount);
		ledgerEntryCount+=1;
		if(flag==true)
		{
			writeinfile("current","Deposit",null,pDepAmt);
		}
		else
			writeinfile("savings","Deposit",null,pDepAmt);
	}
	private void writeinfile(String acc,String type,String pReason,double pDepamt)
	{
		int count1=countAlphabet1(),count2=countAlphabet2();
		String filename=""+firstName.charAt(0)+lastName.charAt(0)+"-"+(firstName.length()+lastName.length())+"-"+count1+"-"+count2+"-"+acc+".txt";
		try {
			FileWriter file = new FileWriter(filename,true);
			PrintWriter output=new PrintWriter(file);
			output.println(type);
			output.println(pReason);
			output.println(pDepamt);
			output.println(currentAmount);
			output.close();
		}catch(IOException ex) {
			System.out.println(ex);
		}
	}
	public void Withdraw(Double pWithdrawlAmt, String pReason,boolean flag)
	{
		if((currentAmount-pWithdrawlAmt)>currentAmount)
		{
			System.out.println("This amount cannot be withdrawn. Please try again with appropriate amount....!!!");
			return;
		}
		currentAmount= currentAmount - pWithdrawlAmt;
		accountLedger[ledgerEntryCount]= new LedgerEntry();
		
		accountLedger[ledgerEntryCount].addEntry("Withdrawl", pReason, pWithdrawlAmt , currentAmount);
		
		ledgerEntryCount+=1;
		if(flag==true)
		{
			writeinfile("current","Withdraw",null,pWithdrawlAmt);
		}
		else
			writeinfile("savings","Withdraw",null,pWithdrawlAmt);
	}
	public void displayfile(String acc)
	{
		int count1=countAlphabet1(),count2=countAlphabet2();
		String filename=""+firstName.charAt(0)+lastName.charAt(0)+"-"+(firstName.length()+lastName.length())+"-"+count1+"-"+count2+"-"+acc+".txt";
		File file = new File(filename);
		
		if(file.isFile() && file.canRead())
		{
			System.out.println("Account Name: " + getfName());
			System.out.println("Last Name: "+getlName());
			System.out.println("Account Number: " + getAccountNumber());
			
			try {
				Scanner input = new Scanner(file);
				
				while(input.hasNextLine())
				{
					String line=input.nextLine();
					
					System.out.println("--------------------------------------------------------");
					System.out.println("Entry Type: " + line);
					line=input.nextLine();
					if(line!=null)
					{
						System.out.println("Reason: "+line);
						line=input.nextLine();
					}
					System.out.println("Amount: "+line);
					line=input.nextLine();
					System.out.printf("Current Amount: "+ line);
					//line=input.nextLine();
					System.out.println("--------------------------------------------------------");
				}
				input.close();
			}catch(IOException ex) {
				System.out.println("Error"+ex);
			}
		}
		else
			System.out.println("Invalid user.......!!!!!");
	}
	public void displayLedgerEntries()
	{
		System.out.println("Account Name: " + getfName());
		System.out.println("Last Name: "+getlName());
		System.out.println("Account Number: " + getAccountNumber());
		System.out.println("********************************************************");
		for(int i=0; i<ledgerEntryCount; i++)
		{
			accountLedger[i].displayEntry();
		}
		System.out.println("********************************************************");
		
	}
	public String getfName()
	{
		return firstName;
	}
	public void setName(String firstname)
	{
		this.firstName=firstname;
	}
	public String getlName(){
		return lastName;
	}
	public void setlName(String lastName){
		this.lastName=lastName;
	}
	public String getAccountNumber()
	{
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber=accountNumber;
	}
	public double getCurrentAmount()
	{
		return currentAmount;
	}
	public void setCurrentAmount(double currentAmount)
	{
		this.currentAmount=currentAmount;
	}
	public void setpin(int pin)
	{
		this.pin=pin;
	}
	public int getpin()
	{
		return pin;
	}
}
