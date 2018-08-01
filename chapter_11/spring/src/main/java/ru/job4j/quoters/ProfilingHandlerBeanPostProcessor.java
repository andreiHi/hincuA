package ru.job4j.quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();
    public ProfilingController controller = new ProfilingController();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if (aClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, aClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object retVal = null;
                    if (controller.isEnabled()) {
                        System.out.println("Профилирую...");
                        long before = System.nanoTime();
                        retVal = method.invoke(bean, args);
                        long after = System.nanoTime();
                        System.out.println(after - before);
                        System.out.println("Всё.");
                        return retVal;
                    } else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }
        return bean;
    }
}
