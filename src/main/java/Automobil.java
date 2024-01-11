import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Automobil extends Vozilo implements AutomobilInterface {
    public static final int MAX_BROJ_PUTNIKA=5;
    public Automobil()
    {
        super(new Random().nextInt(MAX_BROJ_PUTNIKA)+1);
    }
    public void run()
    {
        boolean prosaoPolicijskiTerminal=false;
        int saKogPolicijskogDolazi=0;
        while(!prosaoPolicijskiTerminal) {
            if(Simulacija.granicniRed.size()>0 && Simulacija.granicniRed.peek()==this && Simulacija.policijskiTerminal1.jeSlobodan()) {
                saKogPolicijskogDolazi=1;
                Simulacija.policijskiTerminal1.setSlobodan(false);
                System.out.println("Auto " +this + " je zauzeo policijski terminal 1");
                Main.prebaciIzRedaNaPolicijski(saKogPolicijskogDolazi);
                Simulacija.granicniRed.poll();

                //System.out.println(this+" je uklonjeno iz granicnog reda.");
                //System.out.println("Obrada "+this+" na policijskom:");
                Simulacija.policijskiTerminal1.obradaVozila(this);
                if(paoPolicijski) {
                    System.out.println("Auto "+this + " pao policijsku kontrolu, oslobađa se policijski terminal 1");
                    String imeFajla = "AutaPalaPolicijski.txt";
                    try {
                        FileWriter fileWriter = new FileWriter(imeFajla);
                        BufferedWriter writer = new BufferedWriter(fileWriter);
                        String tekstZaUpis = "Auto " +this+"  nije prosao policijsku provjeru";
                        writer.write(tekstZaUpis);
                        writer.close();
                        fileWriter.close();
                    } catch (IOException e) {
                        Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
                    }
                    Main.izbrisiSaPolicijskog(saKogPolicijskogDolazi);
                    Simulacija.policijskiTerminal1.setSlobodan(true);
                }
                else{
                    Simulacija.carinskiRed.add(this);
                    //System.out.println(this+ "je dodano u carinski red.");
                }
                prosaoPolicijskiTerminal=true;
            }
            else if(Simulacija.granicniRed.size()>0 && Simulacija.granicniRed.peek()==this && Simulacija.policijskiTerminal2.jeSlobodan()) {
                saKogPolicijskogDolazi=2;
                Simulacija.policijskiTerminal2.setSlobodan(false);
                System.out.println("Auto "+this + " je zauzeo policijski terminal 2");
                Main.prebaciIzRedaNaPolicijski(saKogPolicijskogDolazi);
                Simulacija.granicniRed.poll();

                //System.out.println(this+" je uklonjeno iz granicnog reda.");
                //System.out.println("Obrada "+this+" na policijskom:");
                Simulacija.policijskiTerminal2.obradaVozila(this);
                if(paoPolicijski) {
                    System.out.println("Auto "+this + " pao policijsku kontrolu, oslobađa se policijski terminal 2");
                    String imeFajla = "AutaPalaPolicijski.txt";
                    try {
                        FileWriter fileWriter = new FileWriter(imeFajla);
                        BufferedWriter writer = new BufferedWriter(fileWriter);
                        String tekstZaUpis = "Auto " +this+"  nije prosao policijsku provjeru";
                        writer.write(tekstZaUpis);
                        writer.close();
                        fileWriter.close();
                    } catch (IOException e) {
                        Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
                    }
                    Main.izbrisiSaPolicijskog(saKogPolicijskogDolazi);
                    Simulacija.policijskiTerminal2.setSlobodan(true);
                }
                else{
                    Simulacija.carinskiRed.add(this);
                    //System.out.println(this+ "je dodano u carinski red.");
                }
                prosaoPolicijskiTerminal=true;
            }
        }
        boolean prosaoCarinskiTerminal=false;
        if(!paoPolicijski) {
            while(!prosaoCarinskiTerminal) {
                if(Simulacija.carinskiRed.size()>0 && Simulacija.carinskiRed.peek()==this && Simulacija.carinskiTerminal.jeSlobodan()) {
                    Simulacija.carinskiTerminal.setSlobodan(false);
                    if(saKogPolicijskogDolazi==1) {
                        Main.prebaciSaPolicijskihNaCarinski(saKogPolicijskogDolazi);
                        System.out.println("Auto "+this + " je zauzeo carinski terminal");
                        Simulacija.carinskiRed.poll();
                        Simulacija.policijskiTerminal1.setSlobodan(true);
                        System.out.println("Auto "+this + " izašao iz policijskog 1");

                    }
                    else {
                        Main.prebaciSaPolicijskihNaCarinski(saKogPolicijskogDolazi);
                        System.out.println("Auto "+this + " je zauzeo carinski terminal");
                        Simulacija.carinskiRed.poll();
                        Simulacija.policijskiTerminal2.setSlobodan(true);
                        System.out.println("Auto "+this + " izaslo sa policijskog 2.");
                    }
                    //System.out.println("Obrada "+this+" na carinskom terminalu.");
                    Simulacija.carinskiTerminal.obradaVozila(this);
                    System.out.println("Auto "+this+" je presao carinu.");
                    Main.izbrisiSaCarinskog(1);
                    Simulacija.carinskiTerminal.setSlobodan(true);
                    prosaoCarinskiTerminal=true;
                }
            }
        }
    }
}
