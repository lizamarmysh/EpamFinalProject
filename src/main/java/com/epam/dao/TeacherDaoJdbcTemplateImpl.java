package com.epam.dao;

import com.epam.modeles.Student;
import com.epam.modeles.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class TeacherDaoJdbcTemplateImpl implements TeacherDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Map<Long, Teacher> teachersMap = new HashMap<>();

    @Autowired
    private RowMapper<Teacher> teacherRowMapper;

    //language=SQL
    private final static String SQL_SELECT_ALL_TEACHER = "SELECT * FROM teacher";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM teacher WHERE teacher_id = :id";

    //language=SQL
    private final static String SQL_SELECT_USER_BY_NAME = "SELECT * FROM teacher WHERE teacher_name = :name";

    //language=SQL
    private final static String SQL_SELECT_BY_LOG_AND_PASS = "SELECT * FROM teacher where upper(teacher_login) like (upper(:login)) and teacher_password = :password";

    //language=SQL
    private final static String SQL_SELECT_BY_LOGIN = "SELECT * FROM teacher WHERE teacher_login = :login";

    //language=SQL
    private final static String SQL_INSERT_INTO_TEACHER = "INSERT INTO teacher(teacher_name, teacher_surname, teacher_specialization, teacher_login, teacher_password) " +
                                                            "VALUE (:teacherName, :surname, :specialization, :login, :password)";


    @Override
    public Optional<Teacher> findTeacherById(Integer id) {
        Map<String,Integer> params = new HashMap<>();
        params.put("id",id);
        List<Teacher> teachers = namedParameterJdbcTemplate.query(SQL_SELECT_BY_ID,params,teacherRowMapper);
        if(teachers.isEmpty()){
            return Optional.empty();
        }
        Teacher teacher = teachers.get(0);
        return Optional.of(teacher);
    }

    @Override
    public Optional<Teacher> findTeacherByLogPass(String login, String password) {
        Map<String,String> params = new HashMap<>();
        params.put("login",login);
        params.put("password",password);
        List<Teacher> teachers = namedParameterJdbcTemplate.query(SQL_SELECT_BY_LOG_AND_PASS,params,teacherRowMapper);
        if(teachers.isEmpty()){
            return Optional.empty();
        }
        Teacher teacher = teachers.get(0);
        return Optional.of(teacher);
    }

    @Override
    public Optional<Teacher> findTeacherByLogin(String login) {
        Map<String, String> params = new HashMap<>();
        params.put("login",login);
        List<Teacher> teachers = namedParameterJdbcTemplate.query(SQL_SELECT_BY_LOGIN,params,teacherRowMapper);
        if (teachers.isEmpty()){
            return Optional.empty();
        }
        Teacher teacher = teachers.get(0);
        return Optional.of(teacher);
    }

    @Override
    public Optional<Teacher> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Teacher model) {
        Map params = new HashMap<String, Object>();
        params.put("teacherName",model.getName());
        params.put("surname", model.getSurname());
        params.put("specialization",model.getSpezialization());
        params.put("login",model.getLogin());
        params.put("password",model.getPassword());
        namedParameterJdbcTemplate.update(SQL_INSERT_INTO_TEACHER,params);
    }

    @Override
    public void update(Teacher model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }
}
