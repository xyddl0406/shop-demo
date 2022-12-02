package com.crewmate.shopapicommon.aop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TransactionAspect {
    private final static String BASE_DEFAULT_POITCUT = "execution(* com.crewmate..service..*ServiceImpl.*(..))";
    
    private final PlatformTransactionManager txManager; //config.DataSourceConfig.java에서 생성한 bean주입됨.

    @Bean
    public TransactionInterceptor txAdvice() {
        //readonly transaction 설정
        RuleBasedTransactionAttribute readonlyAttr = new RuleBasedTransactionAttribute();
        readonlyAttr.setReadOnly(true);
        
        //writer trasaction 설정에 쓰일 rollback roll 설정
        List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
        rollbackRules.add(new RollbackRuleAttribute(Exception.class));
        rollbackRules.add(new RollbackRuleAttribute(Error.class));
        
        //writer trasaction 설정
        RuleBasedTransactionAttribute writerAttr = new RuleBasedTransactionAttribute();
        writerAttr.setRollbackRules(rollbackRules);
        //readonlyAttr.setReadOnly(false); default false 
        
        //methodname 별 readonly, writer trascation 설정 세팅
        NameMatchTransactionAttributeSource attributeSource = new NameMatchTransactionAttributeSource();
        attributeSource.addTransactionalMethod("get*", readonlyAttr);
        attributeSource.addTransactionalMethod("select*", readonlyAttr);
        attributeSource.addTransactionalMethod("insert*", writerAttr);
        attributeSource.addTransactionalMethod("update*", writerAttr);
        attributeSource.addTransactionalMethod("delete*", writerAttr);
        attributeSource.addTransactionalMethod("*", writerAttr);

        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(txManager);
        transactionInterceptor.setTransactionAttributeSource(attributeSource);

        return transactionInterceptor;
    }

    @Bean
    public DefaultPointcutAdvisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(BASE_DEFAULT_POITCUT);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
