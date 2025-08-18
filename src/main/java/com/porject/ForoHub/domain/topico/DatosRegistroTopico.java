package com.porject.ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Boolean status,
        @NotBlank String autor,
        @NotBlank String nombreCurso
) {
}
