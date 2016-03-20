/*    */ import java.awt.Insets;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ 
/*    */ public class IntTextField
/*    */   extends JTextField
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public IntTextField(boolean E, int size)
/*    */   {
/* 12 */     super(size);
/* 13 */     setMargin(new Insets(2, 5, 2, 5));
/* 14 */     setEditable(E);
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 18 */     try { return Integer.parseInt(getText());
/*    */     }
/*    */     catch (NumberFormatException e) {}
/* 21 */     return 0;
/*    */   }
/*    */   
/*    */   public void setValue(int value) {
/* 25 */     String text = value;
/* 26 */     setText(text);
/*    */   }
/*    */ }


/* Location:              /Users/ayushnvijay/Desktop/clono.jar!/IntTextField.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */