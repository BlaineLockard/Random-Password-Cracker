import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Password pass1 = new Password("!!!!!!");
        Password pass2 = new Password(4, 3);

        System.out.println("\n\n");
        pass1.crackPassword();
        pass2.crackPassword();

        pass1.print();
        pass2.print();

        
        System.out.println("\n\n");
    }
}
