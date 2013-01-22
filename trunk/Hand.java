import java.util.Arrays;
class Hand
{
   public Card[] cards = new Card[5];
   public int handValue = 0;
   public String handName = "";
   Hand(Deck deck)
   {
      cards = deck.deal(5);
      Arrays.sort(cards);
      handValue=calculateHandValue(cards);
   }
   // for test hands
   Hand(int c0, int c1, int c2, int c3, int c4, int s0, int s1, int s2, int s3, int s4)
   {
      cards[0]=new Card(c0,s0);
      cards[1]=new Card(c1,s1);
      cards[2]=new Card(c2,s2);
      cards[3]=new Card(c3,s3);
      cards[4]=new Card(c4,s4);
      Arrays.sort(cards);
      handValue=calculateHandValue(cards);
   }

  void showHand()
   {
      for (int i=0;i<cards.length;i++)
      {
         if (cards[i]==null){System.out.println("found null");}
         System.out.print(cards[i].getNumber() + "" + cards[i].getSuit() + " ");
      }
         System.out.println(" ("+handName+")");
   }
  int calculateHandValue(Card[] card)
   {
         //number of a kind
         int ofAKind=0;
         int[][] m= new int[7][7];
         int a,b,c,x,i,j,k =0;
         for(i=0;i<=6;i++)
         {
            for (j=0;j<=6;j++){ m[i][j]=0; }
         }
         for(k=1;k<=5;k++)
         {
            i=1;
            for(j=k;j<=5;j++)
            {
                  a= m[i+1][j-1];
                  b= m[i][j-1];
                  c= m[i+1][j];
                  x = Math.max(a,b);
                  x = Math.max(x,c);
                  if (card[i-1].CardNumber == card[j-1].CardNumber)
                  {
                     m[i][j]=x+1;
                  }              
                  else
                  {
                     m[i][j]=x;
                  }
               i++;
            }
         }
         ofAKind = m[1][5];
      

      //find duplicates in the hand... the sum of the last column of
      //the generated matrix will sum to 13 if it's 2 of a kind and 3 of a
      //kind, and 9 if it's two pairs.  I'll call this a hand duplicates
      //sum.
      int [][] n = new int[5][6];
      int sumHandDups=0;
      for(i=0;i<5;i++){n[i][0]=0;}
      for(i=0;i<5;i++)
      {
         for(j=1;j<6;j++)
         {
            if (cards[i].CardNumber == cards[j-1].CardNumber)
            {
               n[i][j]=n[i][j-1]+1;
            }
            else
            {
               n[i][j]=n[i][j-1];
            }
         }
      }
      int maxcard=n[0][5];
      int maxcardId=0;
      for(i=0;i<5;i++)
      {
         sumHandDups+=n[i][5];
         if (n[i][5]>maxcard){maxcard=n[i][5];maxcardId=i;}
      }
      int valueOfDuplicateCard = cards[maxcardId].CardNumber;

      // check for flush and straight
      boolean flush = true;
      boolean straight = true;
      int suit=card[0].CardSuit;
      for (i=1;i<card.length;i++)
      {
         if (card[i].CardSuit!=suit){flush=false;}
         if (card[i].CardNumber-card[i-1].CardNumber!=1){straight=false;}
      }

      // straight flush
      if (straight && flush) {handName="straight flush";return 1000;}
      // 4 of a kind
      if (ofAKind == 4) {handName="four of a kind";return 900+valueOfDuplicateCard;}
      // full house logic here
      if (sumHandDups==13) {handName="full house";return 850;}
      // flush
      if (flush) {handName="flush"; return 800;}
      // straight
      if (straight) {handName="straight"; return 700;}
      // 3 of a kind
      if (ofAKind==3) {handName="three of a kind"; return 600+valueOfDuplicateCard;}
      // two pair logic here
      if (sumHandDups==9){handName="two pairs"; return 500+sumCards(card);}
      // 1 pair logic
      if (ofAKind==2) {handName="two of a kind"; return 400+valueOfDuplicateCard;}
      // high card 
      handName= "high card"; return card[4].CardNumber;

   }

   int sumCards(Card[] c)
   {
      int x=0;
      for(int i=0;i<c.length;i++)
      {
         x+= c[i].CardNumber;
      }
      return x;
   }

}
