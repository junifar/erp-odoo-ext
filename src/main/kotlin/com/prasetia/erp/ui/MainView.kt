package com.prasetia.erp.ui

import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.event.ShortcutAction
import com.vaadin.icons.VaadinIcons
import com.vaadin.server.Page
import com.vaadin.server.Responsive
import com.vaadin.server.VaadinRequest
import com.vaadin.shared.Position
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import java.security.Security
import javax.annotation.PostConstruct

@Title("Vaadin Login")
@Theme("valo-green")
@SpringUI(path = "/ui")
class MainView: UI() {

    lateinit var login:Button

    @PostConstruct
    fun init(){
        addStyleName("login-view")
        Responsive.makeResponsive(this)
    }

    override fun init(vaadinRequest: VaadinRequest) {
        val loginLayout = buildLoginLayout(vaadinRequest)
        val layout = VerticalLayout(loginLayout)
        layout.setSizeFull()
        layout.setMargin(false)
        layout.isSpacing = false
        layout.setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER)
        content = layout
        setSizeFull()
        showNotification()
    }

    fun buildLoginLayout(request: VaadinRequest): Component {
        val loginPanel = VerticalLayout()
        loginPanel.setSizeUndefined()
        loginPanel.setMargin(false)
        loginPanel.addStyleName("login-panel")
        Responsive.makeResponsive(loginPanel)

        loginPanel.addComponent(buildLabels())
        loginPanel.addComponent(buildFields())
        loginPanel.addComponent(CheckBox("Remember me", true))
        return loginPanel
    }

    fun buildLabels():Component{
        val labels = CssLayout()
        labels.addStyleName("labels")

        val welcome = Label("Welcome")
        welcome.setSizeUndefined()
        welcome.addStyleName(ValoTheme.LABEL_H4)
        welcome.addStyleName(ValoTheme.LABEL_COLORED)
        labels.addComponent(welcome)

        val title = Label("Prasetia OpenERP Report")
        title.setSizeUndefined()
        title.addStyleName(ValoTheme.LABEL_H3)
        title.addStyleName(ValoTheme.LABEL_LIGHT)
        labels.addComponent(title)

        return labels
    }

    private fun buildFields(): Component {
        val fields = HorizontalLayout()
        fields.addStyleName("fields")

        val username = TextField("Username")
        username.icon = VaadinIcons.USER
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON)

        val password = PasswordField("Password")
        password.icon = VaadinIcons.LOCK
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON)

        login = Button("Login")
        login.addStyleName(ValoTheme.BUTTON_PRIMARY)
        login.isDisableOnClick = true
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER)
        login.focus()

        fields.addComponents(username, password, login)
        fields.setComponentAlignment(login, Alignment.BOTTOM_LEFT)

        login.addClickListener { login() }
        return fields
    }

    private fun showNotification() {
        val notification = Notification("Welcome to Prasetia OpenERP Report")
        notification.description = "<span>Type Username: admin, Password: admin to Login</span>"
        notification.isHtmlContentAllowed = true
        notification.styleName = "tray dark small closable login-help"
        notification.position = Position.BOTTOM_CENTER
        notification.delayMsec = 20000
        notification.show(Page.getCurrent())
    }

    fun login() {
        login.isEnabled = true
        page.setLocation("/ui/dashboard")
    }

}