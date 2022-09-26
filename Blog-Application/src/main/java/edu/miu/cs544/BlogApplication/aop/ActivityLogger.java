package edu.miu.cs544.BlogApplication.aop;

import edu.miu.cs544.BlogApplication.dao.IActivityLogDAO;
import edu.miu.cs544.BlogApplication.entity.ActivityLog;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
@RequiredArgsConstructor
public class ActivityLogger {
    private final IActivityLogDAO repository;

    @Pointcut("within(edu.miu.cs544.BlogApplication.controller..*)")
    public void trigger() {}

    @Around("trigger()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant startedAt = Instant.now();
        Object object = joinPoint.proceed();
        Instant endedAt = Instant.now();
        ActivityLog activityLog = new ActivityLog();
        activityLog.setClassName(joinPoint.getTarget().getClass().getSimpleName());
        activityLog.setMethodName(joinPoint.getSignature().getName());
        activityLog.setExecutedTime(endedAt.toEpochMilli() - startedAt.getEpochSecond());
        repository.save(activityLog);
        return object;
    }
}
}
