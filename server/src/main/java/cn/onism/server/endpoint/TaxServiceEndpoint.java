package cn.onism.server.endpoint;

import cn.onism.server.service.TaxCalculator;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;

/**
 * 税务服务端点
 *
 * @author Onism
 * @date 2024/09/30
 */
@WebService
public class TaxServiceEndpoint {

    private final TaxCalculator calculator = new TaxCalculator();

    /**
     * 计算税费
     *
     * @param income 收入
     * @return double
     */
    @WebMethod
    public double calculateTax(@WebParam(name = "income") double income) {
        return calculator.calculateIncomeTax(income);
    }

    // 发布 Web 服务
    public static void main(String[] args) {
        TaxServiceEndpoint service = new TaxServiceEndpoint();
        String address = "http://localhost:9819/taxservice";
        Endpoint.publish(address, service);
        System.out.println("Web Service published at " + address);
    }
}