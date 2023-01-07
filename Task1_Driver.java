import java.util.*;
import java.io.*;

class Account implements Serializable {
	String name;
	int account_number;
	String pin;
	double Amount;

	Account()
	{
		name = null;
		account_number = 0;
		pin = null;
		Amount = 0;	
	}
	
	Account(String n, int acc, String pi, double amount)
	{
		name = n;
		account_number = acc;
		pin = pi;
		Amount = 1000 + amount;	
	}

	public void setName(String n)
	{
		name = n;
	}

	public void setAccountNumber(int n)
	{
		account_number = n;
	}

	public void setPIN(String p)
	{
		pin = p;
	}

	public void setAmount(double a)
	{
		Amount = a;
	}

	public String getName()
	{
		return name;
	}

	public int getAccountNumber()
	{
		return account_number;
	}

	public String getPIN()
	{
		return pin;
	}

	public double getAmount()
	{
		return Amount;
	}

}



class Individual implements Serializable {
	String name;
	int amount;
	String add;
	//double Amount;

	Individual()
	{
		name = null;
		amount = 0;
		add = null;

	}

	Individual(String n, int acc, String add1)
	{
		name = n;
		amount = acc;
		add = add1;

	}

	public void setName(String n)
	{
		name = n;
	}

	public void setAmountNumber(int n)
	{
		amount = n;
	}

	public void setAddress(String p)
	{
		add = p;
	}



	public String getName()
	{
		return name;
	}

	public int getAmountNumber()
	{
		return amount;
	}

	public String getAddress() {
		return add;
	}


}

class Bank
{
	ArrayList<Account> AL = new ArrayList<Account>();
	ArrayList<Individual> Payee_record = new ArrayList<Individual>();




	//add new consumer to the file ..
	// And this section is only visible to administrator
	public void addNewRecord()
	{
		Scanner input = new Scanner(System.in);

		System.out.print("\nEnter name of Account Holder: ");
		String n = input.nextLine();
		System.out.print("Enter an 8 digit Account Number (contact manager for its allocation): ");
		int a = input.nextInt();
		System.out.print("Enter PIN for Account Holder: ");
		String p = input.next();
		System.out.print("Default amount of 1000 is already added to the account, to add more money, write that amount else enter zero: ");
		double am = input.nextDouble();
		
		Account ac = new Account(n, a, p, am);
		AL.add(ac);
		return;
	}


	//Transfer money section code
	//Here we transfer money from one account to another
	// we used bankrecord.txt file here



	public void transfer()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter sender's 8 digit account number: ");
		int s_acc = input.nextInt();
		System.out.print("Enter Sender's pin code: ");
		String s_pin = input.next();

		int sender_index = -1;
		for(int i = 0; i< AL.size(); i++)
		{
			if (AL.get(i).getAccountNumber() == s_acc && AL.get(i).getPIN().equals(s_pin))
				sender_index = i;
		}

		if(sender_index == -1)
		{
			System.out.println("\n Account not Found");
			return;
		}	

		System.out.print("\nEnter receiver's 8 digit account number: ");
		int r_acc = input.nextInt();

		int receiver_index = -1;
		for(int i = 0; i< AL.size(); i++)
		{
			if (AL.get(i).getAccountNumber() == r_acc)
				receiver_index = i;
		}
		
		if(receiver_index == -1)
		{
			System.out.println("\n Receiver's account not Found");
			return;
		}

