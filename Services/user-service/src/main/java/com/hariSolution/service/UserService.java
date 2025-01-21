package com.hariSolution.service;

import com.hariSolution.Mapper.UserInfoResponseMapper;
import com.hariSolution.Mapper.UserMapper;
import com.hariSolution.Mapper.UserResponseMapper;
import com.hariSolution.model.User;
import com.hariSolution.model.UserDto;
import com.hariSolution.model.UserInfoResponse;
import com.hariSolution.model.UserResponse;
import com.hariSolution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseService;
    private final UserInfoResponseMapper infoService;

    public UserResponse createUserDetails(UserDto userDto) {
        User user = this.userRepository.save(userMapper.toEntity(userDto));
        UserDto Dto = this.userMapper.toDto(user);

        return this.userResponseService.createResponse(
                "User details successfully created",
                "Dear " + userDto.getFullName() + ", your account has been successfully activated.",
                HttpStatus.OK
        );

    }

    public UserInfoResponse getAllUserDetails() {

        List<User> userList = this.userRepository.findAll();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        userList.forEach(user -> {
            UserDto userDto = this.userMapper.toDto(user);
            data.put("UserDetails: " + user.getFullName(), userDto);

        });

        if (data.isEmpty()) {
            return this.infoService.createResponse(
                    data,
                    "No data found for the given query.",
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND
            );
        }
        return this.infoService.createResponse(
                data,
                "Data successfully retrieved from the database.",
                HttpStatus.OK.value(),
                HttpStatus.OK
        );

    }

    public UserResponse updateUserDetails(Integer id, UserDto userDto) {

        User user = this.userRepository.findByUserId(id);
        margeUserDetails(user, userDto);
        this.userRepository.save(user);

        UserDto Dto = this.userMapper.toDto(user);

        return this.userResponseService.createResponse(
                "User details successfully updated",
                "Hello " + userDto.getFullName() + ", your account information has been updated successfully.",
                HttpStatus.OK
        );


    }

    public void margeUserDetails(User user, UserDto userDto) {
        if (StringUtils.isNotBlank(userDto.getFirstName())) {
            user.setFirstName(userDto.getFirstName());
        }
        if (StringUtils.isNotBlank(userDto.getFullName())) {
            user.setFullName(userDto.getFullName());
        }
        if (StringUtils.isNotBlank(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        if (StringUtils.isNotBlank(userDto.getMobileNumber())) {
            user.setMobileNumber(userDto.getMobileNumber());
        }
        if (userDto.getAddress() != null) {
            user.setAddress(userDto.getAddress());
        }
    }


    public UserInfoResponse getUserByUserId(Integer userId) {
        User user = this.userRepository.findByUserId(userId);
        var userDto = this.userMapper.toDto(user);

        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("UserId:" + userDto.getUserId(), userDto);

        if (data.isEmpty()) {
            return this.infoService.createResponse(
                    data,
                    "No data found for the given query.",
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND
            );
        } else {
            return this.infoService.createResponse(
                    data,
                    "Data successfully retrieved from the database.",
                    HttpStatus.OK.value(),
                    HttpStatus.OK
            );
        }
    }

    public UserResponse deleteUserByUserId(Integer userId) {
        User user = this.userRepository.findByUserId(userId);

        if (user != null) {
            this.userRepository.delete(user);
            return this.userResponseService.createResponse(
                    "User details successfully deleted",
                    "Hello " + user.getFullName() + ", your account has been successfully deleted.",
                    HttpStatus.OK
            );
        } else {
            return this.userResponseService.createResponse(
                    "User not found",
                    "The account you are trying to delete does not exist.",
                    HttpStatus.NOT_FOUND
            );
        }


    }
}
