package Serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonina Hales
 */
public class Fichero {

    //creamos dos ficheros APROBADOS y SUSPENSOS
    private File aprobados = new File("APROBADOS.dat");
    private File suspensos = new File("SUSPENSOS.dat");

    //creamos dos ArrayList donde guardamos los objetos alumnos suspensos o aprobados
    private static ArrayList<Alumno> alumnosAprobados = new ArrayList<>();
    private static ArrayList<Alumno> alumnosSuspensos = new ArrayList<>();

    public void grabarRegistroAlumno(String nombreFichero, Alumno alumno) throws FileNotFoundException, IOException {
        //grabamos el alumno en el fichero APROBADOS
        if (nombreFichero.equalsIgnoreCase("APROBADOS")) {
            if (!aprobados.exists()) {
                aprobados.createNewFile();
                System.out.println("Nuevo archivo creado");
            }
            //creamos el flujo
            FileOutputStream fos = new FileOutputStream(aprobados);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //anadimos alumnos en el ArrayList
            alumnosAprobados.add(alumno);
            oos.writeObject(alumnosAprobados);
            oos.close();
            fos.close();

            System.out.println("Los datos han sido grabados con exito.");

            //grabamos el alumno en el fichero SUSPENSOS
        } else if (nombreFichero.equalsIgnoreCase("SUSPENSOS")) {
            if (!suspensos.exists()) {
                suspensos.createNewFile();
                System.out.println("Nuevo archivo creado");
            }
            FileOutputStream fos = new FileOutputStream(suspensos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            alumnosSuspensos.add(alumno);
            oos.writeObject(alumnosSuspensos);
            oos.close();
            fos.close();
            System.out.println("Los datos han sido grabado con exito.");
            //si el nombre del fichero esta mal escrito y no es ni SUSPENSOS ni APROBADOS
        } else {
            System.out.println("No existe el nombre del fichero.");
        }
    }

    //el metodo que permite leer los datos grabados en el fichero
    public void leerFichero(String nombreFichero) throws FileNotFoundException, IOException, ClassNotFoundException {

        //leemos los datos del fichero APROBADOS
        if (nombreFichero.equalsIgnoreCase("APROBADOS")) {
            if(aprobados.canRead()){//comprobamos si lo podemos leer
            FileInputStream fis = new FileInputStream(aprobados);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //leemos el objeto que se encuentra en el archivo y lo transformamos en un ArrayList mediante casting
            alumnosAprobados = (ArrayList<Alumno>) ois.readObject();
            ois.close();
            fis.close();

            //si el ArrayList no esta vacio lo recorremos mediante un bucle for-each
            if (!alumnosAprobados.isEmpty()) {
                int contador = 1;
                alumnosAprobados.forEach(a -> {
                    System.out.println("Alumno nº " + contador + ": " + a);
                });
            } else {
                System.out.println("El archivo esta vacio.");
            }

            //leemos los datos del fichero SUSPENSOS
        } else{
                System.out.println("No podemos leer el fichero.");
            }
            
        }  else if (nombreFichero.equals("SUSPENSOS")) {
            if(suspensos.canRead()){//comprobamos si lo podemos leer
            FileInputStream fis = new FileInputStream(suspensos);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //leemos el objeto que se encuentra en el archivo y lo transformamos en un ArrayList mediante casting
            alumnosSuspensos = (ArrayList<Alumno>) ois.readObject();
            ois.close();
            fis.close();

            //si el ArrayList no esta vacio lo recorremos mediante un bucle for-each
            if (!alumnosSuspensos.isEmpty()) {
                int contador = 1;
                alumnosSuspensos.forEach(a -> {
                    System.out.println("Alumno nº " + contador + ": " + a);
                });
                
            } else{
                System.out.println("No podemos leer el fichero.");
            }
            } else {
                System.out.println("El archivo esta vacio.");
            }

//si introducido nombre del fichero no existe   
        } else {
            System.out.println("El nombre del fichero introducido no es correcto.");
        }

    }

}
