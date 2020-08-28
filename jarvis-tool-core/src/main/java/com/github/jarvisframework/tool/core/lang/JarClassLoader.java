package com.github.jarvisframework.tool.core.lang;

import com.github.jarvisframework.tool.core.exception.UtilException;
import com.github.jarvisframework.tool.core.io.FileUtils;
import com.github.jarvisframework.tool.core.util.ClassUtils;
import com.github.jarvisframework.tool.core.util.ReflectUtils;
import com.github.jarvisframework.tool.core.util.UrlUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * <p>外部Jar的类加载器</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-30 11:08:31
 */
public class JarClassLoader extends URLClassLoader {

    /**
     * 加载Jar到ClassPath
     *
     * @param dir jar文件或所在目录
     * @return JarClassLoader
     */
    public static JarClassLoader load(File dir) {
        final JarClassLoader loader = new JarClassLoader();
        // 查找加载所有jar
        loader.addJar(dir);
        // 查找加载所有class
        loader.addURL(dir);
        return loader;
    }

    /**
     * 加载Jar到ClassPath
     *
     * @param jarFile jar文件或所在目录
     * @return JarClassLoader
     */
    public static JarClassLoader loadJar(File jarFile) {
        final JarClassLoader loader = new JarClassLoader();
        loader.addJar(jarFile);
        return loader;
    }

    /**
     * 加载Jar文件到指定loader中
     *
     * @param loader  {@link URLClassLoader}
     * @param jarFile 被加载的jar
     * @throws UtilException IO异常包装和执行异常
     */
    public static void loadJar(URLClassLoader loader, File jarFile) throws UtilException {
        try {
            final Method method = ClassUtils.getDeclaredMethod(URLClassLoader.class, "addURL", URL.class);
            if (null != method) {
                method.setAccessible(true);
                final List<File> jars = loopJar(jarFile);
                for (File jar : jars) {
                    ReflectUtils.invoke(loader, method, new Object[]{jar.toURI().toURL()});
                }
            }
        } catch (IOException e) {
            throw new UtilException(e);
        }
    }

    /**
     * 加载Jar文件到System ClassLoader中
     *
     * @param jarFile 被加载的jar
     * @return System ClassLoader
     */
    public static URLClassLoader loadJarToSystemClassLoader(File jarFile) {
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        loadJar(urlClassLoader, jarFile);
        return urlClassLoader;
    }

    // ------------------------------------------------------------------- Constructor start

    /**
     * 构造
     */
    public JarClassLoader() {
        this(new URL[]{});
    }

    /**
     * 构造
     *
     * @param urls 被加载的URL
     */
    public JarClassLoader(URL[] urls) {
        super(urls, ClassUtils.getClassLoader());
    }
    // ------------------------------------------------------------------- Constructor end

    /**
     * 加载Jar文件，或者加载目录
     *
     * @param jarFileOrDir jar文件或者jar文件所在目录
     * @return this
     */
    public JarClassLoader addJar(File jarFileOrDir) {
        if (isJarFile(jarFileOrDir)) {
            return addURL(jarFileOrDir);
        }
        final List<File> jars = loopJar(jarFileOrDir);
        for (File jar : jars) {
            addURL(jar);
        }
        return this;
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    /**
     * 增加class所在目录或文件<br>
     * 如果为目录，此目录用于搜索class文件，如果为文件，需为jar文件
     *
     * @param dir 目录
     * @return this
     * @since 4.4.2
     */
    public JarClassLoader addURL(File dir) {
        super.addURL(UrlUtils.getURL(dir));
        return this;
    }

    // ------------------------------------------------------------------- Private method start

    /**
     * 递归获得Jar文件
     *
     * @param file jar文件或者包含jar文件的目录
     * @return jar文件列表
     */
    private static List<File> loopJar(File file) {
        return FileUtils.loopFiles(file, JarClassLoader::isJarFile);
    }

    /**
     * 是否为jar文件
     *
     * @param file 文件
     * @return 是否为jar文件
     * @since 4.4.2
     */
    private static boolean isJarFile(File file) {
        if (false == FileUtils.isFile(file)) {
            return false;
        }
        return file.getPath().toLowerCase().endsWith(".jar");
    }
    // ------------------------------------------------------------------- Private method end
}

