/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import javax.swing.JComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Decke
/*    */   extends JComponent
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected void paintComponent(Graphics g)
/*    */   {
/* 15 */     setOpaque(false);
/* 16 */     setVisible(true);
/*    */     
/* 18 */     super.paintComponent(g);
/* 19 */     Graphics2D g2 = (Graphics2D)g;
/* 20 */     g2.drawRect(10, 10, 150, 150);
/* 21 */     g2.fillRect(50, 50, 100, 100);
/* 22 */     g2.setColor(new Color(255, 0, 0, 120));
/* 23 */     g2.fillRect(10, 10, 150, 150);
/*    */   }
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/Decke.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */