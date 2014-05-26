package com.hp.devops.xs.patch.operation;

import nu.xom.Element;
/**
 * @author StevenGe
 * @version 1.0.0-snapshot
 * Date: 5/25/14
 * Time: 5:19 PM
 * ReplaceFileAction.java
 */


public class ReplaceFileAction extends FileAction {

    public ReplaceFileAction(Element element) {
        super(element);
    }
    public ReplaceFileAction(String fileName, String sourceDir, String targetDir, String requireFeatures) {
        super(fileName, sourceDir, targetDir, requireFeatures);
    }

    @Override
    public void cleanFolder() {
        // empty implement
    }
}
