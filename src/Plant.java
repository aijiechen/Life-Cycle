import java.util.Random;

public class Plant extends Agent {

    private int age=0;
    private int energy=3;
    private Agent[][] earth= Earth.getData();
    private String name = "PLANT";


    public Plant(int i, int j) {
        super(i, j);
    }
    @Override
    public void move(){
        if(age==5 || energy==0)
            die();
        else{
            boolean [] memory=new boolean[9];//momery
            boolean action=false;
            while(!action){
                int r=(int) (Math.random() * 9);
                int row=x+r%3-1;    //move up or down
                int clom=y+r/3-1;     //move left or right
                if(!memory[r] && !(row<0 || row>19 || clom<0 || clom>19)){
                    if (earth[row][clom]==null){ //if location is empty
                        if((age+1)%3==0)// Plant growth at random location in clock 3
                            earth[row][clom]=new Plant(row,clom);
                        action=true;
                    }
                    else{memory[r]=true;}
                }
                else{
                    //momery check,it is all possiable move location
                    int i=0;
                    for(;i<9;i++)
                        if(!memory[r])
                            break;
                    if(i==9)
                        action=true; // Don't move at all
                }
            }
            age++;
        }
    }


    public void die(){
        earth[x][y]=null;
    }

}