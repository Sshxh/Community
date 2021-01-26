package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.UUID;

/**
 * @author Ep流苏
 * @Date: 2020/6/14 16:29
 * @Description:
 */
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
	 * @param contextPath
	 * @return 判断contextPath 是否为 / 防止url 多个 / 导致拼接失败
	 */

}
