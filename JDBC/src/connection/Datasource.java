package connection;

import org.postgresql.ds.PGSimpleDataSource;

import java.awt.dnd.DropTarget;
import java.util.Scanner;

public class Datasource {
    private String name, dbname, password;
    //insert login form
//    public Datasource(){
//        Scanner scanner = new Scanner(System.in);
//        do{
//            //username
//            do {
//                System.out.print("Insert username: ");
//                name = scanner.nextLine();
//            }while (name.isBlank());
//            //password
//            do {
//                System.out.print("Insert password: ");
//                password = scanner.nextLine();
//            }while (name.isBlank());
//            dbname = "postgres";
//        }while (name.isBlank()|| password.isBlank()||dbname.isBlank());
//    }//
    public PGSimpleDataSource datasource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("ponleu");
        dataSource.setDatabaseName("postgres");
        return dataSource;
    }
}
