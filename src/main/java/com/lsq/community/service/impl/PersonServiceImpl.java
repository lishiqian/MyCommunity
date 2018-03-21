package com.lsq.community.service.impl;

import com.lsq.community.mapper.PersonMapper;
import com.lsq.community.pojo.Person;
import com.lsq.community.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public List<Person> getAll() {
        return personMapper.selectPerson();
    }
}
