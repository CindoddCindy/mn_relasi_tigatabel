package steamdom.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assesment_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Assesment assesment;
    private Long assesment_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_semester", referencedColumnName = "id", insertable=false, updatable=false)
    private Semester semester;
    private Long id_semester;

    @ManyToOne(optional = false)
    @JoinColumn(name = "standart_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Standart standart;
    private Long standart_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_teacher", referencedColumnName = "id", insertable = false, updatable = false)
    private Teacher teacher;
    private Long id_teacher;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_student", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;
    private Long id_student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_type_id", referencedColumnName = "id", insertable=false, updatable=false)
    private CourseType coursetype;
    private Long course_type_id;

    @NotNull(message = "Nama kurikulum harus diisi.")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Tahun kurikulum harus diisi.")
    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at", nullable = true)
    private Date deleted_at;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public Semester getSemester() {
    // return semester;
    // }

    // public void setSemester(Semester semester) {
    // this.semester = semester;
    // }

    // public Long getSemester_id() {
    // return id_semester;
    // }

    // public void setSemester_id(Long id_semester) {
    // this.id_semester = id_semester;
    // }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getStudent_id() {
        return id_student;
    }

    public void setStudent_id(Long id_student) {
        this.id_student = id_student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getTeacher_id() {
        return id_teacher;
    }

    public void setTeacher_id(Long id_teacher) {
        this.id_teacher = id_teacher;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    // public Long getId_semester() {
    // return id_semester;
    // }

    // public void setId_semester(Long id_semester) {
    // this.id_semester = id_semester;
    // }

    public Standart getStandart() {
        return standart;
    }

    public void setStandart(Standart standart) {
        this.standart = standart;
    }

    public Long getStandart_id() {
        return standart_id;
    }

    public void setStandart_id(Long standart_id) {
        this.standart_id = standart_id;
    }

    public Long getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Long id_teacher) {
        this.id_teacher = id_teacher;
    }

    public Long getId_student() {
        return id_student;
    }

    public void setId_student(Long id_student) {
        this.id_student = id_student;
    }

    public Assesment getAssesment() {
        return assesment;
    }

    public void setAssesment(Assesment assesment) {
        this.assesment = assesment;
    }

    public Long getAssesment_id() {
        return assesment_id;
    }

    public void setAssesment_id(Long assesment_id) {
        this.assesment_id = assesment_id;
    }

    public CourseType getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(CourseType coursetype) {
        this.coursetype = coursetype;
    }

    public Long getCourse_type_id() {
        return course_type_id;
    }

    public void setCourse_type_id(Long course_type_id) {
        this.course_type_id = course_type_id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Long getId_semester() {
        return id_semester;
    }

    public void setId_semester(Long id_semester) {
        this.id_semester = id_semester;
    }

    // public Assesment getAssesment() {
    // return assesment;
    // }

    // public void setAssesment(Assesment assesment) {
    // this.assesment = assesment;
    // }
    // public Long getAssesnebt_id() {
    // return assesment_id;
    // }

    // public void setAssesnebt_id(Long assesment_id) {
    // this.assesment_id = assesment_id;
    // }

}