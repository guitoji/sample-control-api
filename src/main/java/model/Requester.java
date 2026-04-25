package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "requester")
@Getter
@Setter
@ToString
public class Requester {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "department")
    private String department;

    @Column(name = "build")
    private Integer build;

    @OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
    private List<Sample> samples;
}
