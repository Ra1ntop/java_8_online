package ua.com.alevel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "amount")
    private Long sum;

    @OneToMany(mappedBy = "account1", cascade =  CascadeType.REMOVE)
    private Set<Operation> outgoingOperations;

    @OneToMany(mappedBy = "account2", cascade = CascadeType.REMOVE)
    private Set<Operation> incomingOperations;

    public Account() {
        this.outgoingOperations = new HashSet<>();
        this.incomingOperations = new HashSet<>();
    }
}
