/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JToolBar;
/*    */ 
/*    */ 
/*    */ public class Schalter
/*    */   extends JToolBar
/*    */   implements ActionListener
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 15 */   private Dimension dim = new Dimension(25, 25);
/*    */   private GrafFrame fr;
/*    */   private JButton lupe;
/*    */   
/*    */   public Schalter(GrafFrame fr)
/*    */   {
/* 21 */     this.fr = fr;
/* 22 */     setOrientation(1);
/* 23 */     this.lupe = taste("lupe.gif", "LUPE", "um 1,2 Mal vergrößern");
/* 24 */     add(this.lupe);
/* 25 */     this.kopie = taste("copy.gif", "COPY", "Auswahl kopieren");
/* 26 */     add(this.kopie);
/* 27 */     this.mehr = taste("gt.gif", "GREATER", "Auswahl um 1 px vergrößern");
/* 28 */     add(this.mehr);
/* 29 */     this.wenig = taste("ls.gif", "LESS", "Auswahl um 1 px verkleinern");
/* 30 */     add(this.wenig);
/* 31 */     buttonEnabled(false);
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 35 */     String cmd = e.getActionCommand();
/*    */     
/*    */ 
/* 38 */     if (cmd.equals("LUPE")) {
/* 39 */       System.out.println(cmd);
/* 40 */       this.fr.skaliere();
/*    */     }
/* 42 */     else if (cmd.equals("COPY"))
/*    */     {
/* 44 */       this.fr.kopiere();
/* 45 */     } else if (cmd.equals("GREATER")) {
/* 46 */       this.fr.groesser();
/*    */     }
/* 48 */     else if (cmd.equals("LESS")) {
/* 49 */       this.fr.kleiner();
/*    */     }
/*    */     
/* 52 */     this.fr.getFocus();
/*    */   }
/*    */   
/*    */   private JButton kopie;
/*    */   private JButton mehr;
/*    */   private JButton wenig;
/* 58 */   public JButton taste(String imageName, String actionCommand, String toolTipText) { imageName = "images/" + imageName;
/* 59 */     ImageIcon bild = new ImageIcon(imageName);
/* 60 */     JButton b = new JButton(bild);
/* 61 */     b.setActionCommand(actionCommand);
/* 62 */     b.setToolTipText(toolTipText);
/* 63 */     b.addActionListener(this);
/*    */     
/* 65 */     b.setMaximumSize(this.dim);
/* 66 */     b.setPreferredSize(this.dim);
/* 67 */     return b;
/*    */   }
/*    */   
/*    */   public void buttonEnabled(boolean b) {
/* 71 */     this.kopie.setEnabled(b);
/* 72 */     this.mehr.setEnabled(b);
/* 73 */     this.wenig.setEnabled(b);
/*    */   }
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/Schalter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */