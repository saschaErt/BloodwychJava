/**
 * Created by ERTE005 on 11.03.2015.
 */
public class Player {

    public int pbg;
    public int x;
	public int y;
    public int lastX;
    public int lastY;
	public int level;
	public int rotation;
	public int portalX;
	public int portalY;
	public String[] view;
    Bloodwych bloodwych;
    private int lastlevel;


    public Player(int posX, int posY, int level, int rotation, int portalX, int portalY, Bloodwych bloodwych) {

		this.x = posX;
		this.y = posY;
        this.lastX = posX;
        this.lastY = posY;
		this.level = level;
		this.rotation = rotation;
		this.portalX = portalX;
		this.portalY = portalY;
		this.view = new String[20];
        this.pbg = 0;
        this.bloodwych = bloodwych;
        bloodwych.tower.setHexAtMapPos(level, this.x, this.y,Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[this.y][this.x], 2, '8'));


	}



    public void switchPlayerBackground() {
        if(this.pbg == 0) {
            this.pbg = 1;
        }
        else {
            this.pbg = 0;
        }
    }

    public void action() {

        //door ahead?
         if (this.view[15].substring(3).equals("5")) {

            int xo = 0, yo = 0;
            int mapX;
            int mapY;

            switch (this.rotation) {
                case 0:
                    xo = 0;
                    yo = -1;
                    break;
                case 1:
                    xo = 1;
                    yo = 0;
                    break;
                case 2:
                    xo = 0;
                    yo = 1;
                    break;
                case 3:
                    xo = -1;
                    yo = 0;
                    break;
            }

            mapY = this.y + (1 * yo) - (0 * xo); //-1
            mapX = this.x + (1 * xo) + (0 * yo); //0



             int t = Integer.parseInt(this.view[15].substring(1,2),16);

            switch (t) {

                case 0: {
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY, Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, '1')); // bunte door
                    break;
                }
                case 1: {
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, '0')); // (BB mod 2 = 0 => Open door) bunte tür geöffnet
                    break;
                }
                case 2: { //geöffnete Gittertür (0205)w
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, '3'));
                    break;
                }
                case 3: { // geschlossene Gittertür (0305)
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, '2')); // (BB mod 2 = 0 => Open door)
                    break;
                }
                case 4: {
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, '5'));
                    break;
                }
                case 5: {
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, '4')); // (BB mod 2 = 0 => Open door)
                    break;
                }
                case 6: {
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, '7')); //gittertür geschlossen
                    break;
                }
                case 7: {
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, '6')); // (BB mod 2 = 0 => Open door) Gittertür geöffnet
                    break;
                }
                case 14: {
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, 'F')); //??
                    break;
                }
                case 15: {
                    bloodwych.tower.setHexAtMapPos(level, mapX, mapY,  Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[mapY][mapX], 1, 'E')); // ??
                    break;
                }

                default:
                    break;


            }
             pView(bloodwych.tower.levels.get(this.level).map);
        }

    };


    private void updateMap() {
        try {
            bloodwych.tower.setHexAtMapPos(level, lastX, lastY, Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[lastY][lastX], 2, '0'));
            bloodwych.tower.setHexAtMapPos(level, this.x, this.y, Tools.replaceAt(bloodwych.tower.levels.get(this.level).map[this.y][this.x], 2, '8'));

            }catch(Exception ex) {
             //nothing
            }
        this.lastX = this.x;
        this.lastY = this.y;
    };

	public void moveForward() {
        if(checkObject(this.view[15])) {

            int xo = 0;
            int yo = 0;
            switch (this.rotation) {
                case 0:
                    xo = 0;
                    yo = -1;
                    break;
                case 1:
                    xo = 1;
                    yo = 0;
                    break;
                case 2:
                    xo = 0;
                    yo = 1;
                    break;
                case 3:
                    xo = -1;
                    yo = 0;
                    break;
            }
            this.y = this.y + (1 * yo) - (0 * xo);
            this.x = this.x + (1 * xo) + (0 * yo);
            playerEvents();
        }

	}

	public void moveBackward() {

        if(checkObject(this.view[19])){
            int xo = 0;
            int yo = 0;
            switch (this.rotation) {
                case 0:
                    xo = 0;
                    yo = -1;
                    break;
                case 1:
                    xo = 1;
                    yo = 0;
                    break;
                case 2:
                    xo = 0;
                    yo = 1;
                    break;
                case 3:
                    xo = -1;
                    yo = 0;
                    break;
            }
            this.y = this.y - (1 * yo) - (0 * xo);
            this.x = this.x - (1 * xo) + (0 * yo);
            playerEvents();
        }

	}

    public void moveLeft() {
        if(checkObject(this.view[17])) {
            int xo = 0;
            int yo = 0;
            switch (this.rotation) {
                case 0:
                    xo = -1;
                    yo = 0;
                    break;
                case 1:
                    xo = 0;
                    yo = -1;
                    break;
                case 2:
                    xo = 1;
                    yo = 0;
                    break;
                case 3:
                    xo = 0;
                    yo = 1;
                    break;
            }
            this.y = this.y + (1 * yo) - (0 * xo);
            this.x = this.x + (1 * xo) + (0 * yo);
            playerEvents();
        }

    }

    public void moveRight() {
        if(checkObject(this.view[16])) {
            int xo = 0;
            int yo = 0;
            switch (this.rotation) {
                case 0:
                    xo = 1;
                    yo = 0;
                    break;
                case 1:
                    xo = 0;
                    yo = 1;
                    break;
                case 2:
                    xo = -1;
                    yo = 0;
                    break;
                case 3:
                    xo = 0;
                    yo = -1;
                    break;
            }
            this.y = this.y + (1 * yo) - (0 * xo);
            this.x = this.x + (1 * xo) + (0 * yo);
            playerEvents();
        }

    }


    public void moveLevelUp(Tower tower) {


        this.level = this.level + 1;
        if (this.level > tower.levels.size()){
            this.level = 0;
        }
        else {
            this.moveForward();
            this.moveForward();
        }

    }
    public void moveLevelDown(Tower tower) {

        this.level = this.level - 1;
        if (this.level > tower.levels.size()){
            this.level = tower.levels.size()-1;
        }
        else {
            this.moveForward();
            this.moveForward();
        }

    }





    /**
     * 0 = North
     * 1 = east
     * 2 = south
     * 3 = west
     * @param dir
     */
    public void rotatePlayer(int dir) {
        if (dir == 1) {
            this.rotation = this.rotation -1;
            if (this.rotation < 0) {
                this.rotation = 3;
            }
        }
        else {
            this.rotation = this.rotation +1;
            if (this.rotation > 3) {
                this.rotation = 0;
            }
        }
        switchPlayerBackground();
        pView(bloodwych.tower.levels.get(this.level).map);

    }

    public boolean checkObject(String hex) {

        //example = 0105 = (Door: last pos = 5, closed: first pos = 0)
        //second player
        if (Integer.parseInt(hex.substring(2, 3),16) == 8) {
            return false;
        }
        switch (hex.substring(3, 4)) {

            case "0":
                return true; //empty

            case "1":
                return false; //wall

            case "3":
                return false; //pillar // bed
            case "5": //door
                if (Integer.parseInt(hex.substring(1, 2), 16) % 2 == 0) {
                    return true; //opened door (type  1 or type 2)
                } else {
                    return false; //closed door (type 1 or type 2)
                }
            default: //opened wood door
                return true;
        }

    }


	/**
	 * calculates the fields in map which are seen by the player relative for
	 * current direction and position.
	 * @param map
	 */
	public void pView(String[][] map) {

		int xo = 0;
		int yo = 0;
		switch (this.rotation) {
			case 0:
				xo = 0;
				yo = -1;
				break;
			case 1:
				xo = 1;
				yo = 0;
				break;
			case 2:
				xo = 0;
				yo = 1;
				break;
			case 3:
				xo = -1;
				yo = 0;
				break;
		}

		for(int x = 0; x < 20; x++) {

            try {
                switch (x) {
                    case 0:
                        this.view[0] = map[this.y + (4 * yo) + (2 * xo)][this.x + (4 * xo) - (2 * yo)];
                        break;//-4 +2
                    case 1:
                        this.view[1] = map[this.y + (4 * yo) - (2 * xo)][this.x + (4 * xo) + (2 * yo)];
                        break; //-4 -2
                    case 2:
                        this.view[2] = map[this.y + (4 * yo) + (1 * xo)][this.x + (4 * xo) - (1 * yo)];
                        break; //-4 +1
                    case 3:
                        this.view[3] = map[this.y + (4 * yo) - (1 * xo)][this.x + (4 * xo) + (1 * yo)];
                        break; //-4 -1
                    case 4:
                        this.view[4] = map[this.y + (4 * yo) - (0 * xo)][this.x + (4 * xo) + (0 * yo)];
                        break; //-4 0
                    case 5:
                        this.view[5] = map[this.y + (3 * yo) + (2 * xo)][this.x + (3 * xo) - (2 * yo)];
                        break; //-3 +2
                    case 6:
                        this.view[6] = map[this.y + (3 * yo) - (2 * xo)][this.x + (3 * xo) + (2 * yo)];
                        break; //-3 -2
                    case 7:
                        this.view[7] = map[this.y + (3 * yo) + (1 * xo)][this.x + (3 * xo) - (1 * yo)];
                        break; //-3 +1
                    case 8:
                        this.view[8] = map[this.y + (3 * yo) - (1 * xo)][this.x + (3 * xo) + (1 * yo)];
                        break; //-3 -1
                    case 9:
                        this.view[9] = map[this.y + (3 * yo) - (0 * xo)][this.x + (3 * xo) - (0 * yo)];
                        break; //-3 0
                    case 10:
                        this.view[10] = map[this.y + (2 * yo) + (1 * xo)][this.x + (2 * xo) - (1 * yo)];
                        break; //-2 +1
                    case 11:
                        this.view[11] = map[this.y + (2 * yo) - (1 * xo)][this.x + (2 * xo) + (1 * yo)];
                        break; //-2 -1w
                    case 12:
                        this.view[12] = map[this.y + (2 * yo) - (0 * xo)][this.x + (2 * xo) + (0 * yo)];
                        break; //-2 0
                    case 13:
                        this.view[13] = map[this.y + (1 * yo) + (1 * xo)][this.x + (1 * xo) - (1 * yo)];
                        break; //-1 +1
                    case 14:
                        this.view[14] = map[this.y + (1 * yo) - (1 * xo)][this.x + (1 * xo) + (1 * yo)];
                        break; //-1 -1
                    case 15:
                        this.view[15] = map[this.y + (1 * yo) - (0 * xo)][this.x + (1 * xo) + (0 * yo)];
                        break; //-1 0
                    case 16:
                        this.view[16] = map[this.y + (0 * yo) + (1 * xo)][this.x + (0 * xo) - (1 * yo)];
                        break; //0 +1
                    case 17:
                        this.view[17] = map[this.y + (0 * yo) - (1 * xo)][this.x + (0 * xo) + (1 * yo)];
                        break; //0 -1
                    case 18:
                        this.view[18] = map[this.y][this.x]; break;
                    case 19:
                        this.view[19] = map[this.y - (1 * yo) - (0 * xo)][this.x - (1 * xo) + (0 * yo)];
                        break; //1 0
                    default:
                        this.view[x] = "0001";
                        ;
                        break;


                }

            } catch (Exception ex) {
                this.view[x] = "0001";
            }
        }


	}

    private void playerEvents() {

        switchPlayerBackground();
        updateMap();
        pView(bloodwych.tower.levels.get(this.level).map);
        checkCurrentSquare();

    }

    private void checkCurrentSquare() {

        switch (Integer.parseInt(view[18].substring(3, 4), 16)) {

            case 4: {
                playerOnStair();
                break;
            }

            default:
                break;

        }
    }

    private void playerOnStair() {
       int BB = Integer.parseInt(view[18].substring(1, 2));

        if (BB % 2 == 0){changePlayerLevel(true);} // "Stairs Up";
        else if (BB % 2 == 1){changePlayerLevel(false);} // "Stairs Down";

        switch (BB) {
            case 0:
            case 1: // "South"
                this.rotation = 2;
                if (BB % 2 == 0){
                    this.x = this.x - bloodwych.tower.levels.get(this.level).xOffset;
                    this.y = (this.y - bloodwych.tower.levels.get(this.level).yOffset)+2;

                }
                else {
                    this.x = this.x + bloodwych.tower.levels.get(this.level+1).xOffset;
                    this.y = this.y + bloodwych.tower.levels.get(this.level+1).yOffset+2;

                }
                break;
            case 2:
            case 3: // "West";
                this.rotation = 3;
                if (BB % 2 == 0){
                    this.x = this.x - (bloodwych.tower.levels.get(this.level).xOffset - bloodwych.tower.levels.get(this.level-1).xOffset) - 2;
                    this.y = this.y - (bloodwych.tower.levels.get(this.level).yOffset - bloodwych.tower.levels.get(this.level-1).yOffset);

                }
                else {
                    this.x = this.x + (bloodwych.tower.levels.get(this.level+1).xOffset - bloodwych.tower.levels.get(this.level).xOffset) - 2;
                    this.y = this.y + (bloodwych.tower.levels.get(this.level+1).yOffset - bloodwych.tower.levels.get(this.level).yOffset);

                }
                break;
            case 4:
            case 5: // "North";
                this.rotation = 0;
                if (BB % 2 == 0){
                    this.x = this.x - bloodwych.tower.levels.get(this.level).xOffset;
                    this.y = (this.y - bloodwych.tower.levels.get(this.level).yOffset)-2;

                }
                else {
                    this.x = this.x + bloodwych.tower.levels.get(this.level+1).xOffset;
                    this.y = (this.y + bloodwych.tower.levels.get(this.level+1).yOffset)-2;
                    
                }
                break;
            case 6:
            case 7: // "East";

                this.rotation = 1;
                if (BB % 2 == 0){
                    this.x = this.x - (bloodwych.tower.levels.get(this.level).xOffset - bloodwych.tower.levels.get(this.level-1).xOffset) + 2;
                    this.y = this.y - (bloodwych.tower.levels.get(this.level).yOffset - bloodwych.tower.levels.get(this.level-1).yOffset);

                }
                else {
                    this.x = this.x + (bloodwych.tower.levels.get(this.level+1).xOffset - bloodwych.tower.levels.get(this.level).xOffset) + 2;
                    this.y = this.y + (bloodwych.tower.levels.get(this.level+1).yOffset - bloodwych.tower.levels.get(this.level).yOffset);

                }
                break;

            default:
                break;
        }
       updateMap();
    }

    private void changePlayerLevel(boolean l){

        //Change the Players Level
        //l = Boolean
        //True = Move Player Up a Level
        //False = Move Player Down a Level

        this.lastlevel = this.level;

        if (l) {
            this.level = this.level +1;
        }
        else {
            this.level = this.level -1 ;
        }

    }


}
