package com.aspose.barcode.auxiliary;

import android.util.Log;

import java.io.File;

public class CommonAssist
{
    private static String TAG = "## CommonAssist";
    private static String emulatorDataRootPath = concPath("data", "local", "tmp");
    private static String testsDataFolderName = "testdata";

    public static String getEmulatorDataRootPath()
    {
        return emulatorDataRootPath;
    }

    public static String concPath(String... parts)
    {
        if (parts == null || parts.length < 1)
        {
            return null;
        }
        StringBuilder res = new StringBuilder();
        String startPart = parts[0];
        if (startPart.startsWith("/") || startPart.startsWith("\\"))
        {
            startPart = startPart.substring(1);
        }
        if (startPart.endsWith("/") || startPart.endsWith("\\"))
        {
            startPart = startPart.substring(0, startPart.length() - 1);
        }

        res.append("/").append(startPart);
        int i = 0;
        for (String part : parts)
        {
            i++;
            if (i == 1)
            {
                continue;
            }
            res.append("/").append(part);
        }
        res.append("/");
        return res.toString();
    }

    public static String getTestsDataFolderPath()
    {
        return concPath(emulatorDataRootPath, testsDataFolderName);
    }

    public static String getTestsDataFolderPath(String internalFolder)
    {
        return concPath(getTestsDataFolderPath(), internalFolder);
    }

    public static String getLicenseFolderName()
    {
        return concPath(getTestsDataFolderPath(), "License");
    }

    public static String getLicenseFullPath(String licenseFileName)
    {
        return concPath(getLicenseFolderName(), licenseFileName);
    }

    public static boolean isFileExists(String path)
    {
        File file = new File(path);
        boolean exists = file.exists();
        if (exists)
        {
            Log.d(TAG, "File " + path + " exists");
            return true;
        }
        Log.d(TAG, "File " + path + " doesn't exist");
        return false;
    }
}
