import java.util.Random;

public class Examinado implements Runnable {

    private Thread hilo;

    BufferExamenes buffer;



    public Thread getHilo() {

        return hilo;

    }

    public Examinado(String alumno, BufferExamenes generador) {
        hilo = new Thread(this,alumno);
        this.buffer=generador;
        hilo.start();

    }

    @Override

    public synchronized void run() {

        String codigoExamen = this.buffer.consumirExamen();

        if (codigoExamen != null) {

            Random random = new Random();
            String[] respuestas = {"A", "B", "C", "D", "-"};
            for (int i = 1; i <= 10; i++) {
                String respuesta = respuestas[random.nextInt(respuestas.length)];
                System.out.println(codigoExamen + ";" + hilo.getName() + "; Pregunta " + i + ";" + respuesta);
            }
        }
        else {

            System.out.println("Agotado tiempo de espera y " +

                    "no hay más exámenes");

        }

    }

}
