package com.powerlogix.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.powerlogix.models.Charger;
import com.powerlogix.models.User;

public interface ChargerRepository extends JpaRepository<Charger,Long>
{
	@Query("SELECT c FROM Charger c ")
	public List<Charger> findAll();

	@Query("SELECT u FROM Charger u WHERE u.id = :id")
    Charger findUserById(@Param("id") Long id);
	
    @Modifying
	@Query("UPDATE Charger u SET u.username = :username, u.idTag = :idTag, u.expiryDate = :expiryDate, u.parentIdTag = :parentIdTag, u.status = :status WHERE u.id = :id")
    Charger updateUser(@Param("id") Long id, @Param("username") String username, @Param("idTag") String idTag, @Param("expiryDate") String expiryDate, @Param("parentIdTag") String parentIdTag, @Param("status") String status);

    @Query("SELECT COUNT(u) > 0 FROM Charger u WHERE u.idTag = :idTag")
	public boolean existsByIdTag(String idTag);

    @Query("SELECT u FROM Charger u WHERE u.idTag = :idTag")
	public Charger getChargerByIdTag(@Param("idTag") String idTag);


}
