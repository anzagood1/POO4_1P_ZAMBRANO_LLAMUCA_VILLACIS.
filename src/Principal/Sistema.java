package com.example.Principal;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import com.example.Personas.*;
import com.example.Funcionalidades.*;
import com.example.ENUMS.TipoEspacio;
import com.example.ENUMS.EstadoEspacio;
import com.example.ENUMS.Cargo;
import com.example.ENUMS.EstadoReserva;

public class Sistema {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Espacio> espacios = new ArrayList<>();
    public static ArrayList<Reserva> reservas = new ArrayList<>();

    /**
     * Método para cargar los datos de los usuarios contenidos en archivos.
     * @param nombreArchivoUsuario archivo de los usuarios.
     * @param nombreArchivoEstudiante archivo de los estudiantes.
     * @param nombreArchivoProfesor archivo de los profesores.
     * @param nombreArchivoAdministrador archivo de los administradores.
     */
    public static void cargarUsuarios(String nombreArchivoUsuario, String nombreArchivoEstudiante, String nombreArchivoProfesor, String nombreArchivoAdministrador){
        ArrayList<String> UsuarioDatos = manejoArchivos.LeeFichero(nombreArchivoUsuario);
        ArrayList<String> EstudianteDatos = manejoArchivos.LeeFichero(nombreArchivoEstudiante);
        ArrayList<String> ProfesorDatos = manejoArchivos.LeeFichero(nombreArchivoProfesor);
        ArrayList<String> AdministradorDatos = manejoArchivos.LeeFichero(nombreArchivoAdministrador);
        for (String s : UsuarioDatos){
            String [] datosUser = s.split("\\|");
            String codigoUnico = datosUser[0].trim();
            String cedula = datosUser[1].trim();
            String nombres = datosUser[2].trim();
            String apellidos = datosUser[3].trim();
            String usuario = datosUser[4].trim();                                    
            String contrasenia = datosUser[5].trim();
            String correo = datosUser[6].trim();
            String tipo = datosUser[7].trim();
            switch (tipo){
                case "P":
                for (String c : ProfesorDatos){
                    String [] datosPr = c.split("\\|");
                    if(datosPr[0].trim().equals(codigoUnico)){
                        String facultad = datosPr[4].trim();
                        ArrayList<String> materias = new ArrayList<>();
                        String materiasString = datosPr[5];
                        String [] materiasArreglo = materiasString.split(",");
                        for (String materia : materiasArreglo){
                            materias.add(materia.trim());
                        }
                        Sistema.usuarios.add(new Profesor(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo, facultad, materias));
                    }
                }
                break;
                case "E":
                for (String d : EstudianteDatos){
                    String [] datosEs = d.split("\\|");
                    if(datosEs[0].trim().equals(codigoUnico)){
                        int numMatricula = Integer.parseInt(datosEs[4].trim());
                        String carrera = datosEs[5].trim();
                        Sistema.usuarios.add(new Estudiante(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo, numMatricula, carrera));
                    }
                }
                break;
                case "A":
                for (String x : AdministradorDatos){
                    String [] datosAd = x.split("\\|");
                    if(datosAd[0].trim().equals(codigoUnico)){
                        Cargo cargoAdmin = Cargo.valueOf(datosAd[4].trim().replace(" ", "_").toUpperCase());
                        Sistema.usuarios.add(new Administrador(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo, cargoAdmin));
                    }
                }
                break;
            }
        }
    }

    /**
     * Método para cargar los datos de los espacios contenidos en su archivo.
     * @param nombreArchivoEspacios archivo que contiene los espacios.
     */
    public static void cargarEspacios(String nombreArchivoEspacios){
       ArrayList<String> EspacioDatos = manejoArchivos.LeeFichero(nombreArchivoEspacios);
       for (String z : EspacioDatos){
        String [] datosEspacio = z.split("\\|");
        String codigoUnico = datosEspacio[0].trim();
        String tipoEs = datosEspacio[1].trim();
        TipoEspacio tipoDeEspacio = TipoEspacio.valueOf(tipoEs);
        String nombre = datosEspacio[2].trim();
        int capacidad = Integer.parseInt(datosEspacio[3].trim());
        EstadoEspacio estado = EstadoEspacio.valueOf(datosEspacio[4].trim().toUpperCase());
        Sistema.espacios.add(new Espacio(codigoUnico,tipoDeEspacio,nombre,capacidad,estado));
       }
    }

