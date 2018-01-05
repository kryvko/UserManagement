package ua.kh.kryvko;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.kh.kryvko.entity.RoleTest;
import ua.kh.kryvko.entity.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({RoleTest.class, UserTest.class})
public class TestSuite {
}
