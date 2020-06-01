package data.lab.ongdb.model;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import java.util.Objects;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.model
 * @Description: TODO(用户校验)
 * @date 2020/6/1 10:18
 */
public class AuthUser {
    private String user;
    private String password;

    public AuthUser() {
    }

    public AuthUser(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthUser authUser = (AuthUser) o;
        return Objects.equals(user, authUser.user) &&
                Objects.equals(password, authUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
