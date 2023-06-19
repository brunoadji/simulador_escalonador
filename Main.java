package simulador_escalonador;

public class Main {
    
    public static void main(String[] args) {
        OS os = new OS();

        os.createProcess("P1",0, 0, 10, 2504, 0, 4, 6);

        os.executeCicle();

        os.print();
    }

}
