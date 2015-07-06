package com.epsi.managers;

import com.epsi.forms.SubscribeForm;


public class Mediator implements ClosingListener {

    public void start() {
        SubscribeForm form = new SubscribeForm();
        form.addClosingListener(this);
    }

    public void onClose(String param) {
        System.out.println(param);
    }
}
