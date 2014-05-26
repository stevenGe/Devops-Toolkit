package com.hp.devops.xs.patch.operation;

/**
 * @author StevenGe
 * @version 1.0.0-snapshot
 * Date: 5/25/14
 * Time: 5:19 PM
 * AddFileAction.java
 */

import nu.xom.Element;

public class AddFileAction extends FileAction {

    public AddFileAction(Element element) {
        super(element);
    }

    public AddFileAction(String fileName, String sourceDir, String targetDir, String requireFeatures) {
        super(fileName, sourceDir, targetDir, requireFeatures);
    }

    @Override
    public void cleanFolder() {
        // Empty implement
    }
}
