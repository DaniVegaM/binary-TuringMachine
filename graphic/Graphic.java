package graphic;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import automaton.TuringMachine;
import turingmachine.Main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Graphic extends JFrame implements ActionListener{

    //Variables
    public static int max = 500, min = 2;

    public static char[] stringArray;

    public static int listo = 0;
    public static int primer = 0;

    private JTextArea jtextArea;
    private Button randomButton;
    private Button enterButton;
    private ImageIcon cabezal;
    public static Timer timer;  // Temporizador para manejar la animación
    public static Timer timer2;  // Temporizador para manejar la animación
    public static int objetivoXR = 50;  // Coordenada X a la que deseas mover la imagen
    public static int objetivoXL = -50;  // Coordenada X a la que deseas mover la imagen

    public static int cX = 425;
    public static int inicioCadena = 0;

    public Graphic(){

        stringArray = Main.string.toCharArray();
        primer = 1;
        // Configura la ventana
        setTitle("Turing Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Crea un panel personalizado para dibujar todo
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarCinta(g);
                g.drawImage(cabezal.getImage(), cX, 500, this);
                if(listo == 1 || primer == 1){
                    dibujarString(g);
                    primer = 0;
                }
            }
        };

        //Timer para mover imagenes
        // Inicializa el temporizador con una velocidad de 10 milisegundos entre cada paso
        timer = new Timer(10, new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    moveR();
                }
            });
        timer2 = new Timer(10, new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    moveL();
                }
            });

        // Establece el diseño del panel como nulo para que puedas posicionar los componentes manualmente
        panel.setLayout(null);

        // Agrega el panel a la ventana
        getContentPane().add(panel);

        // Donde se escribe la cadena
        jtextArea = new JTextArea(10, 15);
        jtextArea.setLineWrap(true); // Permite saltos de línea automáticos
        jtextArea.setWrapStyleWord(true); // Ajusta las palabras al ancho del área de texto
        jtextArea.setBounds(385, 125, 200, 50); // Posición y tamaño del JTextArea
        panel.add(jtextArea); // Agrega el TextField al panel

        // Etiqueta del título
        JLabel titleLabel = new JLabel("TURING MACHINE");
        JLabel titleLabel2 = new JLabel("by: Daniel Vega Miranda");
        JLabel titleLabel3 = new JLabel("Ingrese su cadena o genere una random");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Personaliza la fuente
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 18)); // Personaliza la fuente
        titleLabel3.setFont(new Font("Arial", Font.BOLD, 12)); // Personaliza la fuente
        titleLabel.setBounds(380, 25, 300, 30); // Posición y tamaño de la etiqueta
        titleLabel2.setBounds(380, 50, 300, 30);
        titleLabel3.setBounds(370, 90, 300, 30);
        panel.add(titleLabel);
        panel.add(titleLabel2);
        panel.add(titleLabel3);

        // Crea un botón para generar cadena aleatoria
        enterButton = new Button("Ingresar Cadena");
        enterButton.addActionListener(this); // Registra la clase actual como el listener del botón
        enterButton.setBounds(430, 185, 100, 30); // Posición y tamaño del botón
        panel.add(enterButton); // Agrega el botón al panel

        // Crea un botón para generar cadena aleatoria
        randomButton = new Button("Generar Cadena Aleatoria");
        randomButton.addActionListener(this); // Registra la clase actual como el listener del botón
        randomButton.setBounds(410, 225, 150, 30); // Posición y tamaño del botón
        panel.add(randomButton); // Agrega el botón al panel

        URL path = Graphic.class.getResource("cabezal.png");
        cabezal = new ImageIcon(path);
        Image image = cabezal.getImage(); // transform it 
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        cabezal = new ImageIcon(newimg);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == randomButton){
        TuringMachine.f.delete();

        String tempString = "";
        tempString = generateString();
        jtextArea.setText(tempString);

        Main.string = tempString;

        stringArray = Main.string.toCharArray();

        listo = 1;

        if(stringArray.length <= 16){
            dibujarString(getGraphics());
        }
        cX = inicioCadena;
        repaint();

    } else if(e.getSource() == enterButton){
        TuringMachine.f.delete();
        Main.string = jtextArea.getText();

        stringArray = Main.string.toCharArray();

        listo = 1;
        if(stringArray.length <= 16){
            dibujarString(getGraphics());
        }
        cX = inicioCadena;
        repaint();
    }
    }

    private void dibujarCinta(Graphics g) {
        int filaWidth = 50; // Ancho de cada cuadro
        int x = 40; // Posición X
        int y = 550; // Posición Y
    
        for (int i = 0; i < 18; i++) {
            // System.out.println("Soy el c " + i + "y mi fileHeight es: " + filaWidth + "\n");
            g.setColor(Color.BLACK);
            g.drawRect(x, y, filaWidth, 80); // Dibuja el borde de la celda
            g.setColor(Color.white);
            g.fillRect(x + 1, y + 1, filaWidth - 1, 79);
            x += filaWidth; // Mueve la posición X hacia la derecha para la siguiente fila
        }
    }

    private void dibujarString(Graphics g){
        if(stringArray.length <= 16){
            int i, posX = 48;
    
            g.setColor( Color.BLUE );
            g.setFont( new Font( "Tahoma", Font.BOLD, 46 ) );
    
            // System.out.println("Su cadena es de longitud: " + stringArray.length);
    
            for(i = 0; i < ((int) ((18 - stringArray.length) / 2)); i++){ //Lleno con 'B'
                g.drawString("B", posX, 610);
                posX += objetivoXR;
            }
    
            inicioCadena = posX; //Posicion inicial de la cadena
            // System.out.println("Inicio cadena vale: " + inicioCadena);
    
            for(i = 0; i < stringArray.length; i++){ //Lleno con string
                g.drawString(stringArray[i] + " ", posX, 610);
                posX += objetivoXR;
            }
    
            for(i = 0; i < ((int) ((18 - stringArray.length) / 2)); i++){ //Lleno con 'B'
                g.drawString("B", posX, 610);
                posX += objetivoXR;
            }
        }
    }

    public void moveR() {
        if(stringArray.length <= 16){
            System.out.println("Me muevo a la derecha\n");
            // Calcula la diferencia entre las coordenadas actuales y las coordenadas objetivo
            cX += objetivoXR;
    
            repaint();
            timer.stop();  // Detiene el temporizador ya que la animación ha terminado
        }
    }
    public void moveL() {
        if(stringArray.length <= 16){
            System.out.println("Me muevo a la izquierda\n");
            // Calcula la diferencia entre las coordenadas actuales y las coordenadas objetivo
            cX -= objetivoXR;
    
            repaint();
            timer.stop();  // Detiene el temporizador ya que la animación ha terminado
        }
    }

    public void changeChar(int pos, char character){
        System.out.println("Se hizo un cambio\n");
        if((pos - 1) < stringArray.length){
            stringArray[pos - 1] = character;
            dibujarString(getGraphics());
            repaint();
        }
    }

    public static String generateString(){ //Generate a random string to analize
        String string = "";
        int numZeros = min + (int) (Math.random() * ((max - min) + 1));
        int numOnes = min + (int) (Math.random() * ((max - min) + 1));

        // System.out.println("Num zeros " + numZeros);
        // System.out.println("Num ones " + numOnes);

        for(int i = 0; i < numZeros; i++){ //The stack get full of Zeros
            string += '0';
        }
        for(int i = 0; i < numOnes; i++){ //The stack get full of Ones
            string += '1';
        }

        return string;
    }
}
