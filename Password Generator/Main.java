import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final String lowerCases = "abcdefghijklmnopqrstuvwxyz";
    public static final String upperCases = lowerCases.toUpperCase();
    public static final String numbers = "0123456789";
    public static final String symbols = "!@#$%^&*(){}<>?";

    public static final String BlueColor = "\u001B[34m";
    public static final String Reset = "\u001B[0m";

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);
        Random random = new Random();

        while(true){

        System.out.println("======"+BlueColor+" Password"+Reset+" Generator ======");
        System.out.println();
        System.out.print("Enter Desired Password Length: ");
        int passwordLength = getInput.nextInt();

        System.out.print(BlueColor+"Include"+Reset+" uppercase letters? (y/n): ");
        String choice = getInput.next();
        boolean includeUppercase = choice.equals("y");

        System.out.print(BlueColor+"Include"+Reset+" lowercase letters? (y/n): ");
        choice = getInput.next();
        boolean includeLowercase = choice.equals("y");

        System.out.print(BlueColor+"Include"+Reset+" numbers? (y/n): ");
        choice = getInput.next();
        boolean includeNumbers = choice.equals("y");

        System.out.print(BlueColor+"Include"+Reset+" special characters? (y/n): ");
        choice = getInput.next();
        boolean includeSpecialCharacters = choice.equals("y");

        String template = "";
        if(includeUppercase) template += upperCases;

        if(includeLowercase) template += lowerCases;

        if(includeNumbers) template += numbers;

        if(includeSpecialCharacters) template += symbols;

        if(template.isEmpty()){
            System.out.println("Error: No character types selected.");
            return;
        }

        char[] passwordCharacters = new char[passwordLength];
        int randomIndex;

        for(int i = 0;i < passwordLength;i++){
            randomIndex = random.nextInt(template.length());
            passwordCharacters[i] = template.charAt(randomIndex);
        }
        String Password = new String(passwordCharacters);
        System.out.println();
        System.out.println(BlueColor+"Generated password: "+Reset+Password);

        System.out.print("\nDo you want to generate another"+BlueColor+" password?"+Reset+" (y/n): ");
            choice = getInput.next();

            boolean doAnother = choice.equals("y");
            if(!doAnother){
                System.out.println(BlueColor+"\nThanks for using"+Reset+" the Password Generator!");
                break;
            }
        }
    }
}