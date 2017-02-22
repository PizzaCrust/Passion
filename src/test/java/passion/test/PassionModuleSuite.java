package passion.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AsmMethodTest.class,
        SrgRemappingTest.class,
        FileUtilsTest.class,
        FileEnvironmentTest.class,
        ClasspathTest.class,
        HashTest.class
})
public class PassionModuleSuite {
}
