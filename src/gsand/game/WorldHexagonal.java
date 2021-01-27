package gsand.game;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A hexagonal game world & its cells.
 * 
 * @author gsand
 * @version 1.0
 */
public class WorldHexagonal extends World
{
    
    int worldCellsDrawn = 0;
    
    int paddingX = Cell.CELL_WIDTH / 2;
    int paddingY = Cell.CELL_HEIGHT / 2;
    
    /**
     * Creates a new rectangular world.
     * 
     * @return void
     */
    public WorldHexagonal()
    {
        
        super();
        
        this.cellType = new CellHexagonal();

        this.cells = new Cell[this.getNumberOfColumns()][this.getNumberOfRows()];
        
    }
        
    /**
     * Retrieves the width of this world.
     * 
     * @return int
     */
    public double getWidth()
    {
        
        double width = this.getNumberOfColumns() * Cell.CELL_WIDTH + Cell.CELL_WIDTH * .5 + paddingX * 2;
        
        return width;
        
    }
        
    /**
     * Retrieves the height of this world.
     * 
     * @return int
     */
    public double getHeight()
    {
        
        double height = this.getNumberOfRows() * Cell.CELL_HEIGHT * .75 + Cell.CELL_HEIGHT * 0.25 + paddingY * 2;
        
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
                
        double r = Cell.CELL_WIDTH / 2;
        double n = Math.sqrt(r * r * 0.75); 

        String color = COLOR_FIELD_EVEN;
                        
        Group cellGraphic = this.getCellAt(column, row).getCellImage(color);

        sp.setLayoutX(this.getPositionX() + column * Cell.CELL_WIDTH + (row % 2) * Cell.CELL_WIDTH / 2 + paddingX);
        sp.setLayoutY(this.getPositionY() + row * Cell.CELL_HEIGHT * .75 + paddingY);

        sp.getChildren().add(cellGraphic);
        
        if(Settings.getIsEnabledDebuggingTools())
        {
            
            Text t = new Text();
            t.setFont(new Font(10));
            t.setText(column + "-" + row);
            
            sp.getChildren().add(t);
            
        }
        
        ++this.worldCellsDrawn;
                
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
