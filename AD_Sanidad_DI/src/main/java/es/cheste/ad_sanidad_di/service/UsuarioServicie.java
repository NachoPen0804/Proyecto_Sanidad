package es.cheste.ad_sanidad_di.service;

import es.cheste.ad_sanidad_di.model.Usuario;
import es.cheste.ad_sanidad_di.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicie {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario findByNombre(String nombre) {
		return usuarioRepository.findById(nombre).orElse(null);
	}
}
