package gsand.game;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class CellHexagonal extends Cell
{

    public CellHexagonal(int column, int row, World world) 
    {
        
        super();
        
        this.init(column, row, world);
        
    }

    public CellHexagonal()
    {
        
    }

    @Override
    public Group getCellImage(String hexColor) 
    {
        
        Paint paintColor = Paint.valueOf(hexColor);
                
        Polygon hexagon = new Polygon();
        

        double r = Cell.CELL_WIDTH / 2;
        double n = Math.sqrt(r * r * 0.75); 
        
        double x = 0;
        double y = 0;

        double width = 1 * Cell.CELL_WIDTH;
        double height = 1 * Cell.CELL_HEIGHT;
        
        hexagon.getPoints().addAll(
                
            x, y,
            x, y + r,
            x + n, y + r * 1.5,
            x + width, y + r,
            x + height, y,
            x + n, y - r * 0.5
            
        );
        
        hexagon.setFill(paintColor);
        hexagon.setStrokeWidth(1);
        hexagon.setStroke(Paint.valueOf("#aaa"));
        
        if(Settings.getIsEnabledDebuggingTools())
        {
            
            hexagon.setOnMouseClicked(event -> this.onMouseClicked(event));
            
        }
        
        //
        
        Group group = new Group();
        
        group.getChildren().add(hexagon);
        
        return group;
        
    }
    
    /**
     * Handles mouse clicks on the hexagon cell.
     * 
     * @param event
     * @return void
     */
    protected void onMouseClicked(MouseEvent event)
    {
        
        //Cell[] surroundingCells = this.getSurroundingCells();

        System.out.println("Clicked a hexagonal cell at column " + this.getColumn() + ", row " + this.getRow());
        
    }


    /**
     * Retrieves a list of all surrounding / neighboring cells of this cell.
     * 
     * @return Cell[]
     */
    public Cell[] getSurroundingCells()
    {
        
        Cell[] cells = new Cell[6];
        
        int cellColumn = this.getColumn();
        int cellRow = this.getRow();
                
        if(cellRow % 2 == 0)
        {

            if(cellRow > 0)
            {
                
                // Top

                if(cellColumn > 0)
                {
                    
                    cells[0] = this.getWorld().getCellAt(cellColumn - 1 , cellRow - 1);
                    
                }
                
                if(cellColumn < Settings.getWorldNumberColumns())
                {
                    
                    cells[1] = this.getWorld().getCellAt(cellColumn, cellRow - 1);
                    
                }
                
            }

            if(cellRow < Settings.getWorldNumberRows())
            {
                
                // Bottom
                                
                if(cellColumn > 0)
                {
                    
                    cells[4] = this.getWorld().getCellAt(cellColumn, cellRow + 1);
                    
                }

                if(cellColumn < Settings.getWorldNumberColumns())
                {
                                        
                    cells[5] = this.getWorld().getCellAt(cellColumn + 1, cellRow + 1);
                    
                }
            
            }
            
        }
        else
        {

            if(cellRow > 0)
            {
                
                // Top
                
                cells[0] = this.getWorld().getCellAt(cellColumn, cellRow - 1);

                if(cellColumn < Settings.getWorldNumberColumns())
                {
                
                    cells[1] = this.getWorld().getCellAt(cellColumn + 1, cellRow - 1);
                    
                }
                
            }
                
            if(cellRow < Settings.getWorldNumberRows())
            {
                
                // Bottom
                
                cells[4] = this.getWorld().getCellAt(cellColumn, cellRow + 1);

                if(cellColumn < Settings.getWorldNumberColumns())
                {
                
                    cells[5] = this.getWorld().getCellAt(cellColumn + 1, cellRow + 1);
                    
                }
            
            }
                
        }

        // Center
        
        if(cellColumn > 0)
        {

            cells[2] = this.getWorld().getCellAt(cellColumn - 1, cellRow);
                    
        }
        
        if(cellColumn < Settings.getWorldNumberColumns())
        {

            cells[3] = this.getWorld().getCellAt(cellColumn + 1, cellRow);
                    
        }
        
        return cells;
        
    }
    
}
