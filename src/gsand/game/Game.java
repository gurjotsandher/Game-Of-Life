package gsand.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.Group;

abstract public class Game 
{

    protected List<EventListener> eventListeners = new ArrayList<EventListener>();
    
    private boolean isGameRunning = false;
    private boolean isGamePaused = false;
    
    private int countGameTurns = 0;
    private int countGameStarts = 0;
    
    private Lifeform[] lifeforms;
    
    /**
     * Used to sort configurable spawn rates for different lifeforms.
     */
    private class SpawnRate
    {

        public int rate;
        public Lifeform lifeform;
        
        private class SpawnRateComparator implements Comparator<SpawnRate> 
        {
            
            @Override
            public int compare(SpawnRate rateOne, SpawnRate rateTwo) 
            {
                
                return rateOne.getRate().compareTo(rateTwo.getRate());
                
            }
            
        }
        
        public SpawnRate(int rate, Lifeform lifeform)
        {
            
            this.rate = rate;
            this.lifeform = lifeform;
            
        }
        
        public SpawnRate() 
        {
            
        }

        public Integer getRate()
        {
            
            return this.rate;
            
        }
        
        public Lifeform getLifeform()
        {
            
            return this.lifeform;
            
        }
        
        public void sortBySpawnRate(ArrayList<SpawnRate> spawnRates)
        {
            
            Collections.sort(spawnRates, new SpawnRateComparator());
            
        }
        
    }
        
    /**
     * Initializes a new "game of life".
     */
    public Game()
    {
        
        this.addListeners();
        
        this.reInitializeWorld();
        
    }
    
    /**
     * Resets this world and re-initializes it with new lifeforms and parameters.
     * 
     * @return void
     */
    protected void reInitializeWorld()
    {
        
        this.lifeforms = new Lifeform[Cell.MAXIMUM_NUMBER_OF_LIFEFORMS_ON_CELL * Settings.getWorldNumberColumns() * Settings.getWorldNumberRows()];
        
        this.initWorld();
        
    }
    
    /**
     * Initializes the world.
     * 
     * @return void
     */
    abstract void initWorld();

    /**
     * Pauses the game.
     * Used for example for when the "settings" overlay is displayed.
     * 
     * @return void
     */
    public void pause()
    {
        
        this.isGamePaused = true;

        for(EventListener eventListener: eventListeners)
        {
            
            eventListener.onGamePaused();
            
        }
        
    }
    
    /**
     * Resumes the game after it has been paused.
     * 
     * @return void
     */
    public void resume()
    {
        
        this.isGamePaused = false;

        for(EventListener eventListener: eventListeners)
        {
            
            eventListener.onGameResumed();
            
        }
        
    }
    
