package com.jerrydev.carmcsv.repository;

import com.jerrydev.carmcsv.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT * FROM tb_car WHERE user_id = :userId",
            nativeQuery = true)
    List<Car> carsByUserId(@Param("userId") int userId);

    List<Car> findByUserId(int userId);
}
