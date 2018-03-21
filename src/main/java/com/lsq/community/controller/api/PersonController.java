package com.lsq.community.controller.api;

import com.google.gson.Gson;
import com.lsq.community.pojo.Person;
import com.lsq.community.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    Gson gson;

    @RequestMapping(value = "/list",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String list(){
        List<Person> personList = personService.getAll();
        return gson.toJson(personList);
    }
}
