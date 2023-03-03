package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.User;
import org.assertj.core.util.TriFunction;
import org.jetbrains.annotations.Nullable;

public class UserMother {

    private static final String validEmail = "user@sample.com";
    private static final String validUserName = "user";
    private static final String validPassword = "password";

    public static User getUser(@Nullable String userName, @Nullable String password, @Nullable String email) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    public static User getUserWithEncodingPassword(@Nullable String userName, @Nullable String password, @Nullable String email) {
        var user = getUser(userName, password, email);
        user.encodePassword();
        return user;
    }

    public static User getUserWithEncodingPassword(@Nullable String name) {
        return getUserSimple(name, UserMother::getUserWithEncodingPassword);
    }

    public static User getUser(@Nullable String userName) {
        return getUserSimple(userName, UserMother::getUser);
    }

    private static User getUserSimple(String name, TriFunction<String, String, String, User> func) {
        return func.apply(name, validPassword, validEmail);
    }

}
