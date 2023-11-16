/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package proyecto3semestre;

import com.google.zxing.WriterException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Sergio Diaz
 */
public class TakingoffdreamsPI {

    public static void main(String[] args) throws WriterException, IOException {
        System.out.println("Bienvenido a Despegando Sueños");
        System.out.println("1: Generar codigo aleatorio");
        System.out.println("2: Usar codigo");
        System.out.println("Ingrese una opcion");
        int opcion;
        Scanner sc= new Scanner(System.in);
        opcion= sc.nextInt();
        if(opcion==1){
            generarCodigo();
            
        }
    }

    private static void generarCodigo()
    {
       System.out.println("Ingrese los siguientes datos para generar su codigo ");
        
        Scanner sc= new Scanner(System.in);
        String nombre,apellido, aerolinea, ciudadDeOrigen, ciudadDeDestino;
        int cedula;
        System.out.println("Nombre: ");
        nombre= sc.next();
        System.out.println("Apellido: ");
        apellido=sc.next();
        System.out.println("Cedula: ");
        cedula=sc.nextInt();
        System.out.println("Aerolinea: ");
        aerolinea=sc.next();
        System.out.println("Ciudad de origen: ");
        ciudadDeOrigen=sc.next();
        System.out.println("Cuidad de destino: ");
        ciudadDeDestino=sc.next();
     char caracteres []= {'A','B','C','D','E','F','G','H','I','J','K','L','M'
        ,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3',
        '4','5','6','7','8','9'};
        String codigo="";
        Random random= new Random();
        for (int i = 0; i < 10; i++) {
            codigo= codigo+caracteres[random.nextInt(caracteres.length-1)];
            
        }
            System.out.println("su codigo es: "+codigo);
        
       
    }
}
    