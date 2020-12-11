package yuanxin.solr.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import yuanxin.solr.generator.api.DataSourceService;
import yuanxin.solr.generator.api.EntityService;
import yuanxin.solr.generator.api.FieldService;
import yuanxin.solr.generator.util.GeneratorOutPutTool;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:46
 */
@RestController
public class TestController {
    final FieldService fieldService;
    final EntityService entityService;
    final DataSourceService dataSourceService;

    @Autowired
    public TestController(FieldService fieldService, EntityService entityService, DataSourceService dataSourceService) {
        this.fieldService = fieldService;
        this.entityService = entityService;
        this.dataSourceService = dataSourceService;
    }
    @RequestMapping(value = "/generator", method = RequestMethod.GET)
    public String testSql() throws IOException {
        //创建Context对象(存放Model)
        Context context = new Context();
        //放入数据
        FileWriter writer= GeneratorOutPutTool.newWriter();
        TemplateEngine templateEngine= GeneratorOutPutTool.newTemplateEngine();
        context.setVariable("dataSourceList", dataSourceService.generatorDataSourceList());
        context.setVariable("entityList",entityService.generatorEntityList());
        // 暂时断言!null
        assert writer != null;
        templateEngine.process("data-config",context,writer);
        writer.close();
        return "成功";
    }
}
