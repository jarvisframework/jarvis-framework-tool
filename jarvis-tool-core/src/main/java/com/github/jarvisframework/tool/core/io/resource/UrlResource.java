package com.github.jarvisframework.tool.core.io.resource;

import com.github.jarvisframework.tool.core.io.FileUtils;
import com.github.jarvisframework.tool.core.io.IORuntimeException;
import com.github.jarvisframework.tool.core.io.IOUtils;
import com.github.jarvisframework.tool.core.util.ObjectUtils;
import com.github.jarvisframework.tool.core.util.UrlUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * <p>URL资源访问类</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-29 18:00:18
 */
public class UrlResource implements Resource, Serializable {
    private static final long serialVersionUID = 1L;

    protected URL url;
    protected String name;

    //-------------------------------------------------------------------------------------- Constructor start
    /**
     * 构造
     * @param url URL
     */
    public UrlResource(URL url) {
        this(url, null);
    }

    /**
     * 构造
     * @param url URL，允许为空
     * @param name 资源名称
     */
    public UrlResource(URL url, String name) {
        this.url = url;
        this.name = ObjectUtils.defaultIfNull(name, (null != url) ? FileUtils.getName(url.getPath()) : null);
    }

    /**
     * 构造
     * @param file 文件路径
     * @deprecated Please use {@link FileResource}
     */
    @Deprecated
    public UrlResource(File file) {
        this.url = UrlUtils.getURL(file);
    }
    //-------------------------------------------------------------------------------------- Constructor end

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public URL getUrl(){
        return this.url;
    }

    @Override
    public InputStream getStream() throws NoResourceException{
        if(null == this.url){
            throw new NoResourceException("Resource URL is null!");
        }
        return UrlUtils.getStream(url);
    }

    /**
     * 获得Reader
     * @param charset 编码
     * @return {@link BufferedReader}
     * @since 3.0.1
     */
    @Override
    public BufferedReader getReader(Charset charset){
        return UrlUtils.getReader(this.url, charset);
    }

    //------------------------------------------------------------------------------- read
    @Override
    public String readStr(Charset charset) throws IORuntimeException {
        BufferedReader reader = null;
        try {
            reader = getReader(charset);
            return IOUtils.read(reader);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    @Override
    public byte[] readBytes() throws IORuntimeException {
        InputStream in = null;
        try {
            in = getStream();
            return IOUtils.readBytes(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 获得File
     * @return {@link File}
     */
    public File getFile(){
        return FileUtils.file(this.url);
    }

    /**
     * 返回路径
     * @return 返回URL路径
     */
    @Override
    public String toString() {
        return (null == this.url) ? "null" : this.url.toString();
    }
}
