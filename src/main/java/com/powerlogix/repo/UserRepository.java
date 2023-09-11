package com.powerlogix.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.powerlogix.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("Select u from User u where u.email = :email")
	public User findByUsername(@Param("email") String email);
	
    @Query("SELECT c FROM User c WHERE c.email = :email AND c.password = :password")
    User findByUsernameAndPassword(@Param("email") String email, @Param("password") String password);

}
