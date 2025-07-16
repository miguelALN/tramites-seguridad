package mx.gob.tramites.seguridad.repository;

import mx.gob.tramites.seguridad.entity.usuario.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
