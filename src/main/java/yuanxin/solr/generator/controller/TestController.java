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
import yuanxin.solr.generator.entity.IgnoreColumn;
import yuanxin.solr.generator.entity.IgnoreTable;
import yuanxin.solr.generator.util.GeneratorOutPutTool;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        try {
            FileWriter writer = GeneratorOutPutTool.newWriter();
            TemplateEngine templateEngine = GeneratorOutPutTool.newTemplateEngine();
            context.setVariable("dataSourceList", dataSourceService.generatorDataSourceList());
            // 测试数据定义开始
            // 定义不需要的数据库,测试数据为不生成"chenrui"表
            List<String> ignoreDataBaseNameList = new ArrayList<>();
            ignoreDataBaseNameList.add("chenrui");

            // 定义不需要的表名,测试数据为不生成"spring"数据库下的"tablecounter"表
            List<IgnoreTable> ignoreTableList = new ArrayList<>();
            List<String> tableList = new ArrayList<>();
            tableList.add("tablecounter");
            IgnoreTable table = new IgnoreTable("spring", tableList);
            ignoreTableList.add(table);

            // 定义不需要的列名,测试数据为不生成"demo"数据库中"user"表的"UserName"
            List<IgnoreColumn> ignoreColumnList = new ArrayList<>();
            List<String> columnList = new ArrayList<>();
            columnList.add("UserName");
            IgnoreColumn column = new IgnoreColumn("demo", "user", columnList);
            ignoreColumnList.add(column);
            // 测试数据定义结束
            context.setVariable("entityList", entityService.generatorEntityList(ignoreDataBaseNameList, ignoreTableList, ignoreColumnList));
            templateEngine.process("data-config", context, writer);
            writer.close();
            return "生成成功";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "生成失败";
        }
    }
}
