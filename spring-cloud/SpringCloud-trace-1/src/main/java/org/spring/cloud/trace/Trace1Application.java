package org.spring.cloud.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author chenssy
 * @date 2017/6/27
 * @since v1.0.0
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class Trace1Application {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/trace-1")
    public String trace(){
        logger.info("===call trace-1  =====");

        return restTemplate().getForEntity("http://trace-2/trace-2",String.class).getBody();
    }

    public static void main(String[] args){
        SpringApplication.run(Trace1Application.class);
    }
}
