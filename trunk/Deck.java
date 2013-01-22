import java.util.Random;
class Deck implements Comparable <Deck>, Cloneable 
{
   Card[] c = new Card[52];

   public Deck()
   
   {
      int k=0;
      for (int i=1;i<=4;i++)
      {
         for (int j=2;j<=14;j++)
         {
            c[k]=new Card(j,i);  
            k++;
         }
      }
   }
   
   public Card[] deal(int NumberOfCards)
   {
      Card[] out = new Card[NumberOfCards];
      
      int i=0;
      while (c[i] == null)
      {
         i++;
      }
      for (int j=0;j<NumberOfCards;j++)
      {
         out[j] = c[i+j];
         c[i+j] = null;         
      }
      return out;
   }
   public String toString()
   {
      int j=1;
      String s = "";
      for (int i=0;i<52;i++)
      {
         if (c[i]!=null)
         {
           s = s + doPad(c[i].getNumber());
           s = s + c[i].getSuit()+ " ";

           if (j==13){s = s + "\n";j=0;}
           j++;
         }
      }
      s = s + "\n";
      return s;
   }
   String doPad(int n)
   {
      if (n < 10){return " " + Integer.toString(n);}
      else {return Integer.toString(n);}
   }

   //citation: http://leepoint.net/notes-java/algorithms/random/random-shuffling.html
   public void shuffle()
   {
      Random rgen = new Random();
      for (int i=0; i<c.length; i++) 
      {
          int r = rgen.nextInt(c.length);
          Card temp = c[i];
          c[i] = c[r];
          c[r] = temp;
      }
   
   }
   public void sort(){}

   public int compareTo(Deck d)
   {   
     if (d==null){return -1;}
     if (this.c.length > c.length)
     {   
       return 1;
     }   
     else if (this.c.length == c.length)
     {   
       return 0;
     }   
     else
     {   
       return -1; 
     }   
   }
   public Object clone()
   {
      try { return super.clone(); } 
      catch (CloneNotSupportedException e) 
      { System.out.println("cloning disallowed."); return this; }
   }
}

