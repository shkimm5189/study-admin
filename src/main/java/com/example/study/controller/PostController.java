package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {
    // post 발생 시기
    // HTML <Form>
    // ajax 검색
    // http post body -> data (json,xml,multiaprt-form)
    @PostMapping(value= "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    
}
