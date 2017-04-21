package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


/**
 *Created by wiper on 2017/4/21.
 * email : ills@live.cn
 * spring 5 推崇微服务架构，无架构化，插件化。
 * demo 可以通过  http://start.spring.io/  来构建项目
 * 可以通过 https://spring.io/blog/2016/09/22/new-in-spring-5-functional-web-framework  来学习spring 5的新知识
 */

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) throws InterruptedException {

//
//        HandlerFunction hello = new HandlerFunction() {
//            @Override
//            public Mono handle(ServerRequest serverRequest) {
//                return ServerResponse.ok().body(Mono.just(serverRequest.path()),String.class);
//
//        };

        /**
         * 处理请求
         */
        HandlerFunction hello = request -> ok().body(Mono.just(request.path()+"test"),String.class);
        HandlerFunction person = request -> ok().contentType(APPLICATION_JSON).body(Mono.just(new Person("json")),Person.class);


        /**
         * 添加路由
         */
        RouterFunction router =route(GET("/"),hello);
        router = router.andRoute(GET("/json"),person);


        /**
         * 绑定handler
         */
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(router);


        /**
         * 启动服务器
         */
        HttpServer.create("localhost",8888).newHandler(new ReactorHttpHandlerAdapter(httpHandler)).block();


        /**
         * 守护线程
         */
        Thread.currentThread().join();

	}




    /**
     * 为了测试，router打包成方法出来，测试代码在test里面。
     */

    static RouterFunction getRouter(){
        /**
         * 处理请求
         */
        HandlerFunction hello = request -> ok().body(Mono.just(request.path()+"test"),String.class);
        HandlerFunction person = request -> ok().contentType(APPLICATION_JSON).body(Mono.just(new Person("json")),Person.class);


        /**
         * 添加路由
         */
        RouterFunction router =route(GET("/"),hello);
        router = router.andRoute(GET("/json"),person);

        return router;
    }


}
