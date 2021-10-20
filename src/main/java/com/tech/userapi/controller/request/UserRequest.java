package com.tech.userapi.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Ingrese un correo válido")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9].*[0-9])",
            message = "La contraseña debe tener por lo menos 1 letra mayúscula 1 minúscula y 2 números")
    private String password;
    @NotBlank(message = "Debe enviar por lo menos un teléfono")
    private List<PhoneRequest> phones;
}
