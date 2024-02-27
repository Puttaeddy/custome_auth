package com.finastra.users.service.impl;

import com.finastra.users.dao.UserDao;
import com.finastra.users.dto.UserDto;
import com.finastra.users.email.EmailService;
import com.finastra.users.entities.Users;
import com.finastra.users.exception.GraphClientException;
import com.finastra.users.response.UserPagingResponse;
import com.finastra.users.response.UserResponse;
import com.finastra.users.service.UserService;
import com.finastra.users.service.helper.GraphClient;
import com.finastra.users.validation.Validators;
import com.microsoft.graph.http.GraphServiceException;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final EmailService emailService;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final Validators validators;
    private final GraphClient graphClient;

    @Transactional
    @Override
    public UserDto saveUser(UserDto userDto, final HttpServletRequest request) {

        validators.validate(userDto);

        GraphServiceClient graphServiceClient = graphClient.getGraphServiceClient();

        User user = graphClient.createAzureRequest(userDto);

        try {
             graphServiceClient.users().buildRequest().post(user);
             userDto = userDao.save(userDto);

        } catch (GraphServiceException ex) {

            LOGGER.error("ERROR MESSAGE : {} ", ex.getError().error.message);
            throw new GraphClientException(ex.getError().error.message);
        }

         emailService.sendEmailWithHtmlTemplate(userDto, user, "email-template");
        return userDto;
    }

    @Override
    public UserDto modifyUser(UserDto userDto, Integer userId) {
        userDto.setId(userId);
        return userDao.update(userDto, userId);
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.delete(userId);
    }

    @Override
    public UserDto getUserDetails(Integer userId) {
        return userDao.getUser(userId);
    }

    @Transactional
    @Override
    public UserPagingResponse getAllUserDetailsByPage(Integer pageNumber, Integer pageSize, String opId) {
        Page<Users> pageUsers = userDao.getAllUsersByPagination(pageNumber, pageSize, opId);

        List<Users> allusers = pageUsers.getContent();

        List<UserDto> usersDtos = allusers.stream().map(user -> this.modelMapper.map(user, UserDto.class)).toList();
        List<UserResponse> listOfuserResponses = new ArrayList<>();
        for (UserDto dto : usersDtos) {
            UserResponse userResponse = modelMapper.map(dto, UserResponse.class);
            userResponse.setOpId(dto.getFiDetail().getOpId());
            listOfuserResponses.add(userResponse);
        }
        UserPagingResponse postResponse = new UserPagingResponse();

        postResponse.setContent(listOfuserResponses);
        postResponse.setPageNumber(pageUsers.getNumber());
        postResponse.setPageSize(pageUsers.getSize());
        postResponse.setTotalElements(pageUsers.getTotalElements());

        postResponse.setTotalPages(pageUsers.getTotalPages());
        postResponse.setLastPage(pageUsers.isLast());
        return postResponse;
    }

    @Override
    public UserDto searchByEmail(String email) {
        return userDao.searchByEmail(email);
    }
}
