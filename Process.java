package simulador_escalonador;

/*   Essa classe modela o processo
 *   Tem que ter uma função executar, que decrementa o numero de linhas restante
 */

public class Process {
    public static final int END = 0;
    public static final int READY = 1;
    public static final int BLOCK = 2;

    // retorna end, block ou ready
    public int execute(){
        return 0;
    }
}
