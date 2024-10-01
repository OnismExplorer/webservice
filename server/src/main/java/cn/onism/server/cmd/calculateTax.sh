curl -X POST http://localhost:9819/taxservice \
-H "Content-Type: text/xml;charset=UTF-8" \
-d '<?xml version="1.0"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://endpoint.server.onism.cn/">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:calculateTax>
         <income>20000</income>
      </ser:calculateTax>
   </soapenv:Body>
</soapenv:Envelope>'
