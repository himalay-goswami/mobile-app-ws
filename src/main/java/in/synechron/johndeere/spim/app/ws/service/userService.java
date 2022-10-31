package in.synechron.johndeere.spim.app.ws.service;

import in.synechron.johndeere.spim.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface userService extends UserDetailsService {

    UserDto createUser(UserDto user);
}
