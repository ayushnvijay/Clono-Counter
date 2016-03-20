/*    */ import java.awt.Color;
/*    */ import java.awt.Container;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ public class Outwin extends JFrame
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public Outwin(Kopie kopie)
/*    */   {
/* 11 */     setTitle("KoBi Zähler");
/* 12 */     setSize(400, 400);
/* 13 */     Container contentPane = getContentPane();
/* 14 */     contentPane.add(kopie, "Center");
/* 15 */     setBackground(new Color(255, 255, 255));
/* 16 */     setDefaultCloseOperation(3);
/* 17 */     setVisible(true);
/*    */   }
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/Outwin.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */