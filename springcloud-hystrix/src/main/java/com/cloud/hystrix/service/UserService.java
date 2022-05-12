package com.cloud.hystrix.service;

import com.cloud.hystrix.model.User;
import com.example.consumer.model.vo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qcl
 * @version 1.0
 * @date 2021/12/23 5:07 下午
 * @description
 */

@Service
public class UserService {

    int num = 0;

    @Autowired
    private RestTemplate restTemplate;

    // 设置回调 - 请求错误时回调
    // 还可以对降级的服务进行分组
    @HystrixCommand(fallbackMethod = "getUserDefault", commandKey = "getUserById", groupKey = "userGroup",
            threadPoolKey = "getUserThread",

            // 熔断配置
            // https://mrbird.cc/Spring-Cloud-Hystrix-Circuit-Breaker.html 属性讲解
            commandProperties= {
            // 如果5秒内，请求达到了4个，错误率达到50%以上，就开启跳闸，
            // 就会直接走fallback，业务代码将不再会调用
            // 如果3秒以后再调用，会再执行一次业务方法
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),

                    // 不是很推荐 - 尽量使用单独的线程执行
                    // 信号隔离设置 - 在主线程执行
                    //只需要设置execution.isolation.strategy = SEMAPHORE即可
                    // 并设置并发数
//                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
//                    @HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests", value="10")
            }

            // 以上几个参数的含义是：5秒种以内，请求次数达到4个以上，失败率达到50%以上，则开启跳闸。跳闸3秒以后如果有请求过来不会继续跳闸，
            // 而是去实际做请求，如果请求失败，继续保持跳闸的状态，如果请求成功，则取消跳闸。
    )
    // 开启缓存 如果对数据改变不大的 可以使用， 变化的数据不推荐使用 尽量使用redis
//    @CacheResult
    public User hello(String name) {
        Map<String, Object> params = new HashMap<>();
        num ++;
        if (num > 4) {
            // 当num 大于4时 且 过了3秒 后恢复正常执行
            params.put("name", 0);
        }else {
            params.put("name", name);
        }
        User u = restTemplate.getForObject("http://provider/hello/app/{name}", User.class, params);


        System.out.println("执行方法" + num);
        return u;
    }

    // 如果该方法抛出错误 就会降级到 getUserDefault1 , 也可以通过 ignoreExceptions 去忽略某些异常
    @HystrixCommand(fallbackMethod = "getUserDefault1", ignoreExceptions = {NullPointerException.class})
    public User getUserDefault(String name, Throwable e) {
        // 获取异常信息
        System.out.println(e.getMessage());

        // 测试服务降级 - 该方法会错误 回调 getUserDefault1
//        String a = null;
//        a.toString();

        User user = new User();
        user.setUsername("defaultUser");
        return user;
    }

    public User getUserDefault1(String name) {
        User user = new User();
        user.setUsername("defaultUser 1");
        return user;
    }
}
