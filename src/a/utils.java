package a;

import com.google.common.base.Joiner;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Maxence on 25/02/14.
 */

public class utils {
    private ArrayList<String> alphabet ;

    public utils() {
        this.alphabet = StringToList("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    public String decallage(int n,String lettre){
        char theChar = lettre.charAt(0);
        int ascii = (int) theChar;
        if((ascii==39)||(ascii==145)||(ascii==146)){
            return "#";
        }
        if((ascii<=57)&&(ascii>=39)){
            return "";
        }
        if(ascii>122){
            // Special char
            // Conditions non optimisées !
            if((ascii>=192)&&(ascii<=197)){
                return "A";
            }else if(ascii==199){
                return "C";
            }else if((ascii>=200)&&(ascii<=203)){
                return "E";
            }else if((ascii>=204)&&(ascii<=207)){
                return "I";
            }else if((ascii>=208)&&(ascii<=214)){
                return "O";
            }else if((ascii>=217)&&(ascii<=220)){
                return "U";
            }else{
                // Caractère inconnu
                return "#";
            }
        }
        int index = this.alphabet.indexOf(lettre);
        int newIndex = setIndexToValable(n,index);
        String newLetter = this.alphabet.get(newIndex);

        return newLetter;
    }

    public ArrayList<String> StringToList(String s)
    {
        ArrayList<String> convertedString = new ArrayList<String>();

        String character;

        for(int i = 0; i < s.length(); i++){
            character = ""+s.charAt(i);
            convertedString.add(character);
        }

        return convertedString;
    }
    public int setNToValable(int n){
        while(n>=25){
            n = n-25;
        }
        return n;
    }
    public int setIndexToValable(int n,int index){
        int somme =n+index;
        if(somme>=26){
            somme = somme-26;
        }
        return somme;
    }

    public String encodage(String mae,int n){
        ArrayList<String> nonEncodedData = new ArrayList<String>();
        mae = mae.toUpperCase();
        System.out.println(mae);
        mae = mae.replace(" ","");
        System.out.println("Encodage...");
        // fill the array
        ArrayList<String> tmae =StringToList(mae);
        for(int i=0;i<tmae.size();i++){
            nonEncodedData.add(decallage(n, tmae.get(i)));
        }
        Joiner joiner = Joiner.on("");
        String finalString = joiner.join(nonEncodedData);
        return finalString;
    }

    public String decodage(String mad){
        mad = mad.toUpperCase();
        ArrayList<String> finalListe = new ArrayList<String>();
        // fill an array with the message
        ArrayList<String> tmad =StringToList(mad);
        for(int i=1;i<=25;i++){
            ArrayList<String> tempKey= new ArrayList<String>();
            for(int j=0;j<tmad.size();j++){
                tempKey.add(decallage(i, tmad.get(j)));
            }
            Joiner joiner = Joiner.on("");
            String possibility = joiner.join(tempKey);
            //System.out.println(possibility);

        finalListe.add(possibility);
        }
        // OK maintenant choix des possibilités

           /*
        for(int k =0;k<finalListe.size();k++){
            System.out.println(finalListe.get(k));
        }*/
        //System.out.println("-----------------------------------");
        String solution = stringAnalyst("E",finalListe);
        return solution;

    }

    public String stringAnalyst(String stringToFind,ArrayList<String> basicList ){
        int basicCount = 0;
        ArrayList<Integer> countList = new ArrayList<Integer>();
        for(int i=0;i<basicList.size();i++){
           countList.add(getIteration(stringToFind,basicList.get(i)));
           //System.out.println(countList.);
        }

        int indexOfMax= countList.indexOf(maxTableau(countList));

        int indexOfSolution = indexOfMax;
        return basicList.get(indexOfSolution);
    }

    public static int maxTableau(ArrayList<Integer> tableau) {
        if (tableau.size() == 0) {
            throw new IllegalArgumentException("Un tableau vide n'a pas de maximum");
        }
        int max = tableau.get(0);
        for (int i = 1; i < tableau.size(); i++) {
            if (tableau.get(i) > max) {
                max = tableau.get(i);
            }
        }
        return max;
    }

    public int getIteration(String character,String string){
        int count = string.length() - string.replace(character,"").length(); // Optimisation pour limiter les traitements
        return count;
    }

    public String lireFichier(String path) throws FileNotFoundException {
        ArrayList<String> stringList = new ArrayList<String>();
        try{
            InputStream flux = new FileInputStream(path);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            while((ligne=buff.readLine())!=null){
                stringList.add(ligne);
            }
            buff.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        Joiner joiner =  Joiner.on("");
        String finalString = joiner.join(stringList);
        return finalString;
    }
    public void ecireFichier(String path,String string){
        try{
            FileWriter writer = new FileWriter(path,true);
            BufferedWriter output = new BufferedWriter(writer);
            output.write(string);
            output.flush();
            output.close();
            System.out.println("Fichier crée avec succès...");
        }catch (IOException e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }


}
