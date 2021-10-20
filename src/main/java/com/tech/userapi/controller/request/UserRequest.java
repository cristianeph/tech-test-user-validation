package com.tech.userapi.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Pattern(regexp = "^(?=.*?\\\\d.*\\\\d)(?=\\\\w*[A-Z])(?=\\\\w*[a-z])",
            message = "La contraseña debe tener por lo menos 1 letra mayúscula 1 minúscula y 2 números")
    private String password;
    @Size(min = 1, message = "Debe enviar por lo menos un teléfono")
    @Valid
    private List<PhoneRequest> phones;
}
