package ru.itis;

import ru.itis.moduls.Course;
import ru.itis.moduls.Lesson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.*;

public class LessonRepositoryJdbcTemplateImpl implements LessonRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select c.id, * from lesson l left join course c on l.course_id = c.id order by l.lesson_id";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_NAME = "select c.id, * from lesson l left join course c on l.course_id = c.id where l.lesson_name = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select c.id, * from lesson l left join course c on l.course_id = c.id where l.lesson_id = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into lesson(lesson_name, day, time, course_id)" +
            "values (?,?,?,?);";
    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update lesson set lesson_name = ?, day = ?, time = ?, course_id = ? where lesson_id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from lesson where lesson_id = ?";

    private JdbcTemplate jdbcTemplate;

    public LessonRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<Lesson> lessonResultSetExtractor = resultSet -> {
        Lesson lesson = null;
        while (resultSet.next()) {
            int courseId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String start = resultSet.getString("start");
            String ending = resultSet.getString("ending");
            int teacherId = resultSet.getInt("teacher_id");

            int lessonId = resultSet.getInt("lesson_id");
            String lessonName = resultSet.getString("lesson_name");
            String day = resultSet.getString("day");
            String time = resultSet.getString("time");

            Course course = new Course(courseId, name, start, ending, teacherId);
            lesson = new Lesson(lessonId, lessonName, day, time, course);
        }
        return lesson;
    };

    private final ResultSetExtractor<List<Lesson>> lessonsResultSetExtractor = resultSet -> {
        List<Lesson> list = new ArrayList<>();
        while(resultSet.next()) {
            int courseId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String start = resultSet.getString("start");
            String ending = resultSet.getString("ending");
            int teacherId = resultSet.getInt("teacher_id");

            int lessonId = resultSet.getInt("lesson_id");
            String lessonName = resultSet.getString("lesson_name");
            String day = resultSet.getString("day");
            String time = resultSet.getString("time");

            Course course = new Course(courseId, name, start, ending, teacherId);
            Lesson lesson = new Lesson(lessonId, lessonName, day, time, course);
            list.add(lesson);
        }
        return list;
    };

    @Override
    public List<Lesson> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, lessonsResultSetExtractor);
    }

    @Override
    public List<Lesson> findAllByName(String searchName) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_NAME, lessonsResultSetExtractor, searchName);
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.query(SQL_SELECT_BY_ID, lessonResultSetExtractor, id));
        } catch (NullPointerException e) {
            return Optional.empty();
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"lesson_id"});
            statement.setString(1, lesson.getLessonName());
            statement.setString(2, lesson.getDay());
            statement.setString(3,lesson.getTime());
            statement.setInt(4, lesson.getCourse().getId());
            return statement;
        }, keyHolder);

        lesson.setLessonId(keyHolder.getKey().intValue());
    }

    @Override
    public void updateById(Lesson lesson) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID,lesson.getLessonName(), lesson.getDay(), lesson.getTime(),
                lesson.getCourse().getId(), lesson.getLessonId());
    }

    @Override
    public void deleteById(Lesson lesson) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, lesson.getLessonId());
    }
}
