package wang.crick.business.haro.admin.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import wang.crick.business.haro.core.base.helper.guid.HelperGuid;
import wang.crick.business.haro.core.base.helper.json.HelperGson;
import wang.crick.business.haro.core.base.helper.properties.HelperAppProperties;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.dictionary.ConstAppPropertiesKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileController extends BaseController {

    @RequestMapping(value="/imageUpload")
    public @ResponseBody
    String imageUpload(MultipartHttpServletRequest request){
        MultipartFile file = request.getFile("file");
        if (!file.isEmpty()) {
            String fileName = null;
            String fileType = null;
            String id = null;
            try {
                id = HelperGuid.getRandomStr();
                fileType=file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1, file.getOriginalFilename().length());
                fileName = id + "." + fileType;
                File destFile = new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_TEMP_PATH) + fileName);
                boolean flag = true;
                if (!destFile.exists()) {
                    flag = destFile.mkdirs();
                }
                if (flag) {
                    file.transferTo(destFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("id", id);
            result.put("fileType", fileType);
            return HelperGson.toJson(result);
        }
        return ERROR;
    }

    /**
     * 显示图片
     *      应该使用NFS等静态服务器访问图片资源
     *      次方法的意义在于上传图片 或 在临时路径存储图片时，保证图片被正确显示
     *
     */
    @RequestMapping(value="/show")
    public void show(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        String id = request.getParameter("id");
        String fileType = request.getParameter("fileType");
        if (!StringUtils.isEmpty(fileName) && fileName.contains(".")) {
            String[] temp = fileName.split("\\.");
            id = temp[0];
            fileType = temp[1];
        }
        if (!StringUtils.isEmpty(id)) {
            File file = new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_TEMP_PATH) + id +"."+fileType);;
            FileInputStream in = null;
            OutputStream out = null;
            try {
                if (file.exists() && file.isFile()) {
                    in = new FileInputStream(file);
                    out = response.getOutputStream();
                    IOUtils.copy(in, out);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
            }
        }
    }

    @RequestMapping(value="/editor/upload", method = RequestMethod.POST)
    @ResponseBody
    public String ueEditorUpFile(@RequestParam("upfile") MultipartFile imageFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if (imageFile.getSize() > 4 * 1024 * 1024) {
            result.put("state", "图片不能大于4M!");
        } else {
            String fileName = imageFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String dir = HelperAppProperties.getString(ConstAppPropertiesKey.FILE_TEMP_PATH);
            String picTitle=request.getParameter("pictitle");
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                InputStream in = imageFile.getInputStream();
                String url = HelperGuid.getRandomStr() + suffix;
                OutputStream out = new FileOutputStream(dir + url);
                IOUtils.copy(in, out);
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
                result.put("original", imageFile.getOriginalFilename());
                result.put("title", picTitle);
                result.put("url", url);
                result.put("state", "SUCCESS");
            } catch (Exception e) {
                result.put("message", "上传图片出错!");
            }
        }
        return HelperGson.toJson(result);
    }
}

