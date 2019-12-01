package com.marchello;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class HintTextField extends JTextField implements FocusListener {
    private String hint;
    private String text;
    private boolean showingHint;

    public HintTextField(String hint, String text) {
        super(hint);
        this.hint = hint;
        this.text = text;
        this.showingHint = true;
        super.addFocusListener(this);
    }
    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().length() == this.text.length()) {
            super.setText(text);
            showingHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().length() == this.text.length()) {
            super.setText(hint);
            showingHint = true;
        }
    }
    @Override
    public String getText() {
        return showingHint ? text : super.getText();
    }
}
