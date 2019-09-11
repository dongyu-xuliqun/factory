package com.cdf.factory.service.login;

import java.util.Map;

public interface ILoginService {

	Map<String, Object> login(String nickName, String password);
}
