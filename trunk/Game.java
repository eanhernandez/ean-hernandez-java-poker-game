import java.util.Arrays;
class Game
{
   Player[] players = new Player[5];
   Deck deck;
   int pot=0;
   Game()
   {
  }
   void PlayARound()
   {
      System.out.println("\ngetting a new deck...");
      deck = new Deck();

      System.out.print(deck.toString());

      System.out.println("\nshuffling deck...");
      deck.shuffle();

      System.out.print(deck.toString());

      createPlayers( players);
      System.out.println("\nplayers have these purses... ");
      showPurses(players);
      System.out.println("\ndealing a round to "+ players.length +" players...");
      giveEachPlayer5Cards(players, deck);
      System.out.println("\nplayers are holding... ");
      showHands(players);
      System.out.println("\ntaking bets... ");
      pot = takeBets(players, pot);
      System.out.println("\ngiving players 2nd draw if they want... ");
      giveEachPlayer2ndDraw(players, deck);
      System.out.println("\nplayers are holding... ");
      showHands(players);
      System.out.println("\ntaking bets... ");
      pot = takeBets(players,pot);
      System.out.print("\nwinner(s): " + getWinners(players,pot)+ "\n");
      pot=0;
      System.out.println("\nplayers have these purses... ");
      showPurses(players);
      System.out.println("");

  }
   void showPurses(Player[] players)
   {
      System.out.print(" ");
      for (int i=0;i<players.length;i++)
      {
         System.out.print(players[i].name + ":" + players[i].cash + "  ");
      }
      System.out.println(""); 
   }
   int takeBets(Player[] players, int pot)
   {
      System.out.print(" ");
      for (int i=0;i<players.length;i++)
      {  
         if (players[i].fold) 
         {
            //skipping this guy, he's folded already
         }
         else
         {
            if (players[i].placeBet() == 0)
            {
               players[i].hand.handValue=-1; 
               players[i].fold = true;
               System.out.print(players[i].name + " folded, ");
            }
            else 
            {
               System.out.print(players[i].name + " bet 1, ");
               pot = pot + 1; 
               players[i].cash -= 1;
            }
         }
      }
      System.out.println("pot=" + pot + ".");
      return pot;
   }
   void createPlayers(Player[] players)
   {
      for(int i=0;i<players.length;i++)
      {
         players[i] = new Player("guy"+Integer.toString(i));
      }
   }
   void giveEachPlayer2ndDraw(Player[] players, Deck deck)
   {
      int secondDraw;
      Card[] cardHolder = new Card[1];

      for(int i=0;i<players.length;i++)
      {
         if (players[i].fold)
         {
            //skipping this guy, he's folded already
         }
         else
         {
            secondDraw = players[i].howManyMoreCardsToDraw();
            if (secondDraw>0){ System.out.println(" " + players[i].name + " switched " + secondDraw + " cards.");}
            for(int j=0;j<secondDraw;j++)
            {
               cardHolder = deck.deal(1);
               players[i].hand.cards[j]= cardHolder[0];
            }
            Arrays.sort(players[i].hand.cards);
            players[i].hand.handValue=players[i].hand.calculateHandValue(players[i].hand.cards);
         }
      }
   }
   
   void giveEachPlayer5Cards(Player[] players, Deck deck)
   {
      for(int i=0;i<players.length;i++)
      {
         players[i].hand = new Hand(deck);
      }
   }
   void showHands(Player[] players)
   {
      for(int i=0;i<players.length;i++)
      {
         if (players[i].fold)
         {
            //skipping this guy, he's folded already
         }
         else
         {
           System.out.print( " " + players[i].name+ ": ");
           for (int j=0;j<players[i].hand.cards.length;j++)
           {
              System.out.print(players[i].hand.cards[j].getNumber() + "" + players[i].hand.cards[j].getSuit() + " ");
           }
           System.out.println("(" + players[i].hand.handName + " v=" +  players[i].hand.handValue+")");
         }
      }
   }
   String getWinners(Player[] p, int pot)
   {
      Arrays.sort(p);
      Player[] hiScorePlayers = new Player[5];
      hiScorePlayers[0] = p[p.length-1];
      String s = "";
      int i,j=0;
      for (i=p.length-2;i>=0;i--)
      {
         if (p[i].hand.handValue == hiScorePlayers[0].hand.handValue)
         {
            j++;
            hiScorePlayers[j]=p[i];
         }
         else
         {
            // done
            break;
         }
      }
      int pay = pot/(j+1);
      for (i=0;i<j+1;i++)
      {
         hiScorePlayers[i].cash += pay;
         s = s + hiScorePlayers[i].name + ", ";
      }
      return s.substring(0,s.length()-2);
   }
}