    /**
     * Retrieves the count of all lifeforms in the game.
     * 
     * @return int
     */
    public int getCountLifeforms()
    {
        
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
     * Retrieves the count of the lifeform of the specified type in the game.
     * 
     * @param Class<?> type
     * @return int
     */
    public int getCountLifeforms(Class<?> type)
    {
        
        int count = Cell.getCountLifeforms(this.lifeforms, type);
        
        return count;
        
    }
    
    /**
     * Retrieves a list of all lifeforms.
     * 
     * @return Lifeform[]
     */
    public Lifeform[] getLifeforms()
    {
        
        int existingIndex = 0;     
        
        Lifeform[] lifeforms = new Lifeform[this.getCountLifeforms()];
        
        for(int i = 0; i < this.lifeforms.length; ++i)
        {
            
            Lifeform lifeform = this.lifeforms[i];
            
            if(lifeform != null)
            {
                
                lifeforms[existingIndex] = this.lifeforms[i];
                
                ++existingIndex;
                
            }
            
        }
        
        return lifeforms;
        
    }
    
    /**
     * Retrieves a list of all lifeforms of the specified type.
     * 
     * @param Class<?> type
     * @return Lifeform[]
     */
    public Lifeform[] getLifeforms(Class<?> type)
    {
        
        int typeIndex = 0;     
        
        Lifeform[] lifeforms = new Lifeform[this.getCountLifeforms(type)];
        
        for(int i = 0; i < this.lifeforms.length; ++i)
        {
            
            Lifeform lifeform = this.lifeforms[i];
            
            if(lifeform != null && lifeform.getClass().isAssignableFrom(type))
            {
                
                lifeforms[typeIndex] = this.lifeforms[i];
                
                ++typeIndex;
                
            }
            
        }
        
        return lifeforms;
        
    }
    
    /**
     * Advances the game by one turn.
     * 
     * @return void
     */
    public void advanceTurn()
    {
        
        Lifeform[] lifeforms = this.getLifeforms();

        
        for(int i = 0; i < lifeforms.length; ++i)
        {
            
            // Move all lifeforms
            
            lifeforms[i].move();
            
        }
        
        lifeforms = this.getLifeforms();

        for(int i = 0; i < lifeforms.length; ++i)
        {
            
            // Make all lifeforms procreate
            
            lifeforms[i].procreate();
            
        }

        lifeforms = this.getLifeforms();

        for(int i = 0; i < lifeforms.length; ++i)
        {
            
            // Make all lifeforms nourish / eat
            
            lifeforms[i].nourish();
            
        }
        
        ++countGameTurns;
        
        //
        
        if(this.getCountDifferentLifeformsAlive() <= 1)
        {
            
            this.end();
            
            return;
            
        }
        
        //
        
        for(EventListener eventListener: eventListeners)
        {
            
            eventListener.onAdvanceTurn();
            
        }
        
    }
    
    /**
     * Retrieves the count of different lifetypes that are still alive.
     * 
     * @return int
     */
    public int getCountDifferentLifeformsAlive()
    {
        
        boolean allPlantsEaten = App.getGameObject().getCountLifeforms(Plant.class) == 0;
        boolean allCarinivoresEaten = App.getGameObject().getCountLifeforms(Carnivore.class) == 0;
        boolean allHerbivoresEaten = App.getGameObject().getCountLifeforms(Herbivore.class) == 0;
        boolean allOmnivoresEaten = App.getGameObject().getCountLifeforms(Omnivore.class) == 0;

        
        int count = 0;

        count += (!allPlantsEaten) ? 1 : 0;
        count += (!allCarinivoresEaten) ? 1 : 0;
        count += (!allHerbivoresEaten) ? 1 : 0;
        count += (!allOmnivoresEaten) ? 1 : 0;
        
        return count;
        
    }

    /**
     * Retrieves the number of times the game has been (re)started.
     * 
     * @return int
     */
    public int getCountGameStarts()
    {
        
        return this.countGameStarts;
        
    }
    
    /**
     * Retrieves the number of turns in the currenctly started game's iteration.
     * 
     * @return int
     */
    public int getCountGameTurns()
    {
        
        return this.countGameTurns;
        
    }
    
    /**
     * Attaches event listeners.
     * 
     * @return void
     */
    protected void addListeners()
    {
        
        EventHandler eventHandler = new EventHandler();
        
        eventListeners.add(eventHandler);
        
    }

    /**
     * Adds specified lifeform to the specified cell and the game itself.
     * 
     * @param Cell cell
     * @param Lifeform lifeform
     * @return void
     */
    public void addLifeformToWorld(Cell cell, Lifeform lifeform)
    {
        
        if(cell.addLifeform(lifeform))
        {

            this.lifeforms[this.getCountLifeforms()] = lifeform;
            
            this.sortLifeforms();
            
        }
        
    }
    
    /**
     * Adds specified lifeform to the specified cell, 
     * world and the game itself, at the specified coordinates.
     * 
     * @param World world
     * @param int column
     * @param int row
     * @param Lifeform lifeform
     * @return void
     */
    protected void addLifeformToWorld(World world, int column, int row, Lifeform lifeform)
    {
        
        this.addLifeformToWorld(world.getCellAt(column, row), lifeform);
        
    }

    /**
     * Removes a lifeform from the cell it is currently on as well as the game itself.
     * 
     * @param Lifeform lifeform
     * @return void
     */
    public boolean removeLifeformFromWorld(Lifeform lifeform)
    {
                
        for(int i = 0; i < this.lifeforms.length; ++i)
        {
            
            Lifeform existingLifeform = this.lifeforms[i];
            
            if(lifeform == null || existingLifeform == null)
            {
                
                continue;
                
            }
            
            
            if(existingLifeform.hasSameIdentifier(lifeform))
            {
                                
                if(lifeform.getCell().removeLifeform(lifeform))
                {
                
                    this.lifeforms[i] = null;
                   
                    break;
                    
                }
                else
                {

                    if(Settings.getIsEnabledDebuggingTools())
                    {
                        
                        System.out.println("Could not remove lifeform.");
                    
                    }
                    
                }
                
            }
            
        }
        
        this.sortLifeforms();
        
        return true;
        
    }
    
    /**
     * Sorts the game's list of lifeforms.
     * 
     * For example, plants will be put first so that they will be drawn first. 
     * Animals can then easily be drawn on top of them.
     * 
     * @return void
     */
    private void sortLifeforms()
    {
        
        this.lifeforms = Cell.sortLifeforms(this.lifeforms);
        
    }
    
    /**
     * Adds lifeforms to the specified World and this game.
     * 
     * @param World world
     * @return void
     */
    protected void setLifeformsOnCells(World world)
    {

        if(Settings.getResetRandomGeneratorBeforeAddingLifeforms())
        {
            
            RandomGenerator.reset();
            
        }
        
        // Put all spawn rates into a list so we can sort them.
        // The changes from assignment make this a requirement,
        // because the spawn rates in this version of the game can be changed 
        // at runtime through the settings screen. 
        // The lowest spawn rates need to be checked first.
        
        ArrayList<SpawnRate> spawnRates = new ArrayList<SpawnRate>();

        spawnRates.add(new SpawnRate(Settings.getSpawnRateCarnivore(), new Carnivore()));
        spawnRates.add(new SpawnRate(Settings.getSpawnRateGrass(), new Grass()));
        spawnRates.add(new SpawnRate(Settings.getSpawnRateHerbivore(), new Herbivore()));
        spawnRates.add(new SpawnRate(Settings.getSpawnRateOmnivore(), new Omnivore()));
        
        SpawnRate sorter = new SpawnRate();
        sorter.sortBySpawnRate(spawnRates);
        
        // 

        for(int row = 0; row < Settings.getWorldNumberRows(); ++row)
        {

            columnLoop: for(int column = 0; column < Settings.getWorldNumberColumns(); ++column)
            {
                
                int randomNumber = RandomGenerator.nextNumber(99);
                
                for(int i = 0; i < spawnRates.size(); ++i)
                {
                    
                    // Check all spawn rates

                    SpawnRate spawnRate = spawnRates.get(i);
                    
                    if(randomNumber >= 100 - spawnRate.getRate())
                    {
                                         
                        try 
                        {
                            
                            this.addLifeformToWorld(world, column, row, (Lifeform) spawnRate.getLifeform().getClass().newInstance());
                            
                        } 
                        catch (InstantiationException | IllegalAccessException e) 
                        {
                            
                            e.printStackTrace();
                            
                        }
                        
                        // Continue to the next column, according to the assignment 3b,
                        // we must not have more than one lifeform per field anymore.
                        
                        continue columnLoop;
                        
                    }
                    
                }

                // Old version with multiple lifeforms per field:
                
                /*
                if(RandomGenerator.nextNumber(99) >= 100 - Settings.getSpawnRateGrass())
                {
                                     
                    this.addLifeformToWorld(world, column, row, new Grass());
                    
                }
                
                if(RandomGenerator.nextNumber(99) >= 100 - Settings.getSpawnRateHerbivore())
                {
            
                    this.addLifeformToWorld(world, column, row, new Herbivore());
                    
                }
                */
                
            } 
            
        }

    }

    /**
     * Retrieves if the current game is running.
     * Will return false if it ended.
     * 
     * @return boolean
     */
    public boolean isGameRunning()
    {
        
        return this.isGameRunning;
        
    }
    
    /**
     * Retrieves whether the game is paused or not.
     * 
     * @return boolean
     */
    public boolean isGamePaused()
    {
        
        return this.isGamePaused;
        
    }
    
    /**
     * Initializes and starts a new game.
     * 
     * @return void
     */
    public void begin()
    {
        
        this.countGameTurns = 0;
        
        ++this.countGameStarts;
        
        if(this.countGameStarts > 1)
        {
            
            for(EventListener eventListener: eventListeners)
            {
                
                eventListener.onGameRestarted();
                
            }
            
        }

        this.isGameRunning = true;
        this.isGamePaused = false;
        
        if(countGameStarts > 1)
        {
            
            this.reInitializeWorld();
            
        }
        
        for(EventListener eventListener: eventListeners)
        {
            
            eventListener.onGameStarted();
            
        }
        
    }
    
    /**
     * Ends the current game.
     * 
     * @return void
     */
    public void end()
    {
        
        this.isGameRunning = false;

        for(EventListener eventListener: eventListeners)
        {
            
            eventListener.onGameEnded();
            
        }
        
    }

    /**
     * Draws the game to the specified Group.
     * 
     * @param Group root
     * @return void
     */
    abstract public void draw(Group root);
    
    /**
     * Retrieves the world by the specified index.
     * 
     * @param int worldIndex
     * @return World
     */
    public abstract World getWorldByIndex(int worldIndex);

}
