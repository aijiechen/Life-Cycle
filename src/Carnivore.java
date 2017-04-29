import java.util.Random;

public class Carnivore extends Agent {

    private int energy=3;
    private int age=0;
    private Agent[][] earth= Earth.getData();

    public Carnivore(int x, int y) {
        super(x, y);
    }

    public void move(){
        if(age==6 || energy==0){
            die();
        }
        else{
            boolean [] memory=new boolean[9];
            boolean action=false;
            while(!action){
                int r=(int) (Math.random() * 9); // random integer from 0 to 8
                int row=x+r%3-1;
                int clom=y+r/3-1;
                if(!memory[r] && !(row<0 || row>19 || clom<0 || clom>19)){
                    Agent agent= earth[row][clom];
                    if (agent==null || agent instanceof Herbivore){
                        if(agent instanceof Herbivore && energy <13){
                            eat(row,clom);
                        }
                        if((age+1) % 4 == 0 && energy>=8)
                            reproduce();
                        else
                            earth[x][y]=null;
                        action=true;
                    }
                    else{memory[r]=true;}
                }
                else{
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
    public void reproduce(){
        earth[x][y]=new Carnivore(x,y);
        energy -=3;
    }

    public void eat(int x, int y){
        earth[x][y]=null;
        energy +=5;
    }
    public void die(){
        earth[x][y]=null;
    }
}
