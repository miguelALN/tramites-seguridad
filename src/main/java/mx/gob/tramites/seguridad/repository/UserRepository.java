package mx.gob.tramites.seguridad.repository;

import mx.gob.tramites.seguridad.entity.usuario.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String userName);

    @Query("select u from UserEntity u where u.username= ?1")
    Optional<UserEntity> getName(String userName);

    Optional<UserEntity> findByEmail(String email);
}
