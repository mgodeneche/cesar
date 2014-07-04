package a;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by Maxence on 25/02/14.
 */
public class main {
    public static void main(String[] args) throws FileNotFoundException {
        utils Utils = new utils();
        System.out.println("Ave Caesar !");
        System.out.println("Voulez vous crypter ou décrypter un message ?");
        System.out.println("1- ENCODER");
        System.out.println("2- DECODER");
        Scanner sc = new Scanner(System.in);
        String userAnswer = sc.next();
        //sc.close();
        if(userAnswer.equals("1")){
            System.out.println("1- Encoder du texte brut");
            System.out.println("2- Encoder du texte brut et écrire dans un fichier");
            System.out.println("3- Encoder à partir d'un fichier");
            System.out.println("4- Encoder à partir d'un fichier et écrire dans un fichier");
            //Scanner sc2 = new Scanner(System.in);
            String userAnswer2 = sc.next();
            //sc2.close();
            //Scanner sc3 = new Scanner(System.in);
            System.out.println("Donnez un entier pour le décallage");
            int n = sc.nextInt();
            //sc3.close();
            n=Utils.setNToValable(n);
            if(userAnswer2.equals("1")||userAnswer2.equals("2")){
                //Scanner sc4 = new Scanner(System.in);
                sc.nextLine();
                System.out.println("Très bien , donnez maintenant une chaine de caractère...");

                String mae = sc.nextLine();
                //sc4.close();
                String encoded=Utils.encodage(mae,n);
                if(userAnswer2.equals("2")){
                    //Scanner sc5 = new Scanner(System.in);
                    System.out.println("Spécifiez le chemin...");
                    String path = sc.next();
                    //sc5.close();
                    Utils.ecireFichier(path,encoded);
                }else{
                    System.out.println(encoded);
                }
            }else{
                //Scanner sc6 = new Scanner(System.in);
                sc.nextLine();
                System.out.println("Spécifiez le chemin d'accès...");
                String path = sc.nextLine();
                //sc6.close();
                String mae = Utils.lireFichier(path);
                String encoded = Utils.encodage(mae,n);
                if(userAnswer2.equals("4")){
                    //Scanner sc7 = new Scanner(System.in);
                    System.out.println("Spécificiez le chemin du fichier à éditer");
                    String targetPath = sc.nextLine();
                    //sc7.close();
                    Utils.ecireFichier(targetPath,encoded);
                }else{
                    System.out.println(encoded);
                }

            }
        }else if(userAnswer.equals("2")){
            System.out.println("1- Décoder du texte brut");
            System.out.println("2- Décoder du texte brut et écrire dans un fichier");
            System.out.println("3- Décoder à partir d'un fichier");
            System.out.println("4- Décoder à partir d'un fichier et écrire dans un fichier");
            //Scanner sc2 = new Scanner(System.in);
            String userAnswer2 = sc.next();
            //sc2.close();
            if(userAnswer2.equals("1")||(userAnswer2.equals("2"))){
                //Scanner sc3 = new Scanner(System.in);
                sc.nextLine();
                System.out.println("Donnez la chaine de caractère à décoder");
                String mad = sc.nextLine();
                //sc3.close();
                String decoded = Utils.decodage(mad);
                if(userAnswer2.equals("2")){
                    sc.nextLine();
                    //Scanner sc4 = new Scanner(System.in);
                    System.out.println("Spécifiez le chemin d'accès...");
                    String path = sc.nextLine();
                    //sc4.close();
                    Utils.ecireFichier(path,decoded);
                }else{
                    System.out.println(decoded);
                }
            }else{
                //Scanner sc3 = new Scanner(System.in);
                sc.nextLine();
                System.out.println("Spécifiez le chemin d'accès...");
                String path = sc.nextLine();
                //sc3.close();
                String mad = Utils.lireFichier(path);
                String decoded = Utils.decodage(mad);
                if(userAnswer2.equals("4")){
                    sc.nextLine();
                    //Scanner sc4 = new Scanner(System.in);
                    System.out.println("Spécificiez le chemin du fichier à éditer");
                    String targetPath = sc.nextLine();
                    //sc4.close();
                    Utils.ecireFichier(targetPath,decoded);
                }else{
                    System.out.println(decoded);
                }
            }

        }else{
            System.out.println("Je n'ai pas compris !");
        }






    }
}
