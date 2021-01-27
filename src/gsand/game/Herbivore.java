package gsand.game;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

/**
 * Herbivore lifeform
 * 
 * @author gsand
 * @version 1.0
 */
public class Herbivore extends Animal 
{
        
    /**
     * Creates a new Herbivore.
     */
    public Herbivore()
    {
        
        super(LIFEFORM_HERBIVORE);
        
        this.allowedNumberOfTurnsWithoutFood = Settings.getAllowedNumberOfTurnsWithoutFoodHerbivore();

        this.food = new Class<?>[1];
        
        this.food[0] = Plant.class;

        this.requiredAmountOfFreeSurroundingCellsForProcreation = Settings.getRequiredAmountOfFreeSurroundingCellsForProcreationHerbivore();
        this.requiredMinimumAmountOfSurroundingPartnersForProcreation = Settings.getRequiredMinimumAmountOfSurroundingPartnersForProcreationHerbivore();
        this.requiredMinimumAmountOfSurroundingFoodForProcreation = Settings.getRequiredMinimumAmountOfSurroundingFoodForProcreationHerbivore();
                
    }
    
    /**
     * Retrieves the image to display.
     * 
     * @return Group
     */
    public Group getImage()
    {

        float width = Cell.CELL_WIDTH - 20;
        float height = Cell.CELL_HEIGHT - 20;
        
        Ellipse ellipse = new Ellipse(); 

        ellipse.setFill(Paint.valueOf("#ffff00"));
        
        ellipse.setCenterX(Cell.CELL_WIDTH / 2);
        ellipse.setCenterY(Cell.CELL_HEIGHT / 2);
        ellipse.setRadiusX(width / 2);
        ellipse.setRadiusY(height / 2);
        
        Group group = new Group();
        
        group.getChildren().add(ellipse);

        group.setLayoutX(Cell.CELL_WIDTH / 4);
        group.setLayoutY(Cell.CELL_HEIGHT / 4);
        
        return group;
        
    }
        
}
