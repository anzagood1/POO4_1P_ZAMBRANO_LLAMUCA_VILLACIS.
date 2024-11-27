package Principal;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import Personas;
import Funcionalidades;
import ENUMS;

public class Sistema {
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
            String [] datosUser = s.split(" | ");
            String codigoUnico = datosUser[0];
            String cedula = datosUser[1];
            String nombres = datosUser[2];
            String apellidos = datosUser[3];
            String usuario = datosUser[4];                                    
            String contrasenia = datosUser[5];
            String correo = datosUser[6];
            String tipo = datosUser[7];
            switch (tipo){
                case "P":
                for (String c : ProfesorDatos){
                    String [] datosPr = c.split(" | ");
                    if(datosPr[0] == codigoUnico){
                        String facultad = datosPr[4];
                        ArrayList<String> materias = new ArrayList<>();
                        String [] materiasArreglo = datosPr[5].split(", ");
                        for (String materia : materiasArreglo){
                            materias.add(materia);
                        }
                        Sistema.usuarios.add(new Profesor(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo, facultad, materias));
                    }
                }
                break;
                case "E":
                for (String d : EstudianteDatos){
                    String [] datosEs = d.split(" | ");
                    if(datosEs[0] == codigoUnico){
                        int numMatricula = Integer.parseInt(datosEs[4]);
                        String carrera = datosEs[5];
                        Sistema.usuarios.add(new Estudiante(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo, numMatricula, carrera));
                    }
                }
                break;
                case "A":
                for (String x : AdministradorDatos){
                    String [] datosAd = x.split(" | ");
                    if(datosAd[0] == codigoUnico){
                        Cargo cargoAdmin = Cargo.valueOf(datosAd[5].toUpperCase());
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
        String [] datosEspacio = z.split(" | ");
        String codigoUnico = datosEspacio[0];
        TipoEspacio tipoDeEspacio = TipoEspacio.valueOf(datosEspacio[1].toUpperCase());
        String nombre = datosEspacio[2];
        int capacidad = Integer.parseInt(datosEspacio[3]);
        EstadoEspacio estado = EstadoEspacio.valueOf(datosEspacio[4].toUpperCase());
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
            String [] datosEspacio = r.split(" | ");
            int codigoReserva = Integer.parseInt(datosEspacio[0]);
            String codigoUsuario = datosEspacio[1];
            String cedulaUsuario = datosEspacio[2];
            LocalDate fechaReserva = LocalDate.parse(datosEspacio[3]);
            String codigoUnicoEspacio = datosEspacio[4];
            TipoEspacio tipoDeEspacio = TipoEspacio.valueOf(datosEspacio[5].toUpperCase());
            EstadoReserva estadoDeLaReserva = EstadoReserva.valueOf(datosEspacio[6].toUpperCase());
            String motivoDeLaReserva = datosEspacio[7];
            reservas.add(new Reserva(codigoReserva,codigoUnicoEspacio,fechaReserva,tipoDeEspacio,estadoDeLaReserva,motivoDeLaReserva,codigoUsuario,cedulaUsuario));
        }
    }

    /**
     * método para que el usuario inicie sesión.
     * @return retorna null si no encuentra ningún usuario coincidente.
     */
    public static Usuario iniciarSesion(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contrasenia: ");
        String contrasenia = sc.nextLine();
        sc.close();
        for(Usuario usuario : usuarios){
            if (usuario.getUsuario() == user && usuario.getContrasenia() == contrasenia){
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
        Scanner sc = new Scanner(System.in);
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
                    }else{
                    e.consultarReserva();
                    }
                } while (opcion != "Cerrar sesion" && Integer.parseInt(opcion) != 3);
                
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
                    }else{
                    p.consultarReserva();
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
                    }else{
                        a.consultarReserva();
                    }
                } while (opcion != "Cerrar sesion" && Integer.parseInt(opcion) != 3);
            }
            sc.close();
        }

        public static void main(String[] args){
           cargarUsuarios("usuario.txt", "estudiantes.txt", "profesores.txt", "administradores.txt");
           cargarEspacios("espacios.txt");
           cargarReservas("reservas.txt");
           Usuario usuario = iniciarSesion();
           mostrarMenu(usuario);
        }
    }

