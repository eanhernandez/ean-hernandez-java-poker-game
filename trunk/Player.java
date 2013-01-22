class Player implements Comparable <Player>
{
   public Hand hand;
   public String name;
   public int cash;
   public boolean fold = false;

   Player(String name)
   {
      this.name = name;
      cash = 6;
   }
   public int placeBet()
   {
      if (hand.handValue<12)
         {return 0;}
      else
         {return 1;}
   }
   public int howManyMoreCardsToDraw()
   {
      if (hand.handValue>99)
      {return 0;}
      else
      {return 3;}
   }

   void showHand()
   {
      for (int i=0;i<hand.cards.length;i++)
      {
         if (hand.cards[i]==null){System.out.println("found null");}
         System.out.print(hand.cards[i].getNumber() + "" + hand.cards[i].getSuit() + " ");
      }
      System.out.println("");
   }
   public int compareTo(Player p)
   {
      if (p==null){return -1;}
      if (this.hand.handValue > p.hand.handValue)
      {
         return 1;
      }
      else if (this.hand.handValue > p.hand.handValue)
      {
         return 0;
      }
      else
      {
         return -1;
      }
   }
}
