package ru.alishev.springcourse.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
