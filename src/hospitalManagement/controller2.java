package hospitalManagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class controller2 implements Initializable{
	 
		@FXML
	    private AnchorPane aplol;

	    @FXML
	    private Button blol;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		blol.setOnAction(new EventHandler<ActionEvent>() 
		{
           @Override
            public void handle(ActionEvent event)
            { 
        	   System.out.println("yolo bro");
        	   try{
        	   Parent home_page_parent = FXMLLoader.load(getClass().getResource("login.fxml"));
		        Scene home_page_scene = new Scene(home_page_parent);
		        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        app_stage.setScene(home_page_scene);
               app_stage.show(); 
        	   
        	   }
        	   catch(IOException e)
        	   {
        		   System.out.println("GG");
        	   }
        	   
        	   
        	   
            }
           
           
           
           
		});
		
	}

	
	
	
	
	
}
