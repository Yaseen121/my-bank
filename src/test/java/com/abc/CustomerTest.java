package test.java.com.abc;

import org.junit.Ignore;
import org.junit.Test;

import main.java.com.abc.Account;
import main.java.com.abc.AccountException;
import main.java.com.abc.Customer;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        oscar.openAccount(new Account(Account.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
	@Test
    public void testTransferAccountOne() {

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        Customer terry = new Customer("Terry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        
        try {
			terry.transferFunds(savingsAccount, checkingAccount, 500.0);
			assertEquals(600.0, checkingAccount.sumTransactions(), 0);
			assertEquals(3500.0, savingsAccount.sumTransactions(), 0);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    @Test
    public void testTransferAccountTwo() {
    	 Account checkingAccount = new Account(Account.CHECKING);
         Account savingsAccount = new Account(Account.SAVINGS);

         Customer terry = new Customer("Terry").openAccount(checkingAccount);

         checkingAccount.deposit(100.0);
         savingsAccount.deposit(4000.0);
         
         try {
 			terry.transferFunds(savingsAccount, checkingAccount, 500.0);
 		} catch (AccountException e) {
 			// TODO Auto-generated catch block
 			System.out.println(e);
 			assertEquals(100.0, checkingAccount.sumTransactions(), 0);
 			assertEquals(4000.0, savingsAccount.sumTransactions(), 0);
 		}
    }
}
