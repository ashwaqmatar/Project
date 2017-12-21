package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import ConverterPackage.Router;
import ConverterPackage.router_Analyst;

public class TestRightRouter {

	@Test
	public void test() {
		Router router = new Router("c4:6e:1f:30:2b:7c","routerName","10-27-2017 4:15:52 PM",0,0,0,0);
		router_Analyst analyst = new router_Analyst();
		assertEquals(true,analyst.analyze(router));	
		}

}
