package net.javaguides.banking.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Account_holder_name")
    private String accountHolderName;
    private double balance;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
