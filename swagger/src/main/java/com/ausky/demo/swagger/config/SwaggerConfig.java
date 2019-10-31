/**
 * File Name:    SwaggerConfig.java
 * <p>
 * File Desc:    TODO
 * <p>
 * Product AB:   TODO
 * <p>
 * Product Name: TODO
 * <p>
 * Module Name:  TODO
 * <p>
 * Module AB:    TODO
 * <p>
 * Author:       敖海样
 * <p>
 * History:      2019-10-30 created by hy.ao
 */
package com.ausky.demo.swagger.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.ApiSelector;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: hy.ao
 * Date: 2019-10-30
 * Time: 19:16
 * 文件说明：TODO
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket docket()
    {
        ApiSelector DEFAULT = new ApiSelector( Predicates.and( Predicates.not( RequestHandlerSelectors.withClassAnnotation( ApiIgnore.class ) ), Predicates.not( RequestHandlerSelectors.withMethodAnnotation( ApiIgnore.class ) ) ), PathSelectors.any() );
        return new Docket( DocumentationType.SWAGGER_2 ).apiInfo(
                new ApiInfo(
                        "ausky's document",
                        "this is my documentation",
                        "v1.0",
                        "urn:tos",
                        new Contact( "ausky", "www.ausky.space", "haiyang.ao@howbuy.com" ),
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0",
                        new ArrayList()
                )
        ).groupName( "ausky" ).select().paths( PathSelectors.regex("/get.*") ).apis( RequestHandlerSelectors.basePackage("com.ausky") ).build();
    }
}