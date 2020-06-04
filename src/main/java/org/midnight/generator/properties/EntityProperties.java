package org.midnight.generator.properties;

import lombok.Builder;
import lombok.Data;

/**
 * entity 实体类配置
 * @author linchuangang
 * @create 2019-10-28 11:11
 **/
@Data
public class EntityProperties {

    /**是否开启swagger2 模型配置,default:false**/
    private boolean swaggerenabled;
    /**swagger2 api 模型对象名称**/
    private String modelname;
    private boolean persistable;
    private boolean serializable;
    private String tablename;
}
