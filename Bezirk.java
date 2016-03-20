/*    */ import java.awt.image.WritableRaster;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Collections;
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class Bezirk
/*    */ {
/*  8 */   private final int[] gruen = { 0, 255, 0255 };
/*    */   
/*    */ 
/* 11 */   private Vector B = new Vector();
/*    */   
/*    */   public void addPunkt(int y, int x, int F) {
/* 14 */     Punkt pkt = new Punkt(y, x, F);
/* 15 */     this.B.add(pkt);
/*    */   }
/*    */   
/* 18 */   public void destroy() { this.B.clear(); }
/*    */   
/*    */   public void print() {
/* 21 */     System.out.println("Size: " + this.B.size());
/* 22 */     for (int i = 0; i < this.B.size(); i++) {
/* 23 */       Punkt p = (Punkt)this.B.get(i);
/* 24 */       System.out.println(p.toString());
/*    */     }
/*    */   }
/*    */   
/* 28 */   public void sortX() { Collections.sort(this.B, 
/* 29 */       new java.util.Comparator() {
/*    */         public int compare(Object a, Object b) {
/* 31 */           int def = ((Punkt)a).getX() - ((Punkt)b).getX();
/* 32 */           if (def < 0) return -1;
/* 33 */           if (def > 0) return 1;
/* 34 */           return 0;
/*    */         }
/*    */       });
/*    */   }
/*    */   
/*    */   public void chgColor(WritableRaster raster)
/*    */   {
/* 41 */     for (int i = 0; i < this.B.size(); i++) {
/* 42 */       Punkt p = (Punkt)this.B.get(i);
/* 43 */       raster.setPixel(p.getX(), p.getY(), this.gruen);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/Bezirk.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */