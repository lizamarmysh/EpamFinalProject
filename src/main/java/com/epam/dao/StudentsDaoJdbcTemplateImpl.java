package com.epam.dao;

import com.epam.modeles.PersonalTask;
import com.epam.modeles.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.*;

@Component
public class StudentsDaoJdbcTemplateImpl implements StudentsDao {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Map<Long, Student> usersMap = new HashMap<>();

    @Autowired
    private RowMapper<Student> userRowMapper;

    //language=SQL
    private final static String SQL_SELECT_ALL_USERS = "SELECT * FROM user";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE user_id = :id";

    //language=SQL
    private final static String SQL_SELECT_BY_LOGIN = "SELECT * FROM user WHERE user_login = :login";

    //language=SQL
    private final static String SQL_SELECT_BY_LOG_AND_PASS = "SELECT * FROM user " +
            "WHERE upper(user_login) like (upper(:login)) and user_password = :password";

    //language=SQL
    private final static String SQL_INSERT_INTO_USER = "INSERT INTO user(user_name, user_surname, user_group_id, user_login, user_password) " +
            " VALUES (:userName, :surname, :groupId, :login, :password)";

    //language=SQL
    private final static String SQL_SELECT_BY_NAME_OR_SURNAME_OR_GROUP_ID = "SELECT * FROM user " +
            "WHERE (user_name = :name) OR (user_surname = :surname) OR (user_group_id = :groupId) OR " +
            "( (user_name = :name) AND (user_surname = :surname) AND (user_group_id = :groupId) )";



    @Override
    public Optional<Student> findStudentById(Integer id) {
        Map<String,Integer> params = new HashMap<>();
        params.put("id",id);
        List<Student> students = namedParameterJdbcTemplate.query(SQL_SELECT_BY_ID,params,userRowMapper);
        if(students.isEmpty()){
            return Optional.empty();
        }
        Student student = students.get(0);
        return Optional.of(student);
    }

    @Override
    public Optional<Student> findStudentByLogPass(String login, String password) {
        Map<String,String> params = new HashMap<>();
        params.put("login",login);
        params.put("password",password);
        List<Student> students = namedParameterJdbcTemplate.query(SQL_SELECT_BY_LOG_AND_PASS,params,userRowMapper);
        if(students.isEmpty()){
            return Optional.empty();
        }
        Student student = students.get(0);
        return Optional.of(student);
    }

    @Override
    public Optional<Student> findStudentByLogin(String login) {
        Map<String, String> params = new HashMap<>();
        params.put("login",login);
        List<Student> students = namedParameterJdbcTemplate.query(SQL_SELECT_BY_LOGIN,params,userRowMapper);
        if (students.isEmpty()){
            return Optional.empty();
        }
        Student student = students.get(0);
        return Optional.of(student);
    }

    @Override
    public List<Student> findStudentByNameOrSurnameOrGroupId(String name, String surnmane, Integer groupId) {
        Map<String,Object> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surnmane);
        params.put("groupId", groupId);
        return namedParameterJdbcTemplate.query(SQL_SELECT_BY_NAME_OR_SURNAME_OR_GROUP_ID, params, userRowMapper);
    }

    @Override
    public Optional<Student> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Student model) {
        Map params = new HashMap<String,Object>();
        params.put("userName", model.getName());
        params.put("surname", model.getSurname());
        params.put("groupId", model.getGroupId());
        params.put("login", model.getLogin());
        params.put("password", model.getPassword());
        namedParameterJdbcTemplate.update(SQL_INSERT_INTO_USER, params);
    }

    @Override
    public void update(Student model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Student> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_USERS,userRowMapper);
    }
}
