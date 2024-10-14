package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        var peliculaDeWigusa = new Pelicula("Avengers", 2019);
        Serie lost = new Serie("Lost", 2000);

        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaDeWigusa);
        lista.add(lost);

        for (Titulo item : lista) {
            System.out.println(item);
            if (item instanceof Pelicula pelicula) {
                System.out.println(pelicula.getClasificacion());
            }
        }
    }
}
