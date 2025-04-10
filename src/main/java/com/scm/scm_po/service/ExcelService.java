package com.scm.scm_po.service;

import com.scm.scm_po.model.Detail;
import com.scm.scm_po.model.Header;
import com.scm.scm_po.repository.HeaderRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExcelService {

    @Autowired
    private HeaderRepository headerRepo;

    public void processExcelFile(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);

        Sheet headerSheet = workbook.getSheet("HEADER");
        Sheet detailSheet = workbook.getSheet("DETAIL");

        Map<String, Header> headerMap = new HashMap<>();

        for (Row row : headerSheet) {
            if (row.getRowNum() == 0) continue;
            Header h = new Header();
            h.setPoNumber(row.getCell(0).getStringCellValue());
            h.setPoDate(row.getCell(1).getLocalDateTimeCellValue().toLocalDate());
            h.setBuyerName(row.getCell(2).getStringCellValue());
            h.setBuyerAddr(row.getCell(3).getStringCellValue());
            h.setDetails(new ArrayList<>());
            headerMap.put(h.getPoNumber(), h);
        }

        for (Row row : detailSheet) {
            if (row.getRowNum() == 0) continue;
            String poNum = row.getCell(0).getStringCellValue();
            Detail d = new Detail();
            d.setPartNo(row.getCell(1).getStringCellValue());
            d.setPartName(row.getCell(2).getStringCellValue());
            d.setQty((int) row.getCell(3).getNumericCellValue());
            d.setUnit(row.getCell(4).getStringCellValue());
            d.setPrice((int) row.getCell(5).getNumericCellValue());
            d.setHeader(headerMap.get(poNum));
            headerMap.get(poNum).getDetails().add(d);
        }

        headerRepo.saveAll(headerMap.values());
        workbook.close();
    }
}
