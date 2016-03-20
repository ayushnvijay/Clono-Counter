/*    */ import java.io.File;
/*    */ import javax.swing.filechooser.FileFilter;
/*    */ 
/*    */ public class BildFilter extends FileFilter
/*    */ {
/*    */   public boolean accept(File f)
/*    */   {
/*  8 */     if (f.isDirectory()) {
/*  9 */       return true;
/*    */     }
/* 11 */     String s = f.getName().toLowerCase();
/*    */     
/*    */ 
/* 14 */     if ((s.endsWith(".gif") | s.endsWith(".jpeg") | s.endsWith(".png"))) return true;
/* 15 */     return false;
/*    */   }
/*    */   
/* 18 */   public String getDescription() { return "Bilder"; }
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/BildFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */