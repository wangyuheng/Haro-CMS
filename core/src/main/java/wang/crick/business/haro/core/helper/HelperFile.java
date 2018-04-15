package wang.crick.business.haro.core.helper;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.StringUtils;
import wang.crick.business.haro.core.base.helper.properties.HelperAppProperties;
import wang.crick.business.haro.core.dictionary.ConstAppPropertiesKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class HelperFile {

    /**
     * 将temp目录下的文件移动到正式目录
     */
    public static boolean moveTempFileToFormal(String fileName) {
        boolean flag = true;
        if (!StringUtils.isEmpty(fileName)) {
            FileInputStream in = null;
            OutputStream out = null;
            File writeDict = new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_WRITE_PATH));
            File readDict = new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_READ_PATH));
            if (!writeDict.exists()) {
                writeDict.mkdir();
            }
            if (!readDict.exists()) {
                readDict.mkdir();
            }
            File srcFile = new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_TEMP_PATH) + fileName);
            File destFile = new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_WRITE_PATH) + fileName);
            try {
                if (srcFile.exists() && srcFile.isFile()) {
                    in = new FileInputStream(srcFile);
                    out = new FileOutputStream(destFile);
                    IOUtils.copy(in, out);
                }
            } catch (Exception e) {
                flag = false;
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
            }
        }
        return flag;
    }

    /**
     * 删除文件
     */
    private static boolean removeFile(File file) {
        boolean flag = false;
        if (file.exists() && file.isFile()) {
            flag = file.delete();
        }
        return flag;
    }

    /**
     * 删除正式目录文件
     */
    public static boolean removeFormalFile(String fileName) {
        boolean flag = true;
        if (!StringUtils.isEmpty(fileName)) {
            try {
                flag = HelperFile.removeFile(new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_WRITE_PATH) + fileName));
            } catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 删除临时目录文件
     */
    public static boolean removeTempFile(String fileName) {
        boolean flag = true;
        if (!StringUtils.isEmpty(fileName)) {
            try {
                flag = HelperFile.removeFile(new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_TEMP_PATH) + fileName));
            } catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 清空临时目录
     */
    public static boolean clearTempDirectory() {
        File dir = new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_TEMP_PATH));
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
        return true;
    }
}
