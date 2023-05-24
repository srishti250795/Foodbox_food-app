package com.mycompany.fds.model;

import com.mycompany.fds.Controller.CommandePage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CurrentPanier {
    public static LinkedList<String> listeRepasCommande = new LinkedList<String>();
    //public static HashMap<Integer, Integer> ligneCommande = new HashMap<Integer, Integer>();
    public static HashMap<Integer, LinkedList<String>> resultatCommande = new HashMap<Integer, LinkedList<String>>();

    public static  LinkedList<Repas> listeRepas = new LinkedList<Repas>();
    public static HashMap<String,Integer> liggneCom = new HashMap<String, Integer>();
}
