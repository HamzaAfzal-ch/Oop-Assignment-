import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class RegistrationForm extends Application {

    // ArrayList to store form data
    private final ArrayList<String> dataList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Root layout for the form
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0;");

        // Banner (Label at the top)
        Label banner = new Label("Data Entry Form");
        banner.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 16; -fx-alignment: center;");
        banner.setMaxWidth(Double.MAX_VALUE);

        // Form fields
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField fatherNameField = new TextField();
        fatherNameField.setPromptText("Father Name");

        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Lahore", "Gujranwala", "Sialkot", "Narowal", "Faisalabad", "Jehlum", "Gujrat", "Mandibahauddin");
        cityComboBox.setPromptText("Select City");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        // File chooser for image
        Button fileChooserButton = new Button("Choose Image");
        Label imageLabel = new Label("No file chosen");
        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        fileChooserButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                imageLabel.setText(file.getName());
                imageView.setImage(new Image(file.toURI().toString())); // Display selected image
            }
        });

        // Radio buttons for gender
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleButton = new RadioButton("Male");
        maleButton.setToggleGroup(genderGroup);
        RadioButton femaleButton = new RadioButton("Female");
        femaleButton.setToggleGroup(genderGroup);
        RadioButton preferNotToSay = new RadioButton("Prefer not to say");
        preferNotToSay.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleButton, femaleButton, preferNotToSay);

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20 10 20;");
        submitButton.setOnAction(e -> {
            // Collect data
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String city = cityComboBox.getValue();
            String address = addressField.getText();
            String email = emailField.getText();
            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
            String gender = selectedGender != null ? selectedGender.getText() : "Not selected";

            // Add to ArrayList
            dataList.clear(); // Clear previous data if needed
            dataList.add("Name: " + name);
            dataList.add("Father Name: " + fatherName);
            dataList.add("City: " + city);
            dataList.add("Address: " + address);
            dataList.add("Email: " + email);
            dataList.add("Gender: " + gender);

            // Print data to console
            System.out.println("Form Submitted!");
            dataList.forEach(System.out::println);

            // Show data in a new window
            showDataWindow(name, fatherName, city, address, email, gender, imageView.getImage());
        });

        // Exit button
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20 10 20;");
        exitButton.setOnAction(e -> {
            // Print message and close the application
            System.out.println("Form Did Not Submit. Exiting...");
            primaryStage.close();
        });

        // HBox for buttons (Submit and Exit)
        HBox buttonBox = new HBox(10, submitButton, exitButton);

        // Add all elements to the root layout
        root.getChildren().addAll(
                banner,
                new Label("Name:"), nameField,
                new Label("Father Name:"), fatherNameField,
                new Label("City:"), cityComboBox,
                new Label("Address:"), addressField,
                new Label("Email:"), emailField,
                new Label("Image:"), new HBox(10, fileChooserButton, imageLabel),
                imageView, // Display the selected image
                new Label("Gender:"), genderBox,
                buttonBox // Add the button box containing Submit and Exit buttons
        );

        // Add the root layout to a ScrollPane for scrollable content
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true); // Ensure the content fits the width of the scroll pane

        // Set the scene and show the stage
        Scene scene = new Scene(scrollPane, 400, 500); // Adjust scene height to allow scrolling
        primaryStage.setTitle("Registration Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDataWindow(String name, String fatherName, String city, String address, String email, String gender, Image image) {
        // Create a new stage (window)
        Stage dataStage = new Stage();
        VBox dataRoot = new VBox(10);
        dataRoot.setStyle("-fx-padding: 20; -fx-background-color: black; -fx-alignment: center;");

        // Title banner for the new window
        Label title = new Label("Submitted Data");
        title.setStyle("-fx-font-size: 18; -fx-text-fill: red; -fx-font-weight: bold;");
        title.setMaxWidth(Double.MAX_VALUE);

        // Display the submitted data with green text
        Label nameLabel = new Label("Name: " + name);
        Label fatherNameLabel = new Label("Father Name: " + fatherName);
        Label cityLabel = new Label("City: " + city);
        Label addressLabel = new Label("Address: " + address);
        Label emailLabel = new Label("Email: " + email);
        Label genderLabel = new Label("Gender: " + gender);

        nameLabel.setStyle("-fx-text-fill: red;");
        fatherNameLabel.setStyle("-fx-text-fill: red;");
        cityLabel.setStyle("-fx-text-fill: red;");
        addressLabel.setStyle("-fx-text-fill: red;");
        emailLabel.setStyle("-fx-text-fill: red;");
        genderLabel.setStyle("-fx-text-fill: red;");

        // Display the image if available
        ImageView displayImageView = new ImageView(image);
        if (image != null) {
            displayImageView.setFitWidth(150);
            displayImageView.setFitHeight(150);
            displayImageView.setPreserveRatio(true);
        }

        // Add all elements to the VBox
        dataRoot.getChildren().addAll(
                title,
                nameLabel,
                fatherNameLabel,
                cityLabel,
                addressLabel,
                emailLabel,
                genderLabel,
                displayImageView
        );

        // Set the scene and show the stage
        Scene dataScene = new Scene(dataRoot, 300, 400);
        dataStage.setTitle("Submitted Data");
        dataStage.setScene(dataScene);
        dataStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
