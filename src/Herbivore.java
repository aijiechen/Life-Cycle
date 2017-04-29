import java.util.Random;


public class Herbivore extends Agent {
    private int energy=3;
    private int age=0;
    private Agent[][] earth= Earth.getData();

    public Herbivore(int x, int y)
    {
        super(x, y);
    }

    public void move(){
        if(age==6 || energy==0){
            die();
        }
        else{
            boolean[] memory=new boolean[9];
            boolean action=false;
            while(!action){
                int r=(int) (Math.random() * 9);
                int row=x+r%3-1;
                int clom=y+r/3-1;
                if(!memory[r] && !(row<0 || row>19 || clom<0 || clom>19)){
                    Agent agent= earth[row][clom];
                    if (agent==null || agent instanceof Plant){
                        if(agent instanceof Plant)
                            eat(row,clom);
                        else
                            earth[x][y]=null;
                        earth[row][clom]=new Herbivore(row,clom);
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
                        action=true;
                }
            }
            age++;
        }

    }


    public void eat(int x, int y){
        earth[x][y]=null;
    }

    public void die(){
        earth[x][y]=null;
    }
}


