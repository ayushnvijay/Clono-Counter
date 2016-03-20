/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.MediaTracker;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.AffineTransformOp;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Bild
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Image img;
/*     */   public int limit;
/*     */   public int counter;
/*     */   private BufferedImage bi;
/*     */   String Datei;
/*     */   public Graphics2D G;
/*     */   public AffineTransform at;
/*     */   private Point offset;
/*     */   private Rectangle plotArea;
/*     */   private Raster originalRaster;
/*     */   
/*     */   public Bild(String S)
/*     */   {
/*  35 */     this.img = getToolkit().getImage(S);
/*  36 */     MediaTracker mt = new MediaTracker(this);
/*  37 */     mt.addImage(this.img, 0);
/*     */     try
/*     */     {
/*  40 */       mt.waitForAll();
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {}
/*     */     
/*  44 */     this.bi = new BufferedImage(this.img.getWidth(null), 
/*  45 */       this.img.getHeight(null), 2);
/*  46 */     this.G = this.bi.createGraphics();
/*  47 */     this.G.drawImage(this.img, 0, 0, null);
/*  48 */     this.G.dispose();
/*  49 */     setPreferredSize(new Dimension(getW(), getH()));
/*  50 */     this.offset = new Point();
/*  51 */     this.plotArea = new Rectangle();
/*  52 */     this.originalRaster = this.bi.getData();
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  56 */     super.paintComponent(g);
/*  57 */     Graphics2D g2 = (Graphics2D)g;
/*  58 */     int x = getWidth() > this.bi.getWidth() ? 
/*  59 */       (getWidth() - this.bi.getWidth()) / 2 : 0;
/*  60 */     int y = getHeight() > this.bi.getHeight() ? 
/*  61 */       (getHeight() - this.bi.getHeight()) / 2 : 0;
/*  62 */     this.offset.setLocation(x, y);
/*  63 */     this.plotArea.setBounds(this.offset.x, this.offset.y, this.bi.getWidth(), this.bi.getHeight());
/*  64 */     if (this.img != null)
/*  65 */       g2.drawImage(this.bi, x, y, this);
/*     */   }
/*     */   
/*     */   public int getW() {
/*  69 */     return this.bi.getWidth();
/*     */   }
/*     */   
/*  72 */   public int getH() { return this.bi.getHeight(); }
/*     */   
/*     */   public Image getImage() {
/*  75 */     return this.img;
/*     */   }
/*     */   
/*  78 */   public Rectangle getPlotArea() { return this.plotArea; }
/*     */   
/*     */   public Raster getRdRaster() {
/*  81 */     return this.originalRaster;
/*     */   }
/*     */   
/*  84 */   public WritableRaster getWtRaster() { return this.bi.getRaster(); }
/*     */   
/*     */   public Point getOffset() {
/*  87 */     return this.offset;
/*     */   }
/*     */   
/*  90 */   public BufferedImage getBI() { return this.bi; }
/*     */   
/*     */   public void scaleBI(double sf) {
/*  93 */     this.G = this.bi.createGraphics();
/*  94 */     this.G.drawImage(this.img, 0, 0, null);
/*  95 */     this.G.dispose();
/*  96 */     BufferedImage nbi = new BufferedImage(
/*  97 */       (int)(this.bi.getWidth() * sf), 
/*  98 */       (int)(this.bi.getHeight() * sf), 
/*  99 */       this.bi.getType());
/* 100 */     AffineTransform transform = 
/* 101 */       AffineTransform.getScaleInstance(sf, sf);
/* 102 */     AffineTransformOp op = new AffineTransformOp(
/* 103 */       transform, 
/* 104 */       1);
/* 105 */     op.filter(this.bi, nbi);
/* 106 */     this.bi = nbi;
/* 107 */     this.originalRaster = this.bi.getData();
/* 108 */     repaint();
/*     */   }
/*     */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/Bild.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */