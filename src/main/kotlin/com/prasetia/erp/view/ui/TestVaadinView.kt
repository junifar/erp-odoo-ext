package com.prasetia.erp.view.ui

import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.component.Tag
import com.vaadin.flow.component.dependency.HtmlImport
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.polymertemplate.Id
import com.vaadin.flow.component.polymertemplate.PolymerTemplate
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.vaadin.flow.templatemodel.TemplateModel
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo

//@Route("ui")
//@HtmlImport("styles/shared-styles.html")
//@Theme(Lumo::class)
//class TestVaadinView: VerticalLayout() {
//
//    init {
//        val helloWorld = Label("Hello World")
//        add(helloWorld)
//        className = "main-layout"
//    }
//}

@Tag("testvaadin-view")
@HtmlImport("src/testvaadin-view.html")
@Route("ui")
class TestVaadinView: PolymerTemplate<TestVaadinView.ExampleModel>(){
  interface ExampleModel : TemplateModel{
      var value:String
  }
    @Id("search")
    lateinit var search:TextField

    init {
        search.label = "Uji COba"
        search.value = "Value DIsini"
    }
}