Author:Hassan Javed

package TextBasedAdventureGame;

import java.util.*;

public class GameLogic {

    //static objects to be used through out the game
    static Scanner sc = new Scanner(System.in);
    static boolean running = true;
    static Player player;
    static String Map = """
                                                       --------------------
                                                       | Village Entrance |
                                                       --------------------
                                                                 |
                                    ----------------------------------------------------------------
                                    |                                                              |
                     ---------------------------------                           -------------------------------------                
                     |                               |                           |                                   |
            -------------------            -------------------           -------------------                ----------------------
            |    Dark Jungle  |   <---->   |    Watchtower   |           |    GraveYard    |      <---->    |    Ancient Shrine  |
            -------------------            -------------------           -------------------                ----------------------
                    |                                |                           |                                   |
                    ----------------------------------                           -------------------------------------
                                    |                                                              |
                                    ----------------------------------------------------------------
                                                                  |
                                                         --------------------
                                                         |   Castle Ruins   |
                                                         --------------------
                                                                  |
                                                      ---------------------------
                                                      |    Final Boss Chamber   |
                                                      ---------------------------       
             """;

    public void startGame() {

        //starting choice
        System.out.println();
        System.out.println("I think I have been Lost....");
        System.out.println("Enter your choice Turn Left[1],Turn Right[2]");

        //looping after right choice
        while (running) {

            //choice for early path to game over
            int Choice = sc.nextInt();
            if (Choice == 1) {
                System.out.println("Oh ...I seem to have to seen people.");
                System.out.println("Oh there is a valley down there Thank God people are there Now I am safe..");
                player.setPlayerHealth(0);
                System.out.println("Game Over...");
                running = false;
                break;
            }

            //choice for proceeding the whole game
            else if (Choice == 2) {

                //welcoming
                System.out.println("What a strange place it is ... ");
                System.out.println("Some odd smell,Rotten .... there is Something,a keeper,Aged");
                System.out.println("Hey Fella .. I think You have been Lost ...");
                System.out.println("My name is Redo");
                System.out.println("Welcome");

                //intro
                System.out.println();
                System.out.println("Let's Start the Text-Based Adventure Game......");
                System.out.println();
                System.out.println("Loading.....");
                System.out.println();

                //introduction of the fallen cursed kingdom
                String intro = """
                        You arrive at the cursed gates of Eldoria, a fallen kingdom where shadows roam.
                        The land was corrupted by a dark artifact — the Cursed Amulet — hidden deep within.
                        Your mission is clear: explore the ruins, gather strength, and destroy the Amulet before the curse consumes you too.
                        Legends speak of a Sacred Sword, the only weapon strong enough to shatter it.
                        You take a deep breath, tighten your grip, and step through the broken gates…
                        
                        """;
                System.out.println(" *****     GAME INTRO     *****\n");
                System.out.println(intro + "\n");

                //creating different objects
                Room room = new Room("Village Entrance", "You stand at the entrance of a ruined village. Broken houses line the path, and the silence feels heavier than the air itself.");
                Monster monster = new Monster("Stray Goblin", "A small, cowardly goblin lurks among the ruins, scavenging scraps. It squeals in surprise when it sees you");
                monster.setMonsterHealth(15);
                System.out.println("Hey Bravo \nEnter Your Name :\nEnter Your Class(Assassin,Mage,Healer,Archer) : ");
                player = new Player(sc.next().toUpperCase().trim());

                //checking the player class
                while (true) {
                    String choice = sc.next().toUpperCase().trim();
                    if ((choice.equalsIgnoreCase("Assassin") || choice.equalsIgnoreCase("Mage") || choice.equalsIgnoreCase("Healer") || choice.equalsIgnoreCase("Archer"))) {
                        player.setPlayerType(choice);
                        break;
                    } else {
                        System.out.println("Warning !!! Please Enter Choice From Given Options...");
                    }
                }

                //printing player data and initializing the game
                System.out.println("Welcome " + player.getPlayerName() + ".\nAs you are a/an " + player.getPlayerType() + " and your Health is :" + player.getPlayerHealth() + "\n" + room.getRoomName() + ": " + room.getRoomDescription());
                System.out.println(player.getPlayerName() + " you have <" + player.getPlayerWeapon() + "> as Your Weapon");
                System.out.println("Warning !!! There is a " + monster.getMonsterName() + " Roaming at the Village Entrance" + "\n" + monster.getMonsterDescription() + "\nGoblin Health is " + monster.getMonsterHealth());
                System.out.println(player.getPlayerName() + " go get him......");

                //choice after seeing the first monster and standinf at the village entrance
                System.out.println("Choose an Option\n1[Fight]\n2[Run]");
                int cho = sc.nextInt();
                if (cho == 1) {

                    //calling method for starting fight with goblin
                    startingFight(player, monster);
                    System.out.println("\nAs You have Defeated the " + monster.getMonsterName() + " and secured the Village Entrance");
                    System.out.println("From here, paths branch into the cursed lands of Eldoria...");

                    //creating other classes item and monster objects
                    Room darkJungle = new Room("Dark Jungle", "Thick vines and towering trees block out most of the sunlight. The air smells of damp soil and decay. Eyes gleam in the shadows");
                    Monster shadowPanther = new Monster("Shadow Panther", "From the thickets of the overgrown jungle, a pair of glowing yellow eyes fixate on you. The Shadow Panther prowls silently, its sleek black fur blending with the darkness. Every step is measured, every breath controlled — you realize this beast is not just hunting, it is toying with you");
                    shadowPanther.setMonsterHealth(25);
                    shadowPanther.setMonsterAttack(8);
                    darkJungle.getMonsterList().add(shadowPanther);

                    Room abandonedWatchTower = new Room("Abandoned Watch Tower", "The crumbling watchtower leans against the horizon, its stones cracked and ivy-strangled. Rusted arrows litter the ground.");
                    Monster corruptedHawk = new Monster("Corrupted Hawk", "A shrill screech pierces the air as the Corrupted Hawk circles above the crumbling tower. Once a noble guardian’s companion, its feathers now drip with shadow. The creature swoops down with blinding speed, talons stretched wide, as if the very wind obeyed its hunger for blood");
                    corruptedHawk.setMonsterHealth(22);
                    corruptedHawk.setMonsterAttack(7);
                    abandonedWatchTower.getMonsterList().add(corruptedHawk);

                    Room hauntedGraveYard = new Room("Haunted GraveYard", "A chilling fog blankets the crooked tombstones. Whispering voices drift on the wind, and the ground feels alive beneath your feet");
                    Monster restlessSpirit = new Monster("Restless Spirit", "A pale mist rises between shattered gravestones, coalescing into the vague outline of a human form. Hollow eyes burn with hatred as the Restless Spirit moans in agony. Its wailing chills your bones — a soul chained to the grave, desperate to drag another down into eternal torment");
                    restlessSpirit.setMonsterHealth(20);
                    restlessSpirit.setMonsterAttack(10);
                    hauntedGraveYard.getMonsterList().add(restlessSpirit);

                    Room ancientRoom = new Room("Ancient Room", "An overgrown shrine lies in ruins, its statues cracked and moss-covered. A faint glow pulses from the broken altar");
                    Monster fallenPriest = new Monster("Fallen Priest", "At the altar of a ruined shrine stands a figure cloaked in decaying robes. The Fallen Priest clutches a cracked holy symbol, chanting words that warp into curses. With every incantation, dark tendrils seep from his hands, promising both pain and stolen vitality");
                    fallenPriest.setMonsterHealth(28);
                    fallenPriest.setMonsterAttack(6);
                    ancientRoom.getMonsterList().add(fallenPriest);

                    //storing room names as string to be easily used
                    String _1stRoom = "Dark Jungle";
                    String _2ndRoom = "WatchTower";
                    String _3rdRoom = "Haunted GraveYard";
                    String _4thRoom = "Ancient Room";

                    //giving the choices
                    System.out.println("Choose one of the Following");
                    System.out.println("1. Option 1(For Assassin and Archer) :\n" +
                            "2. Option 2 (For Mage and Healer):\n" +
                            "3. Show Inventory\n" +
                            "4. Show Map");

                    while (running) {
                        int CHOICE = sc.nextInt();

                        //choice for assassin and archer
                        if (CHOICE == 1) {
                            if (player.getPlayerType().equalsIgnoreCase("Assassin") || player.getPlayerType().equalsIgnoreCase("Archer")) {
                                //choosing the path
                                System.out.println("Choose \n 1[Dark Jungle]\n2[Watch Tower]");
                                while (running) {
                                    int CHO = sc.nextInt();
                                    if (CHO == 1) {
                                        //introducing and fighting in dark room
                                        System.out.println("You Entered ");
                                        System.out.println(darkJungle.getRoomName() + "\n" + darkJungle.getRoomDescription());
                                        System.out.println("Too much darkness,ahh there it is. Wait what? There is something Roaming here....");
                                        System.out.println(shadowPanther.getMonsterName() + "\n" + shadowPanther.getMonsterDescription() + "\n Health :" + shadowPanther.getMonsterHealth() + "\n" + " Attack: +" + shadowPanther.getMonsterAttack());
                                        System.out.println("Choose\nFight[1]\nExit[2]");
                                        int c = sc.nextInt();
                                        while (running) {
                                            if (c == 1) {
                                                System.out.println("Hey monster i am your Rival ...");
                                                jungleFight(player, shadowPanther);
                                                System.out.println("Now it's Time to move to next Arena.");
                                                //Movinf to next arena
                                                System.out.println("Press 1 to move next Arena,,");
                                                c = sc.nextInt();
                                                if (c == 1) {
                                                    System.out.println("So this is " + abandonedWatchTower.getRoomName());
                                                    System.out.println(abandonedWatchTower.getRoomDescription());
                                                    System.out.println("Here Comes another " + corruptedHawk.getMonsterName());
                                                    System.out.println(corruptedHawk.getMonsterDescription());
                                                    System.out.println("Health: " + corruptedHawk.getMonsterHealth() + "\n Attack: +" + corruptedHawk.getMonsterAttack());
                                                    System.out.println("Let's Fight");
                                                    towerFight(player, corruptedHawk);
                                                    running = false;
                                                    break;
                                                }
                                                break;
                                            }

                                            //option for player to quit the game
                                            else if (c == 2) {
                                                System.out.println("I am Scared i can't fight this Monster i gotta get the hell ou of here...");
                                                player.setPlayerHealth(0);
                                                running = false;
                                                break;
                                            }
                                            else {
                                                System.out.println("Please valid choice");
                                            }
                                        }
                                        break;
                                    }

                                    //choosing the watch tower room
                                    else if (CHO == 2) {
                                        System.out.println("You Entered ");
                                        System.out.println(abandonedWatchTower.getRoomName() + "\n" + abandonedWatchTower.getRoomDescription());
                                        System.out.println("Too much darkness,ahh there it is. Wait what? There is something Roaming here....");
                                        System.out.println(corruptedHawk.getMonsterName() + "\n" + corruptedHawk.getMonsterDescription() + "\n Health :" + corruptedHawk.getMonsterHealth() + "\n" + " Attack: +" + corruptedHawk.getMonsterAttack());
                                        System.out.println("Choose\nFight[1]\nExit[2]");
                                        while (running) {
                                            int C = sc.nextInt();

                                            //choosing watch tower
                                            if (C == 1) {
                                                System.out.println("Hey monster i am your Rival ...");
                                                jungleFight(player, corruptedHawk);
                                                System.out.println("Now it's Time to move to next Arena.");

                                                //entering in next arena
                                                System.out.println("Press 1 to move next Arena,,");
                                                C = sc.nextInt();
                                                if (C == 1) {
                                                    System.out.println("So this is " + darkJungle.getRoomName());
                                                    System.out.println(darkJungle.getRoomDescription());
                                                    System.out.println("Here Comes another " + shadowPanther.getMonsterName());
                                                    System.out.println(shadowPanther.getMonsterDescription());
                                                    System.out.println("Health: " + shadowPanther.getMonsterHealth() + "\n Attack: +" + shadowPanther.getMonsterAttack());
                                                    System.out.println("Let's Fight");
                                                    towerFight(player, shadowPanther);
                                                    running = false;
                                                    break;
                                                }
                                                break;
                                            }

                                            //option for quiting the game
                                            else if (C == 2) {
                                                System.out.println("Let's Get the Hell Out of here!!!!2");
                                                player.setPlayerHealth(0);
                                                running = false;
                                                break;
                                            } else {
                                                System.out.println("Please enter Valid Choice");
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                            //returning to the loop if not archer and assassin
                            else if (player.getPlayerType().equalsIgnoreCase("Mage") || player.getPlayerType().equalsIgnoreCase("Healer")) {
                                System.out.println("Please Choose According to your category");
                            }
                        }

                        //option for mage and healer
                        else if (CHOICE == 2) {
                            if (player.getPlayerType().equalsIgnoreCase("Mage") || player.getPlayerType().equalsIgnoreCase("Healer")) {

                                //choosing
                                System.out.println("Choose \n1[Haunted GraveYard] \n2[Ancient Room]");
                                while (running) {
                                    int CH = sc.nextInt();

                                    //entering in graveyard
                                    if (CH == 1) {
                                        System.out.println("You Entered in " + hauntedGraveYard.getRoomName() + "\n" + hauntedGraveYard.getRoomDescription());
                                        System.out.println("Let's see which thing i can i find here");
                                        System.out.println("ah wait what a " + restlessSpirit.getMonsterName() + "\n" + room.getRoomDescription());

                                        //choices for quiting and fighting
                                        System.out.println("Choose \nRun Back [1]\nFight[2]");
                                        while (running) {
                                            int x = sc.nextInt();
                                            //choice if quit
                                            if (x == 1) {
                                                System.out.println("Run , I have to run to back,I have to save myself ,i can't fight this huge monster");
                                                player.setPlayerHealth(0);
                                                running = false;
                                                break;
                                            }

                                            //fighting
                                            else if (x == 2) {
                                                System.out.println("Hey you pathetic " + restlessSpirit.getMonsterName() + " here i come");
                                                graveYardFight(player, restlessSpirit);
                                                System.out.println("Now it's Time to move to next Arena.");
                                                //entering in next arena
                                                System.out.println("Press 2 to move next Arena,,");
                                                if (x == 2) {
                                                    x = sc.nextInt();
                                                    System.out.println("So this is " + ancientRoom.getRoomName());
                                                    System.out.println(ancientRoom.getRoomDescription());
                                                    System.out.println("Here Comes another " + fallenPriest.getMonsterName());
                                                    System.out.println(fallenPriest.getMonsterDescription());
                                                    System.out.println("Health: " + fallenPriest.getMonsterHealth() + "\n Attack: +" + fallenPriest.getMonsterAttack());
                                                    System.out.println("Let's Fight");
                                                    ancientFight(player, fallenPriest);
                                                    running = false;
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Please Enter Valid Choice");
                                            }
                                        }
                                    }

                                    //choosing ancient room
                                    else if (CH == 2) {
                                        System.out.println("You Entered in " + ancientRoom.getRoomName() + " \n" + ancientRoom.getRoomDescription());
                                        System.out.println("Let's see which thing i can i find here");
                                        System.out.println("ah wait what a " + fallenPriest.getMonsterName() + "\n" + fallenPriest.getMonsterDescription());
                                        System.out.println("Choose \nRun Back [1]\nFight[2]");
                                        while (running) {
                                            int X = sc.nextInt();
                                            if (X == 1) {
                                                System.out.println("Run , I have to run to back,I have to save myself ,i can't fight this huge monster");
                                                player.setPlayerHealth(0);
                                                running = false;
                                                break;
                                            }
                                            //choice for fight
                                            else if (X == 2) {
                                                System.out.println("Hey you pathetic " + fallenPriest.getMonsterName() + " here i come");
                                                ancientFight(player, fallenPriest);
                                                System.out.println("Now it's Time to move to next Arena.");
                                                //entering in the next arena
                                                System.out.println("Press 1 to move next Arena,,");
                                                if (X == 2) {
                                                    X = sc.nextInt();
                                                    System.out.println("So this is " + hauntedGraveYard.getRoomName());
                                                    System.out.println(hauntedGraveYard.getRoomDescription());
                                                    System.out.println("Here Comes another " + restlessSpirit.getMonsterName());
                                                    System.out.println(restlessSpirit.getMonsterDescription());
                                                    System.out.println("Health: " + restlessSpirit.getMonsterHealth() + "\n Attack: +" + restlessSpirit.getMonsterAttack());
                                                    System.out.println("Let's Fight");
                                                    ancientFight(player, restlessSpirit);

                                                    //breaking the loop after completing the figths
                                                    running = false;
                                                    break;
                                                }
                                                break;
                                            } else {
                                                System.out.println("Please Enter Valid Choice");
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("Please enter Valid Choice");
                                    }
                                }
                                break;
                            }
                            //checking if not assassin and archer
                            else if (player.getPlayerType().equalsIgnoreCase("Assassin") || player.getPlayerType().equalsIgnoreCase("Archer")) {
                                System.out.println("Please enter the choice according to option");
                            }
                        }
                        //choice for inventory
                        else if (CHOICE == 3) {
                            System.out.println("Your Inventory");
                            int index = 1;
                            for (Item c : player.getInventory()) {
                                System.out.println(index + " :" + c.getItemName() + "\n" + c.getItemDescription() + "\nItem Effect (Attack) +" + c.getItemAffect());
                                index++;
                            }
                            System.out.println("\nChoose one of the Following");
                            System.out.println("1. Option 1(For Assassin and Mage) :\n" +
                                    "2. Option 2 (For Mage and Healer):\n" +
                                    "3. Show Inventory\n" +
                                    "4. Show Map");
                        }
                        //choice for map showing
                        else if (CHOICE == 4) {
                            System.out.println(Map);
                            System.out.println("\nChoose one of the Following");
                            System.out.println("1. Option 1(For Assassin and Mage) :\n" +
                                    "2. Option 2 (For Mage and Healer):\n" +
                                    "3. Show Inventory\n" +
                                    "4. Show Map");
                        }
                        //invalid choice
                        else {
                            System.out.println("\nX -- Please Enter Valid Choice....");
                            System.out.println("Choose one of the Following");
                            System.out.println("1. Option 1(For Assassin and Mage) :\n" +
                                    "2. Option 2 (For Mage and Healer):\n" +
                                    "3. Show Inventory\n" +
                                    "4. Show Map");
                        }
                    }
                }
                //choice after choosing left in initial game
                else if (cho == 2) {
                    System.out.println("Choose\n1[Turn Left]\n2[Turn Right]");
                    int ch = sc.nextInt();
                    if (ch == 1) {
                        System.out.println(monster.getMonsterName() + " attacked.You are Dead💀💀\nGame Over");
                        player.setPlayerHealth(0);
                        break;
                    } else if (ch == 2) {
                        System.out.println("Hunn Hunn Hunn....\nI escaped.I am saved, " + monster.getMonsterName() + " is not following me\nThank God..uff!!");
                        player.setPlayerHealth(0);
                        break;
                    }
                }
            } else {
                System.out.println("Please Enter Valid choice ");
            }
        }


        boolean True = true;

        //creating objects for castle ruin and final chamber and their related monsters
        Room castleRuins = new Room("Abandoned Castle Ruins", "The shattered halls of an ancient castle. Dust covers the ground, broken banners hang in tatters, and faint whispers echo. Rusty weapons and bones are scattered everywhere.");
        Monster cursedKnight = new Monster("Cursed Knight", "Once a noble warrior, now twisted by dark magic. His armor is cracked, and his blade glows with a faint red curse.");
        cursedKnight.setMonsterHealth(50);
        cursedKnight.setMonsterAttack(12);
        castleRuins.getMonsterList().add(cursedKnight);

        Room finalChamber = new Room("Final Chamber", "The heart of Eldoria, a massive hall of broken pillars and shattered stained glass. " + "The cursed amulet hovers above a cracked throne, radiating dark energy.");
        Monster wraithGeneral = new Monster("Wraith General", "The spectral commander of the fallen army. Its hollow eyes burn with rage ,and every swing of its spectral blade chills your very soul.");
        wraithGeneral.setMonsterHealth(55);
        wraithGeneral.setMonsterAttack(11);
        finalChamber.getMonsterList().add(wraithGeneral);
        Monster amuletGuardian = new Monster("Amulet Guardian", "The final protector of the cursed artifact. A massive, shifting figure of shadow and fire, " + "forged from the souls of fallen heroes. Its voice roars like a thousand whispers, " + "and with every strike, it bends reality itself.");
        amuletGuardian.setMonsterHealth(90);
        amuletGuardian.setMonsterAttack(18);
        finalChamber.getMonsterList().add(amuletGuardian);

        //checking if health is greater than o
        if (player.getPlayerHealth() > 0) {
            System.out.println("Before you proceed forward,You should check your Health ");
            System.out.println("Your Health is: " + player.getPlayerHealth());

            //if health is less than 51 using healing potion first
            if (player.getPlayerHealth() < 51) {
                System.out.println("Warning Warning Warning....Your Health is Low\nPlease should Heal Yourself First💊💊🩹🩹");
                System.out.println("\nPress 3 to continue Healing");
                int h = sc.nextInt();
                if (h == 3) {
                    player.setPlayerHealth(player.getPlayerHealth() + player.getInventory().get(1).getItemAffect());
                    System.out.println("Your current Health after using Healing Potion is " + player.getPlayerHealth());
                }
                System.out.println("Now you can proceed...");

                //choices
                System.out.println("\nChoose\n1.Show Map\n2.Show Inventory\n3.Next Arena");
                while (True) {
                    int ch = sc.nextInt();

                    //choice for map
                    if (ch == 1) {
                        System.out.println(Map);
                        System.out.println("\nChoose\n1.Show Map\n2.Show Inventory\n3Next Arena");
                    }
                    //choice for inventory
                    else if (ch == 2) {
                        System.out.println("Your Inventory");
                        int ind = 1;
                        for (Item t : player.getInventory()) {
                            System.out.println(ind + " :" + t.getItemName() + "\n" + t.getItemDescription() + "\nItem Effect (Attack) +" + t.getItemAffect());
                            ind++;
                        }
                        System.out.println("\nChoose\n1.Show Map\n2.Show Inventory\n3.Next Arena");
                    }
                    //choice for moving forward
                    else if (ch == 3) {
                        System.out.println("Let's move forward");
                        System.out.println("So this is " + castleRuins.getRoomName() + "\n" + castleRuins.getRoomDescription());
                        System.out.println("It's looking much more cursed than previously areas where i have been \nbut i have to clear this to remove curse from Eldoria ");
                        System.out.println("Now come on what a <" + cursedKnight.getMonsterName() + ">\n" + cursedKnight.getMonsterDescription() + "\nHealth: " + cursedKnight.getMonsterHealth() + "\nAttack: " + cursedKnight.getMonsterAttack());
                        System.out.println("choose an option\n1.Quit\n2.Fight");
                        while (True) {
                            ch = sc.nextInt();
                            if (ch == 1) {
                                System.out.println("I can't take it anymore i gotta get out of here");
                                System.out.println("I am leaving this place");
                                True = false;
                            }
                            //castle ruin fight
                            else if (ch == 2) {
                                System.out.println("Well let's take on " + cursedKnight.getMonsterName());
                                ruinFight(player, cursedKnight);
                                True = false;
                            } else {
                                System.out.println("Please press the key as Mentioned");
                            }
                        }
                    }
                }
            }
            //if health is enough continue here
            else {
                System.out.println("You are Built Strong");
                while (True) {
                    int chx = sc.nextInt();

                    if (chx == 1) {
                        System.out.println(Map);
                        System.out.println("\nChoose\n1.Show Map\n2.Show Inventory\n3.Next Arena");
                    }

                    else if (chx == 2) {
                        System.out.println("Your Inventory");
                        int ind = 1;
                        for (Item t : player.getInventory()) {
                            System.out.println(ind + " :" + t.getItemName() + "\n" + t.getItemDescription() + "\nItem Effect (Attack) +" + t.getItemAffect());
                            ind++;
                        }
                        System.out.println("\nChoose\n1.Show Map\n2.Show Inventory\n3.Next Arena");
                    }

                    else if (chx == 3) {
                        System.out.println("Let's move forward");
                        System.out.println("So this is " + castleRuins.getRoomName() + "\n" + castleRuins.getRoomDescription());
                        System.out.println("It's looking much more cursed than previously areas where i have been \nbut i have to clear this to remove curse from Eldoria ");
                        System.out.println("Now come on what a <" + cursedKnight.getMonsterName() + ">\n" + cursedKnight.getMonsterDescription() + "\nHealth: " + cursedKnight.getMonsterHealth() + "\nAttack: " + cursedKnight.getMonsterAttack());
                        System.out.println("choose an option\n1.Quit\n2.Fight");
                        while (True) {
                            chx = sc.nextInt();

                            if (chx == 1) {
                                System.out.println("I can't take it anymore i gotta get out of here");
                                System.out.println("I am leaving this place");
                                player.setPlayerHealth(0);
                                True = false;
                            } else if (chx == 2) {
                                System.out.println("Well let's take on " + cursedKnight.getMonsterName());
                                ruinFight(player, cursedKnight);
                                True = false;
                            } else {
                                System.out.println("Please press the key as Mentioned");
                            }

                        }
                    }
                }
            }
        }
        //if health is zero game over
        else {
            System.out.println("Game Over...");
            True = false;
        }

        if(player.getPlayerHealth()>0){
            boolean False=true;{
                //checking player health
                if(player.getPlayerHealth()<51){

                    //using healing potion before entering final chamber
                    System.out.println("Your Health is low..");
                    System.out.println("Press 6 to Use Healing Potion");
                    while (False){
                        int p=sc.nextInt();
                        if(p==6){
                            System.out.println("Your Health Before Using Healing Potion : " +player.getPlayerHealth());
                            player.setPlayerHealth(player.getPlayerHealth()+player.getInventory().get(7).getItemAffect());
                            System.out.println("Your health After Using Healing Potion : " +player.getPlayerHealth());
                            System.out.println("Before proceeding we have got a Bonus Reward for you <Healing Potion>");
                            System.out.println("i have come to " +finalChamber.getRoomName() +"\nDescription: " + finalChamber.getRoomDescription());
                            System.out.println("Now Come on another Monster,I think Eldoria is much more cursed than I could Have imagined");
                            System.out.println(wraithGeneral.getMonsterName());
                            System.out.println("Description: " + wraithGeneral.getMonsterDescription() + "\nit is not looking Final boss it must be protector of him");

                            System.out.println("Choose\n1.Open Inventory\n2.Fight");
                            while (False) {
                                int z = sc.nextInt();
                                if (z == 1) {
                                    System.out.println("Your Inventory");
                                    int ind = 1;
                                    for (Item t : player.getInventory()) {
                                        System.out.println(ind + " :" + t.getItemName() + "\n" + t.getItemDescription() + "\nItem Effect (Attack) +" + t.getItemAffect());
                                        ind++;
                                    }
                                    System.out.println("Choose\n1.Open Inventory\n2.Fight");
                                }
                                //final chamber first fight
                                else if (z == 2) {
                                    chamber1stFight(player, wraithGeneral);
                                    System.out.println("Here Comes the Final BOSS " + amuletGuardian.getMonsterName());
                                    System.out.println("Description: " + amuletGuardian.getMonsterDescription());
                                    //choices
                                    System.out.println("Choose\n1.Open Inventory\n2.Fight");
                                    while(False) {
                                        int n = sc.nextInt();
                                        if (n == 1) {
                                            System.out.println("Your Inventory");
                                            int indxc = 1;
                                            for (Item t : player.getInventory()) {
                                                System.out.println(indxc + " :" + t.getItemName() + "\n" + t.getItemDescription() + "\nItem Effect (Attack) +" + t.getItemAffect());
                                                indxc++;
                                            }
                                            System.out.println("Choose\n1.Open Inventory\n2.Fight");
                                        }
                                        //fight with Boss
                                        else if (n == 2) {
                                            chamber2ndFight(player, amuletGuardian);
                                            System.out.println("\nNow you have Defeated the Amulet Guardain");
                                            System.out.println("You have free ELDORIA from the curse,for that use the magical stone you receive after Killing King");
                                            System.out.println("press 5 to use Magical stone");
                                            while (False) {
                                                n = sc.nextInt();
                                                //using magical stone to end game
                                                if (n == 5) {
                                                    System.out.println("You used the Magical stone ");
                                                    System.out.println("Now Eldoria is cursed free ");
                                                    System.out.println("**** GAME OVER ****");
                                                    False = false;
                                                    break;
                                                } else {
                                                    System.out.println("Please press 5");
                                                }
                                            }
                                        }
                                    }
                                }
                                else {
                                    System.out.println("Invalid choice");
                                }
                            }
                        }
                        else {
                            System.out.println("Please press 6 to use Healing Potion");
                        }
                    }
                }
                //health is enough
                else{
                    System.out.println("i have come to " +finalChamber.getRoomName() +"\nDescription: " + finalChamber.getRoomDescription());
                    System.out.println("Before proceeding we have got a Bonus Reward for you <Healing Potion>");
                    System.out.println("Now Come on another Monster,I think Eldoris is much more cursed than I could Have imagined");
                    System.out.println(wraithGeneral.getMonsterName());
                    System.out.println("Description: " + wraithGeneral.getMonsterDescription() + "\nit is not looking Final boss it must be protector of him");
                    System.out.println("Choose\n1.Open Inventory\n2.Fight");
                    while (False) {
                        int z = sc.nextInt();
                        if (z == 1) {
                            System.out.println("Your Inventory");
                            int ind = 1;
                            for (Item t : player.getInventory()) {
                                System.out.println(ind + " :" + t.getItemName() + "\n" + t.getItemDescription() + "\nItem Effect (Attack) +" + t.getItemAffect());
                                ind++;
                            }
                            System.out.println("Choose\n1.Open Inventory\n2.Fight");
                        }

                        //final chamber first fight
                        else if (z == 2) {
                            chamber1stFight(player, wraithGeneral);
                                System.out.println("Here Comes the Final BOSS " + amuletGuardian.getMonsterName());
                                System.out.println("Description: " + amuletGuardian.getMonsterDescription());
                                System.out.println("Choose\n1.Open Inventory\n2.Fight");
                                while(False){
                                int n=sc.nextInt();
                                if (n == 1) {
                                    System.out.println("Your Inventory");
                                    int indxc = 1;
                                    for (Item t : player.getInventory()) {
                                        System.out.println(indxc + " :" + t.getItemName() + "\n" + t.getItemDescription() + "\nItem Effect (Attack) +" + t.getItemAffect());
                                        indxc++;
                                    }
                                    System.out.println("Choose\n1.Open Inventory\n2.Fight");
                                }
                                //final chmaber 2n fight
                                else if (n == 2) {
                                    chamber2ndFight(player,amuletGuardian);
                                    System.out.println("\nNow you have Defeated the Amulet Guardain");
                                    System.out.println("You have free ELDORIA from the curse,for that use the magical stone you receive after Killing King");
                                    System.out.println("press 5 to use Magical stone");
                                    while(False){
                                        n=sc.nextInt();
                                        if(n==5){
                                            System.out.println("You used the Magical stone ");
                                            System.out.println("Now Eldoria is cursed free ");
                                            System.out.println("**** GAME OVER ****");
                                            False=false;
                                            break;
                                        }
                                        else {
                                            System.out.println("Please press 5");
                                        }
                                    }
                                    break;
                                }
                                else {
                                    System.out.println("Invalid choice");
                                }
                            }
                        }
                        else{
                            System.out.println("Invalid choice");
                        }
                    }
                }
            }
        }

        //if health 0 game over
        else {
            System.out.println("...Game Over...");
        }
        sc.close();
    }

    //Goblin Fight
    public static void startingFight(Player player, Monster monster) {

        Random ran = new Random();

        //auto looping and choices
        while (player.getPlayerHealth() > 0 && monster.getMonsterHealth() > 0) {

            if (ran.nextInt(2) == 0) {

                monster.setMonsterHealth(monster.getMonsterHealth() - player.getInventory().get(0).getItemAffect());
                System.out.println("You struck with " + player.getPlayerWeapon() + " and " + monster.getMonsterName() + " Health is :" + monster.getMonsterHealth() + "\n");

                if (monster.getMonsterHealth() <= 0) {
                    System.out.println(monster.getMonsterName() + " is Dead ☠☠\n");
                    break;
                }

                player.setPlayerHealth(player.getPlayerHealth() - monster.getMonsterAttack());
                System.out.println(monster.getMonsterName() + " hit you and Your Health is: " + player.getPlayerHealth() + "\n");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("You are Dead.You Lost!!!💀💀\n");
                    break;
                }
            }
            else {
                player.setPlayerHealth(player.getPlayerHealth() - monster.getMonsterAttack());
                System.out.println(monster.getMonsterName() + " hit you and Your Health is: " + player.getPlayerHealth() + "\n");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("You are Dead.You Lost!!!💀💀\n");
                    break;
                }

                monster.setMonsterHealth(monster.getMonsterHealth() - player.getInventory().get(0).getItemAffect());
                System.out.println("You struck with " + player.getPlayerWeapon() + " and " + monster.getMonsterName() + " Health is:" + monster.getMonsterHealth() + "\n");

                if (monster.getMonsterHealth() <= 0) {
                    System.out.println(monster.getMonsterName() + " is Dead ☠️☠️\n");
                    break;
                }
            }

            //checking the health for each round
            System.out.println("Your Health: " + player.getPlayerHealth() + " | " + " Monster Health: " + monster.getMonsterHealth() + "\n");
        }

        //Final result of the first battle and adding the rewards
        if (player.getPlayerHealth() > 0 && monster.getMonsterHealth() <= 0) {
            System.out.println("You Won. You have defeated the " + monster.getMonsterName());
            System.out.println("Congratulations🎊🎊 " + monster.getMonsterName() + " dropped items you can check them in your inventory \n");

            Item potion = new Item("Healing Potion", "A glowing potion that restores 30 HP", 30);
            Item spiritBaneOil = new Item("SpiritBane Oil", "A flask of shimmering silver oil said to be blessed by ancient priests. When coated on a blade, it allows the weapon to cut through creatures of the spirit realm. Without it, your daggers would pass harmlessly through the ghostly form of the Restless Spirit.", 10);
            Item silverArrowhead = new Item("Silver Arrowhead", "An ancient arrowhead forged from pure silver. Though dull from age, its enchantment still lingers, allowing arrows to pierce creatures of shadow and bone. Legends say silver arrows are the bane of unnatural beings.", 8);
            Item runedCharm = new Item("Runed Charm", "A small charm etched with faintly glowing runes. As you hold it, the symbols pulse with arcane energy. It whispers fragments of forgotten spells, sharpening your magical focus against spectral foes.", 12);
            Item amuletOfWarding = new Item("Amulet of Warding", "A tarnished bronze amulet bearing the symbol of an ancient sun-god. When worn, it glows faintly, creating a protective aura that shields the wearer from spiritual corruption and life-drain attacks", 7);

            if (player.getPlayerType().equalsIgnoreCase("Assassin")) {
                player.getInventory().add(potion);
                player.getInventory().add(spiritBaneOil);
            } else if (player.getPlayerType().equalsIgnoreCase("Archer")) {
                player.getInventory().add(potion);
                player.getInventory().add(silverArrowhead);
            } else if (player.getPlayerType().equalsIgnoreCase("Mage")) {
                player.getInventory().add(potion);
                player.getInventory().add(runedCharm);
            } else {
                player.getInventory().add(potion);
                player.getInventory().add(amuletOfWarding);
            }

        }
    }

    //Jungle fight
    public static void jungleFight(Player player, Monster shadowPanther) {
        Random ran = new Random();

        //auto looping and choices
        while (player.getPlayerHealth() > 0 && shadowPanther.getMonsterHealth() > 0) {

            if (ran.nextInt(2) == 0) {
                shadowPanther.setMonsterHealth(shadowPanther.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                System.out.println("You attacked " + shadowPanther.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                if (shadowPanther.getMonsterHealth() <= 0) {
                    System.out.println(shadowPanther.getMonsterName() + " has been died💀💀");
                    running = false;
                }

                player.setPlayerHealth(player.getPlayerHealth() - shadowPanther.getMonsterAttack());
                System.out.println(shadowPanther.getMonsterName() + " Attacked you...");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("Player you are Dead☠️☠️");
                    running = false;
                }
            } else {
                player.setPlayerHealth(player.getPlayerHealth() - shadowPanther.getMonsterAttack());
                System.out.println(shadowPanther.getMonsterName() + " Attacked you...");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("Player you are Dead☠️☠️");
                    running = false;
                }
                shadowPanther.setMonsterHealth(shadowPanther.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                System.out.println("You attacked " + shadowPanther.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                if (shadowPanther.getMonsterHealth() <= 0) {
                    System.out.println(shadowPanther.getMonsterName() + " has been died💀💀");
                    running = false;
                }
            }

            //checking the Health after reach round
            System.out.println("Your Health: " + player.getPlayerHealth() + " | " + " Monster Health: " + shadowPanther.getMonsterHealth() + "\n");
        }

        //Final Result of the battle
        if (player.getPlayerHealth() > 0 && shadowPanther.getMonsterHealth() <= 0) {
            System.out.println("You Won. You have defeated the " + shadowPanther.getMonsterName());
            Item twinDagger = new Item("Twin Dagger", "A pair of sharpened blades, perfectly balanced for rapid strikes. Each cut feels quicker than the eye can follow", 15);
            System.out.println(shadowPanther.getMonsterName() + " dropped " + twinDagger.getItemName() + "\nYou can check it out in your inventory");
            player.getInventory().add(twinDagger);
        }
    }

    //Tower Fight
    public static void towerFight(Player player, Monster corruptedHawk) {
        Random ran = new Random();

        //auto choices and result  and looping
        while (player.getPlayerHealth() > 0 && corruptedHawk.getMonsterHealth() > 0) {

            if (ran.nextInt(2) == 0) {
                corruptedHawk.setMonsterHealth(corruptedHawk.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                System.out.println("You attacked " + corruptedHawk.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                if (corruptedHawk.getMonsterHealth() <= 0) {
                    System.out.println(corruptedHawk.getMonsterName() + " has been died💀💀");
                    running = false;
                }

                player.setPlayerHealth(player.getPlayerHealth() - corruptedHawk.getMonsterAttack());
                System.out.println(corruptedHawk.getMonsterName() + " Attacked you...");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("Player you are Dead☠️☠️");
                    running = false;
                }
            } else {
                player.setPlayerHealth(player.getPlayerHealth() - corruptedHawk.getMonsterAttack());
                System.out.println(corruptedHawk.getMonsterName() + " Attacked you...");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("Player you are Dead☠️☠️");
                    running = false;
                }
                corruptedHawk.setMonsterHealth(corruptedHawk.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                System.out.println("You attacked " + corruptedHawk.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                if (corruptedHawk.getMonsterHealth() <= 0) {
                    System.out.println(corruptedHawk.getMonsterName() + " has been died💀💀");
                    running = false;
                }
            }
            //checking the health after each round
            System.out.println("Your Health: " + player.getPlayerHealth() + " | " + " Monster Health: " + corruptedHawk.getMonsterHealth() + "\n");
        }

        //results
        if (player.getPlayerHealth() > 0 && corruptedHawk.getMonsterHealth() <= 0) {
            System.out.println("You Won. You have defeated the " + corruptedHawk.getMonsterName());
            Item reinforcedBow = new Item("Reinforced Bow", "A sturdy bow strung with fresh sinew. Its arrows pierce deeper and fly straighter than your old weapon", 14);
            System.out.println(corruptedHawk.getMonsterName() + " dropped " + reinforcedBow.getItemName() + "\nYou can check it out in your inventory");
            player.getInventory().add(reinforcedBow);
        }
    }

    //graveyard Fight
    public static void graveYardFight(Player player, Monster restlessSpirit) {
        Random random = new Random();

        //looping and auto fights and auto choices
        while (player.getPlayerHealth() > 0 && restlessSpirit.getMonsterHealth() > 0) {

            if (random.nextInt(2) == 0) {
                restlessSpirit.setMonsterHealth(restlessSpirit.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                System.out.println("You attacked " + restlessSpirit.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                if (restlessSpirit.getMonsterHealth() <= 0) {
                    System.out.println(restlessSpirit.getMonsterName() + " has been died💀💀");
                    running = false;
                }

                player.setPlayerHealth(player.getPlayerHealth() - restlessSpirit.getMonsterAttack());
                System.out.println(restlessSpirit.getMonsterName() + " Attacked you...");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("Player you are Dead☠️☠️");
                    running = false;
                }
            } else {
                player.setPlayerHealth(player.getPlayerHealth() - restlessSpirit.getMonsterAttack());
                System.out.println(restlessSpirit.getMonsterName() + " Attacked you...");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("Player you are Dead☠️☠️");
                    running = false;
                }
                restlessSpirit.setMonsterHealth(restlessSpirit.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                System.out.println("You attacked " + restlessSpirit.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                if (restlessSpirit.getMonsterHealth() <= 0) {
                    System.out.println(restlessSpirit.getMonsterName() + " has been died💀💀");
                    running = false;
                }
            }
            System.out.println("Your Health: " + player.getPlayerHealth() + " | " + " Monster Health: " + restlessSpirit.getMonsterHealth() + "\n");
        }

        //results
        if (player.getPlayerHealth() > 0 && restlessSpirit.getMonsterHealth() <= 0) {
            System.out.println("You Won. You have defeated the " + restlessSpirit.getMonsterName());
            Item enchantedStaff = new Item("Enchanted Staff", "A staff pulsating with arcane energy. Sparks leap from its runes, channeling your magic into devastating bursts", 18);
            System.out.println(restlessSpirit.getMonsterName() + " dropped " + enchantedStaff.getItemName() + "\nYou can check it out in your inventory");
            player.getInventory().add(enchantedStaff);
        }
    }

    //ancient ruin fight
    public static void ancientFight(Player player, Monster fallenPriest) {
        Random random = new Random();

        //looping until one dies
        while (player.getPlayerHealth() > 0 && fallenPriest.getMonsterHealth() > 0) {

            //auto fight and auto choices
            if (random.nextInt(2) == 0) {
                fallenPriest.setMonsterHealth(fallenPriest.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                System.out.println("You attacked " + fallenPriest.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                if (fallenPriest.getMonsterHealth() <= 0) {
                    System.out.println(fallenPriest.getMonsterName() + " has been died💀💀");
                    running = false;
                }

                player.setPlayerHealth(player.getPlayerHealth() - fallenPriest.getMonsterAttack());
                System.out.println(fallenPriest.getMonsterName() + " Attacked you...");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("Player you are Dead☠️☠️");
                    running = false;
                }
            } else {
                player.setPlayerHealth(player.getPlayerHealth() - fallenPriest.getMonsterAttack());
                System.out.println(fallenPriest.getMonsterName() + " Attacked you...");

                if (player.getPlayerHealth() <= 0) {
                    System.out.println("Player you are Dead☠️☠️");
                    running = false;
                }
                fallenPriest.setMonsterHealth(fallenPriest.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                System.out.println("You attacked " + fallenPriest.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                if (fallenPriest.getMonsterHealth() <= 0) {
                    System.out.println(fallenPriest.getMonsterName() + " has been died💀💀");
                    running = false;
                }
            }
            System.out.println("Your Health: " + player.getPlayerHealth() + " | " + " Monster Health: " + fallenPriest.getMonsterHealth() + "\n");
        }

        //Final Result
        if (player.getPlayerHealth() > 0 && fallenPriest.getMonsterHealth() <= 0) {
            System.out.println("You Won. You have defeated the " + fallenPriest.getMonsterName());
            Item blessedRod = new Item("Blessed Rod", "A holy rod infused with divine light. It carries both the power to smite foes and restore vitality", 10);
            System.out.println(fallenPriest.getMonsterName() + " dropped " + blessedRod.getItemName() + "\nYou can check it out in your inventory");
            player.getInventory().add(blessedRod);
        }
    }

    //Castle Ruin Battle
    public static void ruinFight(Player player, Monster cursedKnight) {
        Random ran = new Random();
        Item potion = new Item("Healing Potion", "A glowing potion that restores 30 HP", 30);

        //Swaping the healing potion to the last index so that it can be easily removed
        int potionIndex = 1;
        int lastIndex = player.getInventory().size() - 1;
        Collections.swap(player.getInventory(), potionIndex, lastIndex);

        //looping until one dies
        while (player.getPlayerHealth() > 0 && cursedKnight.getMonsterHealth() > 0) {
            System.out.println("Your Health: " + player.getPlayerHealth() + " | " + cursedKnight.getMonsterName() + " Health: " + cursedKnight.getMonsterHealth());
            System.out.println("1.Attack\n2.Dodge\n3.Use Healing Potion");
            int mon;
            boolean act = true;

            //looping for choices
            while (act) {
                int a = sc.nextInt();
                if (a == 1) {
                    System.out.println("Choose");
                    System.out.println("Attack with Option Alpha, Press 1");
                    System.out.println("Attack with option Beta, Press 2");
                    System.out.println("Attack With Option Gamma, Press 3");
                    int b = sc.nextInt();

                    //one of three choices
                    if (b == 1) {
                        cursedKnight.setMonsterHealth(cursedKnight.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                        System.out.println("You attacked " + cursedKnight.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                        //checking whether the monster is alive or not
                        if (cursedKnight.getMonsterHealth() < 0) {
                            System.out.println("You defeated " + cursedKnight.getMonsterName() + " Congratulations 🫡🫡");
                            System.out.println(cursedKnight.getMonsterName() + " is Dead 👻");
                            act = false;
                            break;
                        }

                        //monster random attack
                        mon = ran.nextInt(3);
                        if (mon == 0) {
                            System.out.println("You dodge the monster attack ");
                        } else if (mon == 1) {
                            player.setPlayerHealth(player.getPlayerHealth() - cursedKnight.getMonsterAttack());
                            System.out.println("You have been attacked by " + cursedKnight.getMonsterName());

                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        } else if (mon == 2) {
                            player.setPlayerHealth(player.getPlayerHealth() - cursedKnight.getMonsterAttack() - 7);
                            System.out.println("You were Brutally attacked by " + cursedKnight.getMonsterName());
                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        }
                    }

                    //one of the three attacks
                    else if (b == 2) {
                        cursedKnight.setMonsterHealth(cursedKnight.getMonsterHealth() - player.getInventory().get(3).getItemAffect());
                        System.out.println("You attacked " + cursedKnight.getMonsterName() + " with " + player.getInventory().get(3).getItemName());

                        if (cursedKnight.getMonsterHealth() < 0) {
                            System.out.println("You defeated " + cursedKnight.getMonsterName() + " Congratulations 🫡🫡");
                            System.out.println(cursedKnight.getMonsterName() + " is Dead 👻");
                            act = false;
                            break;
                        }
                        mon = ran.nextInt(3);
                        if (mon == 0) {
                            System.out.println("And you dodge the monster attack successfully");
                        } else if (mon == 1) {
                            player.setPlayerHealth(player.getPlayerHealth() - cursedKnight.getMonsterAttack());
                            System.out.println("You have been attacked by " + cursedKnight.getMonsterName());

                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        } else if (mon == 2) {
                            player.setPlayerHealth(player.getPlayerHealth() - cursedKnight.getMonsterAttack() - 7);
                            System.out.println("You were Brutally attacked by " + cursedKnight.getMonsterName());
                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        }
                    }

                    else if (b == 3) {
                        cursedKnight.setMonsterHealth(cursedKnight.getMonsterHealth() - player.getInventory().get(1).getItemAffect());
                        System.out.println("You attacked " + cursedKnight.getMonsterName() + " with " + player.getInventory().get(1).getItemName());

                        if (cursedKnight.getMonsterHealth() < 0) {
                            System.out.println("You defeated " + cursedKnight.getMonsterName() + " Congratulations 🫡🫡");
                            System.out.println(cursedKnight.getMonsterName() + " is Dead 👻");
                            act = false;
                            break;
                        }
                        mon = ran.nextInt(3);
                        if (mon == 0) {
                            System.out.println("you dodge the monster attack");
                        } else if (mon == 1) {
                            player.setPlayerHealth(player.getPlayerHealth() - cursedKnight.getMonsterAttack());
                            System.out.println("You have been attacked by " + cursedKnight.getMonsterName());

                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        } else if (mon == 2) {
                            player.setPlayerHealth(player.getPlayerHealth() - cursedKnight.getMonsterAttack() - 7);
                            System.out.println("You were Brutally attacked by " + cursedKnight.getMonsterName());
                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        }
                    } else {
                        System.out.println("choose option from the Above...");
                    }
                }

                //Dodging the attack
                else if (a == 2) {
                    System.out.println("You are Trying to dodge the Attack , ahh\n");
                    mon = ran.nextInt(2);
                    if (mon == 0) {
                        System.out.println("And you dodge the monster attack successfully");
                    } else if (mon == 1) {
                        player.setPlayerHealth(player.getPlayerHealth() - cursedKnight.getMonsterAttack());
                        System.out.println("You have been attacked by " + cursedKnight.getMonsterName());

                        if (player.getPlayerHealth() <= 0) {
                            System.out.println("You are Dead ");
                            System.out.println("Cursed Knight killed You💀");
                            act = false;
                            break;
                        }
                    }
                }

                //using the healing potion
                else if (a == 3) {
                    //finding the healing potion if available
                    int potionIndex1 = -1;
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        if (player.getInventory().get(i).getItemName().equalsIgnoreCase("Healing Potion")) {
                            potionIndex1 = i;
                            break;
                        }
                    }
                    if (potionIndex1 != -1) {
                        Item potionItem = player.getInventory().get(potionIndex1);
                        player.setPlayerHealth(player.getPlayerHealth() + potionItem.getItemAffect());
                        System.out.println("You used " + potionItem.getItemName() + " 🧪");
                        System.out.println("Your health is now: " + player.getPlayerHealth());
                        player.getInventory().remove(potionIndex1);
                    } else {
                        System.out.println("Not Available...");
                    }
                } else {
                    System.out.println("Please enter Valid Choice");
                }

                //checking each monster health after every choice
                System.out.println("Your Health: " + player.getPlayerHealth() + " | " + cursedKnight.getMonsterName() + " Health: " + cursedKnight.getMonsterHealth());
                System.out.println("1.Attack\n2.Dodge\n3.Use Healing Potion");
            }
        }

        //returning the result of the the battle with rewards
        if (player.getPlayerHealth() > 0 && cursedKnight.getMonsterHealth() < 0) {
            Item healingHurbCastle = new Item("Healing Hurb", "Restores 25 HP — found hidden under the fallen throne.", 40);
            Item knightsShield = new Item("Knight’s Shield", "A heavy shield blessed against corruption. Reduces some incoming damage (useful against physical attackers).", 12);
            Item sacredFireOrb = new Item("Sacred Fire Orb", "A glowing orb containing holy fire. Strong against undead foes but limited use.", 30);
            System.out.println(cursedKnight.getMonsterName() + " Dropped Items You can check them in your Inventory");
            player.getInventory().add(knightsShield);
            player.getInventory().add(sacredFireOrb);
            player.getInventory().add(healingHurbCastle);
            System.out.println("Bonus: " + potion.getItemName());
            player.getInventory().add(potion);
        }
    }

    //First Battle of the Final Chamber
    public static void chamber1stFight(Player player, Monster wraithGeneral) {
        Random random = new Random();

        //loop until one of them dies
        while (player.getPlayerHealth() > 0 && wraithGeneral.getMonsterHealth() > 0) {
            System.out.println("Your Health: " + player.getPlayerHealth() + " | " + wraithGeneral.getMonsterName() + " Health: " + wraithGeneral.getMonsterHealth());
            System.out.println("1.Attack\n2.Dodge\n3.Use Healing Potion");
            int mon;
            boolean act = true;
            while (act) {
                int a = sc.nextInt();
                if (a == 1) {
                    System.out.println("Choose");
                    System.out.println("Attack with Option Alpha, Press 1");
                    System.out.println("Attack with option Beta, Press 2");
                    System.out.println("Attack With Option Gamma, Press 3");
                    int b = sc.nextInt();

                    //one of the three attacks of the player
                    if (b == 1) {
                        wraithGeneral.setMonsterHealth(wraithGeneral.getMonsterHealth() - player.getInventory().get(2).getItemAffect());
                        System.out.println("You attacked " + wraithGeneral.getMonsterName() + " with " + player.getInventory().get(2).getItemName());

                        //checking whether the monster is alive or not
                        if (wraithGeneral.getMonsterHealth() < 0) {
                            System.out.println("You defeated " + wraithGeneral.getMonsterName() + " Congratulations 🫡🫡");
                            System.out.println(wraithGeneral.getMonsterName() + " is Dead 👻");
                            act = false;
                            break;
                        }

                        //monster random attack on player choice
                        mon = random.nextInt(3);
                        if (mon == 0) {
                            System.out.println("You dodge the monster attack ");
                        } else if (mon == 1) {
                            player.setPlayerHealth(player.getPlayerHealth() - wraithGeneral.getMonsterAttack());
                            System.out.println("You have been attacked by " + wraithGeneral.getMonsterName());

                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        } else if (mon == 2) {
                            player.setPlayerHealth(player.getPlayerHealth() - wraithGeneral.getMonsterAttack() - 12);
                            System.out.println("You were Brutally attacked by " + wraithGeneral.getMonsterName());
                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        }
                    }

                    //player one of the choices of three attacks
                    else if (b == 2) {
                        wraithGeneral.setMonsterHealth(wraithGeneral.getMonsterHealth() - player.getInventory().get(3).getItemAffect());
                        System.out.println("You attacked " + wraithGeneral.getMonsterName() + " with " + player.getInventory().get(3).getItemName());

                        if (wraithGeneral.getMonsterHealth() < 0) {
                            System.out.println("You defeated " + wraithGeneral.getMonsterName() + " Congratulations 🫡🫡");
                            System.out.println(wraithGeneral.getMonsterName() + " is Dead 👻");
                            act = false;
                            break;
                        }

                        mon = random.nextInt(3);
                        if (mon == 0) {
                            System.out.println("And you dodge the monster attack successfully");
                        } else if (mon == 1) {
                            player.setPlayerHealth(player.getPlayerHealth() - wraithGeneral.getMonsterAttack());
                            System.out.println("You have been attacked by " + wraithGeneral.getMonsterName());

                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        } else if (mon == 2) {
                            player.setPlayerHealth(player.getPlayerHealth() - wraithGeneral.getMonsterAttack() - 12);
                            System.out.println("You were Brutally attacked by " + wraithGeneral.getMonsterName());
                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        }
                    }

                    //player's one of  the three choice of attack
                    else if (b == 3) {
                        wraithGeneral.setMonsterHealth(wraithGeneral.getMonsterHealth() - player.getInventory().get(1).getItemAffect());
                        System.out.println("You attacked " + wraithGeneral.getMonsterName() + " with " + player.getInventory().get(1).getItemName());

                        if (wraithGeneral.getMonsterHealth() < 0) {
                            System.out.println("You defeated " + wraithGeneral.getMonsterName() + " Congratulations 🫡🫡");
                            System.out.println(wraithGeneral.getMonsterName() + " is Dead 👻");
                            act = false;
                            break;
                        }
                        mon = random.nextInt(3);
                        if (mon == 0) {
                            System.out.println("you dodge the monster attack");
                        } else if (mon == 1) {
                            player.setPlayerHealth(player.getPlayerHealth() - wraithGeneral.getMonsterAttack());
                            System.out.println("You have been attacked by " + wraithGeneral.getMonsterName());

                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        } else if (mon == 2) {
                            player.setPlayerHealth(player.getPlayerHealth() - wraithGeneral.getMonsterAttack() - 12);
                            System.out.println("You were Brutally attacked by " + wraithGeneral.getMonsterName());
                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println("Cursed Knight killed You💀");
                                act = false;
                                break;
                            }
                        }
                    } else {
                        System.out.println("choose option from the Above...");
                    }
                } else if (a == 2) {
                    System.out.println("You are Trying to dodge the Attack , ahh\n");
                    mon = random.nextInt(2);
                    if (mon == 0) {
                        System.out.println("And you dodge the monster attack successfully");
                    } else if (mon == 1) {
                        player.setPlayerHealth(player.getPlayerHealth() - wraithGeneral.getMonsterAttack());
                        System.out.println("You have been attacked by " + wraithGeneral.getMonsterName());

                        if (player.getPlayerHealth() <= 0) {
                            System.out.println("You are Dead ");
                            System.out.println("Cursed Knight killed You💀");
                            act = false;
                            break;
                        }
                    }
                }

                //player choose healing potion to heal
                else if (a == 3) {
                    int potionIndex1 = -1;
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        if (player.getInventory().get(i).getItemName().equalsIgnoreCase("Healing Potion")) {
                            potionIndex1 = i;
                            break;
                        }
                    }
                    if (potionIndex1 != -1) {
                        Item potionItem = player.getInventory().get(potionIndex1);
                        player.setPlayerHealth(player.getPlayerHealth() + potionItem.getItemAffect());
                        System.out.println("You used " + potionItem.getItemName() + " 🧪");
                        System.out.println("Your health is now: " + player.getPlayerHealth());
                        player.getInventory().remove(potionIndex1);
                    } else {
                        System.out.println("Not Available...");
                    }
                } else {
                    System.out.println("Please enter Valid Choice");
                }
                System.out.println("Your Health: " + player.getPlayerHealth() + " | " + wraithGeneral.getMonsterName() + " Health: " + wraithGeneral.getMonsterHealth());
                System.out.println("1.Attack\n2.Dodge\n3.Use Healing Potion");
            }
        }

        //final result of monster and player battle and getting reward
        if (player.getPlayerHealth() > 0 && wraithGeneral.getMonsterHealth() < 0) {
            Item potion = new Item("Healing Potion", "A glowing potion that restores 30 HP", 30);
            Item soulforgedBlade = new Item("Soulforged Blade", "A sword reforged from the souls of fallen warriors. Glows with runes, deals immense damage to cursed beings.", 20);
            System.out.println(wraithGeneral.getMonsterName() + " Dropped Item You can check them in your Inventory");
            player.getInventory().add(soulforgedBlade);
            System.out.println("Bonus: " + potion.getItemName());
            player.getInventory().add(potion);
        }
    }

    //Final Boss Fight
    public static void chamber2ndFight(Player player,Monster amuletGuardian){
        Random r=new Random();

        Item healingHurbCastle = new Item("Healing Hurb", "Restores 25 HP — found hidden under the fallen throne.",35);
        int hurbIndex=6;
        int lastIndex=player.getInventory().size()-1;
        Collections.swap(player.getInventory(),hurbIndex,lastIndex);

        while (player.getPlayerHealth() > 0 && amuletGuardian.getMonsterHealth() > 0) {

            System.out.println("Your Health: " + player.getPlayerHealth() + " | " + amuletGuardian.getMonsterName() + " Health: " + amuletGuardian.getMonsterHealth());
            System.out.println("1.Attack\n2.Use Healing Potion");
            int mon;
            boolean act = true;
            while (act) {
                int a = sc.nextInt();

                //choices for player
                if (a == 1) {
                        amuletGuardian.setMonsterHealth(amuletGuardian.getMonsterHealth() - player.getInventory().get(6).getItemAffect());
                        System.out.println("You attacked " + amuletGuardian.getMonsterName() + " with " + player.getInventory().get(6).getItemName());

                        if (amuletGuardian.getMonsterHealth() < 0) {
                            System.out.println("You defeated " + amuletGuardian.getMonsterName() + " Congratulations 🫡🫡");
                            System.out.println(amuletGuardian.getMonsterName() + " is Dead 👻");
                            act = false;
                            break;
                        }

                //in each  player choice monster randomly attacks
                        mon = r.nextInt(2);
                        if (mon == 0) {
                            player.setPlayerHealth(player.getPlayerHealth() - amuletGuardian.getMonsterAttack()-5);
                            System.out.println("You have been attacked by " + amuletGuardian.getMonsterName());

                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println(amuletGuardian.getMonsterName() +" killed You💀");
                                act = false;
                                break;
                            }
                        }
                        else if (mon == 1) {
                            player.setPlayerHealth(player.getPlayerHealth() - amuletGuardian.getMonsterAttack() - 10);
                            System.out.println("You were Brutally attacked by " + amuletGuardian.getMonsterName());
                            if (player.getPlayerHealth() <= 0) {
                                System.out.println("You are Dead ");
                                System.out.println(amuletGuardian.getMonsterName() +" killed You💀");
                                act = false;
                                break;
                            }
                    }  else {
                        System.out.println("choose option from the Above...");
                    }
                }

                //player uses  healing hurb
                else if (a == 2) {
                    int potionIndex1 = -1;
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        if (player.getInventory().get(i).getItemName().equalsIgnoreCase("Healing Hurb")) {
                            potionIndex1 = i;
                            break;
                        }
                    }
                    if (potionIndex1 != -1) {
                        Item potionItem = player.getInventory().get(potionIndex1);
                        player.setPlayerHealth(player.getPlayerHealth() + potionItem.getItemAffect());
                        System.out.println("You used " + potionItem.getItemName() + " 🧪");
                        System.out.println("Your health is now: " + player.getPlayerHealth());
                        player.getInventory().remove(potionIndex1);
                    } else {
                        System.out.println("Not Available Anymore...");
                    }
                }
                else {
                    System.out.println("Please enter Valid Choice");
                }

                //at the end of choice player and Boss Health prints out
                System.out.println("Your Health: " + player.getPlayerHealth() + " | " + amuletGuardian.getMonsterName() + " Health: " + amuletGuardian.getMonsterHealth());
                System.out.println("1.Attack\n2.Use Healing Hurb");
            }
        }

        //final result and getting the  stone to free the eldoria from curse
        if(player.getPlayerHealth()>0&&amuletGuardian.getMonsterHealth()<=0){
            System.out.println("Congratulation on defeating the Amulet Guardian 🎊🎊🎊🎊");
            System.out.println("You have shown a fine spirit Throughout Your journey of ELDORIA");
            Item AmuletStone=new Item("Amulet Stone","A magical and powerful stone that brings joys and happiness and reomve the unturbed curse",0);
            player.getInventory().add(AmuletStone);
        }
    }
}
