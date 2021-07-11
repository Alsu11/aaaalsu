package ru.itis;

import ru.itis.moduls.Course;
import ru.itis.moduls.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.*;
import javax.sql.DataSource;
import java.util.List;


public class CourseRepositoryJdbcTemplateImpl implements CourseRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select s.student_id, * from course c left join student s on s.course_id = c.id order by c.id";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_NAME = "select * from course as c where c.name = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from course as c where c.id = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into course(name, start, ending, teacher_id)" +
            "values (?,?,?,?);";
    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update course set name = ?, start = ?, ending = ?, teacher_id = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE_STUDENT_FROM_COURSE = "delete from student where student.corse = ?";

    //language=SQL
    private static final String SQL_INSERT_STUDENT_ON_COURSE = "insert into student(first_name, last_name, group_number,course_id)" +
            "values (?,?,?,?);";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from course where course.id = ?";

    private JdbcTemplate jdbcTemplate;

    public CourseRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Course> courseRowMapper = (row, rowNumber) -> {
        int id = row.getInt("id");
        String name = row.getString("name");
        String start = row.getString("start");
        String ending = row.getString("ending");
        int teacherId = row.getInt("teacher_id");

        Course course = new Course(id, name, start, ending, teacherId);
        course.setStudents(new ArrayList<>());
        return course;
    };

    private final ResultSetExtractor<List<Course>> coursesResultSetExtractor = resultSet -> {
        List<Course> list = new ArrayList<>();
        Map<Course, List<Student>> map = new LinkedHashMap<>();
        while(resultSet.next()) {
            int courseId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String start = resultSet.getString("start");
            String ending = resultSet.getString("ending");
            int teacherId = resultSet.getInt("teacher_id");
            Course course = new Course(courseId, name, start, ending, teacherId);
            map.putIfAbsent(course, new ArrayList<>());

            int studentId = resultSet.getInt("student_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int groupNumber = resultSet.getInt("group_number");
            Student student = new Student(studentId, firstName, lastName, groupNumber, new ArrayList<>());
            map.get(course).add(student);
        }

        for(Map.Entry<Course, List<Student>> entry : map.entrySet()) {
            // кладем курсу список его студентов
            entry.getKey().setStudents(entry.getValue());
            list.add(entry.getKey());
        }
        return list;
    };

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, coursesResultSetExtractor);
    }

    @Override
    public List<Course> findAllByName(String searchName) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_NAME, courseRowMapper, searchName);
    }

    @Override
    public Optional<Course> findById(Integer id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, courseRowMapper, id)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, course.getNameCourse());
            statement.setString(2, course.getStart());
            statement.setString(3,course.getEnding());
            statement.setInt(4, course.getTeacherId());
            return statement;
        }, keyHolder);

        course.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void updateById(Course course) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID,course.getNameCourse(), course.getStart(),
                course.getEnding(), course.getTeacherId(), course.getId());
        jdbcTemplate.update(SQL_DELETE_STUDENT_FROM_COURSE, course.getId());
        for(Student student : course.getStudents()) {
            jdbcTemplate.update(SQL_INSERT_STUDENT_ON_COURSE, student.getFirstName(),student.getLastName(),student.getGroupNamber(),course.getId());
        }
    }

    @Override
    public void deleteById(Course course) {
        jdbcTemplate.update(SQL_DELETE_STUDENT_FROM_COURSE, course.getId());
        jdbcTemplate.update(SQL_DELETE_BY_ID, course.getId());
    }
}
