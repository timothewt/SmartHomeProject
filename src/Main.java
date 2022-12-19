import model.Weather;

public class Main {

    public static void main(String[] args) {


        Weather w = new Weather();

        for (int i = 0; i < 10; i++) {
            System.out.println(w);
            w.update(i);
        }

    }

}