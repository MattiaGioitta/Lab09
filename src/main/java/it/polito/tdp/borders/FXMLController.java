
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<Country> cmbStati;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	this.txtResult.clear();
    	try {
    		int anno = Integer.parseInt(this.txtAnno.getText());
    		if(anno<1816 || anno >2016) {
    			this.txtResult.setText("Insersci un anno tra 1816 e 2016!");
    			return;
    		}
    		this.model.creaGrafo(anno);
    		this.txtResult.appendText(this.model.statiConGrado());
    		this.txtResult.appendText(String.format("Il numero di componenti connesse e' %d", this.model.getNumberOfConnectedComponents()));
    		this.cmbStati.getItems().addAll(this.model.getCountries());
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.appendText("Inserisci un numero!");
    	}

    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	this.txtResult.clear();
    	Country c = this.cmbStati.getValue();
    	List<Country> vicini = this.model.trovaVicini(c);
    	for(Country co : vicini) {
    		this.txtResult.appendText(co.getNome()+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbStati != null : "fx:id=\"cmbStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
