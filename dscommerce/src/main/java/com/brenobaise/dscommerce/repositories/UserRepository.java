package com.brenobaise.dscommerce.repositories;

import com.brenobaise.dscommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
			SELECT tbl_user.email AS username, tbl_user.password, tbl_role.id AS roleId, tbl_role.authority
			FROM tbl_user
			INNER JOIN tbl_user_role ON tbl_user.id = tbl_user_role.user_id
			INNER JOIN tbl_role ON tbl_role.id = tbl_user_role.role_id
			WHERE tbl_user.email = :email
		""")
    List<UserDetailsProjection> searchByUserAndRolesByEmail(String email);

    Optional<User> findByEmail(String email);

}
