package be.inf1.pokerspel;

import be.inf1.pokerspel.model.HandEvaluator;
import be.inf1.pokerspel.model.Kaart;
import be.inf1.pokerspel.model.KaartDeck;
import be.inf1.pokerspel.model.PokerBot;
import be.inf1.pokerspel.model.Pokerspel;
import be.inf1.pokerspel.model.Status;
import be.inf1.pokerspel.view.PokerspelView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class PokerspelFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button callKnop;

    @FXML
    private Label chipView;

    @FXML
    private Button checkKnop;

    @FXML
    private Label potView;

    @FXML
    private Button foldKnop;

    @FXML
    private Button raiseKnop;

    @FXML
    private Button startKnop;

    @FXML
    private AnchorPane tafelView;

    @FXML
    private Button verlaatKnop;
    
    @FXML
    private Label botActieView;
    
    
    @FXML
    private TextField raiseField;
    
    private Pokerspel model;
    private PokerspelView view;
    private PokerBot bot;
    private KaartDeck deck;
    
    

    @FXML
    void initialize() {
        startKnop.setOnAction(event -> start());
        foldKnop.setOnAction(event-> fold());
        raiseKnop.setOnAction(event -> raise());
        callKnop.setOnAction(event -> call());
        checkKnop.setOnAction(event -> check());
        verlaatKnop.setOnAction(event -> verlaatSpel());
        
    }

    public void start() {

        try {
            int aantalDeelnemers = 2;
            model = new Pokerspel(aantalDeelnemers);

            deck = new KaartDeck();           
            bot = new PokerBot(model);  

            view = new PokerspelView(model);
            tafelView.getChildren().clear();
            tafelView.getChildren().add(0,view);
            
            view.setMouseTransparent(true);
            
            raiseKnop.toFront();
            callKnop.toFront();
            checkKnop.toFront();
            foldKnop.toFront();
            verlaatKnop.toFront();
            startKnop.toFront();
 
            updateChips();
            
            

        } catch (NumberFormatException e) {
            System.out.println("Je hebt geen deelnemers");
        }
    }
    
    


    public void update() {
        view.update();
        
        if (model.isShowdown()) {

            ArrayList<Kaart> spelerHand = new ArrayList<>();
            spelerHand.addAll(model.getSpelerKaarten());
            spelerHand.addAll(model.getTafelKaarten());

            ArrayList<Kaart> botHand = new ArrayList<>();
            botHand.addAll(model.getBotKaarten());
            botHand.addAll(model.getTafelKaarten());

            int spelerScore = HandEvaluator.bepaalHandSterkte(spelerHand);
            int botScore = HandEvaluator.bepaalHandSterkte(botHand);

            if (spelerScore > botScore) {
                System.out.println("Speler wint");
               
            } 
            else if (botScore > spelerScore) {
                System.out.println("Bot wint");
            } 
            else {
                System.out.println("Gelijkspel");
            }
        }
    }
    
    
    // volgende 3 lijnen chatGpt hulp
    public void updateChips(){
        chipView.setText(model.getSpelerChips()+"");
    }
    
    public void updatePot(){
        potView.setText(model.getPot()+"");
    }
    
    public void updateBotActie(){
        botActieView.setText(model.getLaatsteBotActie());
    }
    
    
    public void verlaatSpel(){
        javafx.application.Platform.exit();//chatgpt
    }

    private void fold() {
        System.out.println("Je hebt gefold. De andere speler wint.");
        model.fold();
        updateBotActie();
        
        
    }

    private void raise() {
        try{
            int bedrag = Integer.parseInt(raiseField.getText());//chatgpt
            model.raise(bedrag);
            updateChips();        
            view.update();   
            updateBotActie();
            System.out.println("Je hebt geraised");
        }
        catch(NumberFormatException e){
            System.out.println("ongeldig raisebedrag");
        }
        updatePot();
    }

    private void call() {
        System.out.println("Je hebt gecalled");
        model.call();
        updateChips();
        updatePot();
        updateBotActie();
        view.update();
    }

    private void check() {
        System.out.println("Je hebt gechecked");
        model.check();
        updateChips();
        updateBotActie();
        view.update();
        updatePot();
    }
    
}
