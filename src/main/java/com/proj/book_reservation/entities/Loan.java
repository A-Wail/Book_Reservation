package com.proj.book_reservation.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name ="book_id",nullable = false )
    private Book book;

    @CreationTimestamp
    private LocalDateTime borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Status status=Status.ACTIVE;
    public enum Status{ ACTIVE,
                        RETURNED,
                        OVERDUE}
}
