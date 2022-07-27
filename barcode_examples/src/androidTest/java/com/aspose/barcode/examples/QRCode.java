package com.aspose.barcode.examples;

import android.util.Log;

import com.aspose.barcode.auxiliary.CommonAssist;
import com.aspose.barcode.auxiliary.LicenseAssist;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class QRCode extends CodeExample
{
    @Before
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    @Test
    public void generateAndRead()
    {
        String filePath = CommonAssist.getTestsDataFolderPath("examples") + "qr.png";
        //generate
        BarcodeGenerator bg = new BarcodeGenerator(EncodeTypes.QR, "Aspose常に先を行く");
        {
            bg.getParameters().getBarcode().getXDimension().setPixels(4);
            bg.getParameters().getBarcode().getQR().setQrEncodeType(QREncodeType.FORCE_QR);
            bg.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI_ENCODING);
            bg.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_L);
            bg.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);
            bg.getParameters().getBarcode().getQR().setQrVersion(QRVersion.VERSION_05);
            try
            {
                bg.save(filePath, BarCodeImageFormat.PNG);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //recognize
        BarCodeReader br = new BarCodeReader(filePath, DecodeType.QR);
        BarCodeResult[] barCodeResults = br.readBarCodes();
        for(BarCodeResult result : barCodeResults)
        {
            Log.d(getTag(), "CodeType: " + result.getCodeTypeName());
            Log.d(getTag(), "CodeText: " + result.getCodeText());
        }
    }
}
