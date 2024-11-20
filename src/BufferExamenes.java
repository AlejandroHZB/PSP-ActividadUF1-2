import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {

    private Queue<String> colaExamenes;

    public BufferExamenes() {

        colaExamenes = new LinkedList<String>();
    }

    public synchronized void fabricarNuevoExamen(String codigo) {

        colaExamenes.add(codigo);
        notify();
    }

    public synchronized String consumirExamen() {

        int tiempoEspera =0;
        while (colaExamenes.isEmpty() && tiempoEspera<20){
            tiempoEspera++;
            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());;
            }
        }
        if(tiempoEspera<20){
            String examen = colaExamenes.remove();
            return examen;
        }else {
            return null;
        }

    }

}
