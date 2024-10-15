package com.alura.screenmatch.excepcion;

public class ErrorDeConversionDeDuracionException extends RuntimeException {
    private String mensaje;

    public ErrorDeConversionDeDuracionException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}