		System.out.print("\nAmount to be transferred: ");
		double amount = input.nextDouble();
		if(AL.get(sender_index).getAmount() >= amount)
		{
			AL.get(receiver_index).setAmount(AL.get(receiver_index).getAmount() + amount );
			AL.get(sender_index).setAmount(AL.get(sender_index).getAmount() - amount );
			return;
		}
		else 
		{
			System.out.println("\nSender doesnot have this much balance in his account");
			return;
		}	
	}	




	//here is withdraw option from bank adminstration
	//We withdraw  money from the account
	public void withdraw() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter User's 8 digit account number: ");
		int p_acc = input.nextInt();
		System.out.print("Enter User's pin code: ");
		String p_pin = input.next();

		int person_index = -1;
		for(int i = 0; i< AL.size(); i++)
		{

			if ((AL.get(i).getAccountNumber() == p_acc) && (AL.get(i).getPIN().equals(p_pin)))
			{
				person_index = i;
			}
		}

		if(person_index == -1)
		{
			System.out.println("\n Account not Found");
			return;
		}	

		System.out.print("\nAmount to be Withdrawn: ");
		double amount = input.nextDouble();
		if(AL.get(person_index).getAmount() >= amount)
		{
			AL.get(person_index).setAmount(AL.get(person_index).getAmount() - amount );
			return;
		}
		else 
		{
			System.out.println("\nThis person doesnot have this much balance in his account");
			return;
		}	
	}


     //
	//print the all account visible in the Bankrecord.txt file
	public void print() {
		for(int i = 0; i<AL.size(); i++)
		{
			System.out.println("\nName: " + AL.get(i).getName());
			System.out.println("Account Number: " + AL.get(i).getAccountNumber());
			System.out.println("Balance: " + AL.get(i).getAmount() + "\n");
		}
	}

	//Before viewing payee we need to add that into the file
	public  void addNewPayee(){
		Scanner add_pay = new Scanner(System.in);

		System.out.print("\nEnter name of Payee: ");
		String n = add_pay.nextLine();
		System.out.print("How much money will he get from a consumer: ");
		int a = add_pay.nextInt();
		System.out.print("Adress of Payee: ");
		String p = add_pay.next();


		Individual aa = new Individual(n, a, p);
		Payee_record.add(aa);
		return;
	}

	//here we print all the pay balance from the file
	public void view_balance() {
		for(int j = 0; j<Payee_record.size(); j++) {
			System.out.println("\nName: " + Payee_record.get(j).getName());
			System.out.println("Balance: " + Payee_record.get(j).getAmountNumber() + "\n");
		}
	}

	//print all the pay that are visible in the database or file
	public void print_pay() {
		for(int i = 0; i<Payee_record.size(); i++)
		{
			System.out.println("\nName: " + Payee_record.get(i).getName());
			System.out.println("Amount: " + Payee_record.get(i).getAmountNumber());
			System.out.println("Address: " + Payee_record.get(i).getAddress() + "\n");
		}
	}


	//load the Bankrecord.txt file.Before using this file, we must need load or set it
	public void load()
	{
	 try{
		FileInputStream fis = new FileInputStream("BankRecord.txt");
		ObjectInputStream in = new ObjectInputStream(fis);
		while(true)
		{
			Account temp = (Account) in.readObject(); 
			if(temp == null)
				break;
			AL.add(temp);
		}
		fis.close();
	     }
	 catch(Exception e)
	 {
	 }
	}

	//Here we use two files
	//Bankrecord.txt file is used for administrator section
	// And Bankrecord1.txt for Individual consumer section
	// Similarly  here we need load Bankrecord1.txt file to use it.
	public void pay_load()
	{
		try{
			FileInputStream fisp = new FileInputStream("BankRecord1.txt");
			ObjectInputStream inp = new ObjectInputStream(fisp);
			while(true)
			{
				Individual temp = (Individual) inp.readObject();
				if(temp == null)
					break;
				Payee_record.add(temp);
			}
			fisp.close();
		}
		catch(Exception e)
		{
		}
	}
	//Save the file into the created Bankrecord.txt file
	public void save()
	{
	 try{
		FileOutputStream fos = new FileOutputStream("BankRecord.txt");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		for(int i = 0; i<AL.size(); i++)
			out.writeObject(AL.get(i));
		fos.close();
	    }
	 catch(Exception e)
	 {
		System.out.println("\nError Saving Data to File");
	 }	
	}



	//Similarly this section works on saving payee record for the Bankrecord1.txt file
	public void Paysave()
	{
		try{
			FileOutputStream fos = new FileOutputStream("BankRecord1.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			for(int i = 0; i<Payee_record.size(); i++)
				out.writeObject(Payee_record.get(i));
			fos.close();
		}
		catch(Exception e)
		{
			System.out.println("\nError Saving Data to File");
		}
	}
}



