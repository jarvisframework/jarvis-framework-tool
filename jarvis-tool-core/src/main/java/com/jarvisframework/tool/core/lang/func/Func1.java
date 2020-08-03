package com.jarvisframework.tool.core.lang.func;

/**
 * <p>description</p>
 *
 * @param <P> 参数类型
 * @param <R> 返回值类型
 * @author 王涛
 * @since 1.0, 2020-07-30 09:34:35
 */
@FunctionalInterface
public interface Func1<P, R> {

    /**
     * 执行函数
     *
     * @param parameter 参数
     * @return 函数执行结果
     * @throws Exception 自定义异常
     */
    R call(P parameter) throws Exception;

    /**
     * 执行函数，异常包装为RuntimeException
     *
     * @param parameter 参数
     * @return 函数执行结果
     * @since 5.3.6
     */
    default R callWithRuntimeException(P parameter) {
        try {
            return call(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
