Reading and wrtiting from input file:
    * GameLoader Class
        For reading and writing to input and output files respectively.
        The class reads the Map dimensions and then store each row
        to an array list which is used later to separte each land.
        I store hero types in array list as well as heroMovement which
        are later processed to each hero.
        Then, passing all the above data GameInput class.
    * GameInput Class
        Store all data collected form the input file so it can be easily
        getted in the main game whenever needed.

Heros:
    Hero: Main abstract class for all game heros. 
    I added all common features among heros in this 
    class and any additional features is added in each
    specific hero.
    * Extended classes
        - Pyromancer
        - Knight
        - Wizard
        - Rogue
    * Get each ability for each Hero which is used later
    to calculate the damage on another hero
    * Using enum for heroes types

Abilities:
    Ability: Main abstract class for all heroes' abilities.
    * Using Factory pattern to approach abilities creation.
    * Three main functions in each ability which are:
        - execute abilities without modifiers
        - execute on enemy with modifiers
        - execute for overtime

Game Mechanisim:
    1- Loop over all the input hero types to create each hero
    2- Add all created heros to array list
    3- Loop over all game rounds
    4- In each round I loop throught all the heros to 
        update position and overtime values
    5- Loop on other heros to check if there is another
        hero in same place
    6- If two heros are in same place fight begin
    7- After fight done another loop happen to check if hero
        is dead or is leveled up

 Constants
    All game constants are added in a static class 
