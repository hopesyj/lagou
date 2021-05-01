package com.lagou.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * Description
 *
 * @author sheyanjun
 * @date 2021/04/12
 */

@Intercepts({//注意看这个大花括号，也就这说这里可以定义多个@Signature对多个地方拦截，都用这个拦截器
        @Signature(type = StatementHandler.class, //这是指拦截哪个接口
                method = "prepare",//这个接口内的哪个方法名，不要拼错了
                args = {Connection.class, Integer.class}),// 这是拦截的方法的入参，按顺序写到这，不要多也不要少，如果方法重载，可是要通过方法名和入参来确定唯一的
})
public class MyPlugin implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass()); //这里是每次执行操作的时候，都会进行这个拦截器的方法内

    /**
     * 拦截方法:只要被拦截的目标对象的目标方法被执行时,每次都会执行 intercept 方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable { //增强逻辑
        System.out.println("对方法进行了增强....");
        return invocation.proceed(); //执行原方法
    }

    /**
     * 主要是为了把这个拦截器生成一个代理放到拦截器链中
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("将要包装的目标对象:" + target);
        return Plugin.wrap(target, this);
    }

    /**
     * 获取配置文件的参数
     * 插件初始化的时候调用，也只调用一次，插件配置的属性从这里设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的初始化参数:" + properties);
    }
}