//Main class
class Task1_Driver {
	public static void main(String args[]) {

		Scanner sc1 = new Scanner(System.in);
		System.out.println("\n**********************Welcome to our Bank*************************");
		System.out.print("\n**********Enter types of user: **********\n");
		System.out.println("\n1. Individual User");
		System.out.println("\n2. Bank Adminstration\n");
		System.out.println("Types one or Two for Validation: ");


		int user_choice = sc1.nextInt();

		//This is the main section of individual consumer
		//Here we called many function that we used for the view balance,transfer money
		//And add/view payee record
		if(user_choice==1){
			Bank for_user=new Bank();
			for_user.pay_load();
			try{
				System.out.println("\n**********************Welcome to the Individual Consumer Bank*************************");
				int us_choice=0;
				while(us_choice!=5){
					System.out.println("\n1 - View their Balance");
					System.out.println("2 - View their Transaction by date");//does not work properly
					System.out.println("3 - Transfer money between their own different account types");
					System.out.println("4 - Add new Payee");
					System.out.println("5 - View Payee");
					System.out.println("6 - Payment to someone who is not in the list");
					System.out.println("7 - Exit");
					us_choice=sc1.nextInt();
					if(us_choice==1){
                         for_user.view_balance();
						//for_user.print();
					} else if(us_choice==2){
						System.out.println("This feature does not work properly.So enter other option.\n");
						System.out.println("Thank You");
					}
					else if(us_choice==3){
                         for_user.transfer();
					}else if(us_choice==4){
						 for_user.addNewPayee();
						 System.out.println("Added Successfully");
					}else if(us_choice==5){
						 for_user.print_pay();
					}else if(us_choice==6){
						 System.out.println("Before Payment you must add that payee into database.");
						 for_user.addNewPayee();
					} else if(us_choice==7){
						for_user.save();
						System.out.println("\nData saved to File \"BankRecord1 .txt\"");
						System.exit(1);
					}else{
						System.out.println("Enter Valid Options");
					}
				}
			}
			catch (Exception e) {
				System.out.println("\nWe triggered an Error");
			} finally {
				for_user.Paysave();
			}
		}


        /*
        Similarly in this part we worked on Administration section
        This the main function of adminstration function
         */
		else if (user_choice == 2) {
			Bank obj = new Bank();
			obj.load();
			try {
				Scanner input = new Scanner(System.in);
				System.out.println("\n**********************Welcome to the section of Bank Administration*************************");
				int choice = 0;
				while (choice != 5) {// I have given capacity of only five amount storing
					System.out.println("\n1 - Create new Account");
					System.out.println("2 - Transfer money from an existing account to another existing account");
					System.out.println("3 - Withdraw money from existing account");
					System.out.println("4 - Print all exisitng accounts");
					System.out.println("5 - Exit");
					System.out.print("Enter your choice: ");
					choice = input.nextInt();

					if (choice == 1) {
						obj.addNewRecord();
						System.out.println("\nAccount Created Successfully");
					} else if (choice == 2) {
						obj.transfer();
					} else if (choice == 3) {
						obj.withdraw();
					} else if (choice == 4)
						obj.print();
					else if (choice == 5) {
						obj.save();
						System.out.println("\nData saved to File \"BankRecord .txt\"");
						System.exit(1);
					} else
						System.out.println("\nWrong Input");
				}
			} catch (Exception e) {
				System.out.println("\nWe triggered an Error");
			} finally {
				obj.save();
			}
		}



		//If choice is not valid then this section works
		else{
			System.out.println("Invalid..Please use valid choice.(Individual or Consumer)");
		}

	}
}





