package com.jarvisframework.tool.core.convert.impl;

import cn.hutool.core.util.ClassUtil;
import com.jarvisframework.tool.core.convert.AbstractConverter;

/**
 * 类转换器<br>
 * 将类名转换为类
 * @author Looly
 *
 */
public class ClassConverter extends AbstractConverter<Class<?>> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<?> convertInternal(Object value) {
		String valueStr = convertToStr(value);
		try {
			return ClassUtil.getClassLoader().loadClass(valueStr);
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}

}
