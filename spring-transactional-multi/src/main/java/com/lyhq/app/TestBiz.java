package com.lyhq.app;

import com.lyhq.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lyhq on 18/08/22
 */
@Component
public class TestBiz {

    @Autowired
    private TestService testService;

    @Transactional
    public void insertTest() {
        try {
            for (int j = 0; j < 8; j++) {
                testService.testInsert(j, j + "姓名");
                if (j == 3) {
                    int i = 1 / 0;// 此处会产生异常
                }
            }
        } catch (Exception ex) {
            System.out.println("biz层异常日志处理");
            throw new RuntimeException(ex);//抛出这个运行时异常将进行事物的回滚
        }
        System.out.println("biz层 正常执行");
    }
}
