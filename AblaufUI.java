/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class AblaufUI extends JPanel implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Kopie kopie;
/*     */   private IntTextField gesamt;
/*     */   private IntTextField minpixel;
/*     */   private IntTextField limit;
/*     */   private IntTextField def;
/*     */   private IntTextField rot;
/*     */   private IntTextField grau;
/*     */   private int d;
/*     */   private JPanel pL;
/*     */   private JPanel pR;
/*     */   private Bild bild;
/*     */   private JButton recount;
/*     */   private JButton schliessen;
/*     */   private JButton zaehler;
/*     */   
/*     */   public AblaufUI(Kopie kopie, Bild bild)
/*     */   {
/*  36 */     this.bild = bild;
/*  37 */     this.kopie = kopie;
/*  38 */     this.pL = new JPanel();
/*  39 */     this.pR = new JPanel();
/*  40 */     this.pR.setLayout(new GridLayout(2, 6));
/*     */     
/*     */ 
/*     */ 
/*  44 */     this.pL.setLayout(new FlowLayout());
/*     */     
/*  46 */     setLayout(new BoxLayout(this, 0));
/*  47 */     this.recount = new JButton("Recount");
/*  48 */     this.schliessen = new JButton("Close");
/*  49 */     this.zaehler = new JButton("Count");
/*  50 */     this.recount.setEnabled(false);
/*  51 */     this.pL.add(this.recount);
/*  52 */     this.pL.add(this.schliessen);
/*  53 */     this.pL.add(this.zaehler);
/*     */     
/*  55 */     this.pL.setBorder(BorderFactory.createLineBorder(Color.black));
/*  56 */     this.pR.setBorder(BorderFactory.createLineBorder(Color.black));
/*  57 */     this.pR.add(new JLabel("Total"));
/*  58 */     this.pR.add(new JLabel("Valid"));
/*  59 */     this.pR.add(new JLabel("Too small"));
/*  60 */     this.pR.add(new JLabel("Threshold"));
/*  61 */     this.pR.add(new JLabel("Minimum"));
/*  62 */     this.pR.add(new JLabel("Gray width"));
/*  63 */     this.gesamt = feld(false);
/*  64 */     this.def = feld(false);
/*  65 */     this.rot = feld(false);
/*  66 */     this.limit = feld(true);
/*  67 */     this.minpixel = feld(true);
/*  68 */     this.grau = feld(true);
/*  69 */     add(Box.createRigidArea(new Dimension(5, 0)));
/*  70 */     add(this.pL);
/*  71 */     add(Box.createRigidArea(new Dimension(5, 0)));
/*  72 */     add(this.pR);
/*  73 */     add(Box.createRigidArea(new Dimension(5, 0)));
/*  74 */     this.recount.addActionListener(this);
/*  75 */     this.schliessen.addActionListener(this);
/*  76 */     this.zaehler.addActionListener(this);
/*  77 */     this.limit.addActionListener(this);
/*  78 */     setBorder(BorderFactory.createLineBorder(Color.black));
/*  79 */     this.pL.setMaximumSize(new Dimension(400, 40));
/*  80 */     this.pL.setPreferredSize(new Dimension(300, 40));
/*  81 */     this.pL.setMinimumSize(new Dimension(300, 40));
/*  82 */     this.pR.setMaximumSize(new Dimension(400, 40));
/*  83 */     this.pR.setPreferredSize(new Dimension(400, 40));
/*  84 */     this.pR.setMinimumSize(new Dimension(300, 40));
/*  85 */     this.zaehler.setEnabled(false);
/*  86 */     this.recount.setEnabled(false);
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent evt) {
/*  90 */     String cmd = evt.getActionCommand();
/*  91 */     Object obj = evt.getSource();
/*     */     
/*  93 */     if (!(obj instanceof JTextField))
/*     */     {
/*  95 */       if ((obj instanceof JButton)) {
/*  96 */         if (cmd.equals("Recount")) {
/*  97 */           this.d = this.minpixel.getValue();
/*  98 */           this.kopie.setMinpixel(this.d);
/*  99 */           this.d = this.grau.getValue();
/* 100 */           this.kopie.setGrauBreite(this.d);
/* 101 */           this.d = this.kopie.recount(this.limit.getValue());
/* 102 */           this.gesamt.setValue(this.d);
/* 103 */           this.rot.setValue(this.kopie.getRotpixel());
/* 104 */           this.d -= this.kopie.getRotpixel();
/* 105 */           this.def.setValue(this.d);
/* 106 */           this.bild.requestFocusInWindow();
/*     */         }
/* 108 */         else if (cmd.equals("Close")) {
/* 109 */           System.exit(0);
/*     */         }
/* 111 */         else if (cmd.equals("Count")) {
/* 112 */           if (this.kopie == null) return;
/* 113 */           this.d = this.kopie.zaehler(true);
/* 114 */           this.gesamt.setValue(this.d);
/* 115 */           this.rot.setValue(this.kopie.getRotpixel());
/* 116 */           this.d -= this.kopie.getRotpixel();
/* 117 */           this.def.setValue(this.d);
/* 118 */           this.limit.setValue(this.kopie.getLimit());
/* 119 */           this.minpixel.setValue(this.kopie.getMinpixel());
/* 120 */           this.grau.setValue(this.kopie.getGrauBreite());
/* 121 */           this.zaehler.setEnabled(false);
/* 122 */           this.recount.setEnabled(true);
/* 123 */           this.bild.requestFocusInWindow();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private IntTextField feld(boolean E) {
/* 130 */     IntTextField f = new IntTextField(E, 5);
/* 131 */     f.setMargin(new Insets(2, 5, 2, 5));
/* 132 */     f.setEditable(E);
/* 133 */     this.pR.add(f);
/* 134 */     return f;
/*     */   }
/*     */   
/* 137 */   public void countEnabled(boolean b) { this.zaehler.setEnabled(b);
/* 138 */     this.recount.setEnabled(false);
/*     */   }
/*     */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/AblaufUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */