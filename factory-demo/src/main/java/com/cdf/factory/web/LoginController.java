package com.cdf.factory.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdf.factory.service.login.ILoginService;

import reactor.core.publisher.Mono;

@RestController
public class LoginController {

	@Autowired
	ILoginService loginService;

	@GetMapping("/login/user")
	public Mono<String> sayHelloWorld(@PathVariable("msg") String msg) {

		System.out.println("come on " + msg);
		return Mono.just("sc-provider receive : " + msg);
	}

	@RequestMapping("/user")
	public Map<String, Object> user(String no, Integer pi, Integer ps) {
		List<Map<String, Object>> userList = new ArrayList<>();
		String[] avatarArray = new String[] { "https://gw.alipayobjects.com/zos/rmsportal/eeHMaZBwmTvLdIwMfBpg.png",
				"https://gw.alipayobjects.com/zos/rmsportal/udxAbMEhpwthVVcjLXik.png" };

		for (int i = 0; i < 50; i++) {
			Map<String, Object> user = new HashMap<>();
			user.put("id", i + 1);
			user.put("disabled", i % 6 == 0);
			user.put("href", "https://ant.design");
			user.put("avatar", avatarArray[i % 2]);
			user.put("no", "TradeCode" + i);
			user.put("title", "一个任务名称" + i);
			user.put("owner", "曲丽丽");
			user.put("description", "这是一段描述");
			user.put("callNo", Math.floor(Math.random() * 1000));
			user.put("status", Math.floor(Math.random() * 10) % 4);
			user.put("updatedAt", new Date());
			user.put("createdAt", new Date());
			user.put("progress", Math.ceil(Math.random() * 100));
			userList.add(user);
		}
		if (StringUtils.isNotBlank(no)) {
			userList = userList.stream().filter(ll -> String.valueOf(ll.get("no")).contains(no))
					.collect(Collectors.toList());
		}
		Integer start = (pi - 1) * ps;

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("total", userList.size());
		returnMap.put("list", userList.subList(start, pi * ps));

		return returnMap;
	}

	@RequestMapping("/user/{id}")
	public Map<String, Object> user(@PathVariable Integer id) {
		List<Map<String, Object>> userList = new ArrayList<>();
		String[] avatarArray = new String[] { "https://gw.alipayobjects.com/zos/rmsportal/eeHMaZBwmTvLdIwMfBpg.png",
				"https://gw.alipayobjects.com/zos/rmsportal/udxAbMEhpwthVVcjLXik.png" };

		for (int i = 0; i < 50; i++) {
			Map<String, Object> user = new HashMap<>();
			user.put("id", i + 1);
			user.put("disabled", i % 6 == 0);
			user.put("href", "https://ant.design");
			user.put("avatar", avatarArray[i % 2]);
			user.put("no", "TradeCode" + i);
			user.put("title", "一个任务名称" + i);
			user.put("owner", "曲丽丽");
			user.put("description", "这是一段描述");
			user.put("callNo", Math.floor(Math.random() * 1000));
			user.put("status", Math.floor(Math.random() * 10) % 4);
			user.put("updatedAt", new Date());
			user.put("createdAt", new Date());
			user.put("progress", Math.ceil(Math.random() * 100));
			userList.add(user);
		}
		return userList.get(id - 1);
	}

	/*
	 * @RequestMapping("account") public Map<String, Object>
	 * account(@RequestParam("username") String username,
	 * 
	 * @RequestParam("password") String password) { Map<String, Object> user = new
	 * HashMap<>(); user.put("token", "123456789"); user.put("name", "xuliqun");
	 * user.put("email", "854128573@qq.com"); user.put("id", "111");
	 * user.put("time", new Date()); // System.out.println("come on " + msg);
	 * Map<String, Object> ret = new HashMap<>(); ret.put("user", user); return ret;
	 * }
	 */

	@RequestMapping("/login/account")
	public Map<String, Object> account(@RequestBody Map<String, String> request) {
		return loginService.login(request.get("userName"), request.get("password"));
	}

	@RequestMapping("/chart")
	public Map<String, Object> chart() {
		long beginDay = new Date().getTime();
		List<Map<String, Object>> visitData = new ArrayList<Map<String, Object>>();
		int[] fakeY = new int[] { 7, 5, 4, 2, 4, 7, 5, 6, 5, 9, 6, 3, 1, 5, 3, 6, 5 };
		for (int i = 0; i < fakeY.length; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("x", new SimpleDateFormat("YYYY-MM-DD").format(new Date(beginDay + 1000 * 60 * 60 * 24 * i)));
			item.put("y", fakeY[i]);
			visitData.add(item);
		}

		List<Map<String, Object>> visitData2 = new ArrayList<Map<String, Object>>();
		int[] fakeY2 = new int[] { 1, 6, 4, 8, 3, 7, 2 };
		for (int i = 0; i < fakeY2.length; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("x", new SimpleDateFormat("YYYY-MM-DD").format(new Date(beginDay + 1000 * 60 * 60 * 24 * i)));
			item.put("y", fakeY2[i]);
			visitData2.add(item);
		}

		List<Map<String, Object>> salesData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 12; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("x", "" + (i + 1) + "月");
			item.put("y", Math.floor(Math.random() * 1000) + 200);
			salesData.add(item);
		}

