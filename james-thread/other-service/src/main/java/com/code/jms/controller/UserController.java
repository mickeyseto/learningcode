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
* 控制层：对外开放接口地址及路径   http://127.0.0.1:9080/user/getUserInfo?userId=1
* @author jms
* @throws Exception
*/
@Controller
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//物流对外提供的发货接口
    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public Object getUserInfo(@RequestParam(required = false) String userId) {
    	
    	logger.info("－－－2用户ID为＝"+userId);
        try {
			Thread.currentThread().sleep(2000);//模拟业务处理
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
        Map<String,String> map = new HashMap<String,String>();
        map.put("userId", "james");
        map.put("length", "50");
        map.put("tel", "13813131313");
        return map;//JAVA对象
    }

}
