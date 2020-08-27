package com.github.jarvisframework.tool.core.convert.impl;

import com.github.jarvisframework.tool.core.convert.AbstractConverter;
import com.github.jarvisframework.tool.core.util.BooleanUtils;

/**
 * 波尔转换器
 * @author Looly
 *
 */
public class BooleanConverter extends AbstractConverter<Boolean> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Boolean convertInternal(Object value) {
		//Object不可能出现Primitive类型，故忽略
		return BooleanUtils.toBoolean(convertToStr(value));
	}

}
