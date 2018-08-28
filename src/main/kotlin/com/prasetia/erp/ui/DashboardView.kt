package com.prasetia.erp.ui

import com.prasetia.erp.pojo.PreventiveCustomerYear
import com.prasetia.erp.ui.sidebar.SidebarView
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.navigator.Navigator
import com.vaadin.server.Responsive
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import javax.annotation.PostConstruct

@Title("Dashboard")
@Theme("valo-green")
@SpringUI(path="/ui/dashboard")
class DashboardView: UI(){

    @PostConstruct
    fun init(){
        setSizeFull()
        addStyleName("view-content")
    }

    override fun init(vaadinRequest: VaadinRequest) {
        addStyleName(ValoTheme.UI_WITH_MENU)
        Responsive.makeResponsive(this)

        val labelTest = Label("Sample Dahulu")

        val panel = Panel("<h2><strong>Budget Controlling for Maintenance Preventive</strong><h2/>")
        panel.setSizeFull()

        val grid = Grid<PreventiveCustomerYear>()

        val layout2 = HorizontalLayout()
        layout2.addComponent(labelTest)

        panel.content = layout2

        val layout = HorizontalLayout()
        layout.addComponents(panel)

        layout.setSizeFull()
        layout.isSpacing = false
        content = layout
        setSizeFull()

//        val navigator = Navigator(this, viewContainer)
////        navigator.addView("", DefaultView.class)
    }

    fun buildDashboard(): Component{
        val menu = CssLayout()

        val title = Label("OpenERP Reporting")
        title.addStyleName(ValoTheme.MENU_TITLE)

        val view1 = Button("Maintenance Preventive", (Button.ClickListener { navigator.navigateTo("view1") }))
        val view2 = Button("Maintenance Corrective", (Button.ClickListener { navigator.navigateTo("view2") }))

        view1.addStyleName(ValoTheme.BUTTON_LINK)
        view2.addStyleName(ValoTheme.BUTTON_LINK)

        menu.addComponent(title)
        menu.addComponent(view1)
        menu.addComponent(view2)

        return menu
    }


    fun buildDashboardContainer():Component{
        return CssLayout()
    }
}