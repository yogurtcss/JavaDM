package demo3_xmlioc;

import demo3_myxmlioc.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations="classpath:xmls/bean_demo3_xmlioc_AOP.xml" )
public class testTransfer2 {
    @Autowired
    private AccountService as;

    @Test
    public void testTransfer2(){
        as.transfer( "aaa", "bbb", 100f );
    }

}
