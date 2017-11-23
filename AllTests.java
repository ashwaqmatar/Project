package testPackage;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCSVFilter.class, TestKMLFilter.class })
public class AllTests {

}


