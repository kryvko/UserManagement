package ua.kh.kryvko.entity;

import org.junit.Assert;
import org.junit.Test;

public class RoleTest {

    private static final Long testId = 123L;

    private static final String testName = "testName";

    @Test
    public void roleFieldsTest() {

        Role role = new Role();

        Assert.assertNotNull(role);

        role.setId(testId);
        role.setName(testName);
        Assert.assertEquals(testId, role.getId());
        Assert.assertEquals(testName, role.getName());

        Assert.assertNotEquals("", role.toString());
    }



}
