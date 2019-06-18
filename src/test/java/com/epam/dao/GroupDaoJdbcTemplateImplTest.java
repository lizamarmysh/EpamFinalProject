//package com.epam.dao;
//
//import com.epam.config.TestConfig;
//import com.epam.config.WebConfig;
//import com.epam.modeles.Group;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes={ TestConfig.class })
//public class GroupDaoJdbcTemplateImplTest {
//
//    @Qualifier("getGroupDaoJdbcTemplate")
//    @Autowired
//    private GroupDaoJdbcTemplateImpl groupDaoJdbcTemplate;
//
//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @Autowired
//    private RowMapper<Group> groupRowMapper;
//
//    @Test
//    public void findGroupById() {
//    }
//
//    @Test
//    public void findAll() {
//        List<Group> expected = namedParameterJdbcTemplate.query("SELECT * FROM `group`",groupRowMapper);
//        List<Group> actual = groupDaoJdbcTemplate.findAll();
//
//        assertEquals(expected, actual);
//    }
//}