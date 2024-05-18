package cn.baltics.aggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *@name AggregationApplication
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
@SpringBootApplication(scanBasePackages = {"cn.baltics"})
public class AggregationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AggregationApplication.class, args);
    }
}