    /**
     * Método que carga los datos de las reservas contenidas en su archivo.
     * @param reservas archivo que contiene las reservas.
     */
    public static void cargarReservas(String nombreArchivoReservas){
        ArrayList<String> ReservaDatos = manejoArchivos.LeeFichero(nombreArchivoReservas);
        for(String r : ReservaDatos){
            String [] datosEspacio = r.split("\\|");
            int codigoReserva = Integer.parseInt(datosEspacio[0].trim());
            String codigoUsuario = datosEspacio[1].trim();
            String cedulaUsuario = datosEspacio[2].trim();
            LocalDate fechaReserva = LocalDate.parse(datosEspacio[3].trim());
            String codigoUnicoEspacio = datosEspacio[4].trim();
            TipoEspacio tipoDeEspacio = TipoEspacio.valueOf(datosEspacio[5].trim().toUpperCase());
            EstadoReserva estadoDeLaReserva = EstadoReserva.valueOf(datosEspacio[6].trim().toUpperCase());
            String motivoDeLaReserva = datosEspacio[7];
            reservas.add(new Reserva(codigoReserva,codigoUnicoEspacio,fechaReserva,tipoDeEspacio,estadoDeLaReserva,motivoDeLaReserva,codigoUsuario,cedulaUsuario));
        }
    }

    /**
     * método para que el usuario inicie sesión.
     * @return retorna null si no encuentra ningún usuario coincidente.
     */
    public static Usuario iniciarSesion(){
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contrasenia: ");
        String contrasenia = sc.nextLine();
        for(Usuario usuario : usuarios){
            if (usuario.getUsuario().equalsIgnoreCase(user) && usuario.getContrasenia().equals(contrasenia)){
                return usuario; 
            }
        }
        return null;
    }

    /**
     * método principal para que el sistema corra.
     * @param usuario usuario que usa el sistema.
     */
    public static void mostrarMenu(Usuario usuario){
            if (usuario instanceof Estudiante){
                Estudiante e = (Estudiante) usuario;
                String opcion;
                do {
                    System.out.println("1. Reservar");
                    System.out.println("2. Consultar Reserva");
                    System.out.println("3. Cerrar sesion");
                    opcion = sc.nextLine();
                    if (opcion.equalsIgnoreCase("Reservar") || Integer.parseInt(opcion) == 1){
                        e.gestionarReserva();
                    }else if(opcion.equalsIgnoreCase("Consultar Reserva") || Integer.parseInt(opcion) == 2){
                        e.consultarReserva();
                    }else if(opcion.equalsIgnoreCase("Cerrar sesion") || Integer.parseInt(opcion) == 3){
                        System.out.println("Cerrando sesion...");
                    }
                } while (!opcion.equalsIgnoreCase("Cerrar sesion") && Integer.parseInt(opcion) != 3);
                
            }else if(usuario instanceof Profesor){
                Profesor p = (Profesor) usuario;
                String opcion;
                do {
                    System.out.println("1. Reservar");
                    System.out.println("2. Consultar Reserva");
                    System.out.println("3. Cerrar sesion");
                    opcion = sc.nextLine();
                        if (opcion.equalsIgnoreCase("Reservar") || Integer.parseInt(opcion) == 1){
                    p.gestionarReserva();
                    }else if(opcion.equalsIgnoreCase("Consultar Reserva") || Integer.parseInt(opcion) == 2){
                        p.consultarReserva();
                    }else if(opcion.equalsIgnoreCase("Cerrar sesion") || Integer.parseInt(opcion) == 3){
                        System.out.println("Cerrando sesion...");
                    }
                } while (opcion != "Cerrar sesion" && Integer.parseInt(opcion) != 3);
                
            }else{
                Administrador a = (Administrador) usuario;
                String opcion;
                do {
                    System.out.println("1. Gestionar reserva");
                    System.out.println("2. Consultar Reserva");
                    System.out.println("3. Cerrar sesion");
                    opcion = sc.nextLine();
                    if (opcion.equalsIgnoreCase("Gestionar reserva") || Integer.parseInt(opcion) == 1){
                        a.gestionarReserva();
                    }else if(opcion.equalsIgnoreCase("Consultar reserva") || Integer.parseInt(opcion) == 2){
                        a.consultarReserva();
                    }else if(opcion.equalsIgnoreCase("Cerrar sesion") || Integer.parseInt(opcion) == 3){
                        System.out.println("Cerrando sesion...");
                    }
                } while (opcion != "Cerrar sesion" && Integer.parseInt(opcion) != 3);
            }

        }


        public static void main(String[] args){
           cargarUsuarios("usuarios.txt", "estudiantes.txt", "profesores.txt", "administradores.txt");
           cargarEspacios("espacios.txt");
           cargarReservas("reservas.txt");
           
           Usuario usuario = iniciarSesion();
           int i = 0;
           while(i == 0){
           if (usuario == null){
            System.out.println("Usuario o contraseña incorrectos");
            usuario = iniciarSesion();
           }else{  
             mostrarMenu(usuario);
             i++;
           }
        }
        }
    }



