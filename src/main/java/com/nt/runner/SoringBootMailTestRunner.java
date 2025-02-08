package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.nt.service.IPurchageMgmtService;

@Component
public class SoringBootMailTestRunner implements CommandLineRunner {

    @Autowired
    private IPurchageMgmtService service;

    @Override
    public void run(String... args) throws Exception {
        try {
            String resultMsg = service.shopping(
                new String[]{"shirt", "pant", "ganji"},
                new double[]{40000.0, 50000.0, 60000.0},
                new String[]{"emusk4691@gmail.com", "satyajitpradhan1394@gmail.com"}
            );
            System.out.println(resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
