package es.cheste.ad_sanidad_di.controllerSpring;

import es.cheste.ad_sanidad_di.model.Usuario;
import es.cheste.ad_sanidad_di.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	private final UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@PostMapping
	public Usuario createUsuario(@RequestBody Usuario nuevo) {
		return usuarioRepository.save(nuevo);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
		return usuarioRepository.findById(String.valueOf(id))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
