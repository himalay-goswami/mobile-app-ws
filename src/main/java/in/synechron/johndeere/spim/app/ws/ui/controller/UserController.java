package in.synechron.johndeere.spim.app.ws.ui.controller;

import in.synechron.johndeere.spim.app.ws.service.impl.UserServiceImpl;
import in.synechron.johndeere.spim.app.ws.shared.dto.UserDto;
import in.synechron.johndeere.spim.app.ws.ui.model.request.UserDetailsRequestModel;
import in.synechron.johndeere.spim.app.ws.ui.model.response.UserRest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public String getUser(){
        return "get user was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel){

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel, userDto);
        UserDto createdUser  = userService.createUser(userDto);

        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(createdUser, returnValue);

        System.out.println(returnValue.getFirstName() + " " + returnValue.getLastName());

        return returnValue;
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }



}
