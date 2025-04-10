package com.scm.scm_po.controller;

import com.scm.scm_po.model.Header;
import com.scm.scm_po.repository.HeaderRepository;
import com.scm.scm_po.service.ExcelService;
import com.scm.scm_po.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/scm")
public class ScmController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private HeaderRepository headerRepo;

    @Autowired
    private XmlService xmlService;

    @GetMapping("/process")
    public String processFile() {
        try {
            File file = new File("D:/SCM/INBOX/po_data.xlsx");
            excelService.processExcelFile(file);
            List<Header> headers = headerRepo.findAll();
            xmlService.generateXml(headers);
            return "Sukses proses dan generate XML";
        } catch (Exception e) {
            return "Gagal: " + e.getMessage();
        }
    }
}

