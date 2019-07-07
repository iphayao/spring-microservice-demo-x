package com.iphayao.demo;

import com.iphayao.demo.account.Account;
import com.iphayao.demo.account.AccountDto;
import com.iphayao.demo.account.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private AccountRepository accountRepository;

	private WebTestClient testClient;

	@Before
	public void setUp() throws Exception {
		accountRepository.deleteAll();
		testClient = WebTestClient.bindToApplicationContext(context).build();
	}

	@Test
	public void givenAccounts_whenGetAccounts_thenReturnOKWithAccountListBody() {
		accountRepository.saveAll(TestUtils.mockAccountEntityList()).blockLast();

		testClient.get().uri("/users")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(AccountDto.class)
				.hasSize(5);
	}

	@Test
	public void givenAccountId_whenGetAccount_thenReturnOkWithAccountBody() {
		String mockId = Objects.requireNonNull(accountRepository.save(TestUtils.mockAccountEntity())
				.map(Account::getId).block()).toString();

		testClient.get().uri("/users/" + mockId)
				.exchange()
				.expectStatus().isOk()
				.expectBody(AccountDto.class);
	}

	@Test
	public void givenAccountDto_whenPostCreateAccount_thenReturnOkAccountBody() {

		testClient.post().uri("/users")
				.body(TestUtils.mockAccountDtoBody(), AccountDto.class)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(AccountDto.class);
	}

	@Test
	public void givenAccountIdAndAccountDto_whenPutAccount_thenReturnOkWithAccountBody() {
		String mockId = Objects.requireNonNull(accountRepository.save(TestUtils.mockAccountEntity())
				.map(Account::getId).block()).toString();

		testClient.put().uri("/users/" + mockId)
				.body(TestUtils.mockAccountDtoBody(), AccountDto.class)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.phone").isEqualTo("0888888888");
	}

	@Test
	public void givenAccountId_whenDeleteAccount_thenReturnOkWithoutBody() {
		String mockId = Objects.requireNonNull(accountRepository.save(TestUtils.mockAccountEntity())
				.map(Account::getId).block()).toString();

		testClient.delete().uri("/users/" + mockId)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.isEmpty();
	}
}
