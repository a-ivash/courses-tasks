package project.servlet;

import project.command.ActionCommand;
import project.command.admin.*;
import project.command.authentication.LoginCommand;
import project.command.authentication.LogoutCommand;
import project.command.common.*;
import project.command.subscriber.ConfirmPaymentCommand;
import project.command.subscriber.CreateAccountCommand;
import project.command.subscriber.MakeOrderCommand;
import project.command.subscriber.ShowOrdersCommand;

public enum CommandMap {
    CREATE_ACCOUNT("/register", new CreateAccountCommand()),
    LOGIN("/login", new LoginCommand()),
    LOGOUT("/logout", new LogoutCommand()),
    SUBSCRIBERS("/subscribers", new SubscribersListCommand()),
    SUBSCRIBER("/subscriber", new SubscriberDetailsCommand()),
    SERVICES("/services", new ServiceListCommand()),
    SERVICE("/service", new ServiceDetailsCommand()),
    PAYMENTS("/payments", new PaymentListCommand()),
    PAYMENT("/payment", new PaymentDetailsCommand()),
    ORDERS("/orders", new ShowOrdersCommand()),
    CONFIRM_PAYMENT("/confirmPayment", new ConfirmPaymentCommand()),
    CREATE_PAYMENTS("/createPayments", new CreatePaymentsCommand()),
    CREATE_SERVICE("/createService", new CreateServiceCommand()),
    ORDER_SERVICE("/orderService", new MakeOrderCommand()),
    BLOCK_USER("/blockUser", new BlockSubscriberCommand()),
    ACTIVATE_USER("/activateUser", new ActivateSubscriberCommand()),
    CHANGE_LANGUAGE("/changeLanguage", new ChangeLanguageCommand());

    String regexp;
    ActionCommand actionCommand;

    private CommandMap(String regexp, ActionCommand command) {
        this.regexp = regexp;
        this.actionCommand = command;
    }

    public static ActionCommand getCommand(String url) {
        for (CommandMap commandMap: CommandMap.values()) {
            if (commandMap.regexp.equals(url)) {
                return commandMap.actionCommand;
            }
        }
        throw new Error("Command can't be found." + url);
    }
}
