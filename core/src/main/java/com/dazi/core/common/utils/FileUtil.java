package com.dazi.core.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

/**
 * 文件工具类
 * @author kuanghaibo@gmail.com
 * @createDate 2015/03/12 20:00
 *
 */
public class FileUtil {

    /**
     * 文件存放的顶层父目录
     */
    public static final String ROOT_SRC = "http://127.0.0.1:8080/"; // 读取配置文件

    /**
     *  文件上传大小限制
     */
    public static long MAX_FILE_SIZE;
    
    /**
     * 支持的文件类型：jpeg,jpg,png,bmp,svg,gif,icon
     */
    public enum include {
        JPEG, JPG, PNG, BMP, SVG, GIF,ICON;
    }

    /**
     * 根据默认【上级父级文件路径/当前时间（yyyymmdd)/模块名称】获取当前文件上传的路径
     * @param modelName 模块名称
     * @return String (e.g : D:/temp/game/20150312)
     */
    @Deprecated
    public static String getCurrentDir(final String modelName) {
        return getCurrentDir(modelName, new Date());
    }

    /**
     * 根据默认上级父级文件路径+时间+模块名称获取当前文件上传的路径
     * @param date java.util.Date
     * @param modelName 模块名称
     * @return
     */
    @Deprecated
    public static String getCurrentDir(final String modelName, final Date date) {
        return getCurrentDir(modelName, date, ROOT_SRC);
    }

    /**
     * 根据时间和模块名称获取当前文件上传的路径
     * @param date java.util.Date
     * @param modelName 模块名称
     * @param rootDir 上层父文件目录 e.g ： 'D:/temp'
     * @return
     */
    public static String getCurrentDir(final String modelName, final Date date, String rootDir) {
        return getCurrentDir(modelName, DateUtils.formatDate(date, DateUtils.PATTERN_YYYY_MM_DD), rootDir);
    }


    /**
     * 根据 时间 和 模块名称 和文件扩展名 获取当前文件上传文件的的全路径
     * @param modelName 模块名称
     * @param str_yyyymmdd 时间格式字符串 (e.g：20150312)
     * @param rootDir 上层父文件目录 e.g ： 'D:/temp/'
     * @return d:/temp/game/20150312/1214141242.jpg
     */
    public static String getCurrentDir(final String modelName, final String str_yyyymmdd, String rootDir) {
        StringBuffer sbPath = new StringBuffer(rootDir);
        sbPath.append(modelName);
        sbPath.append("/");
        sbPath.append(str_yyyymmdd);
        sbPath.append("/");
        String path = sbPath.toString();

        File file = new File(path);
        if(!file.exists()){ //创建目录，成功返回true，会创建所有不存在的父目录
            file.mkdirs();
        }
        return path;
    }

    public static String getCurrentDir(final String beforePath, final String afterPath ) {

        File file = new File(beforePath+afterPath);
        if(!file.exists()){ //创建目录，成功返回true，会创建所有不存在的父目录
            file.mkdirs();
        }

        return beforePath+afterPath;
    }

    /**
     * 根据 模块名称 和 当前时间 和 文件扩展类型 获取文件相对路径
     * @param modelName 模块名称  ( e.g ： 'game')
     * @return /uploads/game/20150409/20150409153616408
     */
    //public static String getAfterFilePath(final String modelName) {
    //
    //    return getAfterFilePath(modelName, DateUtil.formatDate(new Date(), DateUtil.PATTERN_YYYY_MM_DD));
    //}

    /**
     * 根据 模块名称 和 时间年月日 和 文件扩展类型 获取文件相对路径
     * @param modelName 模块名称  ( e.g ： 'game')
     * @param date 时间
     * @return /uploads/game/20150409/20150409153616408
     */
    public static String getAfterFilePath(final String modelName, final Date date) {

        return getAfterFilePath(modelName, DateUtils.formatDate(date, DateUtils.PATTERN_YYYY_MM_DD));
    }

    /**
     * 根据 模块名称 和 时间年月日 和 文件扩展类型 获取文件相对路径
     * @param modelName 模块名称  ( e.g ： 'game')
     * @param str_yyyymmdd 时间  (e.g：20150312)
     * @return /uploads/game/20150409/20150409153616408
     */
    public static String getAfterFilePath(final String modelName,final String str_yyyymmdd){

        return "/uploads" + getModelPath(modelName, str_yyyymmdd);
    }

