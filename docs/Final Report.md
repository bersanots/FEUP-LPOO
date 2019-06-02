# LPOO_212 Castle Breakout

In this platform game you can help our hero escape the castle by pulling the lever or collecting a key that opens the gates throughout three different levels in which you will encounter all kinds of foes, from always watchful guards to reckless ogres.

This project was developed by Bernardo Santos (up201504711@fe.up.pt) and Filipe Almeida (up201708999@fe.up.pt) for LPOO 2018/19.

## Implemented Features

The game has two levels. The first one has a small amount of enemies and a lot of free space, allowing the user to first get used with the controls and all the obstacles. The second level it's a lot harder, meat to be a challenge for the user to put in practise all he learned from the first level.

In each level, the elements found are:

* The Hero: the main focus of the game. It's controlled by the user and used to navigate through the map. Initially has 3 lives and every time it loses one, has a short invulnerability time.
* Walls: used to create the main frame of the map. Each wall is created separately, which allows to not only create the 4 outer walls but also to give more depth to the map.
* Monsters: not very smart characters but still a threat. Only move from one side to another not taking in consideration the position of the hero and if this two meet in the same position, one life is taken away.
* Wizards: move in a similar way to monsters but the threat they pose is their spells. One is shot every time there is a clear way to hit the hero, although after one is fired there's minimum time that has to pass after next one.
* Items: extra lives and keys spread throughout the map.
* The Gate: the final objective of the hero. Behaves just like a wall unless the hero has collected all the keys which allows the gate to be opened.

Screenshots:
![Screenshots 1](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_212/blob/master/docs/Screenshots/screenshot1.png)
![Screenshots 2](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_212/blob/master/docs/Screenshots/screenshot2.png)
![Screenshots 3](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_212/blob/master/docs/Screenshots/screenshot3.png)

## Planned Features

With the project finished, some ideas were abandoned because of the lack of time. Here are some examples:
* New obstacles. This could be new characters that have different interactions or by creating traps.
* Give the hero more options. There is still the possibility to give the hero new abilities or powers.
* Better menus. The game could have the possibility to chose which level you want to play or to quit te game.


## Design

### Strategy
#### Problem in Context
To begin coding our project, it was decided that there was a necessity to create multiple classes to represent the different elements. It seemed very evident that a lot of this classes would only differ on their behaviour and shared a lot with each other.

#### The Pattern

After studying the situation carefully, the design that seemed to be more suited was the Strategy pattern. By allowing the game loop to instruct a class to move and it's action depending on the class they represent, we would be creating a more organized way to have characters with different behaviors.

#### Implementation

For the implementation part of the problem, the base was to create a class Character. Each character would have his current position and abstract methods to move and draw itself. Upon the creation of a new character, those methods would be overridden to to change their movement and appearance.

#### Consequences

This allowed the code to be structured and facilitated the creation of new elements. However it came with the drawback of forcing the game's main loop to be aware of all the different elements increasing it's complexity.


### Model-View-Controller (MVC)
#### Problem in Context

Initially the code was thought out to use only Lanterna. This turned out to be a problem when trying to fill the requirement for it to also work whit Swing. So we it was decided that some major changes to the overall structure of the code were necessary.

#### The Pattern

The pattern followed to review the code was the MVC. This was the obvious choice since it would allow the functions that deal with the visual part to be modified and work with the chosen graphics mode and not force any changes to the logical part of the game.

#### Implementation

To implement the new design pattern, the classes were divided into tree different groups: the Controller, the Model, and the View. The controller group consists in class Game that coordinated all the elements and the group model has all the classes that represent the components of the game. These groups only needed changes to the functions that take care of the visual part.
The main new components that were created are in the view group. The creation of new classes that allow both Swing and Lanterna to be used was necessary to be able to make use of all the changes.

#### Consequences

The usage of this mew architecture allows for a much easier way to display the game in different ways but one problem that was noticed right away was that it can bring some issues regarding the efficiency of the code. Although not very noticeable with Lanterna, with Swing the game was running at a much slower pace.



### Classes
#### Game

Used to create and store all the components of the game. The entire purpose of this class is to allow communication between all the object that are part of the game. Contains the game loop which is programmed to run at approximately 30 frames per second.

#### Position

Represents a position on the board. Stores the x and y values.

#### Character

Abstract class that represents a moving character. Stores it's position and provides functions to move it. Also implements abstract methods to draw itself amd to check fo collisions with walls.

#### Hero (extends Character)

Represents the hero. Adds to the class Character information about the lives and keys it has and the functions to alter these values.

#### Monster (extends Character)

Represents one monster. Adds to the class Character information about his speed and the direction of his movement. It also has methods to determine his next movement, since it is programed to travel in one direction until it hits a wall and then turn back.

#### Wizard (extends Character)

Represents one wizard. Adds to the class Character information about his speed and the direction of his movement. It also has a spell that is set to null if one is not being fired. For a spell to be fired, the hero has to share one of the coordinates with the wizard and there needs to be a clear path in that direction (no walls in between). Implements methods to determine his next movement, since it is programed to travel in one direction until it hits a wall and then turn back, and to determine when to fire a spell.

#### Spell (extends Character)

Represents one Spell. Adds to the class Character information about his speed and the direction of his movement. It also has methods to determine his next movement, since it is programed to travel in one direction until it hits a wall and then disappear.

#### Wall

Abstract class that represents a wall. Stores four integers that delimit a rectangle. This rectangle determines the space that the wall takes. It has methods to draw itself, test for collision given a position and a direction and check if there is a straight clear path between two positions.

#### Gate (extends  Wall)

Represents one gate. Adds to the class wall the number of key a hero needs to have to go through it. Implements a method to test if the gate can be opened and overrides the draw method to be represented with a different letter.

#### Item

Represents an item to be picked up by the hero. Stores its position and a boolean value that determines if it was picked up or not. It has two abstract methods: one to draw itself and one to determine the modification it gives to the hero.

#### Life (extends Item)

Represents an extra life the hero can get.

#### Key (extends Item)

Represents a Key the hero can get.


## Known Code Smells and Refactoring Suggestions

### Duplicate Code
In the function processKey from the class Arena, its possible to see that there is some repeated code inside the case statements, used to move the hero for the selected direction if possible.
#### Refactoring Suggestion
Consolidate Duplicate Conditional Fragments: the code should use the switch only to determine the direction of the key pressed and only after try to actually move the hero.

### Long Method
In the function testPath from the class Wall, there are some if statements that make the method seem very long and complicated.
#### Refactoring Suggestion
Decompose Conditional: by rearranging some of the conditions into groups, it would be possible to shorten the code and make it easier to understand.

### Temporary Field
In the class Wizard, the spell attribute only exists if one is being fired, therefore it only gets it's value under certain circumstances.
#### Refactoring Suggestion
Introduce Null Object: creating a class called NullSpell that extends Spell will allow this variable to be assigned a proper value when a spell doesn't actually exist.


## Testing Results

Code coverage:
![Coverage](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_212/blob/master/docs/Screenshots/test_coverage.PNG)

## Self-evaluation

* Bernardo Santos: Review of the code structure to CMV pattern to allow the usage of Swing and its implementation. Creation of the tests.
* Filipe Almeida: Creation of the game logic and initial Strategy structure to work on Lanterna. Construction of the levels.

Both students contributed to the code organization and good object oriented design practices during the project.