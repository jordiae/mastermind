package com.mastermind.persistencia;
import com.mastermind.domini.*;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;

public class DataController {


    static public Usuari getUser(String name){
        Usuari u = null;
        File f = new File("users.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(" ");
                if (info[0].equals(name)) {
                    u = new Usuari(name, info[1]);
                    return u;
                }
            }
            return u;
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer");
            return u;
        }

    }

    static public Partida[] getPartidesUser(String usuari){  //cal controlar nomes 15 ID guardats
        File f = new File("IDs" + usuari + ".txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("IDs" + usuari + ".txt"))) {
            String line;
            Partida[] partides = new Partida[15];
            int i = 0;
            while ((line = br.readLine()) != null) {
                partides[i] = getPartida(Integer.parseInt(line), usuari);
                ++i;
            }
            return partides;
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer");
            return null;
        }
    }


    static public Partida getPartida(int ID, String user){
        String line = getByID(ID, user);
        Partida p = null;
        if(line != null){
            String[] dades = line.split(" ");

            boolean cm =  "1".equals(dades[1]);

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

            boolean help = dades[7].equals("1");

            ArrayList<Tirada> tirades = new ArrayList<>();
            for (int i = 1; i < torn+1; ++i){   ///////el primer torn ha de ser l'1 no el 0!!

                ArrayList<Integer> codi = new ArrayList<>();
                for (int j = 0; j < dades[8 + 2*(i - 1)].length(); j++){
                    codi.add(j, Character.getNumericValue(dades[8 + 2*(i - 1)].charAt(j)));
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



    static private String getByID(int ID, String user){

        String partida = new String();
        File f = new File("partides" + user + ".txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File inFile = new File("partides" + user + ".txt");
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return null;
            }
            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader("partides" + user + ".txt"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line;
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (Integer.parseInt(parts[0]) == ID){
                    partida = line;
                    pw.println(line);
                    pw.flush();
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

    static public boolean deletePartida(String ID, String user) {
        boolean erased = false;
        try {
            File inFile = new File("partides" + user + ".txt");
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");

            }
            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader("partides" + user + ".txt"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line;
            boolean first = true;
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                if (data[0].equals(ID)) {
                    erased = true;
                } else {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            if (!inFile.delete()) {
                System.out.println("Could not delete file");

            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer");
        }

        try {
            File inFile = new File("IDs" + user + ".txt");
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");

            }
            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader("IDs" + user + ".txt"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line;
            boolean first = true;
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (line.equals(ID)) {
                    erased = true;
                } else {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                
            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer");
        }
        return erased;

    }


    static public boolean savePartida(Partida p, String user){
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

        String dificultat = "" + p.getDifficulty();

        String help;
        if (p.getHelp()) {
            help = "1";
        }
        else {
            help = "0";
        }

        ArrayList<Tirada> tir = p.getTaulell().getTirades();

        String tirades = new String();

        for (int i = 0; i < tir.size(); ++i){
            Tirada t = tir.get(i);
            Codi cod = t.getCodi();
            String auxiliar = new String();
            for (int j = 0; j < cod.getPeces().size(); ++j){
                auxiliar = auxiliar + cod.getPeces().get(j);
            }
            tirades = tirades + auxiliar ;
            auxiliar =  t.getResposta().getnBlacks() + "" + t.getResposta().getnWhites();
            tirades = tirades + " " + auxiliar + " ";

        }

        String data = ID + " " + cm + " " + time + " " + codi_sol + " " + max_torn + " " + torn + " " + dificultat + " " + help + " " + tirades;

        Writer output;

        File f = new File("partides" + user + ".txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        f = new File("IDs" + user + ".txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        try (BufferedReader br = new BufferedReader(new FileReader("partides" + user + ".txt"))) {
            String line;
            Partida[] partides = new Partida[15];
            int i = 0;
            while ((line = br.readLine()) != null) {
                ++i;
            }
            if (i == 10){
                updatePartides(data, user, ID);
                return true;
            }
            else{
                afegeixPartida(data, user, ID);
                return true;
            }
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer");
            return false;
        }



    }



    static public boolean saveUser(String user, String password){

        Writer output;

        File f = new File("users.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try{
            output = new BufferedWriter(new FileWriter("users.txt", true));
            if (userExists(user)){
                return false;
            }
            else{
                output.append(user + " " + password + "\n");
                output.close();
                return true;
            }

        }
        catch(Throwable t){
            System.out.println("no s'ha pogut guardar l'usuari");
            return false;
        }
    }

    static public boolean userExists(String User){
        File f = new File("users.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(" ");
                if (info[0].equals(User)) return true;
            }
            return false;
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer");
            return false;
        }
    }

    static public void saveRecord(Record r){
        Writer output;

        File f = new File("records.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            output = new BufferedWriter(new FileWriter("records.txt", true));  //clears file every time
            String score = "" + r.getScore();
            String cm = new String();
            if (r.isCodeMaker()) cm = "1";
            else cm = "0";
            String name = r.getName();
            String time = r.getTime().toString();
            String record = score + " " + name + " " + cm + " " + time;
            output.append(record);
            output.close();
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut guardar el record");
        }
    }

    static public Record[] getRecords(){
        Record[] records = null;
        File f = new File("records.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("records.txt"))) {
            String line;
            records = new Record[10];
            int i = 0;
            while ((line = br.readLine()) != null && i < 10) {
                String[] data = line.split(" ");
                int score = Integer.parseInt(data[1]);
                String user = data[0];
                //boolean cm = "1" == data[2];

                //String[] t = data[3].split(":");
                //Time time = new Time(Integer.parseInt(t[0])*60*60*1000 + Integer.parseInt(t[1])*60*1000 + Integer.parseInt(t[2])*1000);
                Record r = new Record(user, score, false, new Time(1));
                records[i] = r;
                ++i;
            }
            return records;
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer!!" + t);
            return records;
        }

    }

    static public void saveRecords(ArrayList<String> records){
        File inFile = new File("records.txt");
        if (!inFile.isFile()) {
            System.out.println("Parameter is not an existing file");

        }
        if (!inFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
        inFile = new File("records.txt");
        try {
            inFile.createNewFile();
            PrintWriter pw = new PrintWriter(new FileWriter(inFile));
            for (int i = 0; i < records.size(); ++i){
                pw.println(records.get(i));
                pw.flush();
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("no s'ha pogut crear el fitxer");
            return;
        }



    }


     static private void updatePartides(String data, String user, String ID){
         try {
             File inFile = new File("partides" + user + ".txt");
             if (!inFile.isFile()) {
                 System.out.println("Parameter is not an existing file");

             }
             //Construct the new file that will later be renamed to the original filename.
             File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
             BufferedReader br = new BufferedReader(new FileReader("partides" + user + ".txt"));
             PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
             String line;
             boolean first = true;
             //Read from the original file and write to the new
             //unless content matches data to be removed.
             while ((line = br.readLine()) != null) {
                 if (first){
                    first = false;
                 }
                 else{
                     pw.println(line);
                     pw.flush();
                 }
             }
             pw.println(data);
             pw.flush();
             pw.close();
             br.close();

             //Delete the original file
             if (!inFile.delete()) {
                 System.out.println("Could not delete file");

             }
             //Rename the new file to the filename the original file had.
             if (!tempFile.renameTo(inFile))
                 System.out.println("Could not rename file");

         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         } catch (IOException ex) {
             ex.printStackTrace();
         }

         try {
             File inFile = new File("IDs" + user + ".txt");
             if (!inFile.isFile()) {
                 System.out.println("Parameter is not an existing file");

             }
             //Construct the new file that will later be renamed to the original filename.
             File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
             BufferedReader br = new BufferedReader(new FileReader("IDs" + user + ".txt"));
             PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
             String line;
             boolean first = true;
             //Read from the original file and write to the new
             //unless content matches data to be removed.
             while ((line = br.readLine()) != null) {
                 if (first){
                    first = false;
                 }
                 else{
                     pw.println(line);
                     pw.flush();
                 }
             }
             pw.println(ID);
             pw.flush();
             pw.close();
             br.close();

             //Delete the original file
             if (!inFile.delete()) {
                 System.out.println("Could not delete file");

             }
             //Rename the new file to the filename the original file had.
             if (!tempFile.renameTo(inFile))
                 System.out.println("Could not rename file");

         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
     }

     static private void afegeixPartida(String data, String user, String ID){
         Writer output;

         try{
             output = new BufferedWriter(new FileWriter("partides" + user + ".txt", true));  //clears file every time
             output.append(data + "\n");
             output.close();
         }
         catch(Throwable e){
             System.out.println("no s'ha pogut desar la partida");
         }

         try{
             output = new BufferedWriter(new FileWriter("IDs" + user + ".txt", true));  //clears file every time
             output.append(ID + "\n");
             output.close();
         }
         catch(Throwable e){
             System.out.println("no s'ha pogut desar la partida");
         }
     }
}
