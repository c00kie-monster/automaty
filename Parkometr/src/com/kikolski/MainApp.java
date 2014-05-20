package com.kikolski;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp extends Application{
	private static final String BUTTON_ID_1 = "button1";
	private static final String BUTTON_ID_2 = "button2";
	private static final String BUTTON_ID_5 = "button5";
	
	private Label currentState;
	private Label message;
	private Button button1;
	private Button button2;
	private Button button5;
	private ParkingMeter parkingMeter;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Parkometr.fxml"));
        Scene scene = new Scene(root, 600, 460);
          
        stage.setTitle("ParkingMeter FA");
        stage.setScene(scene);
        stage.show();
        initialize(scene);
	}
	
	public void initialize(Scene scene) {
		ChangeStateHandler hander = new ChangeStateHandler();
		parkingMeter = new ParkingMeter();
		
		currentState = (Label) scene.lookup("#currentState");
		message = (Label) scene.lookup("#message");
		
		button1 = (Button) scene.lookup("#" + BUTTON_ID_1);
		button2 = (Button) scene.lookup("#" + BUTTON_ID_2);
		button5 = (Button) scene.lookup("#" + BUTTON_ID_5);
		
		button1.setOnAction(hander);
		button2.setOnAction(hander);
		button5.setOnAction(hander);
	}
	
	private class ChangeStateHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			String id = ((Button) arg0.getSource()).getId();
			message.setText("");
			
			if (BUTTON_ID_1.equals(id)) {
				parkingMeter.changeState("1");
			} else if (BUTTON_ID_2.equals(id)) {
				parkingMeter.changeState("2");
			} else {
				parkingMeter.changeState("5");
			}
			
			if (parkingMeter.getCurrentState().isUnacceptable()) {
				finish("Błędny stan - zwracam kase.");
			}
			
			if (parkingMeter.getCurrentState().isFinal()) {
				finish("Ok. Wydaje potwierdzenie.");
			}
				
			currentState.setText(parkingMeter.toString());
		}
		
		private void finish(final String msg) {
			message.setText(msg);
			System.out.println(parkingMeter.historyToString());
			parkingMeter.rollback();
		}
	}
}
