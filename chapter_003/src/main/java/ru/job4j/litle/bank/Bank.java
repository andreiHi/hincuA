package ru.job4j.litle.bank;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Bank.
 * @author Hincu Andrei (andreih1981@gmail.com) by 19.09.17;
 * @version $Id$
 * @since 0.1
 */
public class Bank {
    /**
     * хранилище всех вкладчиков, ключь user значение список счетов.
     */
    private Map<User, List<Account>> bank;

    /**
     * Конструктор класса.
     */
    public Bank() {
        this.bank = new HashMap<>();

    }

    /**
     * добовляет нового вкладчика.
     * @param user user.
     */
    public void addUser(User user) {
        bank.computeIfAbsent(user, k -> new ArrayList<>());
    }

    /**
     * удаление пользователя.
     * @param user user.
     */
    public void deleteUser(User user) {
        if (user != null) {
            bank.remove(user);
        }
    }

    /**
     * добовляем новый счет пользователю.
     * @param user user.
     * @param account account
     */
    public void addAccountToUser(User user, Account account) {
        if (user != null && account != null) {
            bank.computeIfPresent(user, (k, v) -> {
                if (!v.contains(account)) {
                    v.add(account);
                }
                return v;
            });
        }
    }

    /**
     * getter.
     * @return bank.
     */
    public Map<User, List<Account>> getBank() {
        return bank;
    }

    /**
     * Удаление акаунта у юсера.
     * @param user user.
     * @param account account.
     */
    public boolean deleteAccountFromUser(User user, Account account) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (user != null && account != null) {
            bank.computeIfPresent(user, (k, v) -> {
                result.set(v.remove(account));
                return v;
            });
        }
        return result.get();
    }

    /**
     * Метод возвращает список счетов вкладчика.
     * @param user вкладчик.
     * @return лист.
     */
    public List<Account> getUserAccounts(User user) {
        return bank.get(user);
    }

    public Account getAccountByRequisite(String passport, String requisite) {
//        return bank.keySet().stream()
//                .filter(u -> u.getPasport().equals(passport))
//                .map(u -> bank.get(u))
//                .findFirst()
//                .stream()
//                .findFirst()
//                .map(accounts -> accounts.stream()
//                        .filter(account -> account.getRequisites().equals(requisite))
//                        .findFirst().orElse(null))
//                .orElse(null);
        final User userByPassport = getUserByPassport(passport);
        Account account = null;
        if (userByPassport != null) {
            account = bank.get(userByPassport)
                    .stream()
                    .filter(a -> a.getRequisites().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return account;
    }

    /**
     * Перевод денег с одного счета на другой счет.
     * @param srcUser плательщик.
     * @param srcAccount счет плательщика.
     * @param dstUser получатель.
     * @param dstAccount счет получателя.
     * @param amount сумма платежа.
     * @return true or false.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean weHappy = false;

        return weHappy;
    }
    public User getUserByPassport(String passport) {
        return bank.entrySet()
                .stream().filter(u -> u.getKey().getPasport().equals(passport))
                .map(Map.Entry::getKey)
                .findAny()
                .orElse(null);
    }
}
