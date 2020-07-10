package com.jarvisframework.tool.core.convert.impl;

import com.jarvisframework.tool.core.convert.AbstractConverter;
import com.jarvisframework.tool.core.util.BooleanUtils;
import com.jarvisframework.tool.core.util.StringUtils;

/**
 * 字符转换器
 * 
 * @author Looly
 *
 */
public class CharacterConverter extends AbstractConverter<Character> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Character convertInternal(Object value) {
		if (value instanceof Boolean) {
			return BooleanUtils.toCharacter((Boolean) value);
		} else {
			final String valueStr = convertToStr(value);
			if (StringUtils.isNotBlank(valueStr)) {
				return valueStr.charAt(0);
			}
		}
		return null;
	}

}
