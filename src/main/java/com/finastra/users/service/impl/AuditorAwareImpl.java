package com.finastra.users.service.impl;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
   public Optional<String>getCurrentAuditor(){
    /*String username=SecurityContextHolder.getContext().getAuthentication().getName();
      return Optional.of(username);*/
  return Optional.of("Manoshri");
   }

}
