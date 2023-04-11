package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}