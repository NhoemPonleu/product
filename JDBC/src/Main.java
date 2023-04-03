import connection.Datasource;
import connection.Drivermanager;
import model.Student;
import org.postgresql.core.Query;
import org.postgresql.sspi.ISSPIClient;
import view.View;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;
import java.util.stream.StreamSupport;

public class Main {
    private static Drivermanager drivermanager ;
    private static final Set<Student> list = new HashSet<>();
    private static Datasource datasource = new Datasource();
    public static void main(String[] args) throws SQLException {
        View view = new View();
        Scanner scanner = new Scanner(System.in);
        drivermanager = new Drivermanager();
        datasource = new Datasource();
        //
        String name;
        Integer age;
        Integer id;
        //
        Integer choice = 0;
        Integer newId=0;
        view.view();
        System.out.print(">> Choose: ");
        //
        try{
            choice = Integer.parseInt(scanner.nextLine());
        }catch (Exception exception) {
            System.out.println(">>> Number only!!!<<< ");
        }
        switch (choice){
            case 1->{
                System.out.print("Insert name: ");
                name  =scanner.nextLine();
                System.out.print("Insert age: ");
                age = scanner.nextInt();
                System.out.print("Insert ID: ");
                id = scanner.nextInt();
                newId = id;
                Student student = new Student(name,age,id);
                insert(student);
                System.exit(0);
            }
            case 2->{
                viewAllData();
                System.exit(0);
            }
            case 3->{
                System.out.print("Insert name: ");
                name = scanner.nextLine();
                System.out.print("Insert age: ");
                age = scanner.nextInt();
                System.out.print("Insert ID to Update: ");
                id = scanner.nextInt();
                Student student = new Student(name,age,id);
                update(student);
            }
            case 4->{
                System.out.print("Insert ID to delete: ");
                Integer id1 = scanner.nextInt();
                deleteById(id1);
                System.exit(0);
            }
            case 5->{
                System.out.print("Insert ID to select: ");
                Integer id2 = scanner.nextInt();
                selectById(id2);
                System.exit(0);
            }
            case 6->{
                String name_search = null;
                System.out.print("Insert name to select: ");
                name = scanner.nextLine();
                selectByName(name_search);
            }
        }
    }

    private static void insert(Student student){
        try(Connection connection = datasource.datasource().getConnection()){
            //Insert
            String insert = "INSERT INTO student (name, age, id) values(?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1,student.name());
            statement.setInt(2,student.age());
            statement.setInt(3,student.id());
            int resultSet = statement.executeUpdate();
            System.out.println(resultSet +" row affected!!!");
            ResultSet resultSet1 = statement.executeQuery();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    private static void viewAllData(){
        //Drive manager
//        Datasource
        try(Connection connection = datasource.datasource().getConnection()){
            //1. Create SQL Statement
            String select = "SELECT *FROM student ";
            PreparedStatement statement = connection.prepareCall(select);
            //2. Execute SQL Statement
            ResultSet resultSet = statement.executeQuery();
            //3. Process result with ResultSet
            //
            while (resultSet.next()){
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                Integer id = resultSet.getInt("id");
                list.add(new Student(name,age, id));
            }
            System.out.println("====================================================");
            list.forEach(System.out::println);
            System.out.println("====================================================");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    //update
    private static void update(Student student){
        try(Connection connection = datasource.datasource().getConnection()){
            //Update
            String update = "UPDATE student set name = ?, age = ? where id = ?";
            PreparedStatement statement = connection.prepareCall(update);
            statement.setString(1,student.name());
            statement.setInt(2,student.age());
            statement.setInt(3,student.id());
            int resultUpdate = statement.executeUpdate();
            System.out.println(resultUpdate +" row affected!!!");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        System.out.println(">> Updated successfully <<".toUpperCase());
    }
    //delete
    private static void deleteById(Integer id){
        try(Connection connection = datasource.datasource().getConnection()) {
            String delete = "Delete from student where student_id = ?";
            PreparedStatement preparedStatement = connection.prepareCall(delete);
            preparedStatement.setInt(1,id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println(resultSet + " row affected!!!");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        System.out.println(">> Deleted data by ID Successfully <<".toUpperCase());
    }
    //Select by id
    public static void selectById(Integer id){
        try(Connection connection = datasource.datasource().getConnection()) {
            String selectById = "SELECT *from student where student_id = ?";
            PreparedStatement preparedStatement = connection.prepareCall(selectById);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //output
            while (resultSet.next()){
                String name = resultSet.getString("student_name");
                Integer age = resultSet.getInt("student_age");
                list.add(new Student(name,age, id));
            }
            list.forEach(System.out::println);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    //selectByName
    public static void selectByName(String name){
        try(Connection connection = datasource.datasource().getConnection()) {
            String selectById = "SELECT *from student where name = ?";
            PreparedStatement preparedStatement = connection.prepareCall(selectById);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            //output
            while (resultSet.next()){
                Integer age = resultSet.getInt("age");
                Integer id = resultSet.getInt("id");
                list.add(new Student(name,age, id));
            }
            list.forEach(System.out::println);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}