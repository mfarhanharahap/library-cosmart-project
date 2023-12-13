package com.example.librarycosmartproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "edition_number")
    private String editionNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "available_to_borrow")
    private Boolean availableToBorrow;

    @Column(name = "last_loan_date")
    private Date lastLoanDate;

    @Column(name = "publish_year")
    private Date publishYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
}
