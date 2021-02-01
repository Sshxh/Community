package com.nowcoder.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.UUID;

//这个工具类是用来当用户设置完密码之后 对密码进行加工的一个工具
//函数md5最终生成对应的md5码

public class CommunityUtil {

	/**
	 * 生成随机字符串
	 *
	 * @return
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * MD5 加密
	 * 加盐值
	 */
	//key 就是原始的密码加上盐
	public static String md5(String key) {
		//如果是空的话则返回空 不处理
		if (StringUtils.isBlank(key)) {
			return null;
		}
		//装成一个16进制的字符返回
		return DigestUtils.md5DigestAsHex(key.getBytes());
	}

	/**
	 * 生成 json 字符串
	 *
	 * @param code
	 * @param msg
	 * @param map
	 * @return
	 */
	public static String getJSONString(int code, String msg, Map<String, Object> map) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		if (map != null) {
			for (String key : map.keySet()) {
				json.put(key, map.get(key));
			}
		}
		return json.toJSONString();
	}

	public static String getJSONString(int code, String msg) {
		return getJSONString(code, msg, null);
	}

	public static String getJSONString(int code) {
		return getJSONString(code, null, null);
	}

}
