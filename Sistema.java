import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Espacio> espacios = new ArrayList<>();
    public static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void cargarUsuarios(String nombreArchivoUsuario, String nombreArchivoEstudiante, String nombreArchivoProfesor, String nombreArchivoAdministrador){
        ArrayList<String> UsuarioDatos = manejoArchivos.LeeFichero(nombreArchivoUsuario);
        ArrayList<String> EstudianteDatos = manejoArchivos.LeeFichero(nombreArchivoEstudiante);
        ArrayList<String> ProfesorDatos = manejoArchivos.LeeFichero(nombreArchivoProfesor);
        ArrayList<String> AdministradorDatos = manejoArchivos.LeeFichero(nombreArchivoAdministrador);
        for (String s : UsuarioDatos){
            String [] datosUser = s.split("|");
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
                    String [] datosPr = c.split("|");
                    if(datosPr[0] == codigoUnico){
                        String facultad = datosPr[4];
                        ArrayList<String> materias = new ArrayList<>();
                        String [] materiasArreglo = datosPr[5].split(",");
                        for (String materia : materiasArreglo){
                            materias.add(materia);
                        }
                        Sistema.usuarios.add(new Profesor(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo, facultad, materias));
                    }
                }
                break;
                case "E":
                for (String d : EstudianteDatos){
                    String [] datosEs = d.split("|");
                    if(datosEs[0] == codigoUnico){
                        int numMatricula = Integer.parseInt(datosEs[4]);
                        String carrera = datosEs[5];
                        Sistema.usuarios.add(new Estudiante(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo, numMatricula, carrera));
                    }
                }
                break;
                case "A":
                for (String x : AdministradorDatos){
                    String [] datosAd = x.split("|");
                    if(datosAd[0] == codigoUnico){
                        Cargo cargoAdmin = Cargo.valueOf(datosAd[5].toUpperCase());
                       // Cargo cargoAdmin = (Cargo) datosAd[4];
                        Sistema.usuarios.add(new Administrador(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo, cargoAdmin));
                    }
                }
            }
        }
    }

    public static void cargarEspacios(String nombreArchivoEspacios){
       ArrayList<String> EspacioDatos = manejoArchivos.LeeFichero(nombreArchivoEspacios);
       for (String z : EspacioDatos){
        String [] datosEspacio = z.split("|");
        String codigoUnico = datosEspacio[0];
        TipoEspacio tipoDeEspacio = TipoEspacio.valueOf(datosEspacio[1].toUpperCase());
        String nombre = datosEspacio[2];
        int capacidad = Integer.parseInt(datosEspacio[3]);
        EstadoEspacio estado = EstadoEspacio.valueOf(datosEspacio[4].toUpperCase());
        Sistema.espacios.add(new Espacio(codigoUnico,tipoDeEspacio,nombre,capacidad,estado));
       }
    }

    public static void cargarReservas(ArrayList<Reserva> reservas){
        Sistema.reservas = reservas;
    }

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

    public static void mostrarMenu(Usuario usuario){
        Scanner sc = new Scanner(System.in);
            if (usuario instanceof Estudiante){
                Estudiante e = (Estudiante) usuario;
                System.out.println("1. Reservar");
                System.out.println("2. Consultar Reserva");
                String opcion = sc.nextLine();
                if (opcion.equalsIgnoreCase("Reservar") || Integer.parseInt(opcion) == 1){
                    e.gestionarReserva();
                }else{
                    e.consultarReserva();
                }
            }else if(usuario instanceof Profesor){
                Profesor p = (Profesor) usuario;
                System.out.println("1. Reservar");
                System.out.println("2. Consultar Reserva");
                String opcion = sc.nextLine();
                if (opcion.equalsIgnoreCase("Reservar") || Integer.parseInt(opcion) == 1){
                    p.gestionarReserva();
                }else{
                    p.consultarReserva();
                }
            }else{
                Administrador a = (Administrador) usuario;
                System.out.println("1. Reservar");
                System.out.println("2. Consultar Reserva");
                String opcion = sc.nextLine();
                if (opcion.equalsIgnoreCase("Reservar") || Integer.parseInt(opcion) == 1){
                    a.gestionarReserva();
                }else{
                    a.consultarReserva();
                }
            }
            sc.close();
        }

        public static void main(String[] args){
           Sistema.cargarUsuarios("usuario.txt", "estudiantes.txt", "profesores.txt", "administradores.txt");
           Sistema.cargarEspacios("espacios.txt");
        }
    }

