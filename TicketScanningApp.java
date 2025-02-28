import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Use this template to create Apps with Graphical User Interfaces.
 * This class is to create the ticket scanning app that validate the ticket.
 *
 * @author Thanh Truong Doan - 000918024
 * @version 2023.3.5
 */
public class TicketScanningApp extends Application {

    // TODO: Instance Variables for View Components and Model
    private Table t;
    private Label appTitle;
    private Label validateTicket;
    private Label showName;
    private TextField inputBox;
    private Button redeemButton;
    private MediaPlayer player1;
    private MediaPlayer player2;


    // TODO: Private Event Handlers and Helper Methods

    /**
     *
     * Redeem handler is to validate ticket by clicking redeem button on the app.
     *
     * @param e  the e.
     */
    public void redeemHandler(ActionEvent e) {
        int rowNum;
        player1 = new MediaPlayer
                (new Media("file:///" + System.getProperty("user.dir").replace('\\', '/').replaceAll(" ",
                        "%20") + "/" + "invalid.wav"));

        player2 = new MediaPlayer
                (new Media("file:///" + System.getProperty("user.dir").replace('\\', '/').replaceAll(" ",
                        "%20") + "/" + "valid.wav"));

        String code = inputBox.getText();
        code = code.toUpperCase();
        String[] scanData = new String[t.getNumCols()];
        scanData = t.getMatches(code);

        rowNum = t.lookup(code);

        if (code.equals("RESET")) {
            t.resetTickets();
            validateTicket.setStyle("-fx-text-fill: green");
            validateTicket.setText("All tickets are unredeemed!");
            showName.setText("");
            player2.play();
        } else {
            if (scanData != null) {
                if (scanData[1].equals("Y") && scanData[2].equals("N")) {
                    validateTicket.setStyle("-fx-text-fill: green");
                    validateTicket.setText(code + " - Valid");
                    showName.setText(scanData[3]);
                    player2.play();
                    t.change(rowNum, 2, "Y");
                    t.save();
                } else if (scanData[1].equals("Y") && scanData[2].equals("Y")) {
                    validateTicket.setStyle("-fx-text-fill: red");
                    validateTicket.setText(code + " is a duplicate");
                    showName.setText("");
                    player1.play();
                } else if (scanData[1].equals("N")) {
                    validateTicket.setStyle("-fx-text-fill: red");
                    validateTicket.setText(code + " is not purchase yet");
                    showName.setText("");
                    player1.play();
                }
            } else {
                validateTicket.setStyle("-fx-text-fill: red");
                validateTicket.setText(code + " is invalid");
                showName.setText("");
                player1.play();
            }
        }
        inputBox.setText("");
    }



    /**
     *
     * Redeem enter is to validate ticket by pressing enter or using USB scanner.
     *
     */
    public void redeemEnter() {
        int rowNum;
        player1 = new MediaPlayer
                (new Media("file:///" + System.getProperty("user.dir").replace('\\', '/').replaceAll(" ",
                        "%20") + "/" + "invalid.wav"));

        player2 = new MediaPlayer
                (new Media("file:///" + System.getProperty("user.dir").replace('\\', '/').replaceAll(" ",
                        "%20") + "/" + "valid.wav"));

        String code = inputBox.getText();
        code = code.toUpperCase();
        String[] scanData = new String[t.getNumCols()];
        scanData = t.getMatches(code);

        rowNum = t.lookup(code);

        if (code.equals("RESET")) {
            t.resetTickets();
            validateTicket.setStyle("-fx-text-fill: green");
            validateTicket.setText("All tickets are unredeemed!");
            showName.setText("");
            player2.play();
        }
        else {
            if (scanData != null) {
                if (scanData[1].equals("Y") && scanData[2].equals("N")) {
                    validateTicket.setStyle("-fx-text-fill: green");
                    validateTicket.setText(code + " - Valid");
                    showName.setText(scanData[3]);
                    player2.play();
                    t.change(rowNum, 2, "Y");
                    t.save();
                } else if (scanData[1].equals("Y") && scanData[2].equals("Y")) {
                    validateTicket.setStyle("-fx-text-fill: red");
                    validateTicket.setText(code + " is a duplicate");
                    showName.setText("");
                    player1.play();
                } else if (scanData[1].equals("N")) {
                    validateTicket.setStyle("-fx-text-fill: red");
                    validateTicket.setText(code + " is not purchase yet");
                    showName.setText("");
                    player1.play();
                }
            } else {
                validateTicket.setStyle("-fx-text-fill: red");
                validateTicket.setText(code + " is invalid");
                showName.setText("");
                player1.play();
            }
        }
        inputBox.setText("");
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 420, 300); // set the size here
        stage.setTitle("Ticket Scanning App"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here


        // 1. Create the model
        t = new Table("codes.txt");


        // 2. Create the GUI components
        appTitle = new Label(" Ticket Scanning App ");
        inputBox = new TextField("");
        redeemButton = new Button("REDEEM");
        validateTicket = new Label("");
        showName = new Label("");


        // 3. Add components to the root
        root.getChildren().addAll(appTitle, inputBox, redeemButton, validateTicket, showName);

        // 4. Configure the components (colors, fonts, size, location)
        //root.setBackground(Background.fill(Color.GREY));

        appTitle.relocate(20, 20);
        appTitle.setBackground(Background.fill(Color.YELLOWGREEN));
        appTitle.setFont(new Font("System", 40));
        appTitle.setStyle("-fx-text-fill: blue");

        inputBox.relocate(70, 100);
        inputBox.setFont(new Font("System", 20));
        inputBox.setPrefWidth(120);
        inputBox.setPrefHeight(40);

        redeemButton.relocate(220, 100);
        redeemButton.setFont(new Font("System", 20));
        redeemButton.setBackground(Background.fill(Color.DARKTURQUOISE));
        redeemButton.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
        redeemButton.setPrefWidth(120);
        redeemButton.setPrefHeight(40);

        validateTicket.relocate(20, 160);
        validateTicket.setFont(new Font("System", 30));
        //validateTicket.setBackground(Background.fill(Color.WHITE));

        showName.relocate(20, 210);
        showName.setStyle("-fx-text-fill: green");
        showName.setFont(new Font("System", 14));

        inputBox.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                redeemEnter(); //the routine that handles ticket validation
            }
        });


        // 5. Add Event Handlers and do final setup
        redeemButton.setOnAction(this::redeemHandler);

        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}