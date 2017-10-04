package project.tags;

import project.model.users.Address;
import project.model.users.Phone;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.SubscriberService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Subscriber2XMLTag extends TagSupport {
    List<Subscriber> subscribers;

    public void initSubscribers(){
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        SubscriberService subscriberService = serviceFactory.getSubscriberService();
        try {
            this.subscribers = subscriberService.getSubscribers();
        } catch (SQLException e) {
            this.subscribers = Collections.emptyList();
        }
    }

    @Override
    public int doStartTag() throws JspException {
        initSubscribers();
        try {
            JspWriter jspWriter = pageContext.getOut();
            writeSubscribers(jspWriter);
        } catch (IOException e) {

        }
        return SKIP_BODY;
    }

    private void writeSubscribers(JspWriter writer) throws IOException{
        writer.write("<subscribers>");
        for (Subscriber subscriber: subscribers) {
            writeSubscriber(writer, subscriber);
        }
        writer.write("</subscribers>");
    }

    private void writeSubscriber(JspWriter writer, Subscriber subscriber) throws IOException {
        writer.write("<ID>" + subscriber.getId() + "</ID>");
        writer.write("<firstName>" + subscriber.getFirstName() + "</firstName>");
        writer.write("<lastName>" + subscriber.getLastName() + "</lastName>");
        writer.write("<email>" + subscriber.getEmail() + "</email>");
        if (subscriber.getPhone() != null) {
            writePhone(writer, subscriber.getPhone());
        }
        writer.write("<address>");
        writeAddress(writer, subscriber.getAddress());
        writer.write("</address>");
    }

    private void writePhone(JspWriter writer, Phone phone) throws IOException {
        writer.write("<phone>" + phone.getPhoneNumber() + "</phone>");
    }

    private void writeAddress(JspWriter writer, Address address) throws IOException {
        writer.write("<streetName>" + address.getStreetName() + "</streetName>");
        writer.write("<building>" + address.getBuildingNumber() + "</building>");
        writer.write("<apartments>" + address.getApartmentsNumber() + "</apartments>");
    }
 }
