package com.cloud.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qcl
 * @version 1.0
 * @date 2021/12/24 11:24 上午
 * @description PRE过滤器用于将请求路径与配置的路由规则进行匹配，以找到需要转发的目标地址，并做一些前置加工，比如请求的校验等；
 */
@Component
public class PreZuulFilter extends ZuulFilter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 对应Zuul生命周期的四个阶段：pre、post、route和error；
     * @return
     *
     * PRE：PRE过滤器用于将请求路径与配置的路由规则进行匹配，以找到需要转发的目标地址，并做一些前置加工，比如请求的校验等；
     *
     * ROUTING：ROUTING过滤器用于将外部请求转发到具体服务实例上去；
     *
     * POST：POST过滤器用于将微服务的响应信息返回到客户端，这个过程种可以对返回数据进行加工处理；
     *
     * ERROR：上述的过程发生异常后将调用ERROR过滤器。ERROR过滤器捕获到异常后需要将异常信息返回给客户端，所以最终还是会调用POST过滤器。
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的优先级，数字越小，优先级越高；
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 方法返回boolean类型，true时表示是否执行该过滤器的run方法，false则表示不执行；
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 进行跨域请求的时候，并且请求头中有额外参数，比如token，客户端会先发送一个OPTIONS请求来探测后续需要发起的跨域POST请求是否安全可接受
        // 所以这个请求就不需要拦截，下面是处理方式
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            log.info("OPTIONS请求不做拦截操作");
            return false;
        }
        return true;
    }

    /**
     * 过滤器的过滤逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String userToken = request.getHeader("apikey");
        if (StringUtils.isBlank(userToken)) {
            log.warn("apikey为空");
            sendError(requestContext, 99001, "请传输参数apikey", null);
            return null;
        }

        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}", uri, method, host);
        return null;
    }

    /**
     * 发送错误消息
     * todo: 如果是重定向到本地 还是会正常返回对应的api数据，状态码会变成返回的状态码
     * todo: 如果是转发到其它服务的， 会被拦截下来返回对应的错误信息
     *
     * @param requestContext
     * @param status
     * @param msg
     */
    private void sendError(RequestContext requestContext, int status, String msg, String userToken) {
        requestContext.setSendZuulResponse(false); //不对请求进行路由
        requestContext.setResponseStatusCode(status);//设置返回状态码
        requestContext.setResponseBody(JSONObject.toJSONString(msg));//设置返回响应体
        requestContext.getResponse().setContentType("application/json;charset=UTF-8");//设置返回响应体格式，可能会乱码
    }
}
