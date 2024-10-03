import service.User;

public class Main {
    public static void main(String[] args) {

        User user = new User();

        if (user.getLogin() != null && user.getPassword() != null) {
            System.out.println("Ваши данные: логин: " + user.getLogin() + ", пароль: " + user.getPassword());
        } else {
            System.out.println("Данные для регистрации некорректны.");
        }
    }
}
