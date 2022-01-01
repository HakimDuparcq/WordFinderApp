package com.example.androidprojet.ui.App.API;

import java.util.List;

//classe permettant de récupérer correctement les informations
public class DictionaryAPI {
    public String word; // on prend le nom exact des variables qui sont données par l'api
    public List<Phonetics> phonetics; // on définit une classe à chaque fois que l'on a un objet différent entouré par {}
    public List<Meanings> meanings; // à chaque fois qu'on a une liste d'objets entouré par [] on met List

    public DictionaryAPI(){};


    public class Phonetics {
        public String text;
        public String audio;

        public Phonetics(){};
    }

    public class Meanings {
        public String partOfSpeech;
        public List<Definitions> definitions;


        public Meanings(){};
    }

    public class Definitions {
        public String definition;
        public String example;
        public List<String> synonyms;


        public Definitions(){};
    }

}
