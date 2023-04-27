package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Admin;
import org.assertj.core.util.TriFunction;
import org.jetbrains.annotations.Nullable;

public class AdminMother {

    private static final String validEmail = "user@sample.com";
    private static final String validUserName = "user";
    private static final String validPassword = "password";

    public static Admin getAdmin(@Nullable String userName, @Nullable String password, @Nullable String email) {
        var admin = new Admin();
        admin.setUsername(userName);
        admin.setPassword(password);
        admin.setEmail(email);
        return admin;
    }

    public static Admin getAdminWithEncodingPassword(@Nullable String userName, @Nullable String password, @Nullable String email) {
        var admin = getAdmin(userName, password, email);
        admin.encodePassword();
        return admin;
    }

    public static Admin getAdminWithEncodingPassword(@Nullable String name) {
        return getValidAdminFrom(name, AdminMother::getAdminWithEncodingPassword);
    }

    public static Admin getAdmin(@Nullable String userName) {
        return getValidAdminFrom(userName, AdminMother::getAdmin);
    }

    private static Admin getValidAdminFrom(String name, TriFunction<String, String, String, Admin> adminGenerator) {
        return adminGenerator.apply(name, validPassword, validEmail);
    }

}
