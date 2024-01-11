import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Simulacija {
    public static volatile ArrayBlockingQueue<Vozilo> granicniRed = new ArrayBlockingQueue<>(25);
    public static volatile ArrayBlockingQueue<Vozilo> carinskiRed = new ArrayBlockingQueue<>(2);
    final static int BROJ_AUTOBUSA = 5;
    final static int BROJ_AUTA = 10;
    final static int BROJ_KAMIONA = 10;
    public static Handler handler;
    {
        try {
            // ime logger-a je naziv klase
            handler = new FileHandler("Logger.log");
            Logger.getLogger(Simulacija.class.getName()).addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static volatile CarinskiTerminal carinskiTerminalZaKamione = new CarinskiTerminal();
    public static volatile CarinskiTerminal carinskiTerminal = new CarinskiTerminal();
    public static volatile PolicijskiTerminal policijskiTerminalZaKamione = new PolicijskiTerminal();
    public static volatile PolicijskiTerminal policijskiTerminal1 = new PolicijskiTerminal();
    public static volatile PolicijskiTerminal policijskiTerminal2 = new PolicijskiTerminal();
    public static volatile ArrayList<Vozilo> pomocniRed = new ArrayList<>(25);


}