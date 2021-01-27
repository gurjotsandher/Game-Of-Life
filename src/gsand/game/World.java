package gsand.game;

import java.lang.reflect.Constructor;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;

/**
 * A game world & its cells.
 * 
 * @author gsand
 * @version 1.0
 */
abstract public class World 
{

    static final int WORLD_NUMBER_COLUMNS = 20;
    static final int WORLD_NUMBER_ROWS = 20;

    static final String COLOR_FIELD_EVEN = "#eee";
    static final String COLOR_FIELD_ODD = "#fff";
    
    private int positionX = 0;
    private int positionY = 0;
    private int worldIndex = 0;
    
    protected Cell[][] cells;
    
    protected Cell cellType;
    
    /**
     * Retrieves the index of this world.
     * 
     * @return int
     */
    public int getWorldIndex()
    {
        
        return this.worldIndex;
        
    }
    
    /**
     * Creates a new world.
     * 
     * @return void
     */
    public World()
    {

        this.cells = new Cell[this.getNumberOfColumns()][this.getNumberOfRows()];
        
    }
        
    /**
     * Retrieves the width of this world.
     * 
     * @return int
     */
    public abstract double getWidth();
    
    /**
     * Retrieves the height of this world. 
     * 
     * @return int
     */
    public abstract double getHeight();
    
    /**
     * Retrieves this world's "X" coordinate.
     * 
     * @return int
     */
    public int getPositionX()
    {
        
        return positionX;
        
    }
    
    /**
     * Retrieves this world's "Y" coordinate.
     * 
     * @return int
     */
    public int getPositionY()
    {
        
        return positionY;
        
    }

    /**
     * Retrieves this world's number of rows from the settings.
     * Will use World.WORLD_NUMBER_ROWS as a default value.
     * 
     * @return int
     */
    public int getNumberOfRows()
    {
        
        return Settings.getWorldNumberRows();
        
    }
    
    /**
     * Retrieves this world's number of columns from the settings.
     * Will use World.WORLD_NUMBER_COLUMNS as a default value.
     * 
     * @return int
     */
    public int getNumberOfColumns()
    {
        
        return Settings.getWorldNumberColumns();
        
    }
    
    /**
     * Retrieves the cell at the specified coordinates.
     * 
     * @param int columnIndex
     * @param int rowIndex
     * @return Cell
     */
    public Cell getCellAt(int columnIndex, int rowIndex)
    {
        
        Cell cell = null;
        
        if(rowIndex >= 0 && columnIndex >= 0 && rowIndex < this.getNumberOfRows() && columnIndex < this.getNumberOfColumns())
        {
                    
            cell = this.cells[columnIndex][rowIndex];
        
        }
        else
        {
            
            if(Settings.getIsEnabledDebuggingTools())
            {
                
                System.out.println("The requested cell at column " + columnIndex + ", row " + rowIndex + " is out of bounds!");
                
            }
            
        }
        
        return cell;
        
    }
    
    /**
     * Initializes the world and adds cells.
     * 
     * @return void
     */
    public void init()
    {
        
        Class<?> cellClass = this.cellType.getClass();

        for(int row = 0; row < getNumberOfRows(); ++row)
        {

            for(int column = 0; column < getNumberOfColumns(); ++column)
            {
                                
                try 
                {
                    
                    this.cells[column][row] = (Cell) cellClass.newInstance();
                    
                    this.cells[column][row].init(column, row, this);
                    
                } 
                catch (InstantiationException | IllegalAccessException e) 
                {
                    
                    e.printStackTrace();
                    
                }
                                
            }
            
        }
        
    }
    
    /**
     * Draws this world's cells and lifeforms.
     * 
     * @param Group root
     * @return void
     */
    public void drawFields(Group root)
    {

        for(int row = 0; row < Settings.getWorldNumberRows(); ++row) 
        {

            for(int column = 0; column < Settings.getWorldNumberColumns(); ++column) 
            {
                
                StackPane sp = new StackPane();
                
                this.drawWorldCells(sp, column, row);
                
                this.drawLifeforms(sp, column, row);
                
                root.getChildren().add(sp);
                
            }
            
        }
        
    }

    /**
     * Draws the cells of this world.
     * 
     * @param StackPane sp
     * @param int column
     * @param int row
     * @return void
     */
    abstract public void drawWorldCells(StackPane sp, int column, int row);
    
    /**
     * Draws the lifeforms of this world. 
     * 
     * @param StackPane sp
     * @param int column
     * @param int row
     * @return void
     */
    abstract protected void drawLifeforms(StackPane sp, int column, int row);
    
}
