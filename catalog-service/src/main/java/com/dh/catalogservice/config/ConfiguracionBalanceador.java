package com.dh.catalogservice.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfiguracionBalanceador {
    @Bean
    public ReactorLoadBalancer<ServiceInstance> specificLoadBalancer(Environment environment,
                                                                     LoadBalancerClientFactory loadBalancerClientFactory){
        String defaultName = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(defaultName, ServiceInstanceListSupplier.class),
                defaultName);
    }
}
