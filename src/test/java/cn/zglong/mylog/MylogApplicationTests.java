package cn.zglong.mylog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MylogApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("Hello WWorld");
    }
    @Before
    public void init() {
        System.out.println("------------------------------------------");
        System.out.println("-----------------开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("-----------------测试结束-----------------");
        System.out.println("------------------------------------------");
    }


}
