package com.aspose.barcode.examples;

import android.util.Log;

import com.aspose.barcode.auxiliary.CommonAssist;
import com.aspose.barcode.auxiliary.LicenseAssist;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.*;
import com.aspose.barcode.generation.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SwissQRCode extends CodeExample
{
    @Before
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    @Test
    public void GenerateAndRead()
    {
        String filePath = CommonAssist.getTestsDataFolderPath("examples") + "SwissQRCode.png";
        //create Swiss QR Bill
        SwissQRCodetext qrCode = new SwissQRCodetext();
        qrCode.getBill().setVersion(QrBillStandardVersion.V2_0);
        qrCode.getBill().setAccount("CH4431999123000889012");
        qrCode.getBill().setAmount(1000.25);
        qrCode.getBill().setCurrency("CHF");
        qrCode.getBill().setReference("210000000003139471430009017");
        Address creditor = new Address();
        creditor.setName("Muster & Söhne");
        creditor.setStreet("Musterstrasse");
        creditor.setHouseNo("12b");
        creditor.setPostalCode("8200");
        creditor.setTown("Zürich");
        creditor.setCountryCode("CH");
        qrCode.getBill().setCreditor(creditor);

        Address debtor = new Address();
        debtor.setName("Muster AG");
        debtor.setStreet("Musterstrasse");
        debtor.setHouseNo("1");
        debtor.setPostalCode("3030");
        debtor.setTown("Bern");
        debtor.setCountryCode("CH");
        qrCode.getBill().setDebtor(debtor);

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(qrCode);

        generator.getParameters().getBarcode().getXDimension().setPixels(4);
        generator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI_ENCODING);
        generator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);

        generator.getParameters().getBarcode().getPadding().getBottom().setMillimeters(0);
        generator.getParameters().getBarcode().getPadding().getTop().setMillimeters(0);
        generator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(0);
        generator.getParameters().getBarcode().getPadding().getRight().setMillimeters(0);
        generator.getParameters().getImageWidth().setMillimeters(46);
        generator.getParameters().getBarcode().getQR().setCodeTextEncoding(StandardCharsets.UTF_8);
        try
        {
            generator.save(filePath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        BarCodeReader reader = new BarCodeReader(generator.generateBarCodeImage(), DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        for(BarCodeResult result:results)
        {
            SwissQRCodetext swissResult = ComplexCodetextReader.tryDecodeSwissQR(result.getCodeText());
            if (null == swissResult) continue;
            Log.d(getTag(),"Version: "+swissResult.getBill().getVersion());
            Log.d(getTag(),"Account: "+swissResult.getBill().getAccount());
            Log.d(getTag(),"Amount: "+swissResult.getBill().getAmount());
            Log.d(getTag(),"Currency: "+swissResult.getBill().getCurrency());
            Log.d(getTag(),"Reference: "+swissResult.getBill().getReference());
            Log.d(getTag(),"Creditor: "+swissResult.getBill().getCreditor().getName());
            Log.d(getTag(),"Debtor: "+swissResult.getBill().getDebtor().getName());
        }
    }
}
