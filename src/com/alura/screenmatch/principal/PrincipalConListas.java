package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.evalua(6);
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.evalua(10);
        var peliculaDeWigusa = new Pelicula("Avengers", 2019);
        peliculaDeWigusa.evalua(10);
        Serie lost = new Serie("Lost", 2000);

        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaDeWigusa);
        lista.add(lost);

        for (Titulo item : lista) {
            System.out.println(item);
            if (item instanceof Pelicula pelicula && pelicula.getClasificacion() < 2){
                System.out.println("Clasificación: " + pelicula.getClasificacion());
            }
        }

        ArrayList<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Penélope Cruz");
        listaDeArtistas.add("Antonio Banderas");
        listaDeArtistas.add("Ricardo Darin");
        System.out.println("Lista de artistas no ordenada: " + listaDeArtistas);

        Collections.sort(listaDeArtistas);
        System.out.println("Lista de artistas ordenada: " + listaDeArtistas);

        Collections.sort(lista);
        System.out.println("Lista de títulos ordenados: " + lista);
    }
}
