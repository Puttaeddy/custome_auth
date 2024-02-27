package com.finastra.users.repository;

import com.finastra.users.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    public Optional<Users> findByEmail(String email);
    @Query(value = "SELECT u.* FROM public.users u join public.fi_details fi on fi.id=u.fi_id where u.fi_id =?1", nativeQuery = true)
   public Page<Users> findAllFiUsers (Long op,Pageable pageable);
    @Query(value = "SELECT u.* FROM public.users u join public.fi_details fi on fi.id=u.fi_id where u.fi_id <>-1", nativeQuery = true)
    public Page<Users> findAllFiUsers (Pageable pageable);








}
