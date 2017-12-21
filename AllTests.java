package testPackage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestRightRouter.class, WrongMAC.class ,WrongMAC2.class ,WrongCoordination.class ,WrongCoordination2.class})
public class AllTests {

}
