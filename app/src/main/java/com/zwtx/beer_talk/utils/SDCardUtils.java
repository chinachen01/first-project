package com.zwtx.beer_talk.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * the help class of sd card
 */
public class SDCardUtils
{
    private SDCardUtils()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * charge the SDCard
     * @return
     */
    public static boolean isSDCardEnable()
    {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     * get the path of SDCard
     * @return
     */
    public static String getSDCardPath()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     *get SDCard the left capacity (byte)
     * @return
     */
    public static long getSDCardAllSize()
    {
        if (isSDCardEnable())
        {
            StatFs stat = new StatFs(getSDCardPath());
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * »ñget SDCard the left capacity of path
     * @param filePath
     * @return
     */
    public static long getFreeBytes(String filePath)
    {
        if (filePath.startsWith(getSDCardPath()))
        {
            filePath = getSDCardPath();
        } else
        {
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * get root directory path of SDCard
     * @return
     */
    public static String getRootDirectoryPath()
    {  
        return Environment.getRootDirectory().getAbsolutePath();  
    }  
  
  
}  