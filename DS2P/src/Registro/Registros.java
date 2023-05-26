package Registro;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Registros {
    String dir = "C:\\Registros";
    String dirtipo;
    //File admin = new File(dir+"\\ADMIN.txt");
    File main = new File(dir);
    public boolean coderole(boolean bool){
            if (main.mkdir()) {
                return false;
            } else {
                System.out.println("Carpeta inicial creada");
                return true;
            }

    }
    public void codewrite(String codeA, String codeM){
        String hashAdmin = String.valueOf(codeA.hashCode());
        String hashMan = String.valueOf(codeM.hashCode());

            try {
                FileWriter fwA = new FileWriter(dir + "\\[ADMINISTRADOR].txt");
                BufferedWriter bwA = new BufferedWriter(fwA);
                bwA.write(hashAdmin);
                bwA.flush();
                bwA.close();
                fwA.close();

                FileWriter fwM = new FileWriter(dir + "\\[MANAGER].txt");
                BufferedWriter bwM = new BufferedWriter(fwM);
                bwM.write(hashMan);
                bwM.flush();
                bwM.close();
                fwM.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    public String coderead(String rol){
        String contenido="";
        int c;
        try {
            FileReader fr=new FileReader(dir+"\\"+rol.toUpperCase()+".txt");

            while ((c=fr.read())!=-1){
                contenido+=(char)c;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contenido;
    }

    public void escribir(String usuario, String password, String tipo){
       //System.out.println("User: "+usuario+" Password: "+password);
       String hP= String.valueOf(password.hashCode());
       //System.out.println("HashCode Password: "+hP);
       dirtipo= dir+"\\"+tipo;
       File folder = new File(dirtipo);
        if(folder.mkdir()){
            System.out.println("Repositorio de usuarios creado");
        }

        try {
            FileWriter fw = new FileWriter(dirtipo + "\\" + usuario + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            //bw.write(usuario+"\n"+hP+"\n"+tipo);
            bw.write(hP);
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String leer(String usuario, String tipo) {
        dirtipo= dir+"\\"+tipo;
        String contenido="";
        int c;
        try {
            FileReader fr=new FileReader(dirtipo+"\\"+usuario+".txt");

            while ((c=fr.read())!=-1){
                contenido+=(char)c;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("Escrito: "+contenido);
        return contenido;
    }

    public void Borrar(String user, String rol){
        dirtipo= dir+"\\"+rol;
        File fichero=new File(dirtipo+"\\"+user+".txt");
        fichero.deleteOnExit();
        if (fichero.delete())
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        else
            System.out.println("El fichero no puede ser borrado");
    }

    public void renombrar(String Iname, String Irol, String Nname){
        dirtipo= dir+"\\["+Irol+"]";
        File Iarchivo=new File(dirtipo+"\\"+Iname+".txt");
        File Narchivo=new File(dirtipo+"\\"+Nname+".txt");

        if (Iarchivo.renameTo(Narchivo)) {
            System.out.println("archivo renombrado");
        } else {
            System.out.println("");
        }

    }
    public void cambiotipo(File src, File dest) throws IOException {
        Files.move(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}
