package gsand.game;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

/**
 * Carnivore lifeform
 * 
 * @author gsand
 * @version 1.0
 */
public class Carnivore extends Animal 
{
        
    /**
     * Creates a new Carnivore.
     */
    public Carnivore()
    {
        
        super(LIFEFORM_CARNIVORE);
        
        this.allowedNumberOfTurnsWithoutFood = Settings.getAllowedNumberOfTurnsWithoutFoodCarnivore();
        
        this.food = new Class<?>[2];

        this.food[0] = Herbivore.class;
        this.food[1] = Omnivore.class;
        
        this.requiredAmountOfFreeSurroundingCellsForProcreation = Settings.getRequiredAmountOfFreeSurroundingCellsForProcreationCarnivore();
        this.requiredMinimumAmountOfSurroundingPartnersForProcreation = Settings.getRequiredMinimumAmountOfSurroundingPartnersForProcreationCarnivore();
        this.requiredMinimumAmountOfSurroundingFoodForProcreation = Settings.getRequiredMinimumAmountOfSurroundingFoodForProcreationCarnivore();
                
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

        ellipse.setFill(Paint.valueOf("#ff0000"));
        
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
