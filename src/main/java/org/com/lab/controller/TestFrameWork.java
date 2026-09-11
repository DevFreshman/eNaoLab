package org.com.lab.controller;

import org.example.javaframework.infra.security.JwtProvider;
import org.example.javaframework.web.api.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFrameWork {

    JwtProvider jwtProvider;
    public TestFrameWork(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("public/accesstoken")
    public Response<String> hello() {
        return Response.success(
                jwtProvider.generateToken(
                        "USR000000000000000000005",
                        "phamhoa",
                        "USER",
                        "TECH"
                )
        );
    }

    @GetMapping("public/admintoken")
    public Response<String> hello1() {
        return Response.success(
                jwtProvider.generateToken(
                        "USR000000000000000000001",
                        "admin",
                        "ADMIN",
                        "TECH"
                )
        );
    }


    @GetMapping("amdin/hello")
    public String hello2() {
        return "hello2";
    }



}
