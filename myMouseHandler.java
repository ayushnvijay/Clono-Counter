/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Shape;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.geom.GeneralPath;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Point2D.Double;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class myMouseHandler implements java.awt.event.MouseListener
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 13 */   private Point2D start = new Point2D.Double();
/*    */   private Bild pL;
/*    */   private Shape sp;
/*    */   
/*    */   public myMouseHandler(Bild p)
/*    */   {
/* 19 */     this.pL = p;
/*    */   }
/*    */   
/*    */   public void mouseClicked(MouseEvent e) {}
/*    */   
/* 24 */   public void mousePressed(MouseEvent e) { this.start.setLocation(e.getX(), e.getY()); }
/*    */   
/*    */   public void mouseReleased(MouseEvent e) {
/* 27 */     double W = e.getX() - this.start.getX();
/* 28 */     double H = e.getY() - this.start.getY();
/* 29 */     this.sp = new java.awt.geom.Ellipse2D.Double(this.start.getX(), this.start.getY(), W, H);
/* 30 */     GeneralPath clipShape = new GeneralPath();
/* 31 */     clipShape.append(this.sp, false);
/* 32 */     System.out.println("X2: " + e.getX() + "  Y2: " + e.getY());
/* 33 */     Graphics2D g2 = (Graphics2D)this.pL.getGraphics();
/* 34 */     System.out.println(W + "   " + H);
/*    */     
/*    */ 
/*    */ 
/* 38 */     BufferedImage bi = new BufferedImage(this.pL.getW(), 
/* 39 */       this.pL.getH(), 2);
/* 40 */     Graphics2D bg2 = bi.createGraphics();
/* 41 */     bg2.draw(this.sp);
/* 42 */     g2.drawImage(bi, 0, 0, this.pL);
/*    */   }
/*    */   
/*    */   public void mouseEntered(MouseEvent e) {}
/*    */   
/*    */   public void mouseExited(MouseEvent e) {}
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/myMouseHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */