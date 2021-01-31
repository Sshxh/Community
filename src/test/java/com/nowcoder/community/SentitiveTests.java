package com.nowcoder.community;


import com.nowcoder.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SentitiveTests {
    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSentitiveFilter(){
        String test = "这里可以赌博，可以吸毒,哈哈哈hh";

        String res = sensitiveFilter.filter(test);

        System.out.println(res);

    }
}
