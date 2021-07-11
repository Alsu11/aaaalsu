package ru.itis;

import ru.itis.moduls.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findAll();

    List<Course> findAllByName(String searchName);

    Optional<Course> findById(Integer id);

    void save(Course course);

    void updateById(Course course);

    void deleteById(Course course);

}