    public static String getModelPath(String modelName) {

        return getModelPath(modelName, DateUtils.formatDate(new Date(), DateUtils.PATTERN_YYYY_MM_DD));
    }

    public static String getModelPath(String modelName, String date) {
        StringBuilder buff = new StringBuilder("/");
        buff.append(modelName)
                .append("/").append(date)
                .append("/");
        return buff.toString();
    }

    /**
     * 根据文件名称获取文件扩展名
     * @param filename 文件名称
     * @return String（e.g :   jpg | png )
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 判断文件后缀名，是否是支持的枚举类型</p>
     * 如果是支持的文件类型则返回true,否则返回false
     * @param suffix 文件后缀名称
     * @return
     */
    public static boolean filterSuffix(String suffix) {
        try {
            include.valueOf(suffix.toUpperCase());
        } catch (Exception e) {
            return false;
        }
        return  true;
    }

    /**
     * 检查上传文件大小限制</p>
     * 超过限制大小，返回false，否则返回 true
     * @param fileSize 文件大小[byte]
     * @return ture | false
     */
    public static boolean checkFileSize(long fileSize) {

        if(fileSize > 0) {
            return fileSize > MAX_FILE_SIZE;
        }
        return true;
    }
    
    /**
     * 检查上传文件大小限制</p>
     * 超过限制大小，返回false，否则返回 true
     * @param fileSize 文件大小[byte]
     * @param maxFileSize 限制的大小[byte]
     * @return ture | false
     */
    public static boolean checkFileSize(long fileSize, long maxFileSize) {

        if(fileSize > 0) {
            return fileSize > maxFileSize;
        }
        return true;
    }

    /**
     * 获取随机文件，规则：当前时间戳 + rand(6) + "." + extendName
     * @param extendName 文件后缀
     * @return
     */
    public static String getNewFilename(String extendName) {
        String rand = RandomStringUtils.randomNumeric(6);
        return DateUtils.getCurrentDateStr() + rand + "." + extendName;
    }

    /**
     * 获取上传目录的绝对路径
     * @return
     */
    public static String getDefaultUploadPath() {
        return WebUtils.getRequest().getServletContext().getRealPath("/uploads");
    }

    /**
     * 上传文件 by fengweixiong
     * @param file 待上传的文件对象
     * @param dir 上传根目录，dir为空则使用项目根路径
     * @param modelName 模块名称
     * @return http://127.0.0.1:8080/clam/uploads/game/20150409/20150409153616408.jpg
     */
    public static String fileUpload(MultipartFile file, String dir, String modelName) throws IOException {

       return fileUpload(file, dir, modelName, null);

    }

    /**
     * 上传文件 by fengweixiong
     * @param file 待上传的文件对象
     * @param dir 上传根目录，dir为空则使用项目根路径
     * @param modelName 模块名称
     * @param filename 指定文件名称
     * @return 文件相对路径
     */
    public static String fileUpload(MultipartFile file, String dir, String modelName, String filename) throws IOException {

        if (StringUtils.isEmpty(dir)) {
            dir = getDefaultUploadPath(); // 默认项目上传绝对路径
        }

        String childPath = null;
        String newFileName = null;

        if (StringUtils.isNotEmpty(filename)) {
            childPath = "/" + modelName + "/";
            newFileName = filename;
        } else {
            childPath = FileUtil.getModelPath(modelName); //文件相对存放路径

            String extendName = FileUtil.getExtensionName(file.getOriginalFilename());
            newFileName = FileUtil.getNewFilename(extendName);
        }

        File targetFile = Paths.get(dir, childPath, newFileName).toFile();

        // 如果目录不存在就创建
        targetFile.getParentFile().mkdirs();


        file.transferTo(targetFile);
        return childPath + newFileName;
    }

    /**
     **
     * 获取当前系统换行
     *
     * @return 系统换行
     */
    public static String getSystemLineSeparator() {
        return System.getProperty("line.separator");
    }

    public static void main(String[] args) {

//			System.out.println(File.separator);
    }

}
