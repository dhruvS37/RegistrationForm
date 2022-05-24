package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class RagistrationForm extends Application {

    Scene scene1,scene2;
    Alert a = new Alert(Alert.AlertType.NONE);
    Label blank = new Label();
    @Override
    public void start(Stage stage)  {

//  Header Text
        Text text = new Text("Registration Form");
        HBox hb1 = new HBox(text);
        hb1.setPrefSize(473,30);
        hb1.setAlignment(Pos.CENTER);
        hb1.setStyle("-fx-background-color:BLUE;");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));


//  GridePane for Form element
        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);
        root.relocate(0,31);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:rgb(210,208,208);");


//  Name Lable and TextField
        Label f_name = new Label("First Name");
        Label m_name = new Label("Middle Name");
        Label l_name = new Label("Last Name");
        TextField fnameText = new TextField();
        fnameText.setPromptText("Enter First Name");
        TextField mnameText = new TextField();
        mnameText.setPromptText("Enter Middle Name");
        TextField lnameText = new TextField();
        lnameText.setPromptText("Enter Last Name");
        root.addRow(1, f_name, fnameText);
        root.addRow(2, m_name, mnameText);
        root.addRow(3, l_name, lnameText);


//  Email,MobileNo. Lable and TextField
        Label email = new Label("Email");
        TextField emailText = new TextField();
        emailText.setPromptText("Enter your Email Address");

        Label MOB = new Label("Mobile No.");
        TextField mobText = new TextField();
        mobText.setPromptText("Enter Mobile number");
        Pattern pattern = Pattern.compile(".{0,10}");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        mobText.setTextFormatter(formatter);
        root.addRow(4, email, emailText);
        root.addRow(5,MOB,mobText);


//  ComboBox for Date of Birth
        Label DOB = new Label("Date Of Birth");
        Label Day = new Label("Day");
        Label Month = new Label("Month");
        Label Year = new Label("Year");
        String [] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String [] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        String [] years = {"1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010"};
        ComboBox cBDay = new ComboBox(FXCollections.observableArrayList(days));
        ComboBox cBMonth = new ComboBox(FXCollections.observableArrayList(months));
        ComboBox cBYear = new ComboBox(FXCollections.observableArrayList(years));
        root.addRow(6, DOB);
        root.add(Day,0,7);
        root.add(cBDay,1,7);
        root.add(Month,0,8);
        root.add(cBMonth,1,8);
        root.add(Year,0,9);
        root.add(cBYear,1,9);


//  RadioButton for Gender
        Label gender = new Label("Gender");
        ToggleGroup G = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        male.setToggleGroup(G);
        female.setToggleGroup(G);
        root.addRow(10, gender, male, female);


//  CheckBox for Language
        Label languagesKnows = new Label("Languages knows");
        CheckBox hindi = new CheckBox("Hindi");
        CheckBox gujarati = new CheckBox("Gujarati");
        CheckBox english = new CheckBox("English");
        CheckBox Other = new CheckBox("Other");
        root.addRow(11, languagesKnows, hindi, gujarati);
        root.addRow(12,blank,english,Other);


//  Cancel and submit Button
        Button cancel = new Button("Cancel");
        Button submit = new Button("Submit");
        root.add(cancel,1,14);
        root.add(submit,2,14);
        submit.setStyle("-fx-text-fill: white; -fx-font-family : verdana; -fx-font-size: 20; -fx-background-color: BLUE; ");
        cancel.setStyle("-fx-text-fill: white; -fx-font-family : verdana; -fx-font-size: 20; -fx-background-color: BLUE; ");


//  Welcome Page GridePane
        Label welcomeLable = new Label();
        Button back = new Button("Back");
        back.setStyle("-fx-text-fill: white; -fx-font-family : verdana; -fx-font-size: 20; -fx-background-color: BLUE; ");
        GridPane root2 = new GridPane();
        root2.setAlignment(Pos.CENTER);
        root2.setStyle("-fx-background-color:rgb(210, 208, 208); -fx-font:50px CENTER");


//  Cencel and Submit Action event

        EventHandler<ActionEvent> cancelAction=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fnameText.clear();
                mnameText.clear();
                lnameText.clear();
                emailText.clear();
                mobText.clear();
                cBDay.getSelectionModel().clearSelection();
                cBMonth.getSelectionModel().clearSelection();
                cBYear.getSelectionModel().clearSelection();
                hindi.setSelected(false);
                gujarati.setSelected(false);
                english.setSelected(false);
                male.setSelected(false);
                female.setSelected(false);
            }
        };
        cancel.setOnAction(cancelAction);


        Alert alert = new Alert(Alert.AlertType.NONE);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

