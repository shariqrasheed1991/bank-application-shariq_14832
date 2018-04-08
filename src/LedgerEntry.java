//shariq rasheed
//14832
public class LedgerEntry {
	private String reason;
	private double amount;
	private double depositWithdrawAmt;
	private String entryType;
	
	public LedgerEntry()
	{
		entryType=" ";
		reason=" ";
		amount=0.0;
		depositWithdrawAmt=0.0;
	}
	public void addEntry(String pEntryType, String pReason, double pDepositWithdrawAmt, double pCurrentAmt)
	{
		entryType=pEntryType;
		reason=pReason;
		depositWithdrawAmt=pDepositWithdrawAmt;
		amount=pCurrentAmt;
	}
	public void displayEntry()
	{
		System.out.println("--------------------------------------------------------");
		System.out.println("Entry Type: " + entryType);
		if(reason!=null)
		{
			System.out.println("Reason"+reason);
		}
		System.out.printf("Amount: $%.2f\n",depositWithdrawAmt);
		System.out.printf("Current Amount: $%.2f\n", amount);
		System.out.println("--------------------------------------------------------");
		
	}

}
