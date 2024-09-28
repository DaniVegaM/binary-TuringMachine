package automaton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import graphic.Graphic;

public class TuringMachine {
    //Here "j" is going to be used for move across the stringIdsArray
    static int i = 1, j = 0; //The index starts in 1 because the first element is blank
    static int state = 0;

    static String tapeResult = "";
    static String stringIds = "";
    static ArrayList<Character> tapeResultArray = new ArrayList<Character>();
    static ArrayList<Character> stringIdsArray = new ArrayList<Character>();
    public static File f = new File("ID's of turing machine.txt");

    public String turingMachine(String tape, Graphic g){
        tapeResultArray.add('B');

        for(Character character : tape.toCharArray()){
            tapeResultArray.add(character);

            //First I need to add all the zeros
            stringIdsArray.add(character);
            // if(character == '0'){
            // }
        }

        printID(); //Print the string

        tapeResultArray.add('B');

        // System.out.println("Tu tape en el automata es: \n " + tapeResultArray);

        //Here I start to move the head

        while(state != 4 && state != 999){
            // System.out.println("Entro  al switch con valor de state: " + state);
            switch (state) {
                case 0:
                    switch (tapeResultArray.get(i)) {
                        case '0':
                            // System.out.println("\nEstoy leyendo un 0\n");
                            state = 1;
                            tapeResultArray.set(i, 'X');

                            stringIdsArray.set(j, 'q');
                            printID();
                            stringIdsArray.set(j, '0');

                            g.changeChar(i, 'X');
                            moveR(g);
                        break;
                        case 'Y':
                            // System.out.println("\nEstoy leyendo un Y\n");
                            state = 3;
                            tapeResultArray.set(i, 'Y');

                            stringIdsArray.set(j, 'p');
                            printID();
                            stringIdsArray.set(j, '1');

                            g.changeChar(i, 'Y');
                            moveR(g);
                        break;
                        default:
                            state = 999;
                            System.out.println("ERROR: Cadena Rechazada\n");
                        break;
                    }
                break;
                case 1:
                    switch (tapeResultArray.get(i)) {
                        case '0':
                            // System.out.println("\nEstoy leyendo un 0\n");
                            state = 1;
                            tapeResultArray.set(i, '0');

                            stringIdsArray.set(j, 'q');
                            printID();
                            stringIdsArray.set(j, '0');

                            g.changeChar(i, '0');
                            moveR(g);
                        break;
                        case '1':
                            // System.out.println("\nEstoy leyendo un 1\n");
                            state = 2;
                            tapeResultArray.set(i, 'Y');

                            stringIdsArray.set(j, 'p');
                            printID();
                            stringIdsArray.set(j, '1');

                            g.changeChar(i, 'Y');
                            moveL(g);
                        break;
                        case 'Y':
                            // System.out.println("\nEstoy leyendo un Y\n");
                            state = 1;
                            tapeResultArray.set(i, 'Y');

                            stringIdsArray.set(j, 'p');
                            printID();
                            stringIdsArray.set(j, '1');

                            g.changeChar(i, 'Y');
                            moveR(g);
                        break;
                        default:
                            state = 999;
                            System.out.println("ERROR: Cadena Rechazada\n");
                        break;
                    }
                break;
                case 2:
                    switch (tapeResultArray.get(i)) {
                        case '0':
                            // System.out.println("\nEstoy leyendo un 0\n");
                            state = 2;
                            tapeResultArray.set(i, '0');

                            stringIdsArray.set(j, 'q');
                            printID();
                            stringIdsArray.set(j, '0');

                            g.changeChar(i, '0');
                            moveL(g);
                        break;
                        case 'X':
                            // System.out.println("\nEstoy leyendo un X\n");
                            state = 0;
                            tapeResultArray.set(i, 'X');

                            stringIdsArray.set(j, 'q');
                            printID();
                            stringIdsArray.set(j, '0');

                            g.changeChar(i, 'X');
                            moveR(g);
                        break;
                        case 'Y':
                            // System.out.println("\nEstoy leyendo un Y\n");
                            state = 2;
                            tapeResultArray.set(i, 'Y');

                            stringIdsArray.set(j, 'p');
                            printID();
                            stringIdsArray.set(j, '1');

                            g.changeChar(i, 'Y');
                            moveL(g);
                        break;
                        default:
                            state = 999;
                            System.out.println("ERROR: Cadena Rechazada\n");
                        break;
                    }
                break;
                case 3:
                    switch (tapeResultArray.get(i)) {
                        case 'Y':
                            // System.out.println("\nEstoy leyendo un Y\n");
                            state = 3;
                            tapeResultArray.set(i, 'Y');

                            stringIdsArray.set(j, 'p');
                            printID();
                            stringIdsArray.set(j, '1');

                            g.changeChar(i, 'Y');
                            moveR(g);
                        break;
                        case 'B':
                            // System.out.println("\nEstoy leyendo un B\n");
                            state = 4;
                            // System.out.println("El valor de state ahora es: " + state);
                            tapeResultArray.set(i, 'B');

                            stringIdsArray.add('f');
                            printID();

                            g.changeChar(i, 'B');
                            moveR(g);
                        break;
                        default:
                            state = 999;
                            System.out.println("ERROR: Cadena Rechazada\n");
                        break;
                    }
                break;
            }
        }

        //I need  to review if the final state is correct
        if(state == 4){
            System.out.println("Cadena aceptada\n");
        } else{
            System.out.println("Cadena rechazada\n");
        }

        //Convert String to Array
        for(Character character : tapeResultArray){
            tapeResult += character;
        }

        return tapeResult;
    }

    public static void moveR(Graphic g){
        i++;
        j++;
        g.moveR();
        try {
            Thread.sleep(400); // Espera 100 milisegundos antes de verificar de nuevo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void moveL(Graphic g){
        i--;
        j--;
        g.moveL();
        try {
            Thread.sleep(400); // Espera 100 milisegundos antes de verificar de nuevo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printID(){
        //Convert arrayList to String
        stringIds = "";
        for(Character character : stringIdsArray){
            stringIds += character;
        }
        
        //Write in the file the ID
        try{
            FileWriter w = new FileWriter(f.getAbsoluteFile(), true);

            //Write on file
            w.append(stringIds + " ├ \n");

            w.close();
        } catch(IOException e){};

        System.out.println(tapeResult);
    }
}
