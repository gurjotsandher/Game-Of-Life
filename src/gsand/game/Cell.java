package gsand.game;

import javafx.scene.Group;
import javafx.scene.paint.Paint;

/**
 * Single cell of a game world.
 * 
 * @author gsand
 * @version 1.0
 */
public abstract class Cell
{
    
    public final static int MAXIMUM_NUMBER_OF_LIFEFORMS_ON_CELL = 2;

    public final static int CELL_WIDTH = 40;
    public final static int CELL_HEIGHT = 40;
    
    private Lifeform[] lifeforms = new Lifeform[MAXIMUM_NUMBER_OF_LIFEFORMS_ON_CELL];
    private World world;

    private int column;
    private int row;
    
    /**
     * Generates an image to display this cell.
     * 
     * @param String hexColor A hex color, e.g. #ff8000
     * @return Group
     */
    public abstract Group getCellImage(String hexColor);
    
    public Cell()
    {
        
    }
    
    /**
     * Checks whether this cell is a direct neighbor of the specified cell.
     * 
     * @param Cell cell
     * @return boolean
     */
    public boolean isCellNeighborOf(Cell cell)
    {
        
        boolean isSameWorld = cell.getWorld().getWorldIndex() == this.getWorld().getWorldIndex();
        boolean isNeighboringWorld = Math.abs(cell.getWorld().getWorldIndex() - this.getWorld().getWorldIndex()) == 1;
        
        if(isSameWorld || isNeighboringWorld)
        {
        
            if(Math.abs(this.getColumn() - cell.getColumn()) < 2 && Math.abs(this.getRow() - cell.getRow()) < 2) 
            {
                                
                return true;
                
            }
            
        }
                
        return false;
        
    }
    
    /**
     * Retrieves a list of all surrounding / neighboring cells of this cell.
     * 
     * @return Cell[]
     */
    abstract public Cell[] getSurroundingCells();
    
    /**
     * Initializes this cell with the specified parameters.
     * 
     * @param int column
     * @param int row
     * @param World world
     */
    public void init(int column, int row, World world)
    {

        this.column = column;
        this.row = row;
        
        this.world = world;
        
    }
    
    /**
     * Retrieves this cell's world.
     * 
     * @return World
     */
    public World getWorld()
    {
        
        return this.world;
        
    }
    
    /**
     * Retrieves this cell's column.
     * 
     * @return int
     */
    public int getColumn() 
    {
        
        return column;
        
    }

    /**
     * Retrieves this cell's row.
     * 
     * @return int
     */
    public int getRow() 
    {
        
        return row;
        
    }

    /**
     * Retrieves whether this Cell has lifeforms on it or not.
     * 
     * @return boolean
     */
    public boolean hasLifeForms()
    {
        
        return this.getCountLifeforms() > 0;
        
    }
    
    /**
     * Retrieves the total number of lifeforms on this cell.
     * 
     * @return int
     */
    public int getCountLifeforms()
    {
        
        if(this.lifeforms == null)
        {
            
            return 0;
            
        }
        
        int count = 0;
        
        for(int i = 0; i < this.lifeforms.length; ++i)
        {

            Lifeform lifeform = this.lifeforms[i];
            
            if(lifeform != null)
            {
                
                ++count;
                
            }
            
        }
        
        return count;
        
    }
    
    /**
     * Retrieves the total number of lifeforms of the specified type on this cell.
     * 
     * @param Class<?> type
     * @return int
     */
    public int getCountLifeforms(Class<?> type)
    {
        
        int count = getCountLifeforms(this.lifeforms, type);
        
        return count;
        
    }
    
    /**
     * Retrieves the total number of lifeforms of the specified type in the specified list of cells.
     * 
     * @param Lifeform[] input
     * @param Class<?> type
     * @return int
     */
    public static int getCountLifeforms(Lifeform[] input, Class<?> type)
    {
        
        if(input == null)
        {
            
            return 0;
            
        }
        
        int count = 0;
        
        for(int i = 0; i < input.length; ++i)
        {

            Lifeform lifeform = input[i];
            
            if(lifeform != null)
            {

                
            }
            
            if(lifeform != null && type.isAssignableFrom(lifeform.getClass()))
            {
                
                ++count;
                
            }
            
        }
        
        return count;
        
    }
    
    /**
     * Retrieves whether this cell has a Lifeform of the specified type on it.
     * 
     * @param Class<?> type
     * @return boolean
     */
    public boolean hasLifeform(Class<?> type)
    {
        
        return this.getLifeform(type) != null;
        
    }

