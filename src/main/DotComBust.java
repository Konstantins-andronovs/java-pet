package main;

import java.util.ArrayList;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<>();
    private int numOfGuesses = 0;
    
    private void setUpGame() {
        DotCom one = new DotCom();
        one.setName("Ship1");
        DotCom two = new DotCom();
        one.setName("Ship2");
        DotCom three = new DotCom();
        one.setName("Ship3");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);
        
        System.out.println("Your goal is to hit " + dotComsList.size()+ "ships" );
        System.out.println("Try to hit all of them in minimal time");
        
        for (DotCom dotComToSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
        }
    }
    
    private void startPlaying() {
        while(!dotComsList.isEmpty())  {
            String userGuess = helper.getUserInput("Make your choice \n");
            checkUserGuess(userGuess);
        }
        finishGame();
    }
    
    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        
        String result = "Missed";
        
        for (DotCom dotComToTest : dotComsList) {
            result = dotComToTest.checkYourself(userGuess);
            
            if (result.equals("Hitted")) {
                break;
            }
            if (result.equals("Killed")) {
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }
    
    private void finishGame() {
        System.out.println("All ships are killed, Good Job");
        if (numOfGuesses <= 10) {
            System.out.println("It took just " + numOfGuesses + " guesses, Well done");
            
        } else {
            System.out.println("I took you too long to win");
        }
    }
    
    public static void main (String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}
