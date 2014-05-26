package com.hp.devops.xs.patch.operation;

/**
 * @author StevenGe
 * @version 1.0.0-snapshot
 * Date: 5/25/14
 * Time: 5:19 PM
 * interface Action for sub-classes to implement
 */

public interface Action {
    public void cleanFolder();                  // clean the working directory if there is
    public int copyFileToTargetFolder();        // return the copied files number
}
