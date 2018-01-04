package com.mastermind.persistencia;
import com.mastermind.domini.*;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;

public class DataController {

    public DataController(){
        super();
    }

    public Partida getPartida(int ID, String user){
        String line = getByID(ID, user);
        Partida p = null;
        if(line != null){
            String[] dades = line.split(" ");

            boolean cm =  "1" == dades[1];

            String[] t = dades[2].split(":");

            Time time = new Time((Integer.parseInt(t[0])*60*60*1000 + Integer.parseInt(t[1])*60*1000 + Integer.parseInt(t[2])*1000));

            ArrayList<Integer> sol = new ArrayList<>();
            for (int i = 0; i < dades[3].length(); i++){
                sol.add(i, Character.getNumericValue(dades[3].charAt(i)));
            }
            Codi codi_sol = new Codi(sol);

            int max_torn = Integer.parseInt(dades[4]);

            int torn = Integer.parseInt(dades[5]);

            int diff = Integer.parseInt(dades[6]);

            boolean help = dades[7] == "1";

            ArrayList<Tirada> tirades = new ArrayList<>();
            for (int i = 1; i < torn; ++i){   ///////el primer torn ha de ser l'1 no el 0!!

                ArrayList<Integer> codi = new ArrayList<>();
                for (int j = 0; j < dades[8 + 2*(i - 1)].length(); j++){
                    codi.add(j, Character.getNumericValue(dades[6 + 2*(i - 1)].charAt(j)));
                }
                Codi guess = new Codi(codi);

                int blacks = Character.getNumericValue(dades[8 + 2*(i - 1) + 1].charAt(0));
                int whites = Character.getNumericValue(dades[8 + 2*(i - 1) + 1].charAt(1));

                Resposta res = new Resposta(blacks, whites);

                Tirada ti = new Tirada(guess, res);
                tirades.add(ti);


            }

            Taulell tau = new Taulell(torn, codi_sol, tirades, max_torn);
            p = new Partida(ID, diff, cm, help, time, tau);


        }

        return p;

    }

    private String getByID(int ID, String user){

        String partida = new String();

        try {
            File inFile = new File("partides" + user + ".txt");
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return null;
            }
            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader("Parameter is not an existing file"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line;
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (Integer.parseInt(parts[0]) == ID){
                    partida = line;
                }
                else{
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return null;
            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return partida;
    }

    public void savePartida(Partida p){
        String ID = "" + p.getID();
        boolean aux = p.isCodeMaker();
        String cm = new String();

        if (aux) cm = "1";
        else cm = "0";

        String time = p.getTime().toString();
        String codi_sol = "";

        Codi c = p.getTaulell().getCodiSolucio();
        for (int i = 0; i < c.getPeces().size(); ++i){
            codi_sol = codi_sol + c.getPeces().get(i);
        }

        String max_torn = "" + p.getTaulell().getMaxTorn();

        String torn = "" + p.getTaulell().getTorn();

        ArrayList<Tirada> tir = p.getTaulell().getTirades();

        String tirades = "";

        for (int i = 0; i < tir.size(); ++i){
            Tirada t = tir.get(i);
            Codi cod = t.getCodi();
            String auxiliar = "";
            for (int j = 0; j < cod.getPeces().size(); ++j){
                auxiliar = auxiliar + cod.getPeces().get(j);
            }
            tirades = tirades + " " + auxiliar;
            auxiliar = "" + t.getResposta().getnBlacks() + "" + t.getResposta().getnWhites();
            tirades = tirades + " " + auxiliar;

        }

        String data = ID + " " + cm + " " + time + " " + codi_sol + " " + max_torn + " " + torn + " " + tirades;

        Writer output;
        try{
            output = new BufferedWriter(new FileWriter("partides.txt"));  //clears file every time
            output.append(data + "\n");
            output.close();
        }
        catch(Throwable e){
            System.out.println("no s'ha pogut desar la partida");
        }


    }

    public void saveUser(String user){

        Writer output;

        try{
            output = new BufferedWriter(new FileWriter("users.txt"));  //clears file every time
            output.append(user + "\n");
            output.close();
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut guardar l'usuari");
        }
    }

    public boolean userExists(String User){
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(User)) return true;
            }
            return false;
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer");
            return false;
        }
    }

    public void saveRecord(Record r){
        Writer output;

        try{
            output = new BufferedWriter(new FileWriter("records" + r.getName() + ".txt", true));  //clears file every time
            String score = "" + r.getScore();
            String cm = new String();
            if (r.isCodeMaker()) cm = "1";
            else cm = "0";

            String time = r.getTime().toString();
            String record = score + " " + cm + " " + time;
            output.append(record);
            output.close();
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut guardar el record");
        }
    }

    public Record[] getRecords(String user){
        Record[] records = null;
        try (BufferedReader br = new BufferedReader(new FileReader("records" + user + ".txt"))) {
            String line;
            records = new Record[10];
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                int score = Integer.parseInt(data[0]);
                boolean cm = "1" == data[1];

                String[] t = data[2].split(":");
                Time time = new Time(Integer.parseInt(t[0])*60*60*1000 + Integer.parseInt(t[1])*60*1000 + Integer.parseInt(t[2])*1000);
                Record r = new Record(user, score, cm, time);
            }
            return records;
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer");
            return records;
        }

    }



}
