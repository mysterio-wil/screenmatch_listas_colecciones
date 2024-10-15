package com.alura.screenmatch.principal;

import com.alura.screenmatch.excepcion.ErrorDeConversionDeDuracionException;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true) {
            System.out.println("Escriba el nombre de una película: ");
            var busqueda = lectura.nextLine();

            if (busqueda.equalsIgnoreCase("salir")){
                break;
            }

            String direccion = "https://www.omdbapi.com/?t="+
                    busqueda.replace(" ", "+") +
                    "&apikey=66d123fa";

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

                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println("Datos deserializados: " + miTituloOmdb);

                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Título ya convertido: " + miTitulo);

                titulos.add(miTitulo);
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
            } catch (ErrorDeConversionDeDuracionException e) {
                System.out.println(e.getMessage());
                e.printStackTrace(); // Imprime el stack trace para ayudar a depurar
            }
        }
        System.out.println(titulos);

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();
        System.out.println("Finalizó la ejecución del programa!");

    }
}
