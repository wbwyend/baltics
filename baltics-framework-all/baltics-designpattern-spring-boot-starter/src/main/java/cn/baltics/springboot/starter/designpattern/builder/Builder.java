package cn.baltics.springboot.starter.designpattern.builder;
/**
 *@name Builder 建造者模式接口
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public interface Builder<T> {
    T build();
}
