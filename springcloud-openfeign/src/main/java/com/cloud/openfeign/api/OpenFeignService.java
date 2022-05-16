package com.cloud.openfeign.api;

import com.cloud.openfeign.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/16 10:08 AM
 * @description
 */
@FeignClient(value = "provider")
public interface OpenFeignService {

    @PostMapping("/openfeign/provider/order2")
    User getUser(@RequestBody User user);
}