		List<Map<String, Object>> searchData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 50; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("index", i + 1);
			item.put("keyword", "搜索关键词-" + i);
			item.put("count", Math.floor(Math.random() * 1000));
			item.put("range", Math.floor(Math.random() * 100));
			item.put("status", Math.floor((Math.random() * 10) % 2));
			searchData.add(item);
		}

		String[] typeArray = new String[] { "家用电器", "食用酒水", "个护健康", "服饰箱包", "母婴产品", "其他" };
		List<Map<String, Object>> salesTypeData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < typeArray.length; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("x", typeArray[i]);
			item.put("y", 4544 + (int) (Math.random() * 1000) + 1);
			salesTypeData.add(item);
		}

		// String[] salesTypeDataOnline = new String[]
		// {"家用电器","食用酒水","个护健康","服饰箱包","母婴产品","其他"};
		List<Map<String, Object>> salesTypeDataOnline = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < typeArray.length; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("x", typeArray[i]);
			item.put("y", (int) (Math.random() * 1000) + 1);
			salesTypeDataOnline.add(item);
		}

		List<Map<String, Object>> salesTypeDataOffline = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < typeArray.length; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("x", typeArray[i]);
			item.put("y", (int) (Math.random() * 1000) + 1);
			salesTypeDataOffline.add(item);
		}

		List<Map<String, Object>> offlineData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("name", "门店" + i);
			item.put("cvr", Math.ceil(Math.random() * 9) / 10);
			offlineData.add(item);
		}

		List<Map<String, Object>> offlineChartData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 20; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("x", new Date(beginDay + 1000 * 60 * 60 * 24 * i));
			item.put("y1", Math.floor(Math.random() * 100) + 10);
			item.put("y2", Math.floor(Math.random() * 100) + 10);
			offlineChartData.add(item);
		}

		List<Map<String, Object>> radarOriginData = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<>();
		item1.put("name", "个人");
		item1.put("ref", 10);
		item1.put("koubei", 8);
		item1.put("output", 4);
		item1.put("contribute", 5);
		item1.put("hot", 7);
		radarOriginData.add(item1);
		Map<String, Object> item2 = new HashMap<>();
		item2.put("name", "团队");
		item2.put("ref", 3);
		item2.put("koubei", 9);
		item2.put("output", 6);
		item2.put("contribute", 3);
		item2.put("hot", 1);
		radarOriginData.add(item2);
		Map<String, Object> item3 = new HashMap<>();
		item3.put("name", "部门");
		item3.put("ref", 4);
		item3.put("koubei", 1);
		item3.put("output", 6);
		item3.put("contribute", 5);
		item3.put("hot", 7);
		radarOriginData.add(item3);

		Map<String, Object> radarTitleMap = new HashMap<>();
		radarTitleMap.put("ref", "引用");
		radarTitleMap.put("koubei", "口碑");
		radarTitleMap.put("output", "产量");
		radarTitleMap.put("contribute", "产量");
		radarTitleMap.put("hot", "热度");

		List<Map<String, Object>> radarData = new ArrayList<>();
		radarOriginData.forEach(item -> {
			item.keySet().forEach(key -> {
				if (!StringUtils.equalsIgnoreCase(key, "name")) {
					Map<String, Object> temp = new HashMap<>();
					temp.put("name", item.get("name"));
					temp.put("label", radarTitleMap.get(key));
					temp.put("value", item.get(key));
					radarData.add(temp);
				}
			});
		});

		Map<String, Object> retMap = new HashMap<>();
		retMap.put("visitData", visitData);
		retMap.put("visitData2", visitData2);
		retMap.put("salesData", salesData);
		retMap.put("searchData", searchData);
		retMap.put("offlineData", offlineData);
		retMap.put("offlineChartData", offlineChartData);
		retMap.put("salesTypeData", salesTypeData);
		retMap.put("salesTypeDataOnline", salesTypeDataOnline);
		retMap.put("salesTypeDataOffline", salesTypeDataOffline);
		retMap.put("radarData", radarData);
		return retMap;
	}

	@RequestMapping("/chart/visit")
	public Map<String, Object> chartVisit() {
		long beginDay = new Date().getTime();
		List<Map<String, Object>> visitData = new ArrayList<Map<String, Object>>();
		int[] fakeY = new int[] { 7, 5, 4, 2, 4, 7, 5, 6, 5, 9, 6, 3, 1, 5, 3, 6, 5 };
		for (int i = 0; i < fakeY.length; i += 1) {
			Map<String, Object> item = new HashMap<>();
			item.put("x", new SimpleDateFormat("YYYY-MM-DD").format(new Date(beginDay + 1000 * 60 * 60 * 24 * i)));
			item.put("y", fakeY[i]);
			visitData.add(item);
		}
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("visitData", visitData);
		return retMap;
	}

	@RequestMapping("/api/notice")
	public Map<String, Object> menu() {
		return null;
	}

	@RequestMapping("/chart/tags")
	public Map<String, Object> menu2() {
		return null;
	}

	@RequestMapping("/api/activities")
	public Map<String, Object> menu3() {
		return null;
	}

	@RequestMapping("/api/statistical")
	public Map<String, Object> getStatistical() {
		Map<String, Integer> map = new HashMap<>();
		map.put("WebsiteTraffics", 1);
		map.put("WebsiteImpressions", 2);
		map.put("TotalSales", 3);
		map.put("SupportTickets", 4);
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("statisticalData", map);
		return returnMap;
	}

	@RequestMapping("/api/todolist")
	public Map<String, Object> getTodoList() {

		List<Object> todoList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("completed", true);
		map.put("avatar", '1');
		map.put("name", "苏先生");
		map.put("content", "请告诉我，我应该说点什么好？");
		todoList.add(map);

		map = new HashMap<>();
		map.put("completed", false);
		map.put("avatar", '2');
		map.put("name", "はなさき");
		map.put("content", "ハルカソラトキヘダツヒカリ");
		todoList.add(map);

		map = new HashMap<>();
		map.put("completed", false);
		map.put("avatar", '3');
		map.put("name", "cipchk");
		map.put("content", "this world was never meant for one as beautiful as you.");
		todoList.add(map);

		map = new HashMap<>();
		map.put("completed", true);
		map.put("avatar", '4');
		map.put("name", "Kent");
		map.put("content", "my heart is beating with hers");
		todoList.add(map);

		map = new HashMap<>();
		map.put("completed", false);
		map.put("avatar", '5');
		map.put("name", "Are you");
		map.put("content", "They always said that I love beautiful girl than my friends");
		todoList.add(map);

		map = new HashMap<>();
		map.put("completed", false);
		map.put("avatar", '6');
		map.put("name", "Forever");
		map.put("content", "11111Walking through green fields ，sunshine in my eyes.");
		todoList.add(map);

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("todoList", todoList);
		return returnMap;
	}

	@RequestMapping("/pois")
	public Map<String, Object> getPois() {

		List<Object> todoList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("id", 10000);
		map.put("user_id", 1);
		map.put("name", "测试品牌");
		map.put("branch_name", "测试分店");
		map.put("geo", 310105);
		map.put("country", "中国");
		map.put("province", "上海");
		map.put("city", "上海市");
		map.put("district", "长宁区");
		map.put("address", "中山公园");
		map.put("tel", "15900000000");
		map.put("categories", "美食);粤菜);湛江菜");
		map.put("lng", 121.41707989151003);
		map.put("lat", 31.218656214644792);
		map.put("recommend", "推荐品");
		map.put("special", "特色服务");
		map.put("introduction", "商户简介");
		map.put("open_time", "营业时间");
		map.put("avg_price", 260);
		map.put("reason", null);
		map.put("status", 1);
		map.put("status_str", "待审核");
		map.put("status_wx", 1);
		map.put("modified", "1505826527288");
		map.put("created", "1505826527288");
		todoList.add(map);
		map = new HashMap<>();
		map.put("id", 10001);
		map.put("user_id", 2);
		map.put("name", "测试品牌2");
		map.put("branch_name", "测试分店2");
		map.put("geo", 310105);
		map.put("country", "中国");
		map.put("province", "上海");
		map.put("city", "上海市");
		map.put("district", "长宁区");
		map.put("address", "中山公园");
		map.put("tel", "15900000000");
		map.put("categories", "美食);粤菜);湛江菜");
		map.put("lng", 121.41707989151003);
		map.put("lat", 31.218656214644792);
		map.put("recommend", "推荐品");
		map.put("special", "特色服务");
		map.put("introduction", "商户简介");
		map.put("open_time", "营业时间");
		map.put("avg_price", 260);
		map.put("reason", null);
		map.put("status", 1);
		map.put("status_str", "待审核");
		map.put("status_wx", 1);
		map.put("modified", "1505826527288");
		map.put("created", "1505826527288");
		todoList.add(map);
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", todoList);
		returnMap.put("total", todoList.size());
		return returnMap;
	}
}
