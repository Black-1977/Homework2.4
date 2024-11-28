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

        if (login.length() > 20 || !checkAllowedSymbols(login)) {
            throw new WrongLoginException();
        }

        if (password.length() > 20 || !checkAllowedSymbols(password) || !password.equals(passwordConfirm)) {
            throw new WrongPasswordException();
        }
    }

    public static boolean checkAllowedSymbols(String str) {
        final String allowedSymbols = "0987654321_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < str.length(); i++) {
            if (allowedSymbols.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
}
