/*    */ import java.util.Comparator;
/*    */ 
/*  1 */ final class Bezirk$1 implements Comparator { Bezirk$1(Bezirk paramBezirk) { this.this$0 = paramBezirk; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   final Bezirk this$0;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int compare(Object a, Object b)
/*    */   {
/* 31 */     int def = ((Punkt)a).getX() - ((Punkt)b).getX();
/* 32 */     if (def < 0) return -1;
/* 33 */     if (def > 0) return 1;
/* 34 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/Bezirk$1.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */