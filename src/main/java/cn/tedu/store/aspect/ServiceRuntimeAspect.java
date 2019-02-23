package cn.tedu.store.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceRuntimeAspect {
	@Around("execution(* cn.tedu.store.service.impl.*.*(..))")
	public Object a(ProceedingJoinPoint pjp) throws Throwable {
		long start=System.currentTimeMillis();
		//切面应用到的方法
		Object result= pjp.proceed();
		long end=System.currentTimeMillis();
		System.err.println("耗时:"+(end-start)+"ms");
		return result;
	}
}
 