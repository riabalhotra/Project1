/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author jashandeep kaur , ria , gurleen kaur , navdeep kaur
 */
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args){

        System.out.println("Welcome to the Game!");

        Deck playDeck = new Deck();
        playDeck.FullDeck();
        playDeck.shuffle();

        Deck CardsOfPlayer = new Deck();
        double MoneyOfPlayer = 70.0;

        Deck CardsOfDealer = new Deck();
        Scanner s = new Scanner(System.in);

        while(MoneyOfPlayer>0){
            System.out.println("You are having " + MoneyOfPlayer + " dollars, how much would you like to bet?");
            double playerBetMoney = s.nextDouble();
            boolean end = false;
            if(playerBetMoney > MoneyOfPlayer){
                System.out.println("You cannot bet more than the money you have.");
                break;
            }

            CardsOfPlayer.draw(playDeck);
            CardsOfPlayer.draw(playDeck);

            CardsOfDealer.draw(playDeck);
            CardsOfDealer.draw(playDeck);

            while(true)
            {
                System.out.println("You have: " + CardsOfPlayer.cardsValue()  + CardsOfPlayer.toString() + " Suit");

                System.out.println("Dealer Has: " + CardsOfDealer.getCard(0).toString() + " [hidden]");

                System.out.println("Would you like to (1)Hit or (2)Stand");
                int response = s.nextInt();

                if(response == 1){
                    CardsOfPlayer.draw(playDeck);
                    System.out.println("You draw a:" + CardsOfPlayer.getCard(CardsOfPlayer.deckSize()-1).toString());

                    if(CardsOfPlayer.cardsValue() > 21){
                        System.out.println("You are Currently valued at: " + CardsOfPlayer.cardsValue());
                        MoneyOfPlayer -= playerBetMoney;
                        end = true;
                        break;
                    }
                }

                if(response == 2){
                    break;
                }
            }

            System.out.println("Dealer Cards:" + CardsOfDealer.toString());
            if((CardsOfDealer.cardsValue() > CardsOfPlayer.cardsValue())&&end == false){
                System.out.println("Dealer beats you " + CardsOfDealer.cardsValue() + " to " + CardsOfPlayer.cardsValue());
                MoneyOfPlayer -= playerBetMoney;
                end = true;
            }

            while((CardsOfDealer.cardsValue() < 17) && end == false){
                CardsOfDealer.draw(playDeck);
                System.out.println("Dealer draws: " + CardsOfDealer.getCard(CardsOfDealer.deckSize()-1).toString());
            }

            System.out.println("Dealers has: " + CardsOfDealer.cardsValue());
            //Determine if dealer busted
            if((CardsOfDealer.cardsValue()>21)&& end == false){
                System.out.println("You win!");
                MoneyOfPlayer += playerBetMoney;
                end = true;
            }

            if((CardsOfDealer.cardsValue() == CardsOfPlayer.cardsValue()) && end == false){
                System.out.println("Push.");
                end = true;
            }

            if((CardsOfPlayer.cardsValue() > CardsOfDealer.cardsValue()) && end == false){
                System.out.println("You win the hand.");
                MoneyOfPlayer += playerBetMoney;
                end = true;
            }
            else if(end == false)
            {
                System.out.println("Dealer wins.");
                MoneyOfPlayer -= playerBetMoney;
            }

            CardsOfPlayer.moveToDeck(playDeck);
            CardsOfDealer.moveToDeck(playDeck);
            System.out.println("End of Hand.");

        }

        System.out.println("Game over! Your money is gone");

    }


}
