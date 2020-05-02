package dev.szafraniak.bmresourceserver.rest.v1.utils;

import dev.szafraniak.bmresourceserver.rest.v1.entity.client.ClientCompany;
import dev.szafraniak.bmresourceserver.rest.v1.entity.client.ContactPerson;
import dev.szafraniak.bmresourceserver.rest.v1.invoice.ClientData;

public class ClientConverter {
    public static ClientData parseToClientData(ContactPerson contactPerson) {
        ClientData clientData = new ClientData();
        return clientData;
    }

    public static ClientData parseToClientData(ClientCompany clientCompany) {
        ClientData clientData = new ClientData();
        return clientData;
    }
}