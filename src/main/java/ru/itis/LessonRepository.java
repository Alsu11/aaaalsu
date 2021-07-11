package ru.itis;

import ru.itis.moduls.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    List<Lesson> findAll();

    List<Lesson> findAllByName(String searchName);

    Optional<Lesson> findById(Integer id);

    void save(Lesson lesson);

    void updateById(Lesson lesson);

    void deleteById(Lesson lesson);

}
