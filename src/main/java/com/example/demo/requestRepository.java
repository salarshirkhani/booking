package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface requestRepository extends CrudRepository<request, Long> {
    List<request> findByUserId(String userId);
    List<request> findByGuestId(String guestId);
    @Transactional
    void deleteByIdr(String idr);
    @Transactional
    void deleteByUserIdAndGuestId(String userId, String guestId);

}
