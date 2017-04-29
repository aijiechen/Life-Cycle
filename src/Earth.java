/**
 * Created by aijiechen on 11/6/16.
 */

public class Earth {
    private static final String CARNIVORE = "@ ";
    private static final String HERBIVORE = "& ";
    private static final String PLANT = "* ";


    private static Agent[][] earth;
    private static Earth element;

    private Earth() {
        earth = new Agent[20][20];
        initEarth();
    }

    private void initEarth() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (Math.random() < 0.20) {
                    earth[i][j] = new Plant(i, j);
                } else if (Math.random() < 0.10) {
                    earth[i][j] = new Herbivore(i, j);
                } else if (Math.random() < 0.05) {
                    earth[i][j] = new Carnivore(i, j);
                }
            }
        }
    }


    public static Earth newEarth() {
        if (element == null)
            element = new Earth();

        return element;
    }

    public void startEarth(int iter) {
        for (int itr = 0; itr <= iter; itr++) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (earth[i][j] != null) {
                        if (itr == iter - 1)
                            if (earth[i][j] instanceof Plant)
                                earth[i][j] = null;
                        if (earth[i][j] instanceof Herbivore)
                            if ((itr + 1) % 2 == 0)
                                earth[i][j].move();
                            else
                                earth[i][j].move();


                    }
                }
            }

            printEarth();
            System.out.println("--------------------------iteration " + itr + "------------------------------");
        }
    }

    private void printEarth() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (earth[i][j] instanceof Carnivore) {

                    System.out.print(CARNIVORE);
                } else if (earth[i][j] instanceof Herbivore)
                    System.out.print(HERBIVORE);
                else if (earth[i][j] instanceof Plant)
                    System.out.print(PLANT);
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    public static Agent[][] getData() {
        return earth;
    }

    public static void main(String[] args) {
        Earth.newEarth().startEarth(20);


    }

}