package in.synechron.johndeere.spim.app.ws;

import in.synechron.johndeere.spim.app.ws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//CRUD repository automatically provides ready to use methods for CRUD operations

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);


}
