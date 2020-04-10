package it.polito.tdp.indovina;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovina.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model;
	
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layaoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doNuova(ActionEvent event) {
    	//fare iniziare una partita
    	this.model.nuovaPartita();
    	
    	//interfaccia
    	layaoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(model.getTMAX()));
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//leggere input
    	String ts= txtTentativi.getText();
    	int tentativo;
    	try {
    	tentativo= Integer.parseInt(ts);
    	}catch(NumberFormatException e){
    		txtRisultato.appendText("Devi inserire un numero\n");
    		return ;
    	}
    	int risultato=-5;
    	try {
    	risultato=this.model.tentativo(tentativo);
    	}catch(IllegalStateException se) {
    		txtRisultato.appendText(se.getMessage());
    		return;
    	}catch(InvalidParameterException pe) {
    		txtRisultato.appendText(pe.getMessage());
    		return;
    	}
    	
    	if(risultato==0) {
    		txtRisultato.appendText("Hai vinto con "+ model.getTentativiFatti()+" tentativi");
    		
    	}
    	else if(risultato==-1) {
    		txtRisultato.appendText("Troppo basso!\n");
    	}
    	else
    		txtRisultato.appendText("Troppo alto!\n");
    	
    	txtRimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiFatti()));
    	
    }

    @FXML
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layaoutTentativo != null : "fx:id=\"layaoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}
