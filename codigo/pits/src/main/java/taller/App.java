package taller;

import Enums.ETipoUsuario;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        //BDUsuarios b = new BDUsuarios();
        Usuario tmp = new Usuario(ETipoUsuario.CLIENTE , "C1", "P1");
        // b.addUsr(tmp);
        // tmp = new Usuario(ETipoUsuario.CLIENTE , "C2", "P2");
        // b.addUsr(tmp);
        // b.saveJson();
        //b.loadDB();
        //System.out.println("h");
    } 
}
