// AUTHOR: Kymas Nee-Classey
// DATE: 29th September 2024
// Version 1.0
// DESCRIPTION: A quiz program which will prompt users to guess the link between a series of outputted words

import java.util.Random;
import java.util.Scanner;

class Round
{
    int round_num = 0;

    String [][] guessingWordsAndans;
    String difficulty = "";

    String theBigWord; // the word that links all guessing words
    int bonusPoints; // for solving the big word in the end of a round

    int total_guesses;
    int points_Available;

} //END Round

class Words 
{
    String currentWords = " ";
    String currentAns = " ";

    String [][] round1 = {
        {"Samsung & Idols", "Plumber & Italy", "Honour & Sword" , "Computer & Repair - Spicy & Food"}, 
        {"Korea","Mario","Samurai","Curry"}
    };

    String [][] round2 = {
        {"nan & non", "nin & non", "nib & ui" , "ft & gy - oi & io"}, 
        {"ooo","pp","mm","nn"}
    };
}


class connections
 {
    public static void main (String [] args)
    {
        connectionsLvlX();
        return;
    } //END main


    // A simple shorthand print statement
    //
    public static void print(String message)
    {
        System.out.println(message);
        return;
    } //END print


    // Receives input from user, with optional message to be displayed beforehand
    //
    public static String inputString(String message)
    {
        Scanner scan = new Scanner(System.in);
        String userinput;
        print(message);
        userinput = scan.nextLine();
        return userinput;
    } //END inputString


    // Display the rules
    //
    public static void printRules()
    {
        print("");
        print("The Connections Quiz!");
        print("");
        print("                                RULES                                       ");
        print("----------------------------------------------------------------------------");
        print("You'll be given a series of words.");
        print("Each of those words have one common link between them.");
        print("It could be a theme, a gen0re, a person or etc.");
        print("You can make more than one guess...");
        print("...though the points you'll earn will go down with every incorrect one..");
        print("----------------------------------------------------------------------------");
        return;
    } //END printRules

     
    // Ask how many people are playing, returns this number at the end
    //
    public static int getNumPlayers()
    {
        final int MAX_PLAYERS_ALLOWED = 4;
        final String MAX_PLAYERS_ALLOWED_TEXT = Integer.toString(MAX_PLAYERS_ALLOWED); //use for concatenation purposes
        int numPlayers = Integer.parseInt(inputString("How many are playing today? (max 4 players): "));
        
        while ( (!(numPlayers <= MAX_PLAYERS_ALLOWED)) || (numPlayers <= 0) ) 
        {

            if (numPlayers <= 0)
            {
               print("Invalid number.");
            }
            else
            {
               print("Only up to " + MAX_PLAYERS_ALLOWED_TEXT + " players is allowed.");
            } //ENDIF

            numPlayers = Integer.parseInt(inputString("Enter again: "));
        } //ENDwhile
        
        return numPlayers;
    } //END getNumPlayers


    // Create a round, sets information on thing such as the difficulty, the words to guess, maximum attempts and points, etc
    public static Round createRound 
    (
        int round,
        String [][] wordsAndans_thisRound, String round_difficulty, String thisRounds_BigWord, 
        int guesses_available, int max_points, int bonusPoints
    )

    {
        Round r = new Round();

        r.round_num = round;


        r.guessingWordsAndans = wordsAndans_thisRound;
        r.difficulty = round_difficulty;
        r.theBigWord = thisRounds_BigWord;

        r.total_guesses = guesses_available;
        r.points_Available = max_points;
        r.bonusPoints = bonusPoints;

        return r;
    } //END Round

    //Update a piece of information stored about a round
    //
    public static Round updateRound_info (Words w, Round r, String info, String newValue)
    {
        switch (info) 
        {
            case "round number":
                r.round_num = Integer.parseInt(newValue);
                break;
                
            case "difficulty":
                r.difficulty = newValue;
                break;
                
            case "big word":
                r.theBigWord = newValue;
                break;
                
            case "bonus points":
                r.bonusPoints = Integer.parseInt(newValue);
                break;

            case "max guesses":
                r.total_guesses = Integer.parseInt(newValue);
                break;
                
            case "max points":
                r.points_Available = Integer.parseInt(newValue);
                break;
                
            case "words":

                //looks for the current word, then updates its value
                for (int words = 0; words < 4; words++)
                {
                    
                  if (r.guessingWordsAndans[0][words].equals(w.currentWords))
                  {
                      r.guessingWordsAndans[0][words] = newValue;
                  } //ENDIF
                    
                } //ENDfor
                break;
                
            case "answer":

                //same logic as above but w/ answer
                for (int words = 0; words < 4; words++)
                {
                    
                  if (r.guessingWordsAndans[1][words].equals(w.currentAns))
                  {
                      r.guessingWordsAndans[1][words] = newValue;
                  } //ENDIF
                    
                } //ENDfor
                break;
        
            default:
                print(info + " is not one of the infomation held in a round. Please check you've typed it correctly.");

        } //ENDswitchcase

        return r;
    } //END updateRound_info


    //Display information about a round
    //
    public static void printRound_info (Words w, Round r, String info)
    {

        switch (info) 
        {

            case "round number":
                print("Round " + r.round_num);
                break;
                
            case "difficulty":
                print("Difficulty: " + r.difficulty);
                break;
                
            case "big word":
                print("The big word for this was......" + r.theBigWord);
                break;
                
            case "bonus points":
                print("Bonus points: " + r.bonusPoints);
                break;

            case "max guesses":
                print("You have to find the word in " + r.total_guesses + " attempts.");
                break;
                
            case "max points":
                print("You can win up to " + r.points_Available + " points!");
                break;
                
            case "words and answers":

                //looks for the current word or answer then prints it
                for (int list = 0; list < 2; list++)
                {
                    
                    for (int word = 0; word < 4; word++)
                    {
                        if (r.guessingWordsAndans[list][word].equals(w.currentWords))
                        {
                            print("Words: " + r.guessingWordsAndans[list][word]);
                        }
                        else if (r.guessingWordsAndans[list][word].equals(w.currentAns))
                        {
                            print("Answer: " + r.guessingWordsAndans[list][word]);
                        }
                        else if (r.guessingWordsAndans[list][word].equals("COMPLETED"))
                        {
                            print("This has been solved."); 
                        }
                        else
                        {
                            assert true;
                        } //ENDIF
                        
                    } //ENDfor
                        
                } //ENDfor
                
                  
                break;
                
            default:
                print(info + " is not one of the infomation held in a round. Please check you've typed it correctly.");

        } //ENDswitchcase

        return;
    }//END printRound_info


     
     // Generate a random set of words for guessing the link, as well as their associated answer
     //
     public static String generateWords (String [][] words_and_ans)
     {
         String [] words_to_guess = words_and_ans[0];
         String [] answers = words_and_ans[1];
         
         Random word_chooser = new Random ();
         int choose_thisWord = word_chooser.nextInt(4);
         String promptAndanswer = words_to_guess[choose_thisWord] + "# " + answers[choose_thisWord]; 

         while (promptAndanswer.contains("COMPLETED"))
         {
            choose_thisWord = word_chooser.nextInt(4);
            promptAndanswer = words_to_guess[choose_thisWord] + "# " + answers[choose_thisWord]; 
         }
         return promptAndanswer;
     } //END generateWords

     
    // Starts the quiz - displays the words and prompt and gives user up to 5 attempts to guess the link
    //
    public static void playQuiz(int howManyPlayers)
    {
        Words wordsFor = new Words();
        Round round1 = createRound(1, wordsFor.round1, "Easy", "Asia", 5, 10, 4 );



        String currentGuess = "";
        int guesses = 0;
        boolean isfirstGuessMade = false; 
        String messageToPlayer = "Next player up!";  /* this assumes the quiz is multiplayer & the string'll be displayed to 
                                              *                                              the players after a failed guess */
        int awardedPoints = 0;
        boolean foundAns = false;

        String wordsAndans = generateWords(round1.guessingWordsAndans);
        String [] wordsAndans_Array = wordsAndans.split("# ");

        String linkedWords2guess = wordsAndans_Array[0];
        String ans =  wordsAndans_Array[1];

        String [] updateQuestion = {"words","answer"};
        wordsFor.currentWords = linkedWords2guess;
        wordsFor.currentAns = ans;
    
        for (int points = round1.total_guesses; points >=1; points--)
        {
            if (!(foundAns))
            {
                if (howManyPlayers == 1) messageToPlayer = "Try again!"; //ENDIF
                
                if (isfirstGuessMade == false) //changes message displayed after the first incorrect guess
                {
                    print("The words are:");
                    isfirstGuessMade = true;
                }
                else 
                {
                    print("");
                    print("Ooh that was incorrect.. " + messageToPlayer + "\n");
                } //ENDIF

                
                print(linkedWords2guess);
                currentGuess = inputString("Enter your guess: ");
    
                if (currentGuess.equals(ans))
                {
                    awardedPoints = points * 2;
                    foundAns = true;
                    print(Integer.toString(awardedPoints) + " points! You got it!");
                    printRound_info(wordsFor, round1, "words and answers"); 

                    for (String update : updateQuestion)  //updates the current word and answer as solved
                    {
                        updateRound_info(wordsFor, round1, update, "COMPLETED");
                    } //ENDfor
                
                } //ENDIF
                
            } //ENDIF
            
            guesses += 1;
        }//ENDfor
        
        printRound_info(wordsFor, round1, "words and answers");
        return;
    } //END playQuiz
     


     
    // A quiz program. It presents the rules, asks how many players are in, and outputs the words to be solved by the user 
    // It also calculates and displays the points earned after a correct guess - then prints answer
    //
    public static void connectionsLvlX ()
        {
            printRules();
            int numOFplayers = getNumPlayers();
            playQuiz(numOFplayers); 
            return;
        } //END connectionsLvlX

} //END connections


/*

- got rid of isRight and printAns method
- created classes Round and Word
- added more series of words as well as their associated answers
-
 
*/