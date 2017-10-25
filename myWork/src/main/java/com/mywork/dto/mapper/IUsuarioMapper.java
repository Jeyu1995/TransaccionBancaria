package com.mywork.dto.mapper;

import com.mywork.model.Usuario;
import com.mywork.model.dto.UsuarioDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IUsuarioMapper {
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario)
        throws Exception;

    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO)
        throws Exception;

    public List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> usuarios)
        throws Exception;

    public List<Usuario> listUsuarioDTOToListUsuario(
        List<UsuarioDTO> usuarioDTOs) throws Exception;
}
