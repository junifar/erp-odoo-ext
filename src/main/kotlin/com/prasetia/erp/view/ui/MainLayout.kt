/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.prasetia.erp.view.ui

import com.vaadin.flow.component.Text
import com.vaadin.flow.component.dependency.HtmlImport
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.icon.Icon
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.HighlightConditions
import com.vaadin.flow.router.RouterLayout
import com.vaadin.flow.router.RouterLink
import com.vaadin.flow.server.InitialPageSettings
import com.vaadin.flow.server.PageConfigurator

/**
 * The main layout contains the header with the navigation buttons, and the
 * child views below that.
 */
@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
class MainLayout : Div(), RouterLayout, PageConfigurator {
    init {
        val title = H2("Beverage Buddy")
        title.addClassName("main-layout__title")

        val reviews = RouterLink(null, TestVaadinView::class.java)
        reviews.add(Icon(VaadinIcon.LIST), Text("Reviews"))
        reviews.addClassName("main-layout__nav-item")
        // Only show as active for the exact URL, but not for sub paths
        reviews.highlightCondition = HighlightConditions.sameLocation()

        val categories = RouterLink(null, TestVaadinView::class.java)
        categories.add(Icon(VaadinIcon.ARCHIVES), Text("Categories"))
        categories.addClassName("main-layout__nav-item")

        val navigation = Div(reviews, categories)
        navigation.addClassName("main-layout__nav")

        val header = Div(title, navigation)
        header.addClassName("main-layout__header")
        add(header)

        addClassName("main-layout")
    }

    override fun configurePage(settings: InitialPageSettings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes")
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black")
    }
}
