package wang.crick.business.haro.view.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import wang.crick.business.haro.core.base.helper.properties.HelperAppProperties;
import wang.crick.business.haro.core.dictionary.ConstAppPropertiesKey;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;


@Controller
public class FileController {
    @RequestMapping(value = "/image/{id}/{fileType}")
    public void show(@PathVariable String id, @PathVariable String fileType, HttpServletResponse response) {
        if (!StringUtils.isEmpty(id)) {
            File file = new File(HelperAppProperties.getString(ConstAppPropertiesKey.FILE_READ_PATH) + id + "." + fileType);
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
}
