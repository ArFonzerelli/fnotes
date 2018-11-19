package ru.fonzy.fnotes.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;

import javax.annotation.PostConstruct;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
