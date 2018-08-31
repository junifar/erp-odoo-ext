package com.prasetia.erp.ui

import com.prasetia.erp.pojo.PreventiveCustomerYear
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.server.Responsive
import com.vaadin.server.Sizeable
import com.vaadin.server.ThemeResource
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme

@Title("Maintenance Preventive")
@Theme("valo-default")
@SpringUI(path="/ui/preventive")
class PreventiveView: UI(){
    override fun init(request: VaadinRequest) {
        val layout = VerticalLayout()
        layout.addComponent(logo())
        layout.addComponent(content())

        Responsive.makeResponsive(this)

        content = layout
    }

    fun logo(): Component{
        val layout = HorizontalLayout()
        val logo = Image(null, ThemeResource("images/logo1.png"))
        logo.setWidth(150f, Sizeable.Unit.PIXELS)
        logo.setHeight(90f, Sizeable.Unit.PIXELS)
        val title = Label("Budget Maintenance Preventive")
        title.addStyleName(ValoTheme.LABEL_BOLD)
        title.addStyleName(ValoTheme.LABEL_LARGE)
        layout.setSizeFull()
        layout.addComponent(logo)
        layout.addComponents(title)
        layout.setComponentAlignment(title, Alignment.BOTTOM_LEFT)
        layout.setExpandRatio(logo, 1.0f)
        layout.setExpandRatio(title, 6.0f)
        return layout
    }

    fun menu():Component{
        val menu = MenuBar()
        menu.setWidth(100.0f, Sizeable.Unit.PERCENTAGE)
        menu.setHeight(30f, Sizeable.Unit.PIXELS)
        val item = menu.addItem("Maintenance Preventive", null)
        item.isEnabled = true
        return menu
    }

    fun content(): Component{
        val panel = Panel("Budget Information")
        panel.setHeight(100.0f, Sizeable.Unit.PERCENTAGE)
        val menu = leftMenu()
        val label1 = Label("Content")
        val splitPanel = HorizontalSplitPanel()
        splitPanel.setSizeFull()
        splitPanel.setMaxSplitPosition(10f, Sizeable.Unit.PERCENTAGE)
        splitPanel.firstComponent = menu
        splitPanel.secondComponent = gridData()
        panel.content = splitPanel
        return panel
    }

    fun leftMenu():Component{
        val accordion = Accordion()
        accordion.setHeight(100.0f, Sizeable.Unit.PERCENTAGE)

        val button = Button("Test")
        val button1 = Button("Test1")
        button.setSizeFull()
        button1.setSizeFull()
        val layout=VerticalLayout(button, button1)

        accordion.addTab(layout, "Action")
        return accordion
    }

    fun gridData():Component{
        val treeGrid = TreeGrid<PreventiveCustomerYear>()
        val data:MutableList<PreventiveCustomerYear> = mutableListOf()
        data.add(PreventiveCustomerYear(1, "Sample", null))
        data.add(PreventiveCustomerYear(2, "Sample1", null))
        treeGrid.setItems(data)
        treeGrid.addColumn(PreventiveCustomerYear::id).caption = "ID"
        treeGrid.addColumn(PreventiveCustomerYear::tahun).caption = "Tahun"
        return treeGrid
    }
}