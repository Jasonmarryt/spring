package junit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.service.PersonService;

public class SpringTest {

	@Test
	public void test() {
		
//		ItCastClasspathXmlAppliction ctx = new ItCastClasspathXmlAppliction("beans.xml");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//		PersonService persionServie= (PersonService) ctx.getBean("personService");
//		PersonService persionServie2= (PersonService) ctx.getBean("personService");
////		persionServie.save();
//		System.out.println(persionServie==persionServie2);
//		((AbstractApplicationContext) ctx).close();
		PersonService personService=(PersonService) ctx.getBean("personService");
		personService.save();
	}
}
