package com.code.jms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author jms
 * @since 2019-12-19 18:42
 *
 * 用户信息接口: http://127.0.0.1:9080/user/getUserInfo?userId=123456
 * 用户余额接口: http://127.0.0.1:9080/money/getMoneyInfo?userId=123456
 */
@Service
public class UserService {
	@Autowired
	RemoteService remoteService;//调远程接口

	//待实现
	public String getUserInfo(String userId){
		//todo
		long start = System.currentTimeMillis();
		String v1 = remoteService.getUserInfo(userId);//调http://
		JSONObject userInfo = JSONObject.parseObject(v1);// 1 2 3

		String v2 = remoteService.getUserMoney(userId);
		JSONObject moneyInfo = JSONObject.parseObject(v2);// 4 5 6

		JSONObject result = new JSONObject();
		result.putAll(userInfo);
		result.putAll(moneyInfo);//result = {1 2 3 4 5 6}
		System.out.println("执行的总时间为"+(System.currentTimeMillis()-start));
		return result.toString();
	} 
}

