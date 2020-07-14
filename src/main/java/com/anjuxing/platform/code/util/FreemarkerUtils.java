package com.anjuxing.platform.code.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * freemarker util
 */
public class FreemarkerUtils {

    private Configuration cfg = null;
    private Template tpl = null;
    private static FreemarkerUtils instance;
    public static FreemarkerUtils getInstance(){
        if (instance == null){
            instance = new FreemarkerUtils();
        }
        return instance;
    }

    public FreemarkerUtils(){
        this.cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        this.cfg.setDefaultEncoding("UTF-8");
        this.cfg.setLocalizedLookup(false);
        this.cfg.setClassForTemplateLoading(this.getClass(), "\\template");
    }

    public void build(Object dataModel, String ftlPath, String fileDir, String fileName){
        FileOutputStream fos = null;
        try {
            tpl = cfg.getTemplate(ftlPath);
            File dir = new File(fileDir);
            if (!dir.exists()){
                dir.mkdirs();
            }
            File file = new File(fileDir + "/" + fileName);
            if (!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            tpl.process(dataModel, new OutputStreamWriter(fos,"utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try { fos.flush(); } catch (IOException e) {}
                try { fos.close(); } catch (IOException e) {}
            }
        }
    }

}
