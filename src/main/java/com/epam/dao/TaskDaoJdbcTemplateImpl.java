package com.epam.dao;

import com.epam.modeles.Student;
import com.epam.modeles.Task;
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
public class TaskDaoJdbcTemplateImpl implements TaskDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Map<Long, Task> tasksMap = new HashMap<>();

    @Autowired
    private RowMapper<Task> taskRowMapper;

    //language=SQL
    private final static String SQL_SELECT_ALL_TASK = "SELECT * FROM task";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM task WHERE task_id = :id";

    //language=SQL
    private final static String SQL_INSERT_INTO_TASK = "INSERT INTO task(task_name, task_subject_id, task_text) " +
            "VALUE (:taskName, :subjectId, :text)";

    @Override
    public Optional<Task> findTaskById(Integer id) {
        Map<String,Integer> params = new HashMap<>();
        params.put("id",id);
        List<Task> tasks = namedParameterJdbcTemplate.query(SQL_SELECT_BY_ID,params,taskRowMapper);
        if(tasks.isEmpty()){
            return Optional.empty();
        }
        Task task = tasks.get(0);
        return Optional.of(task);
    }

    @Override
    public Optional<Task> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Task model) {
        Map params = new HashMap<String, Object>();
        params.put("taskName", model.getName());
        params.put("subjectId", model.getSubjectId());
        params.put("text", model.getText());
        namedParameterJdbcTemplate.update(SQL_INSERT_INTO_TASK, params);
    }

    @Override
    public void update(Task model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Task> findAll() {
        return template.query(SQL_SELECT_ALL_TASK,taskRowMapper);
    }
}
