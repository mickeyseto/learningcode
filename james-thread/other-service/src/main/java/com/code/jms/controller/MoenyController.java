package com.code.jms.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 控制层：对外开放接口地址及路径   http://127.0.0.1:9080/money/getMoneyInfo?userId=1
* @author jms
* @throws Exception
*/
@Controller
@RequestMapping("/money")
public class MoenyController {

	private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getMoneyInfo")
    @ResponseBody
    public Object getMoneyInfo(@RequestParam(required = false) String userId) {
    	
    	logger.info("－1money接口－－用户ID为＝"+userId);
        try {
			Thread.currentThread().sleep(2000);//模拟业务处理
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
        Map<String,String> map = new HashMap<String,String>(); 
        map.put("money", "10000000");
        map.put("card", "xxxxxxx");
        return map;//JAVA对象
    }

}
