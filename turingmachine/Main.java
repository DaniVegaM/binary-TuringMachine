package turingmachine;

import automaton.TuringMachine;
import graphic.Graphic;

public class Main {

    public static String string = "XX";

    static Graphic window = new Graphic();
    public static void main(String[] args) {

        System.out.println("Bienvenido a la maquina de Turing\n");

        window.setVisible(true);

        //Wait intil get the string
        while (string == "XX") {
            try {
                Thread.sleep(100); // Espera 100 milisegundos antes de verificar de nuevo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Turing Machine
        String stringResult;
        TuringMachine turingMachine = new TuringMachine();
        stringResult = turingMachine.turingMachine(string, window);

        System.out.println("La cadena final es:\n" + stringResult);
    }
}