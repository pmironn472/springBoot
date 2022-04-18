package com.example.application.views.testConfiguration;

import com.example.application.MainLayout;
import com.example.application.util.LoadTestParameters;
import com.example.application.util.ReadPaths;
import com.example.application.util.TestFiles;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Route(value = "core", layout = MainLayout.class)
@PageTitle("Core Test Configuration")
public class CoreTestConfiguration extends VerticalLayout {
    TextField testTosTicket = new TextField();

    private final ReadPaths readPaths = new ReadPaths();

    private LoadTestParameters loadTestParameters = new LoadTestParameters();


    public CoreTestConfiguration() {
        addClassName("core-conf");
        setSizeFull();
        add(engineerName());
        add(getToolbar());

    }


    private HorizontalLayout engineerName() {
        List<String> engineersProfiles = new ArrayList<>(new TestFiles().lookForEngineerProfile(new File(readPaths.readPropertyPath("TEST_PROFILES_PATH"))));
        ComboBox<String> engineer = new ComboBox<>("Performance Engineer");
        ComboBox<String> platform = new ComboBox<>("Platform");
        ComboBox<String> testFile = new ComboBox<>("Test Files");

        Button button = new Button("Load");

        engineer.setAllowCustomValue(true);
        engineer.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            engineersProfiles.add(customValue);
            engineer.setItems(engineersProfiles);
            engineer.setValue(customValue);
            System.out.println(customValue);
        });
        engineer.setItems(engineersProfiles);
        engineer.setHelperText("Select your profile");

        engineer.addValueChangeListener(engineerProfile -> {
            List<String> profiles = new ArrayList<>(new TestFiles().lookForEngineerProfile(new File(readPaths.readPropertyPath("TEST_PROFILES_PATH") +"/" + engineerProfile.getValue())));
            platform.setAllowCustomValue(true);
            platform.addCustomValueSetListener(e -> {
                String customValue = e.getDetail();
                profiles.add(customValue);
                platform.setItems(profiles);
                platform.setValue(customValue);
                System.out.println(customValue);
            });
            platform.setItems(profiles);
            platform.setHelperText("Select the platform");

            platform.addValueChangeListener(platformChosen -> {
                List<String> testFiles = new ArrayList<>(new TestFiles().lookForTestFiles(new File(readPaths.readPropertyPath("TEST_PROFILES_PATH") +"/" + engineerProfile.getValue() +"/"+ platformChosen.getValue())));
                testFile.setAllowCustomValue(true);
                testFile.addCustomValueSetListener(e -> {
                    String customValue = e.getDetail();
                    testFiles.add(customValue);
                    testFile.setItems(profiles);
                    testFile.setValue(customValue);
                });
                testFile.setItems(testFiles);
                testFile.setHelperText("Select the test file");

                testFile.addValueChangeListener(testFileChosen -> {
                    System.out.println(testFileChosen.getValue());
                    System.out.println(loadTestParameters.loadTestParameters("/" + engineerProfile.getValue() +"/"+ platformChosen.getValue() + "/" + testFileChosen.getValue(),"test_ticket_tos=\""
                            ,"(?<==\")(.*?)(?=\")"));
                    button.addClickListener(clickEvent -> {
                        testTosTicket.setValue(loadTestParameters.loadTestParameters("/" + engineerProfile.getValue() +"/"+ platformChosen.getValue() + "/" + testFileChosen.getValue(),"test_ticket_tos=\""
                                ,"(?<==\")(.*?)(?=\")"));
                    });
                });
            });


        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(engineer, platform, testFile,
                button);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

        return horizontalLayout;

}

    private HorizontalLayout getToolbar() {
        testTosTicket.setValueChangeMode(ValueChangeMode.LAZY);

        testTosTicket.setLabel("Test Ticket Tos");
        testTosTicket.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        HorizontalLayout toolbar = new HorizontalLayout(testTosTicket);
        toolbar.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
