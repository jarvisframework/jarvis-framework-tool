package com.jarvisframework.tool.core.io;

import com.jarvisframework.tool.core.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件类型判断工具类
 *
 * <p>此工具根据文件的前几位bytes猜测文件类型，对于文本、zip判断不准确，对于视频、图片类型判断准确</p>
 * <p>需要注意的是，xlsx、docx等Office2007格式，全部识别为zip，因为新版采用了OpenXML格式，这些格式本质上是XML文件打包为zip</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-13 13:29:01
 */
public class FileTypeUtils {

    private static final Map<String, String> fileTypeMap;

    static {
        fileTypeMap = new ConcurrentHashMap<>();

        // JPEG (jpg)
        fileTypeMap.put("ffd8ff", "jpg");
        // PNG (png)
        fileTypeMap.put("89504e47", "png");
        // GIF (gif)
        fileTypeMap.put("4749463837", "gif");
        // GIF (gif)
        fileTypeMap.put("4749463839", "gif");
        // TIFF (tif)
        fileTypeMap.put("49492a00227105008037", "tif");
        // 16色位图(bmp)
        fileTypeMap.put("424d228c010000000000", "bmp");
        // 24位位图(bmp)
        fileTypeMap.put("424d8240090000000000", "bmp");
        // 256色位图(bmp)
        fileTypeMap.put("424d8e1b030000000000", "bmp");
        // CAD (dwg)
        fileTypeMap.put("41433130313500000000", "dwg");
        // Rich Text Format (rtf)
        fileTypeMap.put("7b5c727466315c616e73", "rtf");
        // Photoshop (psd)
        fileTypeMap.put("38425053000100000000", "psd");
        // Email [Outlook Express 6] (eml)
        fileTypeMap.put("46726f6d3a203d3f6762", "eml");
        // MS Access (mdb)
        fileTypeMap.put("5374616E64617264204A", "mdb");
        fileTypeMap.put("252150532D41646F6265", "ps");
        // Adobe Acrobat (pdf)
        fileTypeMap.put("255044462d312e", "pdf");
        // rmvb/rm相同
        fileTypeMap.put("2e524d46000000120001", "rmvb");
        // flv与f4v相同
        fileTypeMap.put("464c5601050000000900", "flv");
        fileTypeMap.put("00000020667479706", "mp4");
        fileTypeMap.put("00000018667479706D70", "mp4");
        fileTypeMap.put("49443303000000002176", "mp3");
        fileTypeMap.put("000001ba210001000180", "mpg");
        // wmv与asf相同
        fileTypeMap.put("3026b2758e66cf11a6d9", "wmv");
        // Wave (wav)
        fileTypeMap.put("52494646e27807005741", "wav");
        fileTypeMap.put("52494646d07d60074156", "avi");
        // MIDI (mid)
        fileTypeMap.put("4d546864000000060001", "mid");
        // WinRAR
        fileTypeMap.put("526172211a0700cf9073", "rar");
        fileTypeMap.put("235468697320636f6e66", "ini");
        fileTypeMap.put("504B03040a0000000000", "jar");
        fileTypeMap.put("504B0304140008000800", "jar");
        // MS Excel 注意：word、msi 和 excel的文件头一样
        fileTypeMap.put("d0cf11e0a1b11ae10", "xls");
        fileTypeMap.put("504B0304", "zip");
        // 可执行文件
        fileTypeMap.put("4d5a9000030000000400", "exe");
        // jsp文件
        fileTypeMap.put("3c25402070616765206c", "jsp");
        // MF文件
        fileTypeMap.put("4d616e69666573742d56", "mf");
        // java文件
        fileTypeMap.put("7061636b616765207765", "java");
        // bat文件
        fileTypeMap.put("406563686f206f66660d", "bat");
        // gz文件
        fileTypeMap.put("1f8b0800000000000000", "gz");
        // bat文件
        fileTypeMap.put("cafebabe0000002e0041", "class");
        // bat文件
        fileTypeMap.put("49545346030000006000", "chm");
        // bat文件
        fileTypeMap.put("04000000010000001300", "mxp");
        fileTypeMap.put("6431303a637265617465", "torrent");
        // Quicktime (mov)
        fileTypeMap.put("6D6F6F76", "mov");
        // WordPerfect (wpd)
        fileTypeMap.put("FF575043", "wpd");
        // Outlook Express (dbx)
        fileTypeMap.put("CFAD12FEC5FD746F", "dbx");
        // Outlook (pst)
        fileTypeMap.put("2142444E", "pst");
        // Quicken (qdf)
        fileTypeMap.put("AC9EBD8F", "qdf");
        // Windows Password (pwl)
        fileTypeMap.put("E3828596", "pwl");
        // Real Audio (ram)
        fileTypeMap.put("2E7261FD", "ram");
    }

