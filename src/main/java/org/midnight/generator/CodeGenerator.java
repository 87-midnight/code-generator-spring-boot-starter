package org.midnight.generator;


import lombok.extern.slf4j.Slf4j;

/**
 * @author linchuangang
 * @create 2019-10-24 9:36
 **/
@Slf4j
public class CodeGenerator {

    private CodeGenerator(){}

    public static CodeGenerator getInstance(){
        return new CodeGenerator();
    }

    public void print(){
        log.info("starter test");
    }
}
