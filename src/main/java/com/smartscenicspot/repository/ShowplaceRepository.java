package com.smartscenicspot.repository;

import com.smartscenicspot.domain.Showplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShowplaceRepository extends JpaRepository<Showplace, Long> {

    @Query("SELECT s FROM Showplace s WHERE s.latitude BETWEEN ?1 AND ?2 " +
            "and s.longitude BETWEEN ?3 AND ?4")
    Showplace findByFuzzyPosition(double ltLat, double rtLat, double ltLng, double rtLng);
}