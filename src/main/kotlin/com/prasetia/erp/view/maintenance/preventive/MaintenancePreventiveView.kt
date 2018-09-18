package com.prasetia.erp.view.maintenance.preventive

import com.prasetia.erp.constant.GlobalConstant.Companion.APPLICATION_NAME
import com.prasetia.erp.constant.GlobalConstant.Companion.MAINTENANCE_TITLE_PAGE
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dependency.HtmlImport
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.Route

@Route("mtc/prev")
@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
class MaintenancePreventiveView:Div(){
    init {
        val test = H2("$APPLICATION_NAME - $MAINTENANCE_TITLE_PAGE")
        test.addClassName("main-layout__title")
        val button1 = Button("Maintenance Preventive")
        val button2 = Button("Maintenance Corrective")
        val horizontalLayout = HorizontalLayout(button1, button2)
        val header = Div(test)
        val headerMenu = Div(horizontalLayout)

        val test2 = Text("Content Disini")
        val content = Div(test2)

        horizontalLayout.setSizeFull()
        button1.setSizeFull()
        button2.setSizeFull()

        header.addClassName("main-layout__header")
        headerMenu.addClassName("main-layout__view-toolbar")
        addClassName("main-layout")

        add(header, headerMenu, test2)
    }
}