/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class Kopie extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Image img;
/*     */   private int W;
/*     */   private int H;
/*     */   public int limit;
/*     */   private int[][] matrix;
/*     */   public int counter;
/*     */   private int[][] kolonie;
/*     */   public BufferedImage bi;
/*     */   private int minpixel;
/*     */   private int anzahl;
/*     */   private int rotpixel;
/*  25 */   private final int[] weiss = { 255, 255, 255, 255 };
/*  26 */   private final int[] rot = { 255, 00255 };
/*     */   
/*     */   private WritableRaster raster;
/*     */   
/*     */   String Datei;
/*     */   
/*     */   private Bezirk bzk;
/*     */   
/*     */   private int[] farbe;
/*     */   
/*     */   private boolean loch;
/*     */   
/*     */   private boolean firstCount;
/*     */   
/*     */   private int grauBreite;
/*     */   
/*     */   public AffineTransform at;
/*     */   
/*     */   private Raster copyRaster;
/*     */   
/*     */ 
/*     */   public Kopie()
/*     */   {
/*  49 */     this.H = 300;
/*  50 */     this.W = 300;
/*  51 */     this.kolonie = new int[26][3];
/*  52 */     this.limit = 70;
/*  53 */     this.counter = 0;
/*  54 */     this.rotpixel = 0;
/*  55 */     this.minpixel = 0;
/*  56 */     this.farbe = new int['Ā'];
/*  57 */     this.bzk = new Bezirk();
/*  58 */     this.firstCount = true;
/*  59 */     this.grauBreite = 10;
/*  60 */     setPreferredSize(new java.awt.Dimension(400, 400));
/*  61 */     repaint();
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  65 */     super.paintComponent(g);
/*  66 */     Graphics2D g2 = (Graphics2D)g;
/*  67 */     g2.drawImage(this.bi, (getWidth() - this.W) / 2, (getHeight() - this.H) / 2, this);
/*     */   }
/*     */   
/*     */ 
/*     */   public int zaehler(boolean first)
/*     */   {
/*  73 */     this.counter = 0;
/*  74 */     this.matrix = new int[this.H][this.W];
/*  75 */     this.raster = this.bi.getRaster();
/*  76 */     if (first) this.copyRaster = this.bi.getData();
/*  77 */     findLimit();
/*  78 */     int[] rgb = new int[4];
/*  79 */     for (int y = 0; y < this.H; y++) {
/*  80 */       for (int x = 0; x < this.W; x++) {
/*  81 */         this.raster.getPixel(x, y, rgb);
/*  82 */         if (rgb[0] < this.limit) {
/*  83 */           this.matrix[y][x] = rgb[0];
/*     */         }
/*     */         else {
/*  86 */           this.matrix[y][x] = 'ÿ';
/*  87 */           this.raster.setPixel(x, y, this.weiss);
/*     */         }
/*     */       }
/*     */     }
/*  91 */     System.out.println("Counter:  " + this.counter);
/*  92 */     for (int i = 0; i < this.limit; i++) {
/*  93 */       if (this.farbe[i] != 0) {
/*  94 */         for (int y = 0; y < this.H; y++) {
/*  95 */           for (int x = 0; x < this.W; x++) {
/*  96 */             if (this.matrix[y][x] == i) {
/*  97 */               this.anzahl = 0;
/*  98 */               this.loch = false;
/*  99 */               haufen(y, x, i);
/* 100 */               this.bzk.sortX();
/* 101 */               if (!this.loch) {
/* 102 */                 this.bzk.chgColor(this.raster);
/* 103 */                 this.counter += 1;
/* 104 */                 System.out.println("C: " + i + "  " + this.counter);
/*     */               }
/* 106 */               if ((this.anzahl < this.minpixel) && (!this.loch)) {
/* 107 */                 changeColor(this.rot);
/* 108 */                 this.rotpixel += 1;
/*     */               }
/* 110 */               this.bzk.destroy();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 117 */     System.out.println("Counter:  " + this.counter);
/* 118 */     repaint();
/* 119 */     return this.counter;
/*     */   }
/*     */   
/*     */   public void haufen(int y, int x, int f)
/*     */   {
/* 124 */     if (this.matrix[y][x] == 300 + f) return;
/* 125 */     if ((this.matrix[y][x] < 300) && ((this.matrix[y][x] > f + this.grauBreite) || (this.matrix[y][x] > this.limit))) return;
/* 126 */     if ((this.matrix[y][x] >= 300) && (this.matrix[y][x] - 300 <= f)) this.loch = true;
/* 127 */     this.bzk.addPunkt(y, x, this.matrix[y][x]);
/* 128 */     this.matrix[y][x] = (f + 300);
/* 129 */     addPixel(y, x, this.matrix[y][x]);
/* 130 */     if (this.loch) return;
/* 131 */     if (x < this.W - 1) {
/* 132 */       haufen(y, x + 1, f);
/* 133 */       if (y < this.H - 1) haufen(y + 1, x + 1, f);
/* 134 */       if (y > 0) haufen(y - 1, x + 1, f);
/*     */     }
/* 136 */     if (x > 0) {
/* 137 */       haufen(y, x - 1, f);
/* 138 */       if (y < this.H - 1) haufen(y + 1, x - 1, f);
/* 139 */       if (y > 0) haufen(y - 1, x - 1, f);
/*     */     }
/* 141 */     if (y < this.H - 1) haufen(y + 1, x, f);
/* 142 */     if (y > 0) haufen(y - 1, x, f);
/*     */   }
/*     */   
/* 145 */   private void addPixel(int y, int x, int n) { if (this.anzahl < this.minpixel) {
/* 146 */       this.kolonie[this.anzahl][0] = y;
/* 147 */       this.kolonie[this.anzahl][1] = x;
/* 148 */       this.anzahl += 1;
/*     */     }
/*     */   }
/*     */   
/* 152 */   private void changeColor(int[] f) { for (int i = 0; i < this.anzahl; i++)
/* 153 */       this.raster.setPixel(this.kolonie[i][1], this.kolonie[i][0], f);
/*     */   }
/*     */   
/*     */   public int recount(int n) {
/* 157 */     this.counter = 0;
/* 158 */     this.rotpixel = 0;
/* 159 */     this.firstCount = false;
/* 160 */     this.bi.setData(this.copyRaster);
/* 161 */     this.limit = n;
/* 162 */     return zaehler(false);
/*     */   }
/*     */   
/* 165 */   public void findLimit() { int[] rgb = new int[4];
/* 166 */     for (int y = 0; y < this.H; y++) {
/* 167 */       for (int x = 0; x < this.W; x++) {
/* 168 */         this.raster.getPixel(x, y, rgb);
/* 169 */         this.farbe[rgb[0]] += 1;
/*     */       }
/*     */     }
/* 172 */     if (this.firstCount) {
/* 173 */       int max = 0;
/* 174 */       int next = 0;
/* 175 */       System.out.println("Limit: " + this.limit);
/* 176 */       for (int i = 0; i < this.farbe.length; i++) {
/* 177 */         System.out.println(i + ": " + this.farbe[i]);
/* 178 */         if (this.farbe[i] > max) {
/* 179 */           next = max;
/* 180 */           max = i;
/*     */         }
/*     */       }
/* 183 */       this.limit = next;
/* 184 */       System.out.println("Limit: " + this.limit);
/*     */     }
/*     */   }
/*     */   
/* 188 */   public int getRotpixel() { return this.rotpixel; }
/*     */   
/*     */   public void setGrauBreite(int grauBreite) {
/* 191 */     this.grauBreite = grauBreite;
/*     */   }
/*     */   
/* 194 */   public int getGrauBreite() { return this.grauBreite; }
/*     */   
/*     */   public int getW() {
/* 197 */     return this.W;
/*     */   }
/*     */   
/* 200 */   public int getH() { return this.H; }
/*     */   
/*     */   public int setW(int W) {
/* 203 */     return this.W = W;
/*     */   }
/*     */   
/* 206 */   public int setH(int H) { return this.H = H; }
/*     */   
/*     */   public void setMinpixel(int minpixel) {
/* 209 */     this.minpixel = minpixel;
/*     */   }
/*     */   
/* 212 */   public int getMinpixel() { return this.minpixel; }
/*     */   
/*     */   public int getLimit() {
/* 215 */     return this.limit;
/*     */   }
/*     */   
/* 218 */   public Image getImage() { return this.img; }
/*     */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/Kopie.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */