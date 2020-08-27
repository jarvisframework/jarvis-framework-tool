package com.github.jarvisframework.tool.core.convert.impl;

import com.github.jarvisframework.tool.core.convert.AbstractConverter;
import com.github.jarvisframework.tool.core.map.MapUtils;
import com.github.jarvisframework.tool.core.util.ObjectUtils;

import java.util.Map;

/**
 * {@link StackTraceElement} 转换器<br>
 * 只支持Map方式转换
 * 
 * @author Looly
 * @since 3.0.8
 */
public class StackTraceElementConverter extends AbstractConverter<StackTraceElement> {
	private static final long serialVersionUID = 1L;

	@Override
	protected StackTraceElement convertInternal(Object value) {
		if (value instanceof Map) {
			final Map<?, ?> map = (Map<?, ?>) value;

			final String declaringClass = MapUtils.getStr(map, "className");
			final String methodName = MapUtils.getStr(map, "methodName");
			final String fileName = MapUtils.getStr(map, "fileName");
			final Integer lineNumber = MapUtils.getInt(map, "lineNumber");

			return new StackTraceElement(declaringClass, methodName, fileName, ObjectUtils.defaultIfNull(lineNumber, 0));
		}
		return null;
	}

}
