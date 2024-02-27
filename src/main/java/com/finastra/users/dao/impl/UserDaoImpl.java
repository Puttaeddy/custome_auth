package com.finastra.users.dao.impl;

import com.finastra.fi.dao.entity.FIDetails;
import com.finastra.fi.respository.FIRepository;
import com.finastra.users.dao.UserDao;
import com.finastra.users.dto.UserDto;
import com.finastra.users.entities.Role;
import com.finastra.users.entities.Users;
import com.finastra.users.exception.ResourceNotFoundException;
import com.finastra.users.repository.RoleRepo;
import com.finastra.users.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDaoImpl implements UserDao {
    private final ModelMapper modelMapper;
    private final  RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final FIRepository fiRepository;



    @Override
    public UserDto save(UserDto userDto) {
        Users user = userRepo.save(convertDtotToEntity(userDto));
        Role role = roleRepo.findById(userDto.getRole().getId()).get();
       FIDetails detail = fiRepository.findById(userDto.getFiDetail().getId()).get();
        user.setRole(role);
        user.setFiDetail(detail);
        return convertEntityToDto(user);
    }

    @Override
    public UserDto update(UserDto userDto, Integer userId) {
        userRepo.findById(userId.longValue()).orElseThrow(() -> new ResourceNotFoundException("User", " user_Id ", userId.toString()));
        Users user = userRepo.save(convertDtotToEntity(userDto));
        userDto = convertEntityToDto(user);
        return userDto;
    }

    @Override
    public void delete(Integer userId) {
        Optional<Users> user = Optional.ofNullable(userRepo.findById(userId.longValue()).orElseThrow(() -> new ResourceNotFoundException("User", " userId ", userId.toString())));

        userRepo.delete(user.get());

    }

    @Override
    public UserDto getUser(Integer userId) {
        Optional.ofNullable(userRepo.findById(userId.longValue()).orElseThrow(() -> new ResourceNotFoundException("User", " userId ", userId.toString())));
        Users user = userRepo.findById(userId.longValue()).get();
        return convertEntityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> dto = new ArrayList<>();
        List<Users> listOfUsers = userRepo.findAll();
        listOfUsers.stream().forEach(user -> dto.add(convertEntityToDto(user)));
        return dto;
    }

    @Override
    public Page<Users> getAllUsersByPagination(Integer pageNumber, Integer pageSize, String opId) {

        Sort sort = Sort.by("user_id").descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        if(opId!=null) {
            if (opId.equals("all")) {
                return this.userRepo.findAllFiUsers (p);
            }
        }
        return this.userRepo.findAllFiUsers (Long.valueOf(opId),p);
    }


    @Override
    public UserDto searchByEmail(String email) {
        Optional<Users> users= userRepo.findByEmail(email);
        if( !users.isPresent())
        {  throw  new ResourceNotFoundException("User","email",email);
        }
        return convertEntityToDto(users.get());
    }


    private Users convertDtotToEntity(UserDto userDto) {
        return modelMapper.map(userDto, Users.class);
    }

    private UserDto convertEntityToDto(Users userentity) {

        return modelMapper.map(userentity, UserDto.class);
    }

}
