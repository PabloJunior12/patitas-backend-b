package com.proyecto.patitasbackendb.service;

import com.proyecto.patitasbackendb.dto.LoginRequestDTO;
import com.proyecto.patitasbackendb.dto.LogoutRequestDTO;

import java.io.IOException;
import java.util.Date;

public interface AutenticacionService {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;

    Date cerrarSesionUsuario(LogoutRequestDTO logoutRequestDTO) throws IOException;

}
