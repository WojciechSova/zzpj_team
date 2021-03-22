package pl.zzpj2021.solid.isp.contactbook.solution;

public interface EmailerInterface {
    void sendMessage(Contact emailable, String subject, String body);
}
