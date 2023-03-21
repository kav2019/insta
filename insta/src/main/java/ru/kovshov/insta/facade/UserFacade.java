package ru.kovshov.insta.facade;


import org.springframework.stereotype.Component;
import ru.kovshov.insta.dto.UserDTO;
import ru.kovshov.insta.model.User;

@Component
public class UserFacade {
    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
}
