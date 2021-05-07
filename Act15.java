package act15;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Act15 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/act15";
        String user = "java";
        String password = "password";
        Connection coneccion = DriverManager.getConnection(url, user, password); 
        Statement stmt = coneccion.createStatement();
        String sql,nombre,Apellido;
        int opc;
        
        do {
            System.out.print("1.Insertar\n2.Eliminar\n3.Mostrar autor\n4.Mostrar todos los autores\n5.Modificar\nSeleccione una opcion:");
            opc = in.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("Id de Autor: ");
                    int id = in.nextInt();
                    in.nextLine();
                    System.out.println("Nombre del Autor: ");
                    nombre = in.nextLine();
                    System.out.println("Apellido del Autor: ");
                    Apellido = in.nextLine();
                    sql = "INSERT INTO autores\n"
                            + "(idAutor,nombre,apellidoPaterno)\n"
                            + "VALUES\n"
                            + "(?, ?, ?);";

                    PreparedStatement preparedStmt = coneccion.prepareStatement(sql);
                    
                    
                    preparedStmt.setInt(1, id);
                    preparedStmt.setString(2, nombre);
                    preparedStmt.setString(3, Apellido);
                    preparedStmt.execute();
                    break;
                case 2:
                    sql = "delete from autores where idAutor = ?";
                    preparedStmt = coneccion.prepareStatement(sql);
                    System.out.println("Id de Autor a eliminar: ");
                    id = in.nextInt();
                    preparedStmt.setInt(1, id);
                    preparedStmt.execute();
                    break;
                case 3:
                    System.out.println("Id de Autor a mostrar: ");
                    id = in.nextInt();
                    in.nextLine();
                    sql = "select * from autores where idAutor = "+id;
                    ResultSet resultado = stmt.executeQuery(sql);
                    System.out.println("IdAutor:\tNombre Autor:\tApellido Autor:");
                    while (resultado.next()) {
                        System.out.println(resultado.getInt("idAutor") + "\t\t" + resultado.getString("nombre")
                                + "\t\t" + resultado.getString("apellidoPaterno"));
                    }
                    System.out.println();
                    break;
                case 4:
                    stmt = coneccion.createStatement();
                    sql = "select * from autores;";
                    resultado = stmt.executeQuery(sql);
                    System.out.println("IdAutor:\tNombre Autor:\tApellido Autor:");
                    while (resultado.next()) {
                        System.out.println(resultado.getInt("idAutor") + "\t\t" + resultado.getString("nombre")
                                + "\t\t" + resultado.getString("apellidoPaterno"));
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Id de Autor a modificar: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.println("1.Modificar Nombre\n2.Modificar Apellido\nSeleccione una opcion:");
                    int desc = in.nextInt();
                    in.nextLine();
                    if(desc == 1){
                        System.out.println("Nombre del Autor: ");
                        nombre = in.nextLine();
                        sql = "update autores set nombre = ? where idAutor = ?";
                        preparedStmt = coneccion.prepareStatement(sql);
                        preparedStmt.setString(1, nombre);
                        preparedStmt.setInt(2, id);
                        preparedStmt.execute();                      
                    }else{
                        System.out.println("Apellido del Autor: ");
                        Apellido = in.nextLine();
                        sql = "update autores set apellidoPaterno = ? where idAutor = ?";
                        preparedStmt = coneccion.prepareStatement(sql);
                        preparedStmt.setString(1, Apellido);
                        preparedStmt.setInt(2, id);
                        preparedStmt.execute();
                    }
                    break;
                default:
                    System.out.println("No existe esa opcion");
                    break;
            }
        } while (opc != 0);
    } 
}

