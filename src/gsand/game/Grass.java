package gsand.game;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 * Grass lifeform
 * 
 * @author gsand
 * @version 1.0
 */
public class Grass extends Plant 
{
    
    /**
     * Initialized a new Grass object.
     */
    public Grass()
    {
        
        super(LIFEFORM_GRASS);
        
        this.requiredAmountOfFreeSurroundingCellsForProcreation = Settings.getRequiredAmountOfFreeSurroundingCellsForSeedingGrass();
        this.requiredMinimumAmountOfSurroundingPartnersForProcreation = Settings.getRequiredMinimumAmountOfSurroundingPartnersForSeedingGrass();
                
    }
    
    /**
     * Retrieves the image of this lifeform.
     * 
     * @return Group
     */
    public Group getImage()
    {
        
        float width = Cell.CELL_WIDTH - 10;
        float height = Cell.CELL_HEIGHT -10;
        
        Ellipse ellipse = new Ellipse(); 

        ellipse.setFill(Paint.valueOf("#00ff00"));
        
        ellipse.setCenterX(Cell.CELL_WIDTH / 2);
        ellipse.setCenterY(Cell.CELL_HEIGHT / 2);
        ellipse.setRadiusX(width / 2);
        ellipse.setRadiusY(height / 2);

        Group group = new Group();
        
        group.getChildren().add(ellipse);

        group.setLayoutX(0);
        group.setLayoutY(0);
        
        return group;
        
    }
            
    /**
     * Grass will not move.
     * 
     * @return void
     */
    public void move()
    {
        
        // Grass doesn't move.
        
    }
    
    /**
     * Grass never dies by itself.
     * 
     * @return void
     */
    protected void die()
    {
        
        // Grass does not die by itself.
        
    }
        
}
