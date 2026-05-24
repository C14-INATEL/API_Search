package com.api_search.project.controller;

import com.api_search.project.client.FindByPasswordClient;
import com.api_search.project.excepetion.FindByPasswordExcept;
import com.api_search.project.response.FindByPasswordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkPassword")
public class FindByPasswordController {
    @Autowired
    private FindByPasswordClient client;
    @Autowired
    private FindByPasswordResponse response;

    @GetMapping("/password")
    public String check(@RequestParam String password) throws FindByPasswordExcept {
        try
        {
            String hash = client.sha1(password);
            String suffix = hash.substring(5);
            String body = client.checkPassword(password);
            return response.parse(body, suffix);
        }
        catch (Exception e) {
            throw new FindByPasswordExcept(e.getMessage());
        }
    }
}
