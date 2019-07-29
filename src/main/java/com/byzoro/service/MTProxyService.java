package com.byzoro.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.text.ParseException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MTProxyService {

    String webService() throws ParseException, SQLException;


}
