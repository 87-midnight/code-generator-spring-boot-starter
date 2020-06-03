package org.midnight.generator.engine;


import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * @author linchuangang
 * @create 2019-10-24 9:36
 **/
@Slf4j
public class CodeGenerator {

    public void print(){
        log.info("starter test");
    }

    public static void main(String...args){
        Velocity.init();
        Template template = Velocity.getTemplate("\\src\\main\\resources\\controller\\Controller.vm");
        VelocityContext ctx = new VelocityContext();
        ctx.put("list",new ArrayList<Integer>(){
            {
                add(1);
                add(2);
                add(3);
            }
        });
        ctx.put("beans",new ArrayList<BeansTest>(){
            {
                add(BeansTest.builder().name("test1").build());
                add(BeansTest.builder().name("test2").build());
            }
        });
        Writer writer = new StringWriter();
        template.merge(ctx, writer);
        System.out.println(writer.toString());
    }

    @Data
    @Builder
    public static class BeansTest{
        private String name;
    }
}
