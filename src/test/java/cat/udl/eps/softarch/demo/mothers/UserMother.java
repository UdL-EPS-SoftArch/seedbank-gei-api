package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.User;

public class UserMother {

    private static final String validEmail = "user@sample.com";
    private static final String validUserName = "user";
    private static final String validPassword = "password";

    public static User getValidUser() {
        User user = new User();
        user.setEmail(validEmail);
        user.setUsername(validUserName);
        user.setPassword(validPassword);
        return user;
    }

}
