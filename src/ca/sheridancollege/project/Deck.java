/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author jashan
 */
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        //Default constructor
        this.cards = new ArrayList<Card>();
    }

    //Adding 52 playing cards to a deck
    public void FullDeck(){
        //for suits
        for(Suit cardSuit : Suit.values()){
            //for value
            for(Value cardValue : Value.values()){
                //Add new card to the mix
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }
    }


    //Shuffle deck of cards
    public void shuffle(){
        ArrayList<Card> deck1 = new ArrayList<Card>();
        Random r = new Random();
        int rCardIndex = 0;
        int Size = this.cards.size();
        for(int i = 0; i<Size;i++){
            rCardIndex = r.nextInt((this.cards.size()-1 - 0) + 1) + 0;
            deck1.add(this.cards.get(rCardIndex));
            this.cards.remove(rCardIndex);
        }
        this.cards = deck1;
    }


    public void remove(int numofcards){
        this.cards.remove(numofcards);
    }

    public Card getCard(int gc){
        return this.cards.get(gc);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    public void draw(Deck pick){
        this.cards.add(pick.getCard(0));
        pick.remove(0);
    }

    public String toString(){
        String List = "";
        int i = 0;
        for(Card aCard : this.cards){
            List += "\n" + aCard.toString();
            i++;
        }
        return List;
    }

    public void moveToDeck(Deck move) {
        int DeckSize = this.cards.size();
        for (int i = 0; i < DeckSize; i++) {
            move.addCard(this.getCard(i));
        }

        for (int i = 0; i < DeckSize; i++) {
            this.remove(0);
        }
    }

    public int deckSize(){
        return this.cards.size();
    }

    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;
        for(Card aCard : this.cards){
            switch(aCard.getValue()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 11; break;
                case QUEEN: totalValue += 12; break;
                case KING: totalValue += 13; break;
                case ACE: aces += 1; break;
            }
        }


        for(int i = 0; i < aces; i++){

            if (totalValue > 10){
                totalValue += 1;
            }
            else{
                totalValue += 11;
            }
        }
        return totalValue;
    }

    

}

