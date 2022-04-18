package com.example.application;

import com.example.application.views.testConfiguration.CoreTestConfiguration;
import com.example.application.views.testConfiguration.Introduction;
import com.example.application.views.testConfiguration.MgwTestConfiguration;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        crateHeader();
        crateDrawer();
    }

    private void crateHeader() {

        H1 logo = new H1("TOS Performance");
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");


        addToNavbar(header);

    }

    private void crateDrawer() {

        RouterLink coreConfiguration = new RouterLink("Core configurations", CoreTestConfiguration.class);

        coreConfiguration.setHighlightCondition(HighlightConditions.sameLocation());


        addToDrawer(new VerticalLayout(
                new RouterLink("Introduction", Introduction.class),
                coreConfiguration,
                new RouterLink("Mgw Configuration", MgwTestConfiguration.class)

        ));

    }
}
