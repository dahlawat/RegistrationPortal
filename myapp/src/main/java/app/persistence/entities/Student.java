package app.persistence.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
public class Student {

    public Student() {}

    public Student(String name, String registration, LocalDate birthDate, Address address) {
        this.name = name;
        this.registration = registration;
        this.birthDate = birthDate;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String registration;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Embedded
    private Address address;
}
