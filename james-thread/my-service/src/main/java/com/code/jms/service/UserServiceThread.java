package com.code.jms.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jms
 * @since 2019-12-19 18:42
 *
 * 用户信息接口: http://127.0.0.1:9080/user/getUserInfo?userId=123456
 * 用户余额接口: http://127.0.0.1:9080/money/getMoneyInfo?userId=123456
 */
@Service
public class UserServiceThread {
	@Autowired
	RemoteService remoteService;//调远程接口

	//待实现
	public String getUserInfo(String userId){
		//todo
		long start = System.currentTimeMillis();

		/*new Thread(new Runnable() {
			@Override
			public void run() {
				String v1 = remoteService.getUserInfo(userId);//调http://
				JSONObject userInfo = JSONObject.parseObject(v1);// 1 2 3
				return userInfo;
			}
		}).start();*/

		Callable<JSONObject> userInfoCall = new Callable<JSONObject>() {
			@Override
			public JSONObject call() throws Exception {
				String v1 = remoteService.getUserInfo(userId);//调http://
				JSONObject userInfo = JSONObject.parseObject(v1);// 1 2 3
				return userInfo;
			}
		};

		Callable<JSONObject> userMoneyCall = new Callable<JSONObject>() {
			@Override
			public JSONObject call() throws Exception {
				String v2 = remoteService.getUserMoney(userId);
				JSONObject moneyInfo = JSONObject.parseObject(v2);// 4 5 6
				return moneyInfo;
			}
		};

		JamesFutureTask<JSONObject> userTask = new JamesFutureTask<JSONObject>(userInfoCall);
		JamesFutureTask<JSONObject> moneyTask = new JamesFutureTask<JSONObject>(userMoneyCall);

		new Thread(userTask).start();
		new Thread(moneyTask).start();

		JSONObject result = new JSONObject();
		try {
			result.putAll(userTask.get());//阻塞
			result.putAll(moneyTask.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("执行的总时间为"+(System.currentTimeMillis()-start));
		return result.toString();
	} 
}

