package com.finastra.users.dao;

import com.finastra.users.dto.UserDto;
import com.finastra.users.entities.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserDao {
    public UserDto save(UserDto userDto);
    public UserDto update(UserDto userDto,Integer userId);

    public void delete(Integer userId);

    public UserDto getUser(Integer userId);

    public List<UserDto> getAllUsers();
    public Page<Users> getAllUsersByPagination(Integer pageNumber, Integer pageSize,String opId);


    public UserDto searchByEmail(String email);
}
