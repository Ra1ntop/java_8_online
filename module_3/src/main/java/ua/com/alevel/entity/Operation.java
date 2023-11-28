package ua.com.alevel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "operations")
public class Operation extends BaseEntity {

    @Column(name = "date_time")
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account account1;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account account2;

    @Column(name = "amount")
    private Long sum;
}
