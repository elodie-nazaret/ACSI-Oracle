package com.epsi.forms;


import com.epsi.managers.ClosingListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame {
    private List<ClosingListener> closingListeners;

    public Form() {
        this.closingListeners = new ArrayList<ClosingListener>();
    }

    public void addClosingListener(ClosingListener listener) {
        this.closingListeners.add(listener);
    }

    protected void close(String param) {
        for(ClosingListener listener : this.closingListeners) {
            listener.onClose(param);
        }

        this.dispose();
    }
}
