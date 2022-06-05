package com.atsun.dormitory.aop;

import com.atsun.dormitory.annotation.Log;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.LogService;
import com.atsun.dormitory.utils.JwtUtil;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.DataResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO(在软件业 ， AOP为 ‎ Aspect Oriented Programming ‎ 的缩写 ， 意为 ： 面向切面编程 ，
  通过预编译方式和运行期间动态代理实现程序功能的统一维护的一种技术 。AOP是OOP的延续 ， 是软件开发中的一个热点 ，
  也是Spring框架中的一个重要内容 ， 是函数式编程的一种衍生范型 。 利用AOP可以对业务逻辑的各个部分进行隔离 ，
  从而使得业务逻辑各部分之间的耦合度降低 ， 提高程序的可重用性 ， 同时提高了开发的效率 。)
 * @Author SH
 * @Date 2022/1/7 11:53
 */
@Aspect
@Component
@Log4j2
public class LogAop {

    private LogService logService;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    /**
     * Aspect定义切面
     *Pointcut切点
     */
    @Pointcut("execution(* com.atsun.dormitory.controller.*.*(..))")
    public void pointcut1() {
    }

    /**
     * 记录系统日志
     *Around：环绕增强
     * @param jp 。
     */
    @Around(value = "pointcut1()")
    public Object after(ProceedingJoinPoint jp) throws Throwable {
        log.info("==============开启操作日志==================");
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method targetMethod = signature.getMethod();
        //方法上有Log 注解
        if (targetMethod.isAnnotationPresent(Log.class)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();

            //获取类名
            String clas = jp.getTarget().getClass().getSimpleName();

            //获取操作用户id
            String token = request.getHeader("_ut");

            String userId = JwtUtil.decode(token);

            //获取url
            String url = request.getRequestURI();

            //获取方法名
            String method = targetMethod.getName();

            //获取参数，并转成json
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            Object[] args = jp.getArgs();
            Map<String, Object> param = new HashMap<>(2);
            for (Object arg : args) {
                String className = arg.getClass().getSimpleName();
                className = className.substring(0, 1).toLowerCase() + className.substring(1);
                param.put(className, arg);
            }

            //获取ip
            String ip = request.getRemoteAddr();

            //获取方法注解上的value
            Log l = targetMethod.getAnnotation(Log.class);
            log.info(l.value());


            com.atsun.dormitory.po.Log lo = new com.atsun.dormitory.po.Log();
            lo.setId(String.valueOf(SnowFlake.nextId()));
            lo.setClas(clas);
            lo.setIp(ip);
            lo.setUserId(userId);
            lo.setMethod(method);
            lo.setUrl(url);
            lo.setDescription(l.value());
            lo.setParam(objectMapper.writeValueAsString(param));
            lo.setOperateTim(new Date());
            Object result;
            //获取结果
            try {
                result = jp.proceed(args);
                lo.setResult(objectMapper.writeValueAsString(result));
            } catch (TransException e) {
                DataResponse<?> res = new DataResponse<>();
                res.setTransDesc(e.getMessage());
                lo.setResult(objectMapper.writeValueAsString(res));
                throw e;
            } catch (Throwable t) {
                // TODO 设置结果
                lo.setResult(objectMapper.writeValueAsString(t));
                throw t;
            } finally {
                // TODO 添加日志记录
                logService.insert(lo);
            }
            return result;
        }
        return jp.proceed(jp.getArgs());
    }
}
