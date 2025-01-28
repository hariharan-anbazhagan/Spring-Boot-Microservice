package com.hariSolution.controller;

import com.hariSolution.model.UserDto;
import com.hariSolution.model.UserInfoResponse;
import com.hariSolution.model.UserResponse;
import com.hariSolution.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUserDetails(@RequestBody @Valid UserDto userDto){
        UserResponse userResponse= this.userService.createUserDetails(userDto);
        return ResponseEntity.ok(userResponse);

    }

    @GetMapping("/get-allDetails")
    public ResponseEntity<UserInfoResponse> getAllUserDetails(){
        UserInfoResponse infoResponse=this.userService.getAllUserDetails();
        return ResponseEntity.ok(infoResponse);
    }

    @GetMapping("/get/{user-id}")
    public ResponseEntity<UserInfoResponse> getUserByUserId(@PathVariable(value = "user-id")Integer userId){
        UserInfoResponse response=this.userService.getUserByUserId(userId);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserResponse> updatedUserDetails(@PathVariable(value = "id") Integer id, @RequestBody @Valid UserDto userDto ){
        UserResponse userResponse= this.userService.updateUserDetails(id,userDto);
        return ResponseEntity.ok(userResponse);

    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserResponse>deleteUserByUserId(@PathVariable(value = "userId") Integer userId){
        UserResponse response=this.userService.deleteUserByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-user/{user-id}")
    public UserDto getUserDetailsByUserId(@PathVariable(value = "user-id")Integer userId){
        return this.userService.getUserDetailsByUserId(userId);

    }


}
