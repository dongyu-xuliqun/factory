package com.cdf.factory.consumer.repository;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cdf.factory.consumer.entity.OrderDO;

public interface OrderRepository extends JpaRepository<OrderDO, Long>{

	Page<OrderDO> findAll(Pageable pageable);
	
	Page<OrderDO> findAllByStatus(Integer status, Pageable pageable);

	Optional<OrderDO> findByIdAndStatus(long id, int status);
	
	@Query("update OrderDO set status=?1, update_time=?2 where id=?3")
	@Modifying(clearAutomatically = true)
	int updateStatusById(int status, LocalDateTime dateTime, Long id);
}
