package ua.kh.kryvko.entity;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    private static final Long testId = 123L;
    private static final String testLogin = "testLogin";
    private static final String testPassword = "testPassword";
    private static final String testEmail = "testEmail";
    private static final String testFirstName = "testFirstName";
    private static final String testLastName = "testLastName";
    private static final Long testRoleId = 321L;
    private static final String testRoleName = "testRoleName";
    private static final Role testRole = createRole(testRoleId, testRoleName);

    private static Role createRole(Long testRoleId, String testRoleName) {
        Role role = new Role();
        role.setId(testRoleId);
        role.setName(testRoleName);
        return role;
    }

    @Test
    public void UserFieldTest() {

        User user = new User();
        Assert.assertNotNull(user);

        user.setId(testId);
        user.setLogin(testLogin);
        user.setPassword(testPassword);
        user.setEmail(testEmail);
        user.setFirstName(testFirstName);
        user.setLastName(testLastName);
        user.setRole(testRole);

        Assert.assertEquals(testId, user.getId());
        Assert.assertEquals(testLogin, user.getLogin());
        Assert.assertEquals(testPassword, user.getPassword());
        Assert.assertEquals(testEmail, user.getEmail());
        Assert.assertEquals(testFirstName, user.getFirstName());
        Assert.assertEquals(testLastName, user.getLastName());
        Assert.assertEquals(testRole, user.getRole());

        Assert.assertNotEquals("", user.toString());
    }
}
