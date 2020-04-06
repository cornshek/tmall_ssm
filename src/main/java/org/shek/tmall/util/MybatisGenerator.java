package org.shek.tmall.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisGenerator {
    public static void main(String[] args) throws Exception {
        String today = "2020-4-2";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd");
        Date now = simpleDateFormat.parse(today);
        Date date = new Date();

        if (date.getTime() > now.getTime() + 1000 * 60 * 60 * 24) {
            System.out.println("----未成功运行----");
            System.out.println("本程序会覆盖代码，应只运行一次，若必需运行" +
                    "需修改变量today为今天，如：" +
                    simpleDateFormat.format(new Date()));
            return;
        }

        if (false) {
            return;
        }

        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        InputStream inputStream = MybatisGenerator.class.getClassLoader().
                getResource("generatorConfig.xml").openStream();
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration configuration = configurationParser.parseConfiguration(inputStream);
        inputStream.close();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
        myBatisGenerator.generate(null);

        System.out.println("生成代码成功，只能执行一次\n" +
                "以后执行将覆盖mapper,pojo,xml等文件");
    }
}
