package com.finastra.users.service;

import com.finastra.users.dto.UserDto;
import com.finastra.users.response.UserPagingResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    public UserDto saveUser(UserDto userDto, final HttpServletRequest request);

    public UserDto modifyUser(UserDto userDto, Integer userId);

    public void deleteUser(Integer userId);
    public UserDto getUserDetails(Integer userId);

    public UserPagingResponse getAllUserDetailsByPage(Integer pageNumber, Integer pageSize,String opId);

    UserDto searchByEmail(String email);
}