    /**
     * Retrieves a list of all lifeforms on this cell.
     * 
     * @return Lifeform[]
     */
    public Lifeform[] getLifeforms() 
    {
        
        this.sortLifeforms();
        
        return this.lifeforms;
        
    }

    /**
     * Retrieves a list of all lifeforms of the specified type on this cell.
     * 
     * @param Class<?> type
     * @return Lifeform
     */
    public Lifeform getLifeform(Class<?> type) 
    {
        
        for(int i = 0; i < this.lifeforms.length; ++i)
        {
            
            Lifeform lifeform = this.lifeforms[i];
            
            if(lifeform == null)
            {
                
                continue;
                
            }
            
            if(type.isAssignableFrom(lifeform.getClass()))
            {
                
                return this.lifeforms[i];
                
            }
            
        }
        
        return null;
        
    }
    
    /** 
     * Sorts a list of lifeforms.
     * 
     * For example, plants will be put first so that they can be drawn first
     * and Animals can then easily be drawn on top of them.
     * 
     * @param Lifeform[] input
     * @return Lifeform[]
     */
    public static Lifeform[] sortLifeforms(Lifeform[] input)
    {
                
        int indexLifeforms = 0;
        int indexPlants = 0;
        int indexAnimals = 0;
        
        int countPlants = getCountLifeforms(input, Plant.class);
        int countAnimals = getCountLifeforms(input, Animal.class);
                
        Lifeform[] sortingArray = new Lifeform[input.length];
        Lifeform[] plants = new Lifeform[countPlants];
        Lifeform[] animals = new Lifeform[countAnimals];
        
        for(int i = 0; i < input.length; ++i)
        {
            
            Lifeform lifeform = input[i];
            
            if(lifeform != null)
            {
                
                if(Plant.class.isAssignableFrom(lifeform.getClass()))
                {
                    
                    plants[indexPlants] = lifeform;
                    
                    ++indexPlants;
                    
                }
                else if(Animal.class.isAssignableFrom(lifeform.getClass()))
                {
                    
                    animals[indexAnimals] = lifeform;
                    
                    ++indexAnimals;
                    
                }
                                
            }
            
        }

        // Add plants back in first
        
        for(int i = 0; i < plants.length; ++i)
        {
            
            sortingArray[indexLifeforms] = plants[i];
            
            ++indexLifeforms;
            
        }
        
        // Add animals back in after
        
        for(int i = 0; i < animals.length; ++i)
        {
            
            sortingArray[indexLifeforms] = animals[i];
            
            ++indexLifeforms;
            
        }
        
        return sortingArray;
        
    }
    
    /**
     * Sorts this cell's lifeforms.
     * 
     * @return void
     */
    private void sortLifeforms()
    {
        
        this.lifeforms = sortLifeforms(this.lifeforms);
        
    }

    /**
     * Adds a lifeform to this cell.
     * 
     * If a Lifeform of the same type already exists on this field, this will return "false".
     * 
     * Will automatically re-sort this cell's lifeform.
     * 
     * @param Lifeform lifeform
     * @return boolean Successfully added: true; Could not add: false
     */
    public boolean addLifeform(Lifeform lifeform)
    {
        
        if(this.hasLifeform(lifeform.getClass()))
        {
            
            return false;
            
        }

        lifeform.setCell(this);
        
        this.lifeforms[this.lifeforms.length - 1] = lifeform;
        
        this.sortLifeforms();
        
        return true;
        
    }
    
    /**
     * Retrieves whether there's space for the specified type of lifeform on this cell.
     * 
     * @param Class<?> type
     * @return boolean
     */
    public boolean hasSpaceForLifeformType(Class<?> type)
    {
        
        return !this.hasLifeform(type);
        
    }

    /**
     * Removes the specified lifeform from this cell.
     * 
     * If the lifeform is not on this cell, it will return "false".
     * 
     * Will automatically re-sort this cell's lifeform.
     * 
     * @param Lifeform lifeform
     * @return boolean Successfully removed: true; Not removed: false
     */
    public boolean removeLifeform(Lifeform lifeform)
    {
        
        if(!this.hasLifeform(lifeform.getClass()))
        {
            
            return false;
            
        }

        for(int i = 0; i < this.lifeforms.length; ++i)
        {
            
            Lifeform existingLifeForm = this.lifeforms[i];
            
            if(lifeform == null || existingLifeForm == null)
            {
                
                continue;
                
            }
            
            if(existingLifeForm.hasSameIdentifier(lifeform))
            {
                
               this.lifeforms[i] = null;
               
               break;
                
            }
            
        }
        
        this.sortLifeforms();
        
        return true;
        
    }
        
}
