import exceptions.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean allowUserDate = true;
        System.out.print("Введите логин: ");
        String login = br.readLine();
        System.out.print("Введите пароль: ");
        String password = br.readLine();
        System.out.print("Повторите пароль: ");
        String passwordConfirm = br.readLine();

        try {
            checkUserDate(login, password, passwordConfirm);
        } catch (WrongLoginException e) {
            allowUserDate = false;
            System.out.println("Логин длиннее 20 символов или содержит некорректные символы");
        } catch (WrongPasswordException e) {
            allowUserDate = false;
            System.out.println("Пароль длиннее 20 символов, содержит некорректные символы или не совпадает с подтверждением");
        }

        if (allowUserDate) {
            System.out.println("Ваши данные: логин: " + login + ", пароль: " + password);
        } else {
            System.out.println("Данные для регистрации некорректны.");
        }
    }

    public static void checkUserDate(String login, String password, String passwordConfirm) throws WrongLoginException, WrongPasswordException {
        final String allowedSymbols = "0987654321_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        if (login.length() > 20) {
            throw new WrongLoginException();
        }
        for (int i = 0; i < login.length(); i++) {
            if (allowedSymbols.indexOf(login.charAt(i)) == -1) {
                throw new WrongLoginException();
            }
        }
        if (password.length() > 20) {
            throw new WrongPasswordException();
        }
        for (int i = 0; i < password.length(); i++) {
            if (allowedSymbols.indexOf(password.charAt(i)) == -1) {
                throw new WrongPasswordException();
            }
        }
        if (!password.equals(passwordConfirm)) {
            throw new WrongPasswordException();
        }
    }
}
