package es.cheste.ad_sanidad_di.repository;

import es.cheste.ad_sanidad_di.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	Usuario findByNombreAndContraseña(String nombre, String contraseña);

}
