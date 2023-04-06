package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                if (fxmlFile.equals("home-page.fxml")) {

                    LoggedInController loggedInController = loader.getController();
                    loggedInController.setUserInformation(username);
                    DataSingleton data = DataSingleton.getInstance();
                    data.setUserName(username);

                } else if (fxmlFile.equals("signup-donor.fxml")) {

                    SignUpDonorController signupdonorcontroller = loader.getController();
                    signupdonorcontroller.setUsername(username);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 950, 700));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, int NID, String firstName, String lastName)  //sign up to logged in
    {
        Connection conn = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psCheckNIDExists = null;
        PreparedStatement psCheckNIDValid = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "MySQLPass123");

            psCheckUserExists = conn.prepareStatement("SELECT * FROM users2 WHERE username = ?");
            psCheckUserExists.setString(1, username);
            rs = psCheckUserExists.executeQuery();

            psCheckNIDExists = conn.prepareStatement("SELECT * FROM users2 WHERE NID = ?");
            psCheckNIDExists.setInt(1, NID);
            rs1 = psCheckNIDExists.executeQuery();

            psCheckNIDValid = conn.prepareStatement("SELECT * FROM niddatabase WHERE NID = ?");
            psCheckNIDValid.setInt(1, NID);
            rs2 = psCheckNIDValid.executeQuery();

            if (rs.isBeforeFirst())  // if true then already in use, false - not in table
            {
                System.out.println("User already exists.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else if (rs1.isBeforeFirst()) {
                System.out.println("User with this NID already exists.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot create an account with this NID.");
                alert.show();
            } else if (!rs2.isBeforeFirst()) { // not in database - false
                System.out.println("Invalid NID");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot create an account with this NID.");
                alert.show();
            } else {  // NID valid
                while (rs2.next()) {
                    String retrievedfirstName = rs2.getString("firstName");
                    String retrievedlastName = rs2.getString("lastName");

                    if (retrievedfirstName.equals(firstName) && retrievedlastName.equals(lastName)) { //firstName and lastName matches

                        //hashing password
                        String salt = BCrypt.gensalt();
                        String hashedPassword = BCrypt.hashpw(password, salt);


                        psInsert = conn.prepareStatement("INSERT INTO users2 (firstName,lastName,username,password,NID,salt) VALUES (?,?,?,?,?,?)");
                        psInsert.setString(1, firstName);
                        psInsert.setString(2, lastName);
                        psInsert.setString(3, username);
                        psInsert.setString(4, hashedPassword);
                        psInsert.setInt(5, NID);
                        psInsert.setString(6, salt);

                        psInsert.executeUpdate();

                        changeScene(event, "home-page.fxml", "Welcome!", username);
                    } else {
                        System.out.println("Credentials do not match with NID.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Incorrect credentials.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (rs2 != null) {
                try {
                    rs2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckNIDExists != null) {
                try {
                    psCheckNIDExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckNIDValid != null) {
                try {
                    psCheckNIDValid.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "MySQLPass123");
            ps = conn.prepareStatement("SELECT password,salt FROM users2 WHERE username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {  //not in table
                System.out.println("User does not exist.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or password is incorrect!");
                alert.show();
            } else {
                while (rs.next()) {
                    String retrievedPassword = rs.getString("password");
                    String salt = rs.getString("salt");
                    String hashedPasswordToCheck = BCrypt.hashpw(password, salt);

                    if (retrievedPassword.equals(hashedPasswordToCheck)) {
                        DataSingleton.userName = username;
                        changeScene(event, "home-page.fxml", "Welcome!", username);
                    } else {   // username in table but password incorrect
                        System.out.println("Incorrect password.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username or password is incorrect!");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void signUpDonor(ActionEvent event, String username, int age, String phone, String gender, String blood, String location, String lastDonated, String condition)
    {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement psInsert = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
           // System.out.println("inside func");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "MySQLPass123");
            ps2 = conn.prepareStatement("SELECT username FROM donors WHERE username = ?");
            ps2.setString(1, username);
            rs2 = ps2.executeQuery();

            if(rs2.isBeforeFirst()) // already donor
            {
                System.out.println("You have already signed up!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You are already a donor!");
                alert.show();
            }
            else {
                ps1 = conn.prepareStatement("SELECT firstName, lastName FROM users2 WHERE username = ?");
                //  ps2 = conn.prepareStatement("SELECT lastName FROM users2 WHERE username = ?");
                ps1.setString(1, username);
                //  ps1.setString(1, username);
                rs1 = ps1.executeQuery();
                //  rs2 = ps1.executeQuery();

                System.out.println(username);

                boolean ifEligible;
                if (lastDonated.equals("< 3 months")) {
                    ifEligible = false;
                } else {
                    ifEligible = true;
                }

                //  System.out.println(ifEligible + lastDonated);

                while (rs1.next()) {
                    String retrievedFirstName;
                    String retrievedLastName;

                    retrievedFirstName = rs1.getString("firstName");
                    retrievedLastName = rs1.getString("lastName");
                    System.out.println(retrievedFirstName);

                    System.out.println(retrievedLastName);
                    psInsert = conn.prepareStatement("INSERT INTO donors (username,firstName,lastName,age,phone,gender,bloodGroup,location,lastDonated,conditions,ifEligible) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                    psInsert.setString(1, username);
                    psInsert.setString(2, retrievedFirstName);
                    psInsert.setString(3, retrievedLastName);
                    psInsert.setInt(4, age);
                    psInsert.setString(5, phone);
                    psInsert.setString(6, gender);
                    psInsert.setString(7, blood);
                    psInsert.setString(8, location);
                    psInsert.setString(9, lastDonated);
                    psInsert.setString(10, condition);
                    psInsert.setBoolean(11, ifEligible);

                    psInsert.executeUpdate();

                    System.out.println("You have successfully registered as a donor.");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("You are now a registered donor!");
                    alert.show();

                    changeScene(event, "home-page.fxml", "Welcome!", username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void submitForm(ActionEvent event,String place,String firstName,String lastName,String dob,String blood,String gender,String occ,String phone,String email,String address,String lastDonated,String tog1,String tog2,String tog3,String tog4,String tog5,String tog6,String tog7,String appdate) throws SQLException {
        Connection conn = null;
        PreparedStatement ps1 = null;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "MySQLPass123");

            ps1 = conn.prepareStatement("INSERT INTO forms (place,firstName,lastName,dob,blood,gender,occupation,phone,email,address,lastDonated,majorOp,vaccination,medication,antibiotics,pierceOrTattoo,heartCondition,diabetes,appdate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps1.setString(1, place);
            ps1.setString(2, firstName);
            ps1.setString(3, lastName);
            ps1.setString(4, dob);
            ps1.setString(5, blood);
            ps1.setString(6, gender);
            ps1.setString(7, occ);
            ps1.setString(8, phone);
            ps1.setString(9, email);
            ps1.setString(10, address);
            ps1.setString(11, lastDonated);
            ps1.setString(12, tog1);
            ps1.setString(13, tog2);
            ps1.setString(14, tog3);
            ps1.setString(15, tog4);
            ps1.setString(16, tog5);
            ps1.setString(17, tog6);
            ps1.setString(18, tog7);
            ps1.setString(19, appdate);
            ps1.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(ps1 != null){
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
