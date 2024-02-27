package com.finastra.users.controller;


import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.users.consts.AppConstants;
import com.finastra.users.dto.RoleDto;
import com.finastra.users.dto.UserDto;
import com.finastra.users.request.UserRequest;
import com.finastra.users.response.UserPagingResponse;
import com.finastra.users.response.UserResponse;
import com.finastra.users.service.UserService;
import com.finastra.users.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor

public class UserController {

    private final UserService userServiceImpl;

    private final ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<UserResponse> registerUserDetails(@Valid @RequestBody UserRequest createUserRequest, final HttpServletRequest request) {
        UserDto dto = userServiceImpl.saveUser(convertRequestDto(createUserRequest), request);
        UserResponse userResponse = convertDtoToResponse(dto);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserDetail(@Valid @RequestBody UserRequest updateUserRequest, @PathVariable("userId") Integer userId) {
        UserDto dto = userServiceImpl.modifyUser(convertRequestDto(updateUserRequest), userId);
        UserResponse userResponse = convertDtoToResponse(dto);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeUserDetail(@PathVariable("userId") Integer userId) {
        userServiceImpl.deleteUser(userId);
        return new ResponseEntity<>(String.format("User deleted successfully for userID %s", userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getAllUserDetail(@PathVariable("userId") Integer userId) {
        UserDto dto = userServiceImpl.getUserDetails(userId);
        UserResponse userResponse = convertDtoToResponse(dto);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<UserPagingResponse> getUserDetailPage(
            @RequestParam(value = "offset", defaultValue = AppConstants.OFFSET, required = false) Integer offset,
            @RequestParam(value = "limit", defaultValue = AppConstants.LIMIT, required = false) Integer limit,
            @RequestParam(value = "opId" ,defaultValue="-1", required = false) String opId) {
        UserPagingResponse listOfUserDto = userServiceImpl.getAllUserDetailsByPage(offset, limit, opId);
        return new ResponseEntity<>(listOfUserDto, HttpStatus.OK);
    }


    @GetMapping("/me")
    public ResponseEntity<UserResponse> searchEmail(@RequestHeader("email") String email) {
        UserDto result = this.userServiceImpl.searchByEmail(email);
        return new ResponseEntity<>(modelMapper.map(result, UserResponse.class), HttpStatus.OK);
    }

    private UserDto convertRequestDto(UserRequest createRequestRequest) {
        UserDto userDto = modelMapper.map(createRequestRequest, UserDto.class);
        RoleDto roleDto = new RoleDto();
        roleDto.setId(Integer.parseInt(createRequestRequest.getRole()));
        userDto.setRole(roleDto);
        FIDetailsDTO fiDetailsDTO = new FIDetailsDTO();
        fiDetailsDTO.setId(Long.parseLong(createRequestRequest.getFiId()));
        userDto.setFiDetail(fiDetailsDTO);
        return userDto;
    }
    @NotNull
    private UserResponse convertDtoToResponse(UserDto dto) {
        UserResponse userResponse= modelMapper.map(dto, UserResponse.class);
        userResponse.setOpId(dto.getFiDetail().getOpId());
        return userResponse;
    }

}
