
package Serialization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Antonina Hales
 */
public class Principal {
    //unique identifier for Serializable class
    private static final long serialVersionUID = 1L;
    
    //creamos objeto Scanner para leer 
    static Scanner sc = new Scanner(System.in);
    
    //creamos objeto Alumno para pasarlo serializado
    static Alumno alumno;
    
    //creamos objeto Fichero para pasar objetos
    static Fichero fichero = new Fichero();
    
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        boolean salir=false;
        
        //bucle do-while para que se repita el menu haasta que pulsemos la opcion 4
        do{
           try{
            System.out.println("Menu: ");
            System.out.println("1. Lectura de datos");
            System.out.println("2. Lista de aprobados");
            System.out.println("3. Lista de suspensos");
            System.out.println("4. Salir");
            System.out.println("-----------------------");
            System.out.print("Seleccione una opcion: ");
            
            //leer la opcion elegida. Comprobamos que la opcion elegida es un numero
            int opcion= Integer.parseInt(sc.nextLine());
        
            switch(opcion){
                case 1:leerDatos();
                    break;
                case 2:fichero.leerFichero("APROBADOS");
                    break;
                case 3:fichero.leerFichero("SUSPENSOS");
                    break;
                case 4:
                    System.out.println("Adios!");
                    salir=true;    
                    break;
                default: //mensaje que aparece si eligen un numero fuera del rango 1-4
                    System.out.println("Opcion incorrecta. Introduce un numero de 1 a 4 que corresponde a la opcion deseada");
            }
        }catch(NumberFormatException ex){
            System.out.println("Formato incorrecto. Introduce un numero de 1 a 4 que corresponde a la opcion deseada");
        }
        }while(salir==false); 
        
       
        
    }
    
    
    //el metodo que permite leer los datos del alumno introducidos por teclado
    public static void leerDatos() throws IOException{
        //leemos el nombre y el apellido
        System.out.println("Introduce el nombre del alumno");
        String nombreAlumno= sc.nextLine();
        System.out.println("Introduce el apellido del alumno");
       String apellidoAlumno= sc.nextLine();
        
        //leemos las notas de 3 evaluaciones
        int notaPrimeraEvaluacion=0, notaSegundaEvaluacion=0, notaTerceraEvaluacion=0;
        //nota 1
        boolean recibidoNota1= false;
        do{
            try{
       System.out.println("Introduce la nota de la primera evaluacion: ");
       notaPrimeraEvaluacion = Integer.parseInt(sc.nextLine());
       //comprobamos que la nota introducida esta entre 0 y 10
       if(notaPrimeraEvaluacion >10||notaPrimeraEvaluacion<0){
           System.out.println("La nota tiene que ser entre 0 y 10.");
           return;
       }
       recibidoNota1=true;
            }catch(NumberFormatException ex){
                System.out.println("Introduce la nota en el formato numerico");
            }
        }while(recibidoNota1==false);
        
        
        //nota2
        boolean recibidoNota2= false;
        do{
            try{
       System.out.println("Introduce la nota de la segunda evaluacion: ");
       notaSegundaEvaluacion = Integer.parseInt(sc.nextLine());
       //comprobamos que la nota introducida esta entre 0 y 10
       if(notaSegundaEvaluacion >10||notaSegundaEvaluacion<0){
           System.out.println("La nota tiene que ser entre 0 y 10.");
           return;
       }
       recibidoNota2=true;
            }catch(NumberFormatException ex){
                System.out.println("Introduce la nota en el formato numerico");
            }
        }while(recibidoNota2==false);
        
        
        //nota3
    boolean recibidoNota3= false;
        do{
            try{
       System.out.println("Introduce la nota de la tercera evaluacion: ");
       notaTerceraEvaluacion = Integer.parseInt(sc.nextLine());
       //comprobamos que la nota introducida esta entre 0 y 10
       if(notaTerceraEvaluacion >10||notaTerceraEvaluacion<0){
           System.out.println("La nota tiene que ser entre 0 y 10.");
           return;
       }
       recibidoNota3=true;
            }catch(NumberFormatException ex){
                System.out.println("Introduce la nota en el formato numerico");
            }
        }while(recibidoNota3==false);
        
        
        
       //creamos un objeto Alumno con los datos recibidos y mostramos los datos del alumno
            alumno= new Alumno(nombreAlumno, apellidoAlumno, notaPrimeraEvaluacion, notaSegundaEvaluacion, notaTerceraEvaluacion);
            System.out.println(alumno);
                 
       /*comprobamos si el alumno ha parobado o suspendido el curso usando el metodo calcularApto(int nota1, 
            int nota2, int nota3) y grabamos el objeto Alumno en el fichero APROBADOS o SUSPENSOS
            segun el resultado*/
       if(calcularApto(notaPrimeraEvaluacion, notaSegundaEvaluacion, notaTerceraEvaluacion)){
        fichero.grabarRegistroAlumno("APROBADOS", alumno);
       }else{
           fichero.grabarRegistroAlumno("SUSPENSOS", alumno);
       }
        }
    
        
    public static boolean calcularApto(int nota1, int nota2, int nota3){
        
int resultado = (nota1+nota2+nota3)/3;
        return resultado>5;
}
    
}