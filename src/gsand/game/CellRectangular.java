package gsand.game;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CellRectangular extends Cell
{

    public CellRectangular(int column, int row, World world) 
    {
        
        super();
        
        this.init(column, row, world);
        
    }

    public CellRectangular()
    {
        
    }

    @Override
    public Group getCellImage(String hexColor) 
    {
        
        Paint paintColor = Paint.valueOf(hexColor);
        
        Rectangle rect = new Rectangle();

        rect.setFill(paintColor);
        
        rect.setWidth(Cell.CELL_WIDTH);
        rect.setHeight(Cell.CELL_HEIGHT);

        if(Settings.getIsEnabledDebuggingTools())
        {
            
            rect.setOnMouseClicked(event -> this.onMouseClicked(event));
            
        }
        
        Group group = new Group();
        
        group.getChildren().add(rect);
        
        return group;
        
    }

    /**
     * Handles mouse clicks on the cell.
     * 
     * @param event
     * @return void
     */
    protected void onMouseClicked(MouseEvent event)
    {
        
        System.out.println("Clicked a cell at column " + this.getColumn() + ", row " + this.getRow());
        
    }
    
    /**
     * Retrieves a list of all surrounding / neighboring cells of this cell.
     * 
     * @return Cell[]
     */
    public Cell[] getSurroundingCells()
    {
        
        Cell[] cells = new Cell[8];
        
        int cellColumn = this.getColumn();
        int cellRow = this.getRow();
        
        int index = 0;

        for(int row = -1; row < 2; ++row)
        {

            for(int column = -1; column < 2; ++column)
            {

                int currentColumn = cellColumn + column;
                int currentRow = cellRow + row;

                if(row == 0 && column == 0)
                {
                    
                    // Ignore the current cell
                    
                    continue;
                    
                }
                
                if((currentColumn < 0 || currentRow < 0) || (currentColumn >= Settings.getWorldNumberColumns() || currentRow >= Settings.getWorldNumberRows()))
                {
                    
                    // Coordinates are out of bounds, ignore them.
                    
                    continue;
                    
                }
                
                Cell currentCell = this.getWorld().getCellAt(currentColumn, currentRow);
                
                cells[index] = currentCell;
                
                ++index;
                
            }
            
        }
        
        return cells;
        
    }
    
}
