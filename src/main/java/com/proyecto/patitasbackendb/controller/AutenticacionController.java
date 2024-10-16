package com.proyecto.patitasbackendb.controller;

import com.proyecto.patitasbackendb.dto.LoginRequestDTO;
import com.proyecto.patitasbackendb.dto.LoginResponseDTO;
import com.proyecto.patitasbackendb.dto.LogoutRequestDTO;
import com.proyecto.patitasbackendb.dto.LogoutResponseDTO;
import com.proyecto.patitasbackendb.service.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){

        try {

            Thread.sleep(Duration.ofSeconds(5));
            String[] datosUsuario = autenticacionService.validarUsuario(loginRequestDTO);
            System.out.println("Respuesta backend: " + Arrays.toString(datosUsuario));

            if (datosUsuario == null) {
                return new LoginResponseDTO("01", "Error: Usuario no encontrado", "", "");
            }
            return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1]);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return new LoginResponseDTO("99", "Error: Ocurrió un problema", "", "");

        }

    }

    @PostMapping("/logout")
    public LogoutResponseDTO logoutd(@RequestBody LogoutRequestDTO logoutRequestDTO) {

        try {

            Thread.sleep(Duration.ofSeconds(5));
            Date fechaLogout = autenticacionService.cerrarSesionUsuario(logoutRequestDTO);
            System.out.println("Respuesta backend: " + fechaLogout);

            if (fechaLogout == null) {
                return new LogoutResponseDTO(false, null, "Error: No se pudo registrar auditoría");
            }
            return new LogoutResponseDTO(true, fechaLogout, "");

        } catch(Exception e) {

            System.out.println(e.getMessage());
            return new LogoutResponseDTO(false, null, "Error: Ocurrió un problema");

        }

    }



}
