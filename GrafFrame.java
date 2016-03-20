/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GrafFrame
/*     */   extends JFrame
/*     */   implements KeyListener, MouseListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  33 */   private Point start = new Point();
/*     */   private Point offset;
/*     */   private Bild original;
/*     */   private Kopie neubild;
/*     */   private Ellipse2D.Double sp;
/*     */   private Graphics2D bg2;
/*     */   private BufferedImage bi;
/*     */   private Raster ras;
/*     */   private WritableRaster raster;
/*  42 */   private JScrollPane jsp; private final int[] weiss = { 255, 255, 255, 255 };
/*  43 */   boolean first = true;
/*     */   private double wK;
/*     */   private double hK;
/*  46 */   private AblaufUI p; private int[] rgb = new int[4];
/*     */   
/*     */   private Schalter schalter;
/*     */   
/*     */   public GrafFrame()
/*     */   {
/*  52 */     setTitle("Clono-Counter");
/*  53 */     Container contentPane = getContentPane();
/*  54 */     JPanel pCtr = new JPanel();
/*  55 */     JPanel pRe = new JPanel();
/*  56 */     pRe.setPreferredSize(new Dimension(300, 300));
/*  57 */     pCtr.setLayout(new BorderLayout());
/*  58 */     String s = datei();
/*  59 */     this.original = new Bild(s);
/*  60 */     this.offset = this.original.getOffset();
/*  61 */     this.bi = this.original.getBI();
/*  62 */     this.raster = this.original.getWtRaster();
/*  63 */     this.ras = this.original.getRdRaster();
/*  64 */     this.neubild = new Kopie();
/*  65 */     setSize(800, 500);
/*  66 */     this.jsp = new JScrollPane(this.original);
/*     */     
/*     */ 
/*     */ 
/*  70 */     pCtr.add(this.jsp);
/*  71 */     this.p = new AblaufUI(this.neubild, this.original);
/*     */     
/*  73 */     this.schalter = new Schalter(this);
/*  74 */     contentPane.add(this.schalter, "West");
/*  75 */     pCtr.setOpaque(true);
/*  76 */     JSplitPane innerPane = new JSplitPane(1, 
/*  77 */       pCtr, this.neubild);
/*     */     
/*  79 */     innerPane.setDividerLocation(400);
/*     */     
/*  81 */     JSplitPane auterPane = new JSplitPane(0, 
/*  82 */       innerPane, this.p);
/*  83 */     auterPane.setDividerLocation(400);
/*  84 */     contentPane.add(auterPane, "Center");
/*     */     
/*     */ 
/*  87 */     this.original.addMouseListener(this);
/*  88 */     this.original.addKeyListener(this);
/*  89 */     this.original.setFocusable(true);
/*     */   }
/*     */   
/*     */   public String datei()
/*     */   {
/*  94 */     JFileChooser chooser = new JFileChooser();
/*  95 */     chooser.setCurrentDirectory(new File("."));
/*  96 */     chooser.setFileSelectionMode(2);
/*     */     
/*     */ 
/*  99 */     BildFilter filter = new BildFilter();
/* 100 */     chooser.setFileFilter(filter);
/* 101 */     int returnVal = chooser.showOpenDialog(null);
/* 102 */     if (returnVal == 0) {
/* 103 */       System.out.println("You chose to open this file: " + 
/* 104 */         chooser.getSelectedFile().getName());
/* 105 */       System.out.println(chooser.getSelectedFile().getPath());
/* 106 */       return chooser.getSelectedFile().toString();
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */   
/* 111 */   public void kopiere() { Rectangle rec = this.sp.getBounds();
/* 112 */     int W = rec.width;
/* 113 */     int H = rec.height;
/* 114 */     int x0 = this.start.x;
/* 115 */     int y0 = this.start.y;
/* 116 */     boolean put = false;
/* 117 */     BufferedImage nBi = new BufferedImage(W, H, 2);
/* 118 */     this.neubild.bi = nBi;
/* 119 */     WritableRaster rout = nBi.getRaster();
/* 120 */     for (int h = 0; h < H; h++) {
/* 121 */       for (int w = 0; w < W; w++) {
/* 122 */         rout.setPixel(w, h, this.weiss);
/*     */       }
/*     */     }
/* 125 */     int i = 0;int k = 0;
/* 126 */     for (int x = x0; x < x0 + W; x++) {
/* 127 */       int j = 0;
/* 128 */       for (int y = y0; y < y0 + H; y++) {
/* 129 */         this.ras.getPixel(x, y, this.rgb);
/* 130 */         put = this.sp.contains(x, y);
/* 131 */         if (put) {
/* 132 */           rout.setPixel(x - x0, y - y0, this.rgb);
/* 133 */           j++;
/* 134 */           k++;
/* 135 */           if (j == H) break;
/*     */         }
/*     */       }
/* 138 */       if (k > 0) {
/* 139 */         i++;
/* 140 */         k = 0;
/* 141 */         if (i == W) break;
/*     */       }
/*     */     }
/* 144 */     this.neubild.setH(H);
/* 145 */     this.neubild.setW(W);
/* 146 */     this.neubild.repaint();
/* 147 */     biz();
/* 148 */     this.bg2.draw(this.sp);
/* 149 */     this.original.repaint();
/* 150 */     this.p.countEnabled(true);
/*     */   }
/*     */   
/* 153 */   public void biz() { int x0 = (int)this.sp.getMinX();
/* 154 */     int xe = (int)this.sp.getMaxX();
/* 155 */     int y0 = this.sp.getBounds().y;
/* 156 */     int ye = y0 + this.sp.getBounds().height;
/* 157 */     for (int y = y0; y <= ye; y++) {
/* 158 */       for (int x = x0; x <= xe; x++) {
/* 159 */         this.ras.getPixel(x, y, this.rgb);
/* 160 */         this.raster.setPixel(x, y, this.rgb);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void skaliere()
/*     */   {
/* 168 */     this.original.scaleBI(1.2D);
/* 169 */     this.bi = this.original.getBI();
/* 170 */     this.raster = this.original.getWtRaster();
/* 171 */     this.ras = this.original.getRdRaster();
/* 172 */     this.original.setPreferredSize(new Dimension(this.bi.getWidth(), this.bi.getHeight()));
/* 173 */     setSize(getSize());
/* 174 */     repaint();
/* 175 */     this.offset = this.original.getOffset();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void groesser()
/*     */   {
/* 182 */     if (((this.start.getX() + this.wK + 1.0D > this.original.getW() ? 1 : 0) | (this.start.getX() < 1.0D ? 1 : 0) | (this.start.getY() + this.hK + 1.0D > this.original.getH() ? 1 : 0) | (this.start.getY() < 1.0D ? 1 : 0)) != 0) return;
/* 183 */     biz();
/* 184 */     this.wK += 2.0D;
/* 185 */     this.hK += 2.0D;
/* 186 */     this.start.setLocation(this.start.x - 1, this.start.y - 1);
/* 187 */     this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/* 188 */     this.bg2.draw(this.sp);
/* 189 */     this.original.repaint();
/*     */   }
/*     */   
/*     */   public void kleiner() {
/* 193 */     biz();
/* 194 */     this.wK -= 2.0D;
/* 195 */     this.hK -= 2.0D;
/* 196 */     this.start.setLocation(this.start.x + 1, this.start.y + 1);
/* 197 */     this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/* 198 */     this.bg2.draw(this.sp);
/* 199 */     this.original.repaint();
/*     */   }
/*     */   
/* 202 */   public void getFocus() { this.original.requestFocusInWindow(); }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 208 */     this.start.setLocation(e.getX(), e.getY());
/* 209 */     this.original.requestFocusInWindow();
/*     */   }
/*     */   
/* 212 */   public void mouseReleased(MouseEvent e) { int h = e.getY() - this.start.y;
/* 213 */     int w = e.getX() - this.start.x;
/* 214 */     int a = h > w ? h / 2 : w / 2;
/* 215 */     int xm = w / 2 + this.start.x - this.offset.x;
/* 216 */     int ym = h / 2 + this.start.y - this.offset.y;
/* 217 */     this.start.setLocation(xm - a, ym - a);
/* 218 */     this.wK = (this.hK = 2 * a);
/* 219 */     this.sp = new Ellipse2D.Double(this.start.getX(), this.start.getY(), this.wK, this.hK);
/* 220 */     this.bi.setData(this.ras);
/* 221 */     this.bg2 = this.bi.createGraphics();
/* 222 */     this.bg2.setColor(new Color(255, 0, 0));
/* 223 */     this.bg2.draw(this.sp);
/* 224 */     this.jsp.setEnabled(false);
/* 225 */     this.original.repaint();
/* 226 */     this.schalter.buttonEnabled(true);
/* 227 */     this.p.countEnabled(false);
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void keyPressed(KeyEvent e) {
/* 235 */     if (this.sp == null) return;
/* 236 */     int kC = e.getKeyCode();
/*     */     
/* 238 */     if (kC == 39) {
/* 239 */       biz();
/* 240 */       if (e.isShiftDown()) {
/* 241 */         if (this.start.getX() + this.wK + 1.0D > this.original.getW() - 1) return;
/* 242 */         this.wK += 1.0D;
/* 243 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/* 245 */       else if (e.isControlDown()) {
/* 246 */         this.wK -= 1.0D;
/* 247 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/*     */       else {
/* 250 */         if (this.start.x + this.wK + 1.0D > this.original.getW() - 1) return;
/* 251 */         this.start.setLocation(this.start.x + 1, this.start.y);
/* 252 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/*     */       
/*     */     }
/* 256 */     else if (kC == 37) {
/* 257 */       biz();
/* 258 */       if (e.isShiftDown()) {
/* 259 */         if (this.start.getX() == 0.0D) return;
/* 260 */         this.start.setLocation(this.start.x - 1, this.start.y);
/* 261 */         this.wK += 1.0D;
/* 262 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/* 264 */       else if (e.isControlDown()) {
/* 265 */         this.start.setLocation(this.start.x + 1, this.start.y);
/* 266 */         this.wK -= 1.0D;
/* 267 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/*     */       else {
/* 270 */         if (this.start.getX() == 0.0D) return;
/* 271 */         this.start.setLocation(this.start.x - 1, this.start.y);
/* 272 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/*     */       
/*     */     }
/* 276 */     else if (kC == 38) {
/* 277 */       biz();
/* 278 */       if (e.isShiftDown()) {
/* 279 */         if (this.start.getY() == 0.0D) return;
/* 280 */         this.start.setLocation(this.start.x, this.start.y - 1);
/* 281 */         this.hK += 1.0D;
/* 282 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/* 284 */       else if (e.isControlDown()) {
/* 285 */         this.start.setLocation(this.start.x, this.start.y + 1);
/* 286 */         this.hK -= 1.0D;
/* 287 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/*     */       else {
/* 290 */         if (this.start.getY() == 0.0D) return;
/* 291 */         this.start.setLocation(this.start.x, this.start.y - 1);
/* 292 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/*     */       
/*     */     }
/* 296 */     else if (kC == 40) {
/* 297 */       biz();
/* 298 */       if (e.isShiftDown()) {
/* 299 */         if (this.start.getY() + this.hK + 1.0D > this.original.getH() - 1) return;
/* 300 */         this.hK += 1.0D;
/* 301 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/* 303 */       else if (e.isControlDown()) {
/* 304 */         this.hK -= 1.0D;
/* 305 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/*     */       else {
/* 308 */         if (this.start.getY() + this.hK + 1.0D > this.original.getH() - 1) return;
/* 309 */         this.start.setLocation(this.start.x, this.start.y + 1);
/* 310 */         this.sp.setFrame(this.start.getX(), this.start.getY(), this.wK, this.hK);
/*     */       }
/*     */     }
/*     */     else {
/* 314 */       if (kC == 65)
/*     */       {
/* 316 */         kopiere();
/* 317 */         return;
/*     */       }
/*     */       
/* 320 */       if (kC == 33) {
/* 321 */         skaliere();
/* 322 */         return;
/*     */       }
/* 324 */       return; }
/* 325 */     this.bg2.draw(this.sp);
/* 326 */     this.original.repaint(); }
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */   
/* 330 */   public void keyTyped(KeyEvent e) { if (this.sp == null) return;
/* 331 */     char kC = e.getKeyChar();
/* 332 */     if (kC == '<') {
/* 333 */       kleiner();
/*     */     }
/* 335 */     else if (kC == '>') {
/* 336 */       groesser();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/GrafFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */