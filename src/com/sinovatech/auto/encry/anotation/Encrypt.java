/**
 * 
 */
package com.sinovatech.auto.encry.anotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符串标记类，标记要加密解密的字段
 * @author wujiajun
 * mail:wujiajun@sinovatech.com
 * QQ:352181949
 * 2017年6月21日下午2:59:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Encrypt {
	/**
	 * 是否加密 默认打了标记是
	 * @return
	 */
	boolean isEncrypt() default true;
	
	/**
	 * 是否解密 默认打了标记是
	 * @return
	 */
	boolean isUnEncrypt() default true;//是否解密
}
