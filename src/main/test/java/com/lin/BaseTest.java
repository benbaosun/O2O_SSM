package com.lin;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lkmc2
 * @date 2018/4/25.
 * spring和junit整合，junit启动时加载spring
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉Spring配置文件在哪
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BaseTest {

}
