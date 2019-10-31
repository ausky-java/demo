/**
 * File Name:    FirstController.java
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
 * History:      2019-10-31 created by hy.ao
 */
package com.ausky.demo.swagger.controller;

import com.ausky.demo.swagger.bean.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: hy.ao
 * Date: 2019-10-31
 * Time: 10:20
 * 文件说明：TODO
 */
@RestController
@Api( description = "first test controller" )
public class FirstController
{

    @GetMapping( "/testGet" )
    public String testGet()
    {
        return "hello world";
    }


    @PostMapping( "/testPost" )
    public String testPost()
    {
        return "hello world";
    }

    @GetMapping( "/getUser" )
    @ApiOperation( "get user method" )
    public User getUser()
    {
        return new User( "aa", 18 );
    }

    @GetMapping( "/setUser" )
    public User getUser( User user )
    {
        return user;
    }

}