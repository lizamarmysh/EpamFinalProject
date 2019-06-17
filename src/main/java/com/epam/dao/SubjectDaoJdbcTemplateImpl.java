package com.epam.dao;

import com.epam.modeles.Student;
import com.epam.modeles.Subject;
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
public class SubjectDaoJdbcTemplateImpl implements SubjectDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Map<Long, Subject> subjectMap = new HashMap<>();

    @Autowired
    private RowMapper<Subject> subjectRowMapper;

    //language=SQL
    private final static String SQL_SELECT_ALL_SUBJECTS = "SELECT * FROM subject";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM subject WHERE subject_id = :id";

    @Override
    public Optional<Subject> findSubjectById(Integer id) {
        Map<String,Integer> params = new HashMap<>();
        params.put("id",id);
        List<Subject> subjects = namedParameterJdbcTemplate.query(SQL_SELECT_BY_ID,params,subjectRowMapper);
        if(subjects.isEmpty()){
            return Optional.empty();
        }
        Subject subject = subjects.get(0);
        return Optional.of(subject);
    }

    @Override
    public Optional<Subject> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Subject model) {

    }

    @Override
    public void update(Subject model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Subject> findAll() {
        return template.query(SQL_SELECT_ALL_SUBJECTS,subjectRowMapper);
    }
}
