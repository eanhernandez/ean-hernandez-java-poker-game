class Card implements Comparable <Card>
{
   public int CardNumber;
   public int CardSuit;
   public Card(int CardNumber, int CardSuit)
   {
      this.CardNumber = CardNumber;
      this.CardSuit = CardSuit;
   }

   public char getSuit()
   {
         switch (this.CardSuit)
         {
            case 1: return 'S';
            case 2: return 'H';
            case 3: return 'C';
            case 4: return 'D';
         }
   return 'e'; //error case, shouldn't reach here
   }
   public int getNumber()
   {
      return CardNumber;
   }
   public int compareTo(Card c)
   {
      if (this.CardNumber > c.CardNumber)
      {
         return 1;
      }
      else if (this.CardNumber == c.CardNumber)
      {
         return 0;
      }
      else
      {
         return -1;
      }
   }
   public String toString()
   {
      return Integer.toString(CardNumber) + getSuit();
   }
}
