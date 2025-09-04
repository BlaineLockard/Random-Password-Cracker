public class Password {
    
    String password = "";
    double time_to_crack = 0.00;


    public Password(String inPass){ // If user enters password
        this.password = inPass;
    }

    public Password(int length, int level){ // If user doesn't enter password
        this.password = passwordGen(length, level);
    }

    private String passwordGen(int length, int level){
        String generatedPass = "";
        char[] characterList;

        if(level == 1){
            characterList = "1234567890".toCharArray();
        }
        else if(level == 2){
            characterList = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        }
        else if(level == 3){
            characterList = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%&*?|\\".toCharArray();
        }
        else{
            System.out.println("ERROR: Could not make password of level " + level);
            return "NULL";
        }

        while(generatedPass.length() < length){
            int index = (int)(Math.random() * (characterList.length - 1)); //gets random item from the avalible characters
            generatedPass += characterList[index];
        }
        return generatedPass;
    }

    public void crackPassword(){
        double startTime = System.nanoTime();
        char[] characterList = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%&*?|\\".toCharArray();
        String guess = "!";
        long secondTimer = System.nanoTime();
        

        while(!this.password.equals(guess)){
            StringBuilder sb = new StringBuilder(guess);
            boolean incrementing = true;
            int currIndex = 0;
            //System.out.println(guess);
            //System.out.println(password);

            while(incrementing){ //checks if we still need to go through the string to change further characters
                if(guess.charAt(currIndex) != '~'){
                    char currChar = sb.charAt(currIndex);
                    sb.setCharAt(currIndex, (char)(currChar + 1));
                    guess = sb.toString();
                    incrementing = false;
                }
                else{
                    if (currIndex + 2 > guess.length()){
                        sb.setCharAt(currIndex, '!');
                        sb.append("!");
                        guess = sb.toString();
                        incrementing = false;
                    }
                    else{
                        sb.setCharAt(currIndex, '!');
                        guess = sb.toString();
                        currIndex++;
                    }
                }
            }
            
            //System.out.println(guess);
            /* Find out how to get this to work
               print something every second to show that it's running still
            long secondTimerEnd = System.nanoTime();
            int secondsPassed = (int)((secondTimerEnd-secondTimer)/1000000000);
        
            if(secondsPassed%3 == 0){
                System.out.println("\n\n\n\n\nWorking.");
            }
            else if (secondsPassed%3 == 1){
                System.out.println("\n\n\n\n\nWorking..");
            }
            else if (secondsPassed%3 == 2){
                System.out.println("\n\n\n\n\nWorking...");
            }
                */
        }
        // Calculate total time taken, convert to seconds
        double endTime = System.nanoTime();
        double totalTime = (endTime-startTime)/1000000000;
        //System.out.println(totalTime);
        this.time_to_crack = totalTime;
    }

    public void print(){
        System.out.printf("Password info:\n\tPassword: %s\n\tTime to Crack: %.2f seconds\n\n", this.password, this.time_to_crack);
    }


}
