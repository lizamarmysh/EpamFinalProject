package com.epam.dao;

import com.epam.modeles.PersonalTask;
import com.epam.modeles.Student;
import com.epam.modeles.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PersonalTaskDaoJdbcTemplateImpl implements PersonalTaskDao {

    @Autowired
    private RowMapper<PersonalTask> personalTaskRowMapper;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_TASK_FOR_STUDENT = "SELECT * FROM personal_task " +
            "WHERE personal_task_user_id = :id";

    //language=SQL
    private static final String SQL_SELECT_PERSONAL_TASK_BY_ID = "SELECT * FROM personal_task " +
            "WHERE personal_task_id = :id";

    //language=SQL
    private static final String SQL_SELECT_TASK_FOR_TEACHER = "SELECT * FROM personal_task " +
            "WHERE personal_task_teacher_id = :id";

    //language=SQL
    private static final String SQL_INSERT_INTO_PERSONAL_TASK = "INSERT INTO personal_task(personal_task_user_id, personal_task_task_id, personal_task_teacher_id, personal_task_status) " +
            "VALUE (:studentId, :taskId, :teacherId, :status)";

    @Override
    public List<PersonalTask> findPersonalTaskByStudent(Student student) {
        Map<String,Integer> params = new HashMap<>();
        params.put("id",student.getId());
        List<PersonalTask> personalTasks = namedParameterJdbcTemplate.query(SQL_SELECT_TASK_FOR_STUDENT,params,personalTaskRowMapper);

        return personalTasks;
    }

    @Override
    public List<PersonalTask> findPersonalTaskByTeacher(Teacher teacher) {
        Map<String,Integer> params = new HashMap<>();
        params.put("id",teacher.getId());
        List<PersonalTask> personalTasks = namedParameterJdbcTemplate.query(SQL_SELECT_TASK_FOR_TEACHER,params,personalTaskRowMapper);

        return personalTasks;
    }

    @Override
    public Optional<PersonalTask> find(Integer id) {
        Map<String,Integer> params = new HashMap<>();
        params.put("id",id);
        List<PersonalTask> personalTasks = namedParameterJdbcTemplate.query(SQL_SELECT_PERSONAL_TASK_BY_ID,params,personalTaskRowMapper);
        if (personalTasks.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(personalTasks.get(0));
    }

    @Override
    public void save(PersonalTask model) {
        Map params = new HashMap<String, Object>();
        params.put("studentId", model.getStudentId());
        params.put("taskId", model.getTaskId());
        params.put("teacherId", model.getTeacherId());
        params.put("status", model.getStatus());
        namedParameterJdbcTemplate.update(SQL_INSERT_INTO_PERSONAL_TASK, params);
    }

    @Override
    public void update(PersonalTask model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<PersonalTask> findAll() {
        return null;
    }
}
