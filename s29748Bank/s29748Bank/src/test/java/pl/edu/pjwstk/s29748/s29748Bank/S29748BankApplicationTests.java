package pl.edu.pjwstk.s29748.s29748Bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pjwstk.s29748.s29748Bank.account.Account;
import pl.edu.pjwstk.s29748.s29748Bank.account.AccountService;
import pl.edu.pjwstk.s29748.s29748Bank.account.AccountStorage;
import pl.edu.pjwstk.s29748.s29748Bank.finances.Finances;
import pl.edu.pjwstk.s29748.s29748Bank.finances.FinancesService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
class S29748BankApplicationTests {
	@Autowired
	private S29748BankApplication s29748BankApplication;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountStorage accountStorage;
	  @Autowired
    private FinancesService financesService;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@Test
	void shouldNewAccount(){
		System.setOut(new PrintStream(outputStreamCaptor));
		accountService.newAccount(4000d);
		List<Account> testAccountList = accountStorage.getAccounts();
		assertThat(testAccountList.size()).isEqualTo(5);
		assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Congratulations! you created an account. your client ID is: 5");
	}
	@Test
	void shouldPrzelew(){
		accountService.przelew(1,1000d);
		Finances testFinances=financesService.findByFinancesID(accountService.findByClientID(1).getFinancesID());

		assertThat(testFinances.getFinancesAmount()).isEqualTo(3200d);
	}
	@Test
	void shouldPrzelewNot(){

	assertThatExceptionOfType(RuntimeException.class)
			.isThrownBy(()->accountService.przelew(1,100000d))
			.withMessage("Transfer DENIED, You don't have enough money");
	}
	@Test
	void shouldDeposit(){
		accountService.deposit(1,1000d);
		Finances testFinances=financesService.findByFinancesID(accountService.findByClientID(1).getFinancesID());

		assertThat(testFinances.getFinancesAmount()).isEqualTo(4200d);
	}


	@Test
	void contextLoads() {
	}

}
