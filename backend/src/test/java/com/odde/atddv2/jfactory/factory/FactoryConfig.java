package com.odde.atddv2.jfactory.factory;

import com.github.leeonky.jfactory.JFactory;
import com.github.leeonky.jfactory.repo.JPADataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class FactoryConfig {

    private static ApplicationContext context;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public FactoryConfig(ApplicationContext ac) {
        context = ac;
    }

    @Bean
    public JFactory factorySet() {
        return new EntityFactory(new JPADataRepository(entityManagerFactory.createEntityManager()));
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
