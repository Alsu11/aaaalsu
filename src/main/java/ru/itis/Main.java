package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.moduls.Course;
import ru.itis.moduls.Lesson;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            // TODO: пофиксить, чтобы не писать абсолютный путь
            properties.load(new FileReader("src\\main\\resources\\application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        DataSource dataSource = new HikariDataSource(config);

        CourseRepository courseRepository = new CourseRepositoryJdbcTemplateImpl(dataSource);
        LessonRepository lessonRepository = new LessonRepositoryJdbcTemplateImpl(dataSource);

        /*System.out.println(courseRepository.findAll());

        System.out.println(courseRepository.findAllByName("Java"));

        System.out.println(courseRepository.findById(1));

        Course course = new Course( "AlsuTheBest", "febrary", "may", 5);
        courseRepository.save(course);

        courseRepository.updateById(course);

        courseRepository.deleteById(course);

        System.out.println(lessonRepository.findAll());

        System.out.println(lessonRepository.findAllByName("java"));

        System.out.println(lessonRepository.findById(2));

        Lesson lesson = new Lesson("alsu","10:10", "12:12", courseForUpdete);
        lessonRepository.save(lesson);

        lessonRepository.updateById(lesson);

        lessonRepository.deleteById(lesson);
         */
    }
}

