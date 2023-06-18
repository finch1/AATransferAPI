package com.AATransferAPI.AATransferAPI;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import com.AATransferAPI.AATransferAPI.Persistence.IAccountRepository;
import com.AATransferAPI.AATransferAPI.Persistence.ITransferRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTest {

    @Autowired private IAccountRepository accountRepository;
    @Autowired private ITransferRepository transferRepository;

    @Test
    public void testAddAccount(){
        Account account = new Account(1L, "EUR");
        Account savedAccount = accountRepository.save(account);

        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.get_accountID()).isGreaterThan(0);
    }

    @Test
    public void testTransfer(){
        Account account_1 = new Account(1L, "EUR");
        Account savedAccount_1 = accountRepository.save(account_1);

        Account account_2 = new Account(1L, "EUR");
        Account savedAccount_2 = accountRepository.save(account_2);

        transferRepository.MakeTransfer(savedAccount_1.get_accountID(),
                                        savedAccount_2.get_accountID(),
                                        100.0,
                                        "Transfer Test Scenario",
                                        LocalDateTime.now());

        Optional<Account> updatedAccount_1 = accountRepository.findById(savedAccount_1.get_accountID());
        Optional<Account> updatedAccount_2 = accountRepository.findById(savedAccount_2.get_accountID());

        assertThat(updatedAccount_1.get().get_balance()).isEqualTo(0.0);
        assertThat(updatedAccount_2.get().get_balance()).isEqualTo(200.0);
    }
}
