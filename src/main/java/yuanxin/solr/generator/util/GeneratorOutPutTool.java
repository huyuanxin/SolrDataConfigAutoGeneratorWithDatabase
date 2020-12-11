package yuanxin.solr.generator.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author huyuanxin
 * @create 2020/12/11 9:03
 */
public class GeneratorOutPutTool {
    public static FileWriter newWriter()throws FileNotFoundException{
        File file = new File("data-config.xml");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("文件生成成功");
                }
                System.out.println("文件生成失败");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            // 清空文件内的内容
            writer.flush();
            return writer;
        } catch (IOException e) {
            e.printStackTrace();
        }
       throw new FileNotFoundException("文件创建失败");
    }

    public static TemplateEngine newTemplateEngine() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".xml");
        //创建模板引擎
        TemplateEngine templateEngine = new TemplateEngine();
        //将加载器放入模板引擎
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }
}
