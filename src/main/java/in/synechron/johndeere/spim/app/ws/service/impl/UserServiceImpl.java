package in.synechron.johndeere.spim.app.ws.service.impl;

import in.synechron.johndeere.spim.app.ws.UserRepository;
import in.synechron.johndeere.spim.app.ws.io.entity.UserEntity;
import in.synechron.johndeere.spim.app.ws.service.userService;
import in.synechron.johndeere.spim.app.ws.shared.dto.UserDto;
import in.synechron.johndeere.spim.app.ws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements userService {

    final
    UserRepository userRepository;

    final
    Utils utils;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto user) {

        //copy the values from UserDto object to userEntity object

        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new RuntimeException("Record Already Exists.");
        }

        UserEntity userEntity  = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        //save the values to the database. MAke use of UserRepository
        //generate userID using Utils we created.

        String publicUserId = utils.generateUserId(30);

        userEntity.setUserID(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnedValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnedValue);

        return returnedValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity==null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
