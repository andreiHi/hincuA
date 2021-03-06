package ru.job4j.litle.testtasc;

import org.junit.Test;
import ru.job4j.litle.bank.Account;
import ru.job4j.litle.bank.Bank;
import ru.job4j.litle.bank.User;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * Test.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 19.09.17;
 * @version $Id$
 * @since 0.1
 */
public class BankTest {
    /**
     * Тест добавления нового пользователя.
     */
    @Test
    public void whenNewUserAdded() {
        Bank bank = new Bank();
        User user = new User("Ivan", "A022022");
        bank.addUser(user);
        Map<User, List<Account>> map = bank.getBank();
        boolean ex = map.containsKey(user);
        assertTrue(ex);
    }

    /**
     * Тест перевода, когда пользователь переводит деньги со своего счета на свой другой счет.
     */
    @Test
    public void whenTransferMoneyFromAccountToAccountOneUser() {
        Bank bank = new Bank();
        User user = new User("Ivan", "A022022");
        bank.addUser(user);
        Account account =  new Account(100, "1");
        bank.addAccountToUser(user, account);
        Account account1 =  new Account(0, "2");
        bank.addAccountToUser(user, account1);
        bank.transferMoney(user, account, user, account1, 50.00d);
        List<Account> list = bank.getUserAccounts(user);
        account1 = list.get(1);
        Account ex = new Account(50, "2");
        assertThat(account1, is(ex));
    }

    /**
     * Тест перевода денег с счета одного пользователя другому.
     */
    @Test
    public void whenFirstUserTransferMoneyToSecondUser() {
        Bank bank = new Bank();
        User userSr = new User("Ivan", "A022022");
        User userDs = new User("Kolea", "A022025");
        bank.addUser(userSr);
        bank.addUser(userDs);
        Account accountSr =  new Account(100, "1");
        Account accountDs =  new Account(100, "2");
        bank.addAccountToUser(userSr, accountSr);
        bank.addAccountToUser(userDs, accountDs);
        bank.transferMoney(userSr, accountSr, userDs, accountDs, 50);
        List<Account> list = bank.getUserAccounts(userDs);
        accountDs = list.get(0);
        Account ex = new Account(150, "2");
        assertThat(accountDs, is(ex));
    }

    /**
     * Удаление пользователя из списка вкладчиков.
     */
    @Test
    public void whenUserWasDeleted() {
        Bank bank = new Bank();
        User userSr = new User("Ivan", "A022022");
        User userDs = new User("Kolea", "A022025");
        bank.addUser(userSr);
        bank.addUser(userDs);
        bank.deleteUser(userDs);
        Map<User, List<Account>> map =  bank.getBank();
        boolean present = map.containsKey(userDs);
        assertFalse(present);
    }

    /**
     * Тест добавления нового счета пользователю.
     */
    @Test
    public void whenNewAccountAddedToUser() {
        Bank bank = new Bank();
        User user = new User("Ivan", "A022022");
        bank.addUser(user);
        Account account =  new Account(100, "1");
        bank.addAccountToUser(user, account);
        Account account2 =  new Account(100, "2");
        bank.addAccountToUser(user, account2);
        List<Account> list = bank.getUserAccounts(user);
        assertTrue(list.contains(account2));
        assertTrue(list.contains(account));
    }

    /**
     * Тест удаления одного из счетов пользователя.
     */
    @Test
    public void whenRemovedAccountFromUser() {
        Bank bank = new Bank();
        User user = new User("Ivan", "A022022");
        bank.addUser(user);
        Account account =  new Account(100, "1");
        bank.addAccountToUser(user, account);
        Account account2 =  new Account(100, "2");
        bank.addAccountToUser(user, account2);
        boolean res = bank.deleteAccountFromUser(user, account2);
        assertTrue(res);
        List<Account> list = bank.getUserAccounts(user);
        boolean contains = list.contains(account2);
        boolean ex = false;
        assertThat(contains, is(ex));
    }
}
/**
 *putIfAbsent эквивалентно:
 * ValueClass value = new ValueClass();
 * if (map.get("key") == null) {
 * map.put("key",value);
 * }
 *а computeIfAbsent эквивалентно:
 *
 * if (map.get("key") == null) {
 * map.put("key",new ValueClass());
 }
 */
