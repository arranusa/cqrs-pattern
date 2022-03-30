package com.example.queryapi.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

@EnableJms
@Configuration
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    String BROKER_URL;

    @Value("${spring.activemq.user}")
    String BROKER_USERNAME;

    @Value("${spring.activemq.password}")
    String BROKER_PASSWORD;

    @Value("${spring.activemq.pool.max-connections}")
    int MAX_CONNECTION;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUserName(BROKER_USERNAME);
        connectionFactory.setPassword(BROKER_PASSWORD);
        return connectionFactory;
    }

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(final ActiveMQConnectionFactory connectionFactory) {
        PooledConnectionFactory factory = new PooledConnectionFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMaxConnections(MAX_CONNECTION);
        return factory;
    }

    @Bean
    @Qualifier("jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer,
            final PooledConnectionFactory connectionFactory,
            final MessageConverter messageConverter) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(messageConverter);
        factory.setConcurrency("10-50");
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    @Qualifier("jmsTopicListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer,
            final @Qualifier("pooledConnectionFactory") PooledConnectionFactory connectionFactory,
            final MessageConverter messageConverter) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(messageConverter);
//        factory.setConcurrency("10-50");
        factory.setPubSubDomain(true);
        return factory;
    }


    @Bean
    public JmsTemplate jmsTemplate(final PooledConnectionFactory pooledConnectionFactory,
                                   final MessageConverter messageConverter,
                                   final DynamicDestinationResolver destinationResolver) {
        JmsTemplate template = new JmsTemplate(pooledConnectionFactory);
        template.setMessageConverter(messageConverter);
        template.setDestinationResolver(destinationResolver);
        return template;
    }

    @Bean
    @Qualifier("jmsTransactionManager")
    JmsTransactionManager jmsTransactionManager(final PooledConnectionFactory pooledConnectionFactory) {
        return new JmsTransactionManager(pooledConnectionFactory);
    }

    @Bean
    public Validator validatorFactory() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setProviderClass(HibernateValidator.class);
        return factory;
    }


    @Bean
    public DefaultMessageHandlerMethodFactory methodFactory(final Validator validator) {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setValidator(validator);
        return factory;
    }

    @Bean
    public DynamicDestinationResolver destinationResolver() {
        return new DynamicDestinationResolver() {
            @Override
            public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
                if (destinationName.substring(0, 2).equals("T.")) {
                    pubSubDomain = true;
                }
                return super.resolveDestinationName(session, destinationName, pubSubDomain);
            }
        };
    }


    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean("jms")
    public JmsComponent jmsComponent(final PooledConnectionFactory connectionFactory, final @Qualifier("jmsTransactionManager") JmsTransactionManager jmsTransactionManager) {
        return JmsComponent.jmsComponentTransacted(connectionFactory, jmsTransactionManager);
    }
}