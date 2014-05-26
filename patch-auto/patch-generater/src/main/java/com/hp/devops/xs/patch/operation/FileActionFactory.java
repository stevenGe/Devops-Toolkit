package com.hp.devops.xs.patch.operation;

import nu.xom.Element;

/**
 * Created with IntelliJ IDEA.
 * User: gexinlu
 * Date: 5/26/14
 * Time: 2:38 PM
 * FileActionFactory.java
 */

public class FileActionFactory {
    public static FileAction generateFileAction(String actionName, Element elememt) {
        if(actionName.equalsIgnoreCase("AddFileAction")) {
            return new AddFileAction(elememt);
        }
        if(actionName.equalsIgnoreCase("ReplaceFileAction")) {
            return new ReplaceFileAction(elememt);
        }
        return new FileAction(elememt);
    }
}
