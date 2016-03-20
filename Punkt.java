/*    */ public class Punkt {
/*    */   private int x;
/*    */   private int y;
/*    */   private int F;
/*    */   
/*  6 */   public Punkt(int y, int x, int F) { this.x = x;
/*  7 */     this.y = y;
/*  8 */     this.F = F;
/*    */   }
/*    */   
/* 11 */   public int getF() { return this.F; }
/*    */   
/*    */   public int getX() {
/* 14 */     return this.x;
/*    */   }
/*    */   
/* 17 */   public int getY() { return this.y; }
/*    */   
/*    */   public String toString() {
/* 20 */     return 
/* 21 */       this.x + "  " + this.y + "  " + this.F;
/*    */   }
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/Punkt.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */