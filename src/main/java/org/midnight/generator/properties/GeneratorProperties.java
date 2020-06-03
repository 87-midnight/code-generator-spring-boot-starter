package org.midnight.generator.properties;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.Assert;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linchuangang
 * @create 2019-10-25 11:22
 **/
@ConfigurationProperties(prefix = "midnight.generator")
@Data
public class GeneratorProperties implements InitializingBean {
    /**是否开启代码生成器**/
    private boolean enabled;

    /**ORM框架选择激活类型，可选值为mybatis,jpa。默认为mybatis**/
    private String active = "mybatis";

    private String output;

    private List<EntityProperties> entities;

    private DatabaseProperties datasource;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.enabled){
            if (entities == null){
                this.entities = new ArrayList<>();
            }
            Assert.notNull(datasource,"数据库连接信息不能为空");
            Assert.notNull(datasource.getDatabaseType(),"数据库类型不能为空");
            Assert.notNull(datasource.getDriverClassName(),"数据库驱动类型不能为空");
            Assert.notNull(datasource.getUrl(),"数据库连接地址不能为空");
            Assert.notNull(datasource.getUsername(),"用户名不能为空");
            Assert.notNull(datasource.getPassword(),"登录密码不能为空");
            if (StringUtils.isEmpty(output)){
                ApplicationHome home = new ApplicationHome(this.getClass());
                output = home.getSource().getParent().concat("/output/");
                File file = Paths.get(output).toFile();
                if (!file.exists()){
                    file.mkdir();
                }
            }
        }
    }
}
