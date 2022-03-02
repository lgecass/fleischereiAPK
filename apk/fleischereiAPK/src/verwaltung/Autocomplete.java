// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.event.DocumentEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

public class Autocomplete implements DocumentListener
{
    private JTextField textField;
    private final List<String> keywords;
    private Mode mode;
    
    public Autocomplete(final JTextField textField, final List<String> keywords) {
        this.mode = Mode.INSERT;
        this.textField = textField;
        Collections.sort(this.keywords = keywords);
    }
    
    @Override
    public void changedUpdate(final DocumentEvent ev) {
    }
    
    @Override
    public void removeUpdate(final DocumentEvent ev) {
    }
    
    @Override
    public void insertUpdate(final DocumentEvent ev) {
        if (ev.getLength() != 1) {
            return;
        }
        final int pos = ev.getOffset();
        String content = null;
        try {
            content = this.textField.getText(0, pos + 1);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
        int w;
        for (w = pos; w >= 0 && Character.isLetter(content.charAt(w)); --w) {}
        if (pos - w < 2) {
            return;
        }
        final String prefix = content.substring(w + 1).toLowerCase();
        final int n = Collections.binarySearch(this.keywords, prefix);
        if (n < 0 && -n <= this.keywords.size()) {
            final String match = this.keywords.get(-n - 1);
            if (match.startsWith(prefix)) {
                final String completion = match.substring(pos - w);
                SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
            }
        }
        else {
            this.mode = Mode.INSERT;
        }
    }
    
    public void addElem(final String string) {
        this.keywords.add(string);
    }
    
    static /* synthetic */ void access$2(final Autocomplete autocomplete, final Mode mode) {
        autocomplete.mode = mode;
    }
    
    private enum Mode
    {
        INSERT("INSERT", 0), 
        COMPLETION("COMPLETION", 1);
        
        private Mode(final String name, final int ordinal) {
        }
    }
    
    public class CommitAction extends AbstractAction
    {
        private static final long serialVersionUID = 5794543109646743416L;
        
        @Override
        public void actionPerformed(final ActionEvent ev) {
            if (Autocomplete.this.mode == Mode.COMPLETION) {
                final int pos = Autocomplete.this.textField.getSelectionEnd();
                final StringBuffer sb = new StringBuffer(Autocomplete.this.textField.getText());
                sb.insert(pos, " ");
                Autocomplete.this.textField.setText(sb.toString());
                Autocomplete.this.textField.setCaretPosition(pos + 1);
                Autocomplete.access$2(Autocomplete.this, Mode.INSERT);
            }
            else {
                Autocomplete.this.textField.replaceSelection("\t");
            }
        }
    }
    
    private class CompletionTask implements Runnable
    {
        private String completion;
        private int position;
        
        CompletionTask(final String completion, final int position) {
            this.completion = completion;
            this.position = position;
        }
        
        @Override
        public void run() {
            final StringBuffer sb = new StringBuffer(Autocomplete.this.textField.getText());
            sb.insert(this.position, this.completion);
            Autocomplete.this.textField.setText(sb.toString());
            Autocomplete.this.textField.setCaretPosition(this.position + this.completion.length());
            Autocomplete.this.textField.moveCaretPosition(this.position);
            Autocomplete.access$2(Autocomplete.this, Mode.COMPLETION);
        }
    }
}
