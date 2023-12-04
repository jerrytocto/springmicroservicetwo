package com.jerrydev.bikemcsv.repository;

import com.jerrydev.bikemcsv.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike,Integer> {

    List<Bike> findByUserId(int userId);
}
