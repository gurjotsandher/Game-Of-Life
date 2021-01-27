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
public class Omnivore extends Animal 
{
        
    /**
     * Creates a new Omnivore.
     */
    public Omnivore()
    {
        
        super(LIFEFORM_CARNIVORE);
        
        this.allowedNumberOfTurnsWithoutFood = Settings.getAllowedNumberOfTurnsWithoutFoodOmnivore();
        
        this.food = new Class<?>[3];

        this.food[0] = Plant.class;
        this.food[1] = Herbivore.class;
        this.food[2] = Carnivore.class;

        this.requiredAmountOfFreeSurroundingCellsForProcreation = Settings.getRequiredAmountOfFreeSurroundingCellsForProcreationOmnivore();
        this.requiredMinimumAmountOfSurroundingPartnersForProcreation = Settings.getRequiredMinimumAmountOfSurroundingPartnersForProcreationOmnivore();
        this.requiredMinimumAmountOfSurroundingFoodForProcreation = Settings.getRequiredMinimumAmountOfSurroundingFoodForProcreationOmnivore();
                
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

        ellipse.setFill(Paint.valueOf("#0000ff"));
        
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
