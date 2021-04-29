package com.wxw.test;

import com.wxw.manager.config.ActionListAndMapConfig;
import com.wxw.manager.config.ActionMapOrListConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author: wxw
 * @date: 2021-04-06-23:03
 * @link:
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActionTest {

    @Autowired
    private ActionMapOrListConfig actionMapOrListConfig;

    @Autowired
    private ActionListAndMapConfig actionListAndMapConfig;


    /**
     * 自定义 yml 实现 List和 map 的组合
     */
    @Test
    public void testActionListAndMapConfig() {
        // actionListAndMapConfig = [
        // {interactive_close=互动题退出, interactive_open=互动题开始推送},
        // {redbag_close=抢红包结束, redbag_open=开始抢红包}
        // ]
        List<Map<String, String>> actionList = actionListAndMapConfig.getActionList();
        for (Map<String, String> actionMap : actionList) {
            System.out.println("actionMap = " + actionMap);
        }
        actionList.forEach(actionMap->{
            actionMap.forEach((key,value)->{
                System.out.println("key = " + key + "   value = " + value);
            });
        });
        System.out.println("actionListAndMapConfig = " + actionListAndMapConfig.getActionList());
    }

    /**
     * 自定义 properties 实现 List 或 map
     */
    @Test
    public void testActionMapOrListConfig(){
        System.out.println("testActionProperties = " + actionMapOrListConfig.getPerson());
        System.out.println("testActionProperties = " + actionMapOrListConfig.getList());
    }

}