//  First name Validation
                String namePattern= "[A-Za-z\\s]+";
                Pattern p = Pattern.compile(namePattern);
                boolean isfNameValid = p.matcher(fnameText.getText()).matches();
                if (!isfNameValid){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Invalid Name");
                    alert.setHeaderText(null);
                    alert.setContentText("First name must be character and not be null");
                    alert.showAndWait();
                }

//  Middel name Validation
                boolean ismNameValid = p.matcher(mnameText.getText()).matches();
                if(!ismNameValid){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Invalid Name");
                    alert.setHeaderText(null);
                    alert.setContentText("Middle name must be character and not be null");
                    alert.showAndWait();
                }

//  Last name Validation
                boolean islNameValid = p.matcher(lnameText.getText()).matches();
                if(!islNameValid){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Invalid Name");
                    alert.setHeaderText(null);
                    alert.setContentText("Last name must be character and not be null");
                    alert.showAndWait();
                }

//  Validation for email address
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern emailPattern = Pattern.compile(emailRegex);
                boolean isEmailValid = emailPattern.matcher(emailText.getText()).matches();
                if(!isEmailValid){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Invalid Email");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Email Address");
                    alert.showAndWait();
                }

//  Validation for mobile number
                String numberRegex = "[6-9][0-9]{9}";
                Pattern numberPattern = Pattern.compile(numberRegex);
                boolean isNumberValid = numberPattern.matcher(mobText.getText()).matches();
                if(!isNumberValid){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Invalid MobileNo.");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter Valid Mobile Number");
                    alert.showAndWait();
                }
//  validation for ComboBox
                boolean d = cBDay.getSelectionModel().isEmpty();
                if(d){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Date Of Birth Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Select the Day");
                    alert.showAndWait();
                }
                boolean m = cBMonth.getSelectionModel().isEmpty();
                if(m){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Date Of Birth Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Select the Month");
                    alert.showAndWait();
                }
                boolean y = cBYear.getSelectionModel().isEmpty();
                if(y) {
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Date Of Birth Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Select the Year");
                    alert.showAndWait();
                }

//  Validation for RadioButton
                boolean isRadioSelected = ((male.isSelected() | female.isSelected()));
                if(!isRadioSelected){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Validate Fields");
                    alert.setHeaderText(null);
                    alert.setContentText("Gender is Mandatory");
                    alert.showAndWait();
                }

//  Validation for CheckBox
                boolean isCheckSelected =  (hindi.isSelected() | gujarati.isSelected() | english.isSelected() | Other.isSelected());
                if(!isCheckSelected) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Validate Fields");
                    alert.setHeaderText(null);
                    alert.setContentText("Select one of the language");
                    alert.showAndWait();
                }

                if(isfNameValid && ismNameValid && islNameValid && !d && !m && !y && isRadioSelected && isCheckSelected && isEmailValid && isNumberValid ){
                    stage.setScene(scene2);
                    String name = fnameText.getText() + " " + lnameText.getText();
                    welcomeLable.setText("Welcome " + name + " !");
                    root2.add(welcomeLable,1,1);
                    root2.add(back,1,4);
//  setOnAction on Back button
                    back.setOnAction(e->{
                        stage.setScene(scene1);
                        fnameText.clear();
                        mnameText.clear();
                        lnameText.clear();
                        emailText.clear();
                        mobText.clear();
                        cBDay.getSelectionModel().clearSelection();
                        cBMonth.getSelectionModel().clearSelection();
                        cBYear.getSelectionModel().clearSelection();
                        hindi.setSelected(false);
                        gujarati.setSelected(false);
                        english.setSelected(false);
                        male.setSelected(false);
                        female.setSelected(false);
                    });

                }

            }
        });

//  Main Gridpane
        GridPane pane = new GridPane();
        pane.addColumn(1,hb1,root);
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color:rgb(243, 239, 239)");

        scene1 = new Scene(pane, 476, 518);
        scene2 = new Scene(root2, 900,300);

        stage.setScene(scene1);
        stage.setTitle("Registration Form");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}