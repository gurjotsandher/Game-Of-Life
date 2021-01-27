package gsand.game;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A game world & its cells.
 * 
 * @author gsand
 * @version 1.0
 */
public class WorldRectangular extends World
{
    
    /**
     * Creates a new rectangular world.
     * 
     * @return void
     */
    public WorldRectangular()
    {
        
        super();
        
        this.cellType = new CellRectangular();

        this.cells = new Cell[this.getNumberOfColumns()][this.getNumberOfRows()];
        
    }
        
    /**
     * Retrieves the width of this world.
     * 
     * @return int
     */
    public double getWidth()
    {
        
        double width = this.getNumberOfColumns() * Cell.CELL_WIDTH;
        
        return width;
        
    }
        
    /**
     * Retrieves the height of this world.
     * 
     * @return int
     */
    public double getHeight()
    {
        
        double height = this.getNumberOfRows() * Cell.CELL_HEIGHT;
        
        return height;
        
    }
    
    /**
     * Draws the cells of this world.
     * 
     * @param StackPane sp
     * @param int column
     * @param int row
     * @return void
     */
    public void drawWorldCells(StackPane sp, int column, int row)
    {
        
        String color = (column + row) % 2 == 0 ? COLOR_FIELD_EVEN : COLOR_FIELD_ODD;
        
        Group cellGraphic = this.getCellAt(column, row).getCellImage(color);

        sp.setLayoutX(this.getPositionX() + column * Cell.CELL_WIDTH);
        sp.setLayoutY(this.getPositionY() + row * Cell.CELL_HEIGHT);
        
        sp.getChildren().add(cellGraphic);

        if(Settings.getIsEnabledDebuggingTools())
        {
            
            Text t = new Text();
            t.setFont(new Font(10));
            t.setText(column + "-" + row);
            
            sp.getChildren().add(t);
            
        }
        
    }
    
    /**
     * Draws the lifeforms of this world. 
     * 
     * @param StackPane sp
     * @param int column
     * @param int row
     * @return void
     */
    protected void drawLifeforms(StackPane sp, int column, int row)
    {
        
        Lifeform[] lifeforms = this.getCellAt(column, row).getLifeforms();
                
        for(int i = 0; i < lifeforms.length; ++i)
        {
            
            Lifeform lifeform = lifeforms[i];
    
            if(lifeform != null) 
            {
                
                Group image = lifeform.getImage();
                
                image.setLayoutX(column * Cell.CELL_WIDTH);
                image.setLayoutY(row * Cell.CELL_HEIGHT);
                
                sp.getChildren().add(image);
                                
            }
            
        }
        
    }
    
}
