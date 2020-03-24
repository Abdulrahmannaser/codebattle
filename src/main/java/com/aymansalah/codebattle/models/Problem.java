package com.aymansalah.codebattle.models;

import com.aymansalah.codebattle.util.judge.SampleTest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "problems")
public class Problem {
    @Id
    @Column(name = "problem_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    @Column(name = "problem_name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "problem_input")
    private String inputDescription;

    @NotNull
    @NotBlank
    @Column(name = "problem_output")
    private String outputDescription;

    @NotNull
    @NotBlank
    @Column(name = "problem_description")
    private String description;

    @NotNull
    @Column(name = "problem_checker_id")
    private int checkerId;

    @NotNull
    @Column(name = "problem_timer")
    private int timerInSeconds;

    @Column(name = "problem_creation_date")
    private Date creationDate;

    @NotNull
    @Column(name = "problem_score")
    private int score;

    @Column(name = "problem_notes")
    private String notes;

    @Column(name = "problem_tutorial")
    private String tutorial;

    @Column(name = "problem_creator_username")
    private String creatorUsername;

    @OneToMany
    @JoinColumn(name = "author_username")
    private List<Submission> submissions;

    @Transient
    private List<File> io;

    @Transient
    private List<SampleTest> samples;

    @Transient
    private String index;

    // TODO: fix this method it's not working correctly
    public int getACSubmissionsCount() {
        int count = 0;
        for(Submission submission : submissions)
            count += submission.getVerdict().equalsIgnoreCase("OK") ? 1 : 0;
        return count;
    }
}
