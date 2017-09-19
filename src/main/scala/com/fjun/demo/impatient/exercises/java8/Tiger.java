package com.fjun.demo.impatient.exercises.java8;

/**
 * <p>
 * <br> =========================
 * <br> 公司：阿里移动事业群-国际业务部-国际研发部
 * <br> 系统：U盟业务线-中台系统
 * <br> @author：陈飞君（feijun.cfj@alibaba-inc.com）
 * <br> @date： 2017-09-19
 * <br> =========================
 */
public interface Tiger {
    default void say() {
        System.out.println("嗷嗷嗷");
    }
}
