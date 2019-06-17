package com.epam.config;

import com.epam.modeles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam")
public class WebConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resources/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);

        return viewResolver;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
        String username = "root";
        String password = "LizaLukyanenko";

        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(className);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public RowMapper<Student> userRowMapper(){
        return (resultSet,i)->{
            Student student = new Student(
                    resultSet.getInt("user_id"),
                    resultSet.getString("user_name"),
                    resultSet.getString("user_surname"),
                    resultSet.getInt("user_group_id"),
                    resultSet.getString("user_login"),
                    resultSet.getString("user_password")
            );
            return student;
        };
    }

    @Bean
    public RowMapper<PersonalTask> personalTaskRowMapper(){
        return (resultSet,i)->{
            PersonalTask personalTask = new PersonalTask(
                    resultSet.getInt("personal_task_id"),
                    resultSet.getInt("personal_task_user_id"),
                    resultSet.getInt("personal_task_task_id"),
                    resultSet.getInt("personal_task_teacher_id"),
                    resultSet.getString("personal_task_status")
            );
            return personalTask;
        };
    }

    @Bean
    public RowMapper<Group> groupRowMapper(){
        return (resultSet,i)->{
            Group group = new Group(
                    resultSet.getInt("group_id"),
                    resultSet.getString("group_name"),
                    resultSet.getInt("group_kurs")
            );
            return group;
        };
    }

    @Bean
    public RowMapper<Subject> subjectRowMapper(){
        return (resultSet,i)->{
            Subject subject = new Subject(
                    resultSet.getInt("subject_id"),
                    resultSet.getString("subject_name"),
                    resultSet.getInt("subject_kurs")
            );
            return subject;
        };
    }

    @Bean
    public RowMapper<Task> taskRowMapper(){
        return (resultSet,i)->{
            Task task = new Task(
                    resultSet.getInt("task_id"),
                    resultSet.getString("task_name"),
                    resultSet.getInt("task_subject_id"),
                    resultSet.getString("task_text")
            );
            return task;
        };
    }

    @Bean
    public RowMapper<Teacher> teacherRowMapperRowMapper(){
        return (resultSet,i)->{
            Teacher teacher = new Teacher(
                    resultSet.getInt("teacher_id"),
                    resultSet.getString("teacher_name"),
                    resultSet.getString("teacher_surname"),
                    resultSet.getString("teacher_specialization"),
                    resultSet.getString("teacher_login"),
                    resultSet.getString("teacher_password")
            );
            return teacher;
        };
    }
}
