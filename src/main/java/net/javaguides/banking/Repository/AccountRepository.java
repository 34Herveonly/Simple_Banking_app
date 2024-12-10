package net.javaguides.banking.Repository;

import net.javaguides.banking.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
