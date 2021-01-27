package gsand.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Initializes the chess game.
 * 
 * @author gsand
 * @version 1.0
 */
public class App extends Application 
{
    
    private final static String GAME_TITLE = "The game of life by @gsand";
    
    private static Game game;
    private static App context;
    
    private Stage mainStage;
    private Scene gameScene;
    
    /**
     * Initializes the game.
     * 
     * @return void
     */
    public App() 
    {

        game = new Game2D();
        
        context = this;
        
    }
    
    /**
     * Retrieves the main context.
     * Used for several references, e.g. in the settings.
     * 
     * @return App
     */
    public static App getContext()
    {
        
        return context;
        
    }
    
    /**
     * Retrieves the main stage that the game is drawing on.
     * 
     * @return Stage mainStage
     */
    public Stage getMainStage()
    {
        
        return this.mainStage;
        
    }

    /**
     * @param String[] args
     * @return void
     */
    public static void main(String[] args) 
    {
        
        launch(args);
        
    }
    
    /**
     * Initializes the app & starts the game
     * 
     * @return void
     */
    public void start(Stage primaryStage) 
    {
        
        primaryStage.setTitle(GAME_TITLE);
        
        this.mainStage = primaryStage;
        
        this.gameScene = new Scene(new Group());
        
        this.gameScene.setOnMouseClicked(event -> this.onMouseClicked(event));
        
        this.startGame();
        
    }
    
    /**
     * Adds a "Settings" button and overlay to the application.
     * 
     * @param Group root
     * @return void
     */
    private void drawSettings()
    {
        
        int buttonWidth = 200;
        int buttonHeight = 40;
        int buttonMargin = 20;
        
        Group root = (Group)gameScene.getRoot();
        
        Settings.drawSettingsOverlay(root, buttonWidth, buttonHeight, buttonMargin);
        
    }
       
    /**
     * Starts the game.
     * 
     * @return void
     */
    private void startGame()
    {

        this.draw();
        
        game.begin();
        
    }
    
    /**
     * Handles clicks on the main stage.
     * Will consider whether the game is running (not ended yet) and if it is paused or not.
     * 
     * @param MouseEvent event
     * @return void
     */
    private void onMouseClicked(MouseEvent event) 
    {
                
        if(game.isGameRunning() && !game.isGamePaused())
        {

            game.advanceTurn();
                    
            this.draw();
            
        }
        
    }
    
    /**
     * Main method to draw the application's content to the screen.
     * 
     * @return void
     */
    protected void draw()
    {
                
        Group root = new Group();
        
        game.draw(root);
        
        gameScene.setRoot(root);

        this.drawSettings();
        
        mainStage.setScene(gameScene);
        
        mainStage.show();
                  
    }
    
    /**
     * Retrieves a reference to the application's main Game object.
     * 
     * @return Game
     */
    public static Game getGameObject()
    {
        
        return game;
        
    }
        
}
