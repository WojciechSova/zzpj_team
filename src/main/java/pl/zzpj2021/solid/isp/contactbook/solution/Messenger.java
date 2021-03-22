package pl.zzpj2021.solid.isp.contactbook.solution;

public class Messenger implements DialerInterface, EmailerInterface {

    @Override
    public void sendMessage(Contact emailable, String subject, String body) {
        //sending message via email
    }

    @Override
    public void makeCall(Contact dialable) {
        //making call
    }
}
