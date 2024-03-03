package com.hibernatedirty.customdirty;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@Configuration
public class HibernateListenerConfigurer {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private CustomDefaultFlushEntityEventListener customDefaultFlushEntityEventListener;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.FLUSH_ENTITY).appendListener(customDefaultFlushEntityEventListener);
    }
}