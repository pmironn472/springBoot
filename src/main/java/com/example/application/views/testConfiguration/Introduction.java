package com.example.application.views.testConfiguration;

import com.example.application.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Core Test Configuration")
public class Introduction extends VerticalLayout {

    public Introduction() {

    }
}
