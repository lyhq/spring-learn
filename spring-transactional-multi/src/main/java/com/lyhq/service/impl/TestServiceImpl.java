package com.lyhq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyhq.center.dao.entity.LyhqTable;
import com.lyhq.center.dao.mapper.LyhqTableMapper;
import com.lyhq.service.TestService;

/**
 * Created by lyhq on 18/08/22
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private LyhqTableMapper lyhqTableMapper;

    @Transactional
    public void testInsert(int num, String name) {
        try {
            LyhqTable lyhqTable = new LyhqTable();
            lyhqTable.setName(name);
            lyhqTable.setNum(num);
            lyhqTableMapper.insert(lyhqTable);
            if (num == 3) {
                int i = 1 / 0;// 此处会产生异常
            }
        } catch (Exception ex) {
            System.out.println(num + "service异常日志处理");
            throw new RuntimeException(ex);//抛出运行时异常将进行事务的回滚
        }
        System.out.println(num + "service正常执行");
    }
}
