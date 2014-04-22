package org.maugtaurus.projects.genealogie.test.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommonTestCase {

	private static final Log log = LogFactory.getLog(CommonTestCase.class);

	private ApplicationContext appContext = null;
	private final static String APPLICATION_CONTEXT = "application-test-context.xml";

	@Before
	public void setUp() throws Exception {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
	}

	public static Log getLog() {
		return log;
	}

	public ApplicationContext getAppContext() {
		return appContext;
	}

}
