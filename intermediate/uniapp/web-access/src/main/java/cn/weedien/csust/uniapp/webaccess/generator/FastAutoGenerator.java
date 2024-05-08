package cn.weedien.csust.uniapp.webaccess.generator;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class FastAutoGenerator {

    public static void main(String[] args) {
        com.baomidou.mybatisplus.generator.FastAutoGenerator.create("jdbc:mysql://remote:3306/acmbak", "weedien", "031209")
                .globalConfig(builder -> {
                    builder.author("weedien") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D:\\Weedien\\JavaCode\\Csust\\intermediate\\uniapp\\web-access\\src\\main\\java\\cn\\weedien\\webaccess"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.weedien") // 设置父包名
                            .moduleName("webaccess"); // 设置父包模块名
                })
                .strategyConfig(builder -> {
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .entityBuilder()
                            .enableLombok()
                            .disableSerialVersionUID()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}