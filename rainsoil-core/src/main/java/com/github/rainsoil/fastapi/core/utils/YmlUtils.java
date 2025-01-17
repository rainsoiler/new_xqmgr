package com.github.rainsoil.fastapi.core.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * yml工具类
 *
 * @author luyanan
 * @since 2023/04/08
 */
@Slf4j
public class YmlUtils {

	private static String bootstrap_file = "classpath:application-dev.yml";

	private static Map<String, String> result = new HashMap<>();

	/**
	 * 根据文件名获取yml的文件内容
	 *
	 * @param filePath getYmlByFileName(bootstrap_file,"spring", "name");
	 * @return
	 */
	public static Map<String, String> getYmlByFileName(String filePath) {
		result = new HashMap<>();
		if (filePath == null) {
			filePath = bootstrap_file;
		}
		InputStream in = null;
		try {
			File file = ResourceUtils.getFile(filePath);
			in = new BufferedInputStream(new FileInputStream(file));
			Yaml props = new Yaml();
			Object obj = props.loadAs(in, Map.class);
			Map<String, Object> param = (Map<String, Object>) obj;

			for (Map.Entry<String, Object> entry : param.entrySet()) {
				String key = entry.getKey();
				Object val = entry.getValue();
//				if (keys.length != 0 && keys[0].equals(key)) {
////				if (keys.length == 0) {
//					continue;
//				}
				if (val instanceof Map) {
					forEachYaml(key, (Map<String, Object>) val, 1);
				} else {
					result.put(key, val.toString());
				}
			}
			return result;
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	/**
	 * 根据key获取值
	 *
	 * @param key
	 * @return
	 */
	public static String getValue(String key) throws FileNotFoundException {
		Map<String, String> map = getYmlByFileName(null);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	/**
	 * 遍历yml文件，获取map集合
	 *
	 * @param key_str
	 * @param obj
	 * @param i
	 * @return
	 */
	public static Map<String, String> forEachYaml(String key_str, Map<String, Object> obj, int i) {
		for (Map.Entry<String, Object> entry : obj.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
//			if (keys.length > i && !keys[i].equals(key)) {
//				continue;
//			}
			String str_new = "";
			if (StringUtils.isNotEmpty(key_str)) {
				str_new = key_str + "." + key;
			} else {
				str_new = key;
			}
			if (val instanceof Map) {
				forEachYaml(str_new, (Map<String, Object>) val, ++i);
				i--;
			} else {

				result.put(str_new, val.toString());
			}
		}

		return result;
	}

	/**
	 * 获取bootstrap.yml的name
	 *
	 * @return
	 */
	public static String getApplicationName() throws FileNotFoundException {
		return getYmlByFileName(bootstrap_file).get("server.port");
	}

	/**
	 * 获取bootstrap.yml的name
	 *
	 * @return
	 */
	public static String getApplicationName1() throws FileNotFoundException {
		String name = getYmlByFileName(bootstrap_file).get("spring.application.name");
		return name + "center";
	}


}
