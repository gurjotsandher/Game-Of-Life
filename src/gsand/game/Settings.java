package gsand.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Settings 
{
    
    private static Button buttonApplyRestart = null;
    private static Button buttonSettings = null;

    private static Label labelAllowedNumberOfTurnsWithoutFoodCarnivore;
    private static Label labelAllowedNumberOfTurnsWithoutFoodHerbivore;
    private static Label labelAllowedNumberOfTurnsWithoutFoodOmnivore;
    private static Label labelGrassSeedFreeCells;
    private static Label labelGrassSeedNeighbors;

    private static Label labelProcreationFreeCellsCarnivore;
    private static Label labelProcreationFreeCellsHerbivore;
    private static Label labelProcreationFreeCellsOmnivore;
    private static Label labelProcreationFoodCarnivore;
    private static Label labelProcreationFoodHerbivore;
    private static Label labelProcreationFoodOmnivore;
    private static Label labelProcreationNeighborsCarnivore;
    private static Label labelProcreationNeighborsHerbivore;
    private static Label labelProcreationNeighborsOmnivore;
    
    private static Label labelSpawnRateCarnivores;
    private static Label labelSpawnRateHerbivores;
    private static Label labelSpawnRateOmnivores;
    private static Label labelSpawnRateGrass;
    private static Label labelWorldRows;
    private static Label labelWorldColumns;

    private static TextField inputAllowedNumberOfTurnsWithoutFoodCarnivore;
    private static TextField inputAllowedNumberOfTurnsWithoutFoodHerbivore;
    private static TextField inputAllowedNumberOfTurnsWithoutFoodOmnivore;
    private static TextField inputGrassSeedFreeCells;
    private static TextField inputGrassSeedNeighbors;

    private static TextField inputProcreationFreeCellsCarnivore;
    private static TextField inputProcreationFreeCellsHerbivore;
    private static TextField inputProcreationFreeCellsOmnivore;
    private static TextField inputProcreationFoodCarnivore;
    private static TextField inputProcreationFoodHerbivore;
    private static TextField inputProcreationFoodOmnivore;
    private static TextField inputProcreationNeighborsCarnivore;
    private static TextField inputProcreationNeighborsHerbivore;
    private static TextField inputProcreationNeighborsOmnivore;
    
    private static TextField inputSpawnRateCarnivores;
    private static TextField inputSpawnRateHerbivores;
    private static TextField inputSpawnRateOmnivores;
    private static TextField inputSpawnRateGrass;
    private static TextField inputWorldRows;
    private static TextField inputWorldColumns;

    private static CheckBox inputWorldHexagonal;
    private static CheckBox inputDebuggingTools;
    private static CheckBox inputResetRandomGeneratorBeforeAddingLifeforms;

    private static int valueAllowedNumberOfTurnsWithoutFoodCarnivore = -1;
    private static int valueAllowedNumberOfTurnsWithoutFoodHerbivore = -1;
    private static int valueAllowedNumberOfTurnsWithoutFoodOmnivore = -1;
    private static int valueGrassSeedFreeCells = -1;
    private static int valueGrassSeedNeighbors = -1;

    private static int valueProcreationFreeCellsCarnivore = -1;
    private static int valueProcreationFreeCellsHerbivore = -1;
    private static int valueProcreationFreeCellsOmnivore = -1;
    private static int valueProcreationFoodCarnivore = -1;
    private static int valueProcreationFoodHerbivore = -1;
    private static int valueProcreationFoodOmnivore = -1;
    private static int valueProcreationNeighborsCarnivore = -1;
    private static int valueProcreationNeighborsHerbivore = -1;
    private static int valueProcreationNeighborsOmnivore = -1;
    
    private static int valueSpawnRateCarnivores = -1;
    private static int valueSpawnRateHerbivores = -1;
    private static int valueSpawnRateOmnivores = -1;
    private static int valueSpawnRateGrass = -1;
    private static int valueWorldRows = -1;
    private static int valueWorldColumns = -1;
    
    private static boolean resetRandomGeneratorBeforeAddingLifeforms = true;
    private static boolean isWorldHexagonal = true;
    private static boolean isEnabledDebuggingTools = false;
    
    private static boolean showSettings = false;

    /**
     * Draws a "settings" button and, if "showSettings" is set to "true", an overlay the to specified Group
     * where you can configure certain parameters of the application.
     * 
     * @param Group root
     * @param int buttonWidth
     * @param int buttonHeight
     * @param int buttonMargin
     * @return void
     */
    public static void drawSettingsOverlay(Group root, int buttonWidth, int buttonHeight, int buttonMargin)
    {

        initSettingElements(buttonWidth, buttonHeight, buttonMargin);

        int calculatedWidth = (int)App.getContext().getMainStage().getWidth() - 18;
        int calculatedHeight = (int)App.getContext().getMainStage().getHeight() - 47;
                
        if(showSettings)
        {
                                                
            StackPane sp = new StackPane();
            
            sp.setLayoutX(0);
            sp.setLayoutY(-10);
            
            Rectangle background = new Rectangle();
            
            background.setFill(Paint.valueOf("#333"));
            background.setOpacity(0.25);
            
            background.setWidth(calculatedWidth);
            background.setHeight(calculatedHeight);
                    
            sp.getChildren().add(background);
            
            //
            
            // Main StackPane for the settings
            
            StackPane spSettings = new StackPane();

            spSettings.setLayoutX(0);
            spSettings.setLayoutY(0);
            
            // Main VBox for the settings

            VBox vbSettingsMain = new VBox();
            vbSettingsMain.setSpacing(20);
            vbSettingsMain.setAlignment(Pos.CENTER);

            // World rows
            
            VBox vbWorldRows = new VBox();
            
            vbWorldRows.getChildren().addAll(labelWorldRows, inputWorldRows);
            vbWorldRows.setSpacing(10);
            vbWorldRows.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbWorldRows);
            
            // World columnms
            
            VBox vbWorldColumns = new VBox();
            vbWorldColumns.getChildren().addAll(labelWorldColumns, inputWorldColumns);
            vbWorldColumns.setSpacing(10);
            vbWorldColumns.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbWorldColumns);

            // Spawn rate carnivores

            VBox vbSpawnRateCarnivores = new VBox();
            
            vbSpawnRateCarnivores.getChildren().addAll(labelSpawnRateCarnivores, inputSpawnRateCarnivores);
            vbSpawnRateCarnivores.setSpacing(10);
            vbSpawnRateCarnivores.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbSpawnRateCarnivores);
            
            // Spawn rate herbivores

            VBox vbSpawnRateHerbivores = new VBox();
            
            vbSpawnRateHerbivores.getChildren().addAll(labelSpawnRateHerbivores, inputSpawnRateHerbivores);
            vbSpawnRateHerbivores.setSpacing(10);
            vbSpawnRateHerbivores.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbSpawnRateHerbivores);
            
            // Spawn rate herbivores

            VBox vbSpawnRateOmnivores = new VBox();
            
            vbSpawnRateOmnivores.getChildren().addAll(labelSpawnRateOmnivores, inputSpawnRateOmnivores);
            vbSpawnRateOmnivores.setSpacing(10);
            vbSpawnRateOmnivores.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbSpawnRateOmnivores);

            // Spawn rate plants

            VBox vbSpawnRatePlants = new VBox();
            
            vbSpawnRatePlants.getChildren().addAll(labelSpawnRateGrass, inputSpawnRateGrass);
            vbSpawnRatePlants.setSpacing(10);
            vbSpawnRatePlants.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbSpawnRatePlants);

            // Grass seed neighbors

            VBox vbGrassSeedNeighbors = new VBox();
            
            vbGrassSeedNeighbors.getChildren().addAll(labelGrassSeedNeighbors, inputGrassSeedNeighbors);
            vbGrassSeedNeighbors.setSpacing(10);
            vbGrassSeedNeighbors.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbGrassSeedNeighbors);
            
            // Grass seed free cells

            VBox vbGrassSeedFreeCells = new VBox();
            
            vbGrassSeedFreeCells.getChildren().addAll(labelGrassSeedFreeCells, inputGrassSeedFreeCells);
            vbGrassSeedFreeCells.setSpacing(10);
            vbGrassSeedFreeCells.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbGrassSeedFreeCells);

            // Carnivore procreation neighbors

            VBox vbProcreationNeighborsCarnivore = new VBox();
            
            vbProcreationNeighborsCarnivore.getChildren().addAll(labelProcreationNeighborsCarnivore, inputProcreationNeighborsCarnivore);
            vbProcreationNeighborsCarnivore.setSpacing(10);
            vbProcreationNeighborsCarnivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationNeighborsCarnivore);

            // Carnivore procreation food neighbors

            VBox vbProcreationNeighborsFoodCarnivore = new VBox();
            
            vbProcreationNeighborsFoodCarnivore.getChildren().addAll(labelProcreationFoodCarnivore, inputProcreationFoodCarnivore);
            vbProcreationNeighborsFoodCarnivore.setSpacing(10);
            vbProcreationNeighborsFoodCarnivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationNeighborsFoodCarnivore);
            
            // Carnivore procreation cells

            VBox vbProcreationFreeCellsCarnivore = new VBox();
            
            vbProcreationFreeCellsCarnivore.getChildren().addAll(labelProcreationFreeCellsCarnivore, inputProcreationFreeCellsCarnivore);
            vbProcreationFreeCellsCarnivore.setSpacing(10);
            vbProcreationFreeCellsCarnivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationFreeCellsCarnivore);

            // Herbivore procreation neighbors

            VBox vbProcreationNeighborsHerbivore = new VBox();
            
            vbProcreationNeighborsHerbivore.getChildren().addAll(labelProcreationNeighborsHerbivore, inputProcreationNeighborsHerbivore);
            vbProcreationNeighborsHerbivore.setSpacing(10);
            vbProcreationNeighborsHerbivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationNeighborsHerbivore);

            // Herbivore procreation food neighbors

            VBox vbProcreationNeighborsFoodHerbivore = new VBox();
            
            vbProcreationNeighborsFoodHerbivore.getChildren().addAll(labelProcreationFoodHerbivore, inputProcreationFoodHerbivore);
            vbProcreationNeighborsFoodHerbivore.setSpacing(10);
            vbProcreationNeighborsFoodHerbivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationNeighborsFoodHerbivore);
            
            // Herbivore procreation cells

            VBox vbProcreationFreeCellsHerbivore = new VBox();
            
            vbProcreationFreeCellsHerbivore.getChildren().addAll(labelProcreationFreeCellsHerbivore, inputProcreationFreeCellsHerbivore);
            vbProcreationFreeCellsHerbivore.setSpacing(10);
            vbProcreationFreeCellsHerbivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationFreeCellsHerbivore);

            // Omnivore procreation neighbors

            VBox vbProcreationNeighborsOmnivore = new VBox();
            
            vbProcreationNeighborsOmnivore.getChildren().addAll(labelProcreationNeighborsOmnivore, inputProcreationNeighborsOmnivore);
            vbProcreationNeighborsOmnivore.setSpacing(10);
            vbProcreationNeighborsOmnivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationNeighborsOmnivore);

            // Omnivore procreation food neighbors

            VBox vbProcreationNeighborsFoodOmnivore = new VBox();
            
            vbProcreationNeighborsFoodOmnivore.getChildren().addAll(labelProcreationFoodOmnivore, inputProcreationFoodOmnivore);
            vbProcreationNeighborsFoodOmnivore.setSpacing(10);
            vbProcreationNeighborsFoodOmnivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationNeighborsFoodOmnivore);
            
            // Omnivore procreation cells

            VBox vbProcreationFreeCellsOmnivore = new VBox();
            
            vbProcreationFreeCellsOmnivore.getChildren().addAll(labelProcreationFreeCellsOmnivore, inputProcreationFreeCellsOmnivore);
            vbProcreationFreeCellsOmnivore.setSpacing(10);
            vbProcreationFreeCellsOmnivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbProcreationFreeCellsOmnivore);
            
            // Allowed number of turns before a Carnivore starves

            VBox vbAllowedNumberOfTurnsWithoutFoodCarnivore = new VBox();
            
            vbAllowedNumberOfTurnsWithoutFoodCarnivore.getChildren().addAll(labelAllowedNumberOfTurnsWithoutFoodCarnivore, inputAllowedNumberOfTurnsWithoutFoodCarnivore);
            vbAllowedNumberOfTurnsWithoutFoodCarnivore.setSpacing(10);
            vbAllowedNumberOfTurnsWithoutFoodCarnivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbAllowedNumberOfTurnsWithoutFoodCarnivore);
            
            // Allowed number of turns before a Herbivore starves

            VBox vbAllowedNumberOfTurnsWithoutFoodHerbivore = new VBox();
            
            vbAllowedNumberOfTurnsWithoutFoodHerbivore.getChildren().addAll(labelAllowedNumberOfTurnsWithoutFoodHerbivore, inputAllowedNumberOfTurnsWithoutFoodHerbivore);
            vbAllowedNumberOfTurnsWithoutFoodHerbivore.setSpacing(10);
            vbAllowedNumberOfTurnsWithoutFoodHerbivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbAllowedNumberOfTurnsWithoutFoodHerbivore);
            
            // Allowed number of turns before a Herbivore starves

            VBox vbAllowedNumberOfTurnsWithoutFoodOmnivore = new VBox();
            
            vbAllowedNumberOfTurnsWithoutFoodOmnivore.getChildren().addAll(labelAllowedNumberOfTurnsWithoutFoodOmnivore, inputAllowedNumberOfTurnsWithoutFoodOmnivore);
            vbAllowedNumberOfTurnsWithoutFoodOmnivore.setSpacing(10);
            vbAllowedNumberOfTurnsWithoutFoodOmnivore.setAlignment(Pos.CENTER);
            
            vbSettingsMain.getChildren().add(vbAllowedNumberOfTurnsWithoutFoodOmnivore);

            // "Reset random generator before placing lifeforms"
            
            vbSettingsMain.getChildren().add(inputResetRandomGeneratorBeforeAddingLifeforms);
            
            // "Hexagonal world"
            
            vbSettingsMain.getChildren().add(inputWorldHexagonal);
            
            // "Debugging tools enabled"
            
            vbSettingsMain.getChildren().add(inputDebuggingTools);
            
            //
            
            HBox buttonGroup = new HBox();

            buttonGroup.getChildren().add(buttonSettings);
            buttonGroup.getChildren().add(buttonApplyRestart);
            
            buttonGroup.setSpacing(10);

            vbSettingsMain.getChildren().add(buttonGroup);
            
            //
                        
            spSettings.getChildren().add(vbSettingsMain);
            
            ScrollPane scroll = new ScrollPane();
            
            StackPane.setMargin(scroll, new Insets(30, 30, 30, 30));
            
            scroll.setPadding(new Insets(30));
            
            scroll.setMaxWidth(calculatedWidth - 60);
            scroll.setMaxHeight(calculatedHeight - 40);

            scroll.setContent(vbSettingsMain); 
            
            //

            sp.getChildren().add(scroll);

            root.getChildren().add(sp);
            
        }
        else
        {
                        
            StackPane sp = new StackPane();
             
            sp.setLayoutX(0);
            sp.setLayoutY(App.getGameObject().getWorldByIndex(0).getHeight());
             
            Rectangle background = new Rectangle();
             
            background.setFill(Paint.valueOf("#333"));
            background.setOpacity(0.25);
             
            background.setWidth(App.getGameObject().getWorldByIndex(0).getWidth());
            background.setHeight(30 + buttonHeight + buttonMargin * 2);
            
                     
            sp.getChildren().add(background);
            sp.getChildren().add(buttonSettings);
            
            root.getChildren().add(sp);

        }
        
    }

    /**
     * Initializes all buttons and input fields used for the "settings" overlay.
     * 
     * @param Group root
     * @param int buttonWidth
     * @param int buttonHeight
     * @param int buttonMargin
     * @return void
     */
    private static void initSettingElements(int buttonWidth, int buttonHeight, int buttonMargin)
    {

        if(buttonSettings == null)
        {
         
            Insets labelInsets = new Insets(-8, -12, -8, -12);
            CornerRadii labelCornerRadius = new CornerRadii(4d);

            Background background = new Background(new BackgroundFill(Paint.valueOf("#eee"), labelCornerRadius, labelInsets));
            
            //
            
            buttonSettings = new Button("Settings");
            
            buttonSettings.setMinWidth(buttonWidth);
            buttonSettings.setMinHeight(buttonHeight);
            
            buttonSettings.setOnMouseClicked(event -> onMouseClickedButtonSettings(event));
            
            //
            
            buttonApplyRestart = new Button("Apply & restart");
            
            buttonApplyRestart.setMinWidth(buttonWidth);
            buttonApplyRestart.setMinHeight(buttonHeight);
            
            buttonApplyRestart.setOnMouseClicked(event -> onMouseClickedButtonSaveAndRestart(event));
            
            //
            
            labelWorldColumns = new Label("World - Number of columns:");
            labelWorldColumns.setBackground(background);
            
            inputWorldColumns = new TextField();

            //
            
            labelWorldRows = new Label("World - Number of rows:");
            labelWorldRows.setBackground(background);
            
            inputWorldRows = new TextField();

            //
            
            labelSpawnRateCarnivores = new Label("Spawn rate - Carnivores:");
            labelSpawnRateCarnivores.setBackground(background);
            
            inputSpawnRateCarnivores = new TextField();
            
            //
            
            labelSpawnRateHerbivores = new Label("Spawn rate - Herbivores:");
            labelSpawnRateHerbivores.setBackground(background);
            
            inputSpawnRateHerbivores = new TextField();
            
            //
            
            labelSpawnRateOmnivores = new Label("Spawn rate - Omivores:");
            labelSpawnRateOmnivores.setBackground(background);
            
            inputSpawnRateOmnivores = new TextField();

            //
            
            labelSpawnRateGrass = new Label("Spawn rate - Grass:");
            labelSpawnRateGrass.setBackground(background);
            
            inputSpawnRateGrass = new TextField();
            
            //
            
            labelGrassSeedNeighbors = new Label("Grass - Mimimum # of neighbors required to seed:");
            labelGrassSeedNeighbors.setBackground(background);
            
            inputGrassSeedNeighbors = new TextField();
            
            //
            
            labelGrassSeedFreeCells = new Label("Grass - Mimimum # of free surrounding cells required to seed:");
            labelGrassSeedFreeCells.setBackground(background);
            
            inputGrassSeedFreeCells = new TextField();
            
            // 
                        
            labelProcreationNeighborsCarnivore = new Label("Carnivores - Mimimum # of neighbors required to procreate:");
            labelProcreationNeighborsCarnivore.setBackground(background);
            
            inputProcreationNeighborsCarnivore = new TextField();
            
            // 
                        
            labelProcreationFoodCarnivore = new Label("Carnivores - Mimimum # of neighboring food required to procreate:");
            labelProcreationFoodCarnivore.setBackground(background);
            
            inputProcreationFoodCarnivore = new TextField();
            
            //
            
            labelProcreationFreeCellsCarnivore = new Label("Carnivores - Mimimum # of free surrounding cells required to procreate:");
            labelProcreationFreeCellsCarnivore.setBackground(background);
            
            inputProcreationFreeCellsCarnivore = new TextField();
            
            // 
                        
            labelProcreationNeighborsHerbivore = new Label("Herbivores - Mimimum # of neighbors required to procreate:");
            labelProcreationNeighborsHerbivore.setBackground(background);
            
            inputProcreationNeighborsHerbivore = new TextField();
            
            // 
                        
            labelProcreationFoodHerbivore = new Label("Herbivores - Mimimum # of neighboring food required to procreate:");
            labelProcreationFoodHerbivore.setBackground(background);
            
            inputProcreationFoodHerbivore = new TextField();
            
            //
            
            labelProcreationFreeCellsHerbivore = new Label("Herbivores - Mimimum # of free surrounding cells required to procreate:");
            labelProcreationFreeCellsHerbivore.setBackground(background);
            
            inputProcreationFreeCellsHerbivore = new TextField();
            
            // 
                        
            labelProcreationNeighborsOmnivore = new Label("Omnivores - Mimimum # of neighbors required to procreate:");
            labelProcreationNeighborsOmnivore.setBackground(background);
            
            inputProcreationNeighborsOmnivore = new TextField();
            
            // 
                        
            labelProcreationFoodOmnivore = new Label("Omnivores - Mimimum # of neighboring food required to procreate:");
            labelProcreationFoodOmnivore.setBackground(background);
            
            inputProcreationFoodOmnivore = new TextField();
            
            //
            
            labelProcreationFreeCellsOmnivore = new Label("Omnivores - Mimimum # of free surrounding cells required to procreate:");
            labelProcreationFreeCellsOmnivore.setBackground(background);
            
            inputProcreationFreeCellsOmnivore = new TextField();
            
            // 
            
            labelAllowedNumberOfTurnsWithoutFoodCarnivore = new Label("Carnivores - # of turns before starving:");
            labelAllowedNumberOfTurnsWithoutFoodCarnivore.setBackground(background);
            
            inputAllowedNumberOfTurnsWithoutFoodCarnivore = new TextField();
            
            // 
            
            labelAllowedNumberOfTurnsWithoutFoodHerbivore = new Label("Herbivores - # of turns before starving:");
            labelAllowedNumberOfTurnsWithoutFoodHerbivore.setBackground(background);
            
            inputAllowedNumberOfTurnsWithoutFoodHerbivore = new TextField();
            
            // 
            
            labelAllowedNumberOfTurnsWithoutFoodOmnivore = new Label("Omnivores - # of turns before starving:");
            labelAllowedNumberOfTurnsWithoutFoodOmnivore.setBackground(background);
            
            inputAllowedNumberOfTurnsWithoutFoodOmnivore = new TextField();
            
            // 

            inputResetRandomGeneratorBeforeAddingLifeforms = new CheckBox("Reset random generator before placing lifeforms");
            inputResetRandomGeneratorBeforeAddingLifeforms.setBackground(background);

            // 

            inputWorldHexagonal = new CheckBox("Hexagonal world");
            inputWorldHexagonal.setBackground(background);
            // 

            inputDebuggingTools = new CheckBox("Debugging tools");
            inputDebuggingTools.setBackground(background);
            
        }
        
        initSettingElementValues();
        
    }
    
    /**
     * Initializes the values of all input fields of the "settings".
     * 
     * @return void
     */
    private static void initSettingElementValues()
    {

        inputAllowedNumberOfTurnsWithoutFoodCarnivore.setText("" + getAllowedNumberOfTurnsWithoutFoodCarnivore());
        inputAllowedNumberOfTurnsWithoutFoodHerbivore.setText("" + getAllowedNumberOfTurnsWithoutFoodHerbivore());
        inputAllowedNumberOfTurnsWithoutFoodOmnivore.setText("" + getAllowedNumberOfTurnsWithoutFoodOmnivore());
        inputGrassSeedFreeCells.setText("" + getRequiredAmountOfFreeSurroundingCellsForSeedingGrass());
        inputGrassSeedNeighbors.setText("" + getRequiredMinimumAmountOfSurroundingPartnersForSeedingGrass());

        inputProcreationFreeCellsCarnivore.setText("" + getRequiredAmountOfFreeSurroundingCellsForProcreationCarnivore());
        inputProcreationFreeCellsHerbivore.setText("" + getRequiredAmountOfFreeSurroundingCellsForProcreationHerbivore());
        inputProcreationFreeCellsOmnivore.setText("" + getRequiredAmountOfFreeSurroundingCellsForProcreationOmnivore());
        inputProcreationFoodCarnivore.setText("" + getRequiredMinimumAmountOfSurroundingFoodForProcreationCarnivore());
        inputProcreationFoodHerbivore.setText("" + getRequiredMinimumAmountOfSurroundingFoodForProcreationHerbivore());
        inputProcreationFoodOmnivore.setText("" + getRequiredMinimumAmountOfSurroundingFoodForProcreationOmnivore());
        inputProcreationNeighborsCarnivore.setText("" + getRequiredMinimumAmountOfSurroundingPartnersForProcreationCarnivore());
        inputProcreationNeighborsHerbivore.setText("" + getRequiredMinimumAmountOfSurroundingPartnersForProcreationHerbivore());
        inputProcreationNeighborsOmnivore.setText("" + getRequiredMinimumAmountOfSurroundingPartnersForProcreationOmnivore());
        
        inputSpawnRateGrass.setText("" + getSpawnRateGrass());
        inputSpawnRateCarnivores.setText("" + getSpawnRateCarnivore());
        inputSpawnRateHerbivores.setText("" + getSpawnRateHerbivore());
        inputSpawnRateOmnivores.setText("" + getSpawnRateOmnivore());
        inputWorldRows.setText("" + getWorldNumberRows());
        inputWorldColumns.setText("" + getWorldNumberColumns());

        inputWorldHexagonal.setSelected(isWorldHexagonal);
        inputDebuggingTools.setSelected(isEnabledDebuggingTools);
        inputResetRandomGeneratorBeforeAddingLifeforms.setSelected(resetRandomGeneratorBeforeAddingLifeforms);
        
    }

    /**
     * Handles clicks on the "Settings" button.
     * Toggles the "settings" overlay.
     * 
     * @return void
     */
    private static void onMouseClickedButtonSettings(MouseEvent event) 
    {
        
        toggleDisplay();
        
    }
    
    /**
     * Tries to apply the values entered in the "settings" overlay.
     * 
     * @return void
     */
    private static void updateSettings()
    {

        int parsedAllowedNumberOfTurnsWithoutFoodCarnivore = -1;
        int parsedAllowedNumberOfTurnsWithoutFoodHerbivore = -1;
        int parsedAllowedNumberOfTurnsWithoutFoodOmnivore = -1;
        int parsedRateGrass = -1;
        int parsedRateCarnivores = -1;
        int parsedRateHerbivores = -1;
        int parsedRateOmnivores = -1;
        int parsedColumns = -1;
        int parsedRows = -1;
        int parsedFreeCellsGrass = -1;
        int parsedNeighborsGrass = -1;

        int parsedFreeCellsCarnivores = -1;
        int parsedFreeCellsHerbivores = -1;
        int parsedFreeCellsOmnivores = -1;
        int parsedFoodCarnivores = -1;
        int parsedFoodHerbivores = -1;
        int parsedFoodOmnivores = -1;
        int parsedNeighborsCarnivores = -1;
        int parsedNeighborsHerbivores = -1;
        int parsedNeighborsOmnivores = -1;
        
        try
        {

            parsedAllowedNumberOfTurnsWithoutFoodCarnivore = Integer.parseInt(inputAllowedNumberOfTurnsWithoutFoodCarnivore.getText());
            parsedAllowedNumberOfTurnsWithoutFoodHerbivore = Integer.parseInt(inputAllowedNumberOfTurnsWithoutFoodHerbivore.getText());
            parsedAllowedNumberOfTurnsWithoutFoodOmnivore = Integer.parseInt(inputAllowedNumberOfTurnsWithoutFoodOmnivore.getText());

            parsedRateCarnivores = Integer.parseInt(inputSpawnRateCarnivores.getText());
            parsedRateHerbivores = Integer.parseInt(inputSpawnRateHerbivores.getText());
            parsedRateOmnivores = Integer.parseInt(inputSpawnRateOmnivores.getText());
            parsedRateGrass = Integer.parseInt(inputSpawnRateGrass.getText());

            parsedColumns = Integer.parseInt(inputWorldColumns.getText());
            parsedRows = Integer.parseInt(inputWorldRows.getText());
            
            parsedFreeCellsGrass = Integer.parseInt(inputGrassSeedFreeCells.getText());
            parsedNeighborsGrass = Integer.parseInt(inputGrassSeedNeighbors.getText());

            parsedFreeCellsCarnivores = Integer.parseInt(inputProcreationFreeCellsCarnivore.getText());
            parsedFreeCellsHerbivores = Integer.parseInt(inputProcreationFreeCellsHerbivore.getText());
            parsedFreeCellsOmnivores = Integer.parseInt(inputProcreationFreeCellsOmnivore.getText());
            parsedFoodCarnivores = Integer.parseInt(inputProcreationFoodCarnivore.getText());
            parsedFoodHerbivores = Integer.parseInt(inputProcreationFoodHerbivore.getText());
            parsedFoodOmnivores = Integer.parseInt(inputProcreationFoodOmnivore.getText());
            parsedNeighborsCarnivores = Integer.parseInt(inputProcreationNeighborsCarnivore.getText());
            parsedNeighborsHerbivores = Integer.parseInt(inputProcreationNeighborsHerbivore.getText());
            parsedNeighborsOmnivores = Integer.parseInt(inputProcreationNeighborsOmnivore.getText());
            
        }
        catch(Exception e)
        {
            
            System.out.println("Invalid value entered in the settings, please verify!");
            
        }

        valueAllowedNumberOfTurnsWithoutFoodCarnivore = parsedAllowedNumberOfTurnsWithoutFoodCarnivore >= 0 ? parsedAllowedNumberOfTurnsWithoutFoodCarnivore : valueAllowedNumberOfTurnsWithoutFoodCarnivore;
        valueAllowedNumberOfTurnsWithoutFoodHerbivore = parsedAllowedNumberOfTurnsWithoutFoodHerbivore >= 0 ? parsedAllowedNumberOfTurnsWithoutFoodHerbivore : valueAllowedNumberOfTurnsWithoutFoodHerbivore;
        valueAllowedNumberOfTurnsWithoutFoodOmnivore = parsedAllowedNumberOfTurnsWithoutFoodOmnivore >= 0 ? parsedAllowedNumberOfTurnsWithoutFoodOmnivore : valueAllowedNumberOfTurnsWithoutFoodOmnivore;
        
        valueGrassSeedFreeCells = parsedFreeCellsGrass >= 0 ? parsedFreeCellsGrass : valueGrassSeedFreeCells;
        valueGrassSeedNeighbors = parsedNeighborsGrass >= 0 ? parsedNeighborsGrass : valueGrassSeedNeighbors;
        
        valueProcreationFreeCellsCarnivore = parsedFreeCellsCarnivores >= 0 ? parsedFreeCellsCarnivores : valueProcreationFreeCellsCarnivore;
        valueProcreationFreeCellsHerbivore = parsedFreeCellsHerbivores >= 0 ? parsedFreeCellsHerbivores : valueProcreationFreeCellsHerbivore;
        valueProcreationFreeCellsOmnivore = parsedFreeCellsOmnivores >= 0 ? parsedFreeCellsOmnivores : valueProcreationFreeCellsOmnivore;
        valueProcreationFoodCarnivore = parsedFoodCarnivores >= 0 ? parsedFoodCarnivores : valueProcreationFoodCarnivore;
        valueProcreationFoodHerbivore = parsedFoodHerbivores >= 0 ? parsedFoodHerbivores : valueProcreationFoodHerbivore;
        valueProcreationFoodOmnivore = parsedFoodOmnivores >= 0 ? parsedFoodOmnivores : valueProcreationFoodOmnivore;
        valueProcreationNeighborsCarnivore = parsedNeighborsCarnivores >= 0 ? parsedNeighborsCarnivores : valueProcreationNeighborsCarnivore;
        valueProcreationNeighborsHerbivore = parsedNeighborsHerbivores >= 0 ? parsedNeighborsHerbivores : valueProcreationNeighborsHerbivore;
        valueProcreationNeighborsOmnivore = parsedNeighborsOmnivores >= 0 ? parsedNeighborsOmnivores : valueProcreationNeighborsOmnivore;
        
        valueSpawnRateGrass = parsedRateGrass >= 0 ? parsedRateGrass : valueSpawnRateGrass;
        valueSpawnRateCarnivores = parsedRateCarnivores >= 0 ? parsedRateCarnivores : valueSpawnRateCarnivores;
        valueSpawnRateHerbivores = parsedRateHerbivores >= 0 ? parsedRateHerbivores : valueSpawnRateHerbivores;
        valueSpawnRateOmnivores = parsedRateOmnivores >= 0 ? parsedRateOmnivores : valueSpawnRateOmnivores;

        valueWorldColumns = parsedColumns >= 0 ? parsedColumns : valueWorldColumns;
        valueWorldRows = parsedRows >= 0 ? parsedRows : valueWorldRows;

        resetRandomGeneratorBeforeAddingLifeforms = inputResetRandomGeneratorBeforeAddingLifeforms.isSelected();
        isWorldHexagonal = inputWorldHexagonal.isSelected();
        isEnabledDebuggingTools = inputDebuggingTools.isSelected();
        
    }

    /**
     * Handles clicks on the button "Save and restart".
     * Will try to apply the data input and restart the game.
     * 
     * @return void
     */
    private static void onMouseClickedButtonSaveAndRestart(MouseEvent event) 
    {
        
        updateSettings();
        
        App.getGameObject().begin();
           
        toggleDisplay();
        
    }
    
    /**
     * Toggles the display of the "settings" overlay.
     * 
     * @return void
     */
    private static void toggleDisplay()
    {
        
        if(showSettings)
        {

            App.getGameObject().resume();
            
            buttonSettings.setText("Settings");
                        
        }
        else
        {

            App.getGameObject().pause();

            buttonSettings.setText("Close without saving");
            
        }
        
        showSettings = !showSettings; 
        
        App.getContext().draw();
        
    }

    /**
     * Retrieves the configured number of free surrounding cells that are required for a Grass object to seed.
     * Defaults to Plant.REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_SEEDING_GRASS
     * 
     * @return int
     */
    public static int getRequiredAmountOfFreeSurroundingCellsForSeedingGrass()
    {

        return valueGrassSeedFreeCells > 0 ? valueGrassSeedFreeCells : Plant.REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_SEEDING_GRASS;
        
    }
    
    /**
     * Retrieves the configured number of surrounding partners that are required for a Grass object to seed.
     * Defaults to Plant.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_SEEDING_GRASS
     * 
     * @return int
     */
    public static int getRequiredMinimumAmountOfSurroundingPartnersForSeedingGrass()
    {

        return valueGrassSeedNeighbors > 0 ? valueGrassSeedNeighbors : Plant.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_SEEDING_GRASS;
        
    }

    /**
     * Retrieves the configured number of free surrounding cells that are required for a Carnivore object to procreate.
     * Defaults to Animal.REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_CARNIVORE
     * 
     * @return int
     */
    public static int getRequiredAmountOfFreeSurroundingCellsForProcreationCarnivore()
    {

        return valueProcreationFreeCellsCarnivore > 0 ? valueProcreationFreeCellsCarnivore : Animal.REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_CARNIVORE;
        
    }
    
    /**
     * Retrieves the configured number of surrounding partners that are required for a Carnivore object to procreate.
     * Defaults to Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_CARNIVORE
     * 
     * @return int
     */
    public static int getRequiredMinimumAmountOfSurroundingPartnersForProcreationCarnivore()
    {

        return valueProcreationNeighborsCarnivore > 0 ? valueProcreationNeighborsCarnivore : Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_CARNIVORE;
        
    }
    
    /**
     * Retrieves the configured number of surrounding food that is required for a Carnivore object to procreate.
     * Defaults to Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_CARNIVORE
     * 
     * @return int
     */
    public static int getRequiredMinimumAmountOfSurroundingFoodForProcreationCarnivore()
    {

        return valueProcreationFoodCarnivore > 0 ? valueProcreationFoodCarnivore : Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_CARNIVORE;
        
    }

    /**
     * Retrieves the configured number of free surrounding cells that are required for a Herbivore object to procreate.
     * Defaults to Animal.REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_HERBIVORE
     * 
     * @return int
     */
    public static int getRequiredAmountOfFreeSurroundingCellsForProcreationHerbivore()
    {

        return valueProcreationFreeCellsHerbivore > 0 ? valueProcreationFreeCellsHerbivore : Animal.REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_HERBIVORE;
        
    }
    
    /**
     * Retrieves the configured number of surrounding partners that are required for a Herbivore object to procreate.
     * Defaults to Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_HERBIVORE
     * 
     * @return int
     */
    public static int getRequiredMinimumAmountOfSurroundingPartnersForProcreationHerbivore()
    {

        return valueProcreationNeighborsHerbivore > 0 ? valueProcreationNeighborsHerbivore : Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_HERBIVORE;
        
    }

    /**
     * Retrieves the configured number of surrounding food that is required for a Herbivore object to procreate.
     * Defaults to Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_HERBIVORE
     * 
     * @return int
     */
    public static int getRequiredMinimumAmountOfSurroundingFoodForProcreationHerbivore()
    {

        return valueProcreationFoodHerbivore > 0 ? valueProcreationFoodHerbivore : Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_HERBIVORE;
        
    }

    /**
     * Retrieves the configured number of free surrounding cells that are required for a Omnibore object to procreate.
     * Defaults to Animal.REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_OMNIVORE
     * 
     * @return int
     */
    public static int getRequiredAmountOfFreeSurroundingCellsForProcreationOmnivore()
    {

        return valueProcreationFreeCellsOmnivore > 0 ? valueProcreationFreeCellsOmnivore : Animal.REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_OMNIVORE;
        
    }
    
    /**
     * Retrieves the configured number of surrounding partners that are required for a Omnivore object to procreate.
     * Defaults to Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_OMNIVORE
     * 
     * @return int
     */
    public static int getRequiredMinimumAmountOfSurroundingPartnersForProcreationOmnivore()
    {

        return valueProcreationNeighborsOmnivore > 0 ? valueProcreationNeighborsOmnivore : Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_OMNIVORE;
        
    }

    
    /**
     * Retrieves the configured number of surrounding food that is required for a Omnivre object to procreate.
     * Defaults to Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_OMNIVORE
     * 
     * @return int
     */
    public static int getRequiredMinimumAmountOfSurroundingFoodForProcreationOmnivore()
    {

        return valueProcreationFoodOmnivore > 0 ? valueProcreationFoodOmnivore : Animal.REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_OMNIVORE;
        
    }
    
    /**
     * Retrieves the configured number of rows in a world.
     * Defaults to World.WORLD_NUMBER_ROWS
     * 
     * @return int
     */
    public static int getWorldNumberRows()
    {
        
        return valueWorldRows > 0 ? valueWorldRows : World.WORLD_NUMBER_ROWS;
        
    }

    /**
     * Retrieves the configured number of columns in a world.
     * Defaults to World.WORLD_NUMBER_COLUMNS
     * 
     * @return int
     */
    public static int getWorldNumberColumns()
    {
        
        return valueWorldColumns > 0 ? valueWorldColumns : World.WORLD_NUMBER_COLUMNS;
        
    }

    /**
     * Retrieves the spawn rate percentage for a Grass object.
     * Defaults to Lifeform.LIFEFORM_GRASS_SPAWN_RATE
     * 
     * @return int
     */
    public static int getSpawnRateGrass()
    {
        
        return valueSpawnRateGrass >= 0 ? valueSpawnRateGrass : Lifeform.LIFEFORM_GRASS_SPAWN_RATE;
        
    }

    /**
     * Retrieves the spawn rate percentage for a Carnivore object.
     * Defaults to Lifeform.LIFEFORM_CARNIVORE_SPAWN_RATE
     * 
     * @return int
     */
    public static int getSpawnRateCarnivore()
    {
        
        return valueSpawnRateCarnivores >= 0 ? valueSpawnRateCarnivores : Lifeform.LIFEFORM_CARNIVORE_SPAWN_RATE;
        
    }

    /**
     * Retrieves the spawn rate percentage for a Herbivore object.
     * Defaults to Lifeform.LIFEFORM_HERBIVORE_SPAWN_RATE
     * 
     * @return int
     */
    public static int getSpawnRateHerbivore()
    {
        
        return valueSpawnRateHerbivores >= 0 ? valueSpawnRateHerbivores : Lifeform.LIFEFORM_HERBIVORE_SPAWN_RATE;
        
    }

    /**
     * Retrieves the spawn rate percentage for a Omnivore object.
     * Defaults to Lifeform.LIFEFORM_OMNIVORE_SPAWN_RATE
     * 
     * @return int
     */
    public static int getSpawnRateOmnivore()
    {
        
        return valueSpawnRateOmnivores >= 0 ? valueSpawnRateOmnivores : Lifeform.LIFEFORM_OMNIVORE_SPAWN_RATE;
        
    }
    
    /**
     * Retrieves the configured value for whether the random generator should be reset
     * each time just before the lifeforms are being placed in a world. 
     * 
     * When set to true, the starting conditions will always be the same.
     * Defaults to "false".
     *  
     * @return boolean
     */
    public static boolean getResetRandomGeneratorBeforeAddingLifeforms()
    {
        
        return resetRandomGeneratorBeforeAddingLifeforms;
        
    }

    /**
     * Retrieves whether the game should be displayed with hexagonal fields rather than square ones.
     * Defaults to true
     *  
     * @return boolean
     */
    public static boolean getIsWorldHexagonal()
    {
        
        return isWorldHexagonal;
        
    }

    /**
     * Retrieves whether the game should display additional debugging tools or not.
     *  
     * @return boolean
     */
    public static boolean getIsEnabledDebuggingTools()
    {
        
        return isEnabledDebuggingTools;
        
    }
    
    /**
     * Retrieves the configures value for how many turns a Herbivore can survivie without food, before it starved.
     * 
     * Defaults to Animal.ALLOWED_COUNT_TURNS_WITHOUT_FOOD_HERBIVORE
     * 
     * @return int
     */
    public static int getAllowedNumberOfTurnsWithoutFoodHerbivore()
    {

        return valueAllowedNumberOfTurnsWithoutFoodHerbivore >= 0 ? valueAllowedNumberOfTurnsWithoutFoodHerbivore : Animal.ALLOWED_COUNT_TURNS_WITHOUT_FOOD_HERBIVORE;
        
    }
    
    /**
     * Retrieves the configures value for how many turns a Carnivore can survivie without food, before it starved.
     * 
     * Defaults to Animal.ALLOWED_COUNT_TURNS_WITHOUT_FOOD_CARNIVORE
     * 
     * @return int
     */
    public static int getAllowedNumberOfTurnsWithoutFoodCarnivore()
    {

        return valueAllowedNumberOfTurnsWithoutFoodCarnivore >= 0 ? valueAllowedNumberOfTurnsWithoutFoodCarnivore : Animal.ALLOWED_COUNT_TURNS_WITHOUT_FOOD_CARNIVORE;
        
        
    }
    
    /**
     * Retrieves the configures value for how many turns a Omnivore can survivie without food, before it starved.
     * 
     * Defaults to Animal.ALLOWED_COUNT_TURNS_WITHOUT_FOOD_OMNIVORE
     * 
     * @return int
     */
    public static int getAllowedNumberOfTurnsWithoutFoodOmnivore()
    {

        return valueAllowedNumberOfTurnsWithoutFoodOmnivore >= 0 ? valueAllowedNumberOfTurnsWithoutFoodOmnivore : Animal.ALLOWED_COUNT_TURNS_WITHOUT_FOOD_OMNIVORE;
        
        
    }

}
