package com.epam.dao;

import com.epam.modeles.Group;
import com.epam.modeles.Student;
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
public class GroupDaoJdbcTemplateImpl implements GroupDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Map<Long, Group> groupMap = new HashMap<>();

    @Autowired
    private RowMapper<Group> groupRowMapper;

    //language=SQL
    private final static String SQL_SELECT_ALL_GROUPS = "SELECT * FROM `group`";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM `group` WHERE group_id = :id";

    @Override
    public Optional<Group> findGroupById(Integer id) {
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        List<Group> groups = namedParameterJdbcTemplate.query(SQL_SELECT_BY_ID,params,groupRowMapper);
        if(groups.isEmpty()){
            return Optional.empty();
        }
        Group group = groups.get(0);
        return Optional.of(group);
    }

    @Override
    public Optional<Group> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Group model) {

    }

    @Override
    public void update(Group model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Group> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_GROUPS,groupRowMapper);
    }
}
