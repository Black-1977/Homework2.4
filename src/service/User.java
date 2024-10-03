package service;

import service.exceptions.*;

import java.io.*;

public class User {
    private String login;
    private String password;
    private final String allowedSymbols = "0987654321_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public User() {
        try {
            login = checkLogin();
            password = checkPass();
        } catch (WrongLoginException e) {
            System.out.println("Логин длиннее 20 символов или содержит некорректные символы");
        } catch (WrongPasswordException e) {
            System.out.println("Пароль длиннее 20 символов, содержит некорректные символы или не совпадает с подтверждением");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String checkLogin() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите логин: ");
        String login = br.readLine();
        if (login.length() > 20) {
            throw new WrongLoginException();
        }
        for (int i = 0; i < login.length(); i++) {
            if (allowedSymbols.indexOf(login.charAt(i)) == -1) {
                throw new WrongLoginException();
            }
        }
        return login;
    }

    public String checkPass() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите пароль: ");
        String password = br.readLine();
        if (password.length() > 20) {
            throw new WrongPasswordException();
        }
        for (int i = 0; i < password.length(); i++) {
            if (allowedSymbols.indexOf(password.charAt(i)) == -1) {
                throw new WrongPasswordException();
            }
        }
        System.out.print("Повторите пароль: ");
        String passwordConfirm = br.readLine();
        if (!password.equals(passwordConfirm)) {
            throw new WrongPasswordException();
        }
        return password;
    }
}
