import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome\n");
        Map<String, Password> passwords = new HashMap<>();

        while (true){
            String rawIn = input.nextLine();
            String[] userIn = rawIn.split(" ");

            if(userIn[0].equals("help")){ // help command
                if (userIn.length > 1){
                    System.out.println("Unknown command");
                    continue;
                }
                System.out.println("help - shows all commands\nnew pass 1234 - creates new passcode 1234 with name \"pass\"");
                System.out.println("new pass 1 3 - creates new passcode of length 1 and level 3 with name \"pass\"");
                System.out.println("print pass - prints out info of name \"pass\"\ncrack pass - cracks pass word of name \"pass\"");
                System.out.println("quit - quits\n");
            }

            else if (userIn[0].equals("quit")){ // quit command
                if (userIn.length > 1){
                    System.out.println("Unknown command");
                    continue;
                }
                input.close();
                return;
            }

            else if(userIn[0].equals("new")){ // new command
                if (userIn.length == 3){
                    passwords.put(userIn[1], new Password(userIn[2]));
                }
                else if (userIn.length == 4){
                    int length;
                    int level;
                    try {
                        length = Integer.valueOf(userIn[2]);
                        level = Integer.valueOf(userIn[3]);

                        if( length > 50 || level > 3 || length < 0 || level < 0)
                            throw new NumberFormatException();

                    } catch (NumberFormatException e){
                        System.out.println("Unknown command");
                        continue;
                    }
                    passwords.put(userIn[1], new Password(length, level));
                }
                else{
                    System.out.println("Unknown command");
                    continue;
                }
            }

            else if(userIn[0].equals("print")){ // print command
                if (userIn.length > 2 || !passwords.containsKey(userIn[1])){
                    System.out.println("Unknown command");
                    continue;
                }
                passwords.get(userIn[1]).print();
            }

            else if(userIn[0].equals("crack")){ // crack password
                if (userIn.length > 2 || !passwords.containsKey(userIn[1])){
                    System.out.println("Unknown command");
                    continue;
                }
                System.out.println("Starting...");
                passwords.get(userIn[1]).crackPassword();
                System.out.println("done!");
            }
        }
    }
}
