package com.prasetia.erp.ui

import com.vaadin.navigator.View
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout

class DefaultView: VerticalLayout(), View {
    fun DefaultView(){
        val label = Label("Test")
    }
}
