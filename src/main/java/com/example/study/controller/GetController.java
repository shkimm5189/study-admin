package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;
// getMapping의 활용

@RestController
@RequestMapping("/api")  // localhost:8080/api
public class GetController {
    @RequestMapping(method = RequestMethod.GET ,path =  "/getMethod")
    public String getRequest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id , @RequestParam(name = "password") String pwd){
        String password = "bbbbb";
        System.out.println("id : "+id);
        System.out.println("pwd : "+pwd);
        return id+pwd;
    }
    // 파라미터들이 많아 질경우. 하나하나 파라미터로 받아오는건 비효율적이다
    // 객체를 활용해서 만든다.
    //localhost:8080/api/multiParameter?account=abc&mail=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getMail());
        System.out.println(searchParam.getPage());
        //{"account,"", "email", "" ,"page","" "}
        return searchParam;
    }


    @GetMapping("/header")
    public Header getHeader(){
        // {"resultCode : "OK", "description : "OK" "
        return Header.builder()
                .resultCode("OK")
                .description("OK")
                .build();
    }
}
