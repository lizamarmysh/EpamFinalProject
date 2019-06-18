//package com.epam.config;
//
//import com.epam.dao.GroupDaoJdbcTemplateImpl;
//import com.epam.modeles.Group;
//import com.epam.services.GroupService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@ComponentScan(basePackages = {
//        "com.epam.controllers",
//        "com.epam.dao",
//        "com.epam.services"
//})
//public class TestConfig {
//
//    @Bean
//    public GroupDaoJdbcTemplateImpl getGroupDaoJdbcTemplate() {
//        return new GroupDaoJdbcTemplateImpl();
//    }
//
//    @Bean
//    public GroupService getGroupService() {
//        return new GroupService();
//    }
//
//    @Bean
//    public RowMapper<Group> groupRowMapper(){
//        return (resultSet,i)->{
//            Group group = new Group(
//                    resultSet.getInt("group_id"),
//                    resultSet.getString("group_name"),
//                    resultSet.getInt("group_kurs")
//            );
//            return group;
//        };
//    }
//
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
//        return new NamedParameterJdbcTemplate(dataSource());
//    }
//
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        String className = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
//        String username = "root";
//        String password = "LizaLukyanenko";
//
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setUrl(url);
//        dataSource.setDriverClassName(className);
//
//        return dataSource;
//    }
//}
