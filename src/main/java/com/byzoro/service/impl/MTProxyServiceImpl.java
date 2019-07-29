package com.byzoro.service.impl;

import com.byzoro.service.MTProxyService;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.text.ParseException;

/**
 * @author huanyuguo
 * 2018/7/30 14:50
 **/
@WebService(endpointInterface = "com.byzoro.service.MTProxyService",
        targetNamespace = "http://impl.service.web.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class MTProxyServiceImpl implements MTProxyService {

    @Override
    public String webService() throws ParseException, SQLException {
        return null;
    }
}
