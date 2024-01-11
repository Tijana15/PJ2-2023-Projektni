import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kamion extends Vozilo implements KamionInterface
{
    private final Teret teret;
    private final boolean potrebnaCarinskaDokumentacija;
    private final double deklarisanaMasaTereta;
    private final boolean kamionImaVeciTeret;
    private static int globalniBrojacKamiona=0;
    public Kamion()
    {
        super(new Random().nextInt(2)+1);
        globalniBrojacKamiona++;
        deklarisanaMasaTereta=new Random().nextDouble(1000);
        teret=new Teret(deklarisanaMasaTereta);
        potrebnaCarinskaDokumentacija=new Random().nextBoolean();
        kamionImaVeciTeret=new Random().nextInt(100)<20;
        if(kamionImaVeciTeret)
        {
            teret.setStvarnaMasaTereta(deklarisanaMasaTereta+new Random().nextDouble(31)*deklarisanaMasaTereta);
        }
    }
    public Teret getTeret() {
        return teret;
    }
    public double getDeklarisanaMasaTereta() {
        return deklarisanaMasaTereta;
    }
    public boolean daLiJePotrebnaCarinskaDokumentacija() {
        return potrebnaCarinskaDokumentacija;
    }
    public boolean daLiKamionImaVeciTeret() {
        return kamionImaVeciTeret;
    }

    public void run()
    {

        boolean prosaoPolicijskiTerminal = false;
        while (!prosaoPolicijskiTerminal){
            if (Simulacija.granicniRed.size() > 0 && Simulacija.granicniRed.peek() == this && Simulacija.policijskiTerminalZaKamione.jeSlobodan()) {
                Simulacija.policijskiTerminalZaKamione.setSlobodan(false);
                System.out.println("Kamion "+this + " je zauzeo policijski terminal.");
                Main.prebaciIzRedaNaPolicijski(3);
                Simulacija.granicniRed.poll();

                //System.out.println(this + " je uklonjeno iz granicnog reda.");
                //System.out.println("Obrada " + this + " na policijskom:");
                Simulacija.policijskiTerminalZaKamione.obradaVozila(this);
                if (paoPolicijski) {
                    System.out.println("Kamion "+this+" pao policijsku kontrolu.");
                    String imeFajla = "KamioniPaliPolicijski.txt";
                    try {
                        FileWriter fileWriter = new FileWriter(imeFajla);
                        BufferedWriter writer = new BufferedWriter(fileWriter);
                        String tekstZaUpis = "Kamion " +this+"  nije prosao policijsku provjeru";
                        writer.write(tekstZaUpis);
                        writer.close();
                        fileWriter.close();
                    } catch (IOException e) {
                        Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
                    }
                    Main.izbrisiSaPolicijskog(3);
                    Simulacija.policijskiTerminalZaKamione.setSlobodan(true);
                }
                prosaoPolicijskiTerminal=true;
            }
        }
        boolean prosaoCarinskiTerminal = false;
        if (!paoPolicijski) {
            while (!prosaoCarinskiTerminal) {
                if (Simulacija.carinskiTerminalZaKamione.jeSlobodan()) {
                    Simulacija.carinskiTerminalZaKamione.setSlobodan(false);
                    System.out.println("Kamion "+this + " je zauzeo carinski terminal");
                    //System.out.println(this + " oslobađa iza sebe policijski terminal.");
                    Main.prebaciSaPolicijskihNaCarinski(3);

                    Simulacija.policijskiTerminalZaKamione.setSlobodan(true);
                    //System.out.println("Obrada " + this + " na carinskom terminalu:");
                    Simulacija.carinskiTerminalZaKamione.obradaVozila(this);
                    Main.izbrisiSaCarinskog(2);
                    if (paoCarinski) {
                        System.out.println("Kamion "+this+" je pao na carinskoj kontroli. Oslobađamo carinski terminal za sljedeće kamione.");
                        String imeFajla = "KamioniPaliCarinski.txt";
                        try {
                            FileWriter fileWriter = new FileWriter(imeFajla);
                            BufferedWriter writer = new BufferedWriter(fileWriter);
                            String tekstZaUpis = "Kamion "+this+" nije prosao carinsku provjeru";
                            writer.write(tekstZaUpis);
                            writer.close();
                            fileWriter.close();
                        } catch (IOException e) {
                            Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
                        }
                    } else {
                        System.out.println("Kamion "+this+" je presao carinu. Slijedi oslobađanje carinskog terminala.");
                    }
                    Simulacija.carinskiTerminalZaKamione.setSlobodan(true);
                    prosaoCarinskiTerminal = true;
                }
            }
        }
    }
}
