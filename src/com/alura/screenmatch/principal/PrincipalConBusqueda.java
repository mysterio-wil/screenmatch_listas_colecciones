package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Escriba el nombre de una película: ");
        var busqueda = lectura.nextLine();

        String direccion = "https://www.omdbapi.com/?t="+busqueda+"&apikey=66d123fa";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println("Respuesta JSON: " + json);

            if (json == null || json.isEmpty()) {
                System.out.println("La respuesta JSON está vacía o es nula.");
            }

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println("Datos deserializados: " + miTituloOmdb);

            Titulo miTitulo = new Titulo(miTituloOmdb);
            System.out.println("Título ya convertido: " + miTitulo);

        } catch (NumberFormatException e) {
            System.out.println("Ocurrió un error de formato numérico: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la URI: " + direccion);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al enviar o recibir datos: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error: La operación fue interrumpida.");
            Thread.currentThread().interrupt();  // Restablecer el estado de interrupción
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace para ayudar a depurar
        }
        System.out.println("Finalizó la ejecución del programa!");

    }
}
