package com.code.jms.controller;

import com.code.jms.service.UserService;
import com.code.jms.service.UserServiceThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * 控制层：对外开放接口地址及路径 http://127.0.0.1:8080/my-service/main/user?userId=123
 *
 * @author  jms
 * @throws Exception
 */
@Controller
@RequestMapping("/main")
public class UserController {

	@Autowired
	private UserServiceThread userServiceThread;

	@RequestMapping(value = "/user")
	@ResponseBody
	public Callable<String> getUserInfo(String userId) {
		System.out.println("主线程开始...."+Thread.currentThread()+"---->"+System.currentTimeMillis());

		//子线程来处理
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("子线程开始...."+Thread.currentThread()+"---->"+System.currentTimeMillis());
				String result = userServiceThread.getUserInfo(userId).toString();
				System.out.println("子线程结束...."+Thread.currentThread()+"---->"+System.currentTimeMillis());
				return result;
			}
		};
		System.out.println("主线程结束...."+Thread.currentThread()+"---->"+System.currentTimeMillis());
		return callable;
	}

}
