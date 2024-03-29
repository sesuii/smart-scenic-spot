package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.Attraction;
import io.lettuce.core.dynamic.annotation.Param;
import org.hibernate.annotations.OrderBy;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.List;

/**
 * @author jiahui
 */
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update Attraction a set a.status = ?1 where a.id = ?2")
    void updateStatusById(Byte status, Long id);

    @Query(value = "select * from tb_attraction a order by calcDistance(row(?1, ?2)\\:\\:location, " +
            "row(a.latitude, a.longitude)\\:\\:location) ASC LIMIT 1", nativeQuery = true)
    Attraction findNearestAttraction(Double lat, Double lng);

    List<Attraction> findByIdIn(List<Long> ids);
}