    /**
     * 增加文件类型映射<br>
     * 如果已经存在将覆盖之前的映射
     *
     * @param fileStreamHexHead 文件流头部Hex信息
     * @param extName           文件扩展名
     * @return 之前已经存在的文件扩展名
     */
    public static String putFileType(String fileStreamHexHead, String extName) {
        return fileTypeMap.put(fileStreamHexHead.toLowerCase(), extName);
    }

    /**
     * 移除文件类型映射
     *
     * @param fileStreamHexHead 文件流头部Hex信息
     * @return 移除的文件扩展名
     */
    public static String removeFileType(String fileStreamHexHead) {
        return fileTypeMap.remove(fileStreamHexHead.toLowerCase());
    }

    /**
     * 根据文件流的头部信息获得文件类型
     *
     * @param fileStreamHexHead 文件流头部16进制字符串
     * @return 文件类型，未找到为<code>null</code>
     */
    public static String getType(String fileStreamHexHead) {
        for (Map.Entry<String, String> fileTypeEntry : fileTypeMap.entrySet()) {
            if (StringUtils.startWithIgnoreCase(fileStreamHexHead, fileTypeEntry.getKey())) {
                return fileTypeEntry.getValue();
            }
        }
        return null;
    }

    /**
     * 根据文件流的头部信息获得文件类型
     *
     * @param in {@link InputStream}
     * @return 类型，文件的扩展名，未找到为<code>null</code>
     * @throws IORuntimeException 读取流引起的异常
     */
    public static String getType(InputStream in) throws IORuntimeException {
        return getType(IoUtils.readHex28Upper(in));
    }

    /**
     * 根据文件流的头部信息获得文件类型
     *
     * <pre>
     *     1、无法识别类型默认按照扩展名识别
     *     2、xls、doc、msi头信息无法区分，按照扩展名区分
     *     3、zip可能为docx、xlsx、pptx、jar、war头信息无法区分，按照扩展名区分
     * </pre>
     *
     * @param file 文件 {@link File}
     * @return 类型，文件的扩展名，未找到为<code>null</code>
     * @throws IORuntimeException 读取文件引起的异常
     */
    public static String getType(File file) throws IORuntimeException {
        String typeName;
        FileInputStream in = null;
        try {
            in = IoUtils.toStream(file);
            typeName = getType(in);
        } finally {
            IoUtils.close(in);
        }

        if (null == typeName) {
            // 未成功识别类型，扩展名辅助识别
            typeName = FileUtils.extName(file);
        } else if ("xls".equals(typeName)) {
            // xls、doc、msi的头一样，使用扩展名辅助判断
            final String extName = FileUtils.extName(file);
            if ("doc".equalsIgnoreCase(extName)) {
                typeName = "doc";
            } else if ("msi".equalsIgnoreCase(extName)) {
                typeName = "msi";
            }
        } else if ("zip".equals(typeName)) {
            // zip可能为docx、xlsx、pptx、jar、war等格式，扩展名辅助判断
            final String extName = FileUtils.extName(file);
            if ("docx".equalsIgnoreCase(extName)) {
                typeName = "docx";
            } else if ("xlsx".equalsIgnoreCase(extName)) {
                typeName = "xlsx";
            } else if ("pptx".equalsIgnoreCase(extName)) {
                typeName = "pptx";
            } else if ("jar".equalsIgnoreCase(extName)) {
                typeName = "jar";
            } else if ("war".equalsIgnoreCase(extName)) {
                typeName = "war";
            }
        }
        return typeName;
    }

    /**
     * 通过路径获得文件类型
     *
     * @param path 路径，绝对路径或相对ClassPath的路径
     * @return 类型
     * @throws IORuntimeException 读取文件引起的异常
     */
    public static String getTypeByPath(String path) throws IORuntimeException {
        return getType(FileUtils.file(path));
    }
}
