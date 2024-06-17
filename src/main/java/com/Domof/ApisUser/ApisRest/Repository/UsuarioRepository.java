package com.Domof.ApisUser.ApisRest.Repository;

import com.Domof.ApisUser.ApisRest.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

}
