package com.hp.devops.xs.patch.parser;

/**
 * @author StevenGe
 * @version 1.0.0-snapshot
 * Date: 5/25/14
 * Time: 5:19 PM
 * main class for parsing patch xml file
 */

import com.hp.devops.xs.patch.operation.FileAction;
import com.hp.devops.xs.patch.operation.FileActionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.Elements;
import nu.xom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PatchResolver {
    Logger _logger = LoggerFactory.getLogger(PatchResolver.class);

    private String _path_for_patch_xml;
    private Document patchDoc;

    public PatchResolver(String _path_for_patch_xml) {
        this._path_for_patch_xml = _path_for_patch_xml;
    }

    public void initialize() {
        Builder xmlBuilder = new Builder();
        try {
            _logger.info("Start to initialize patch.xml");
            this.patchDoc = xmlBuilder.build(new File(this._path_for_patch_xml));
        } catch (ParsingException e) {
            _logger.error("Failed to parse patch.xml, please check the file!");
            e.printStackTrace();
        } catch (IOException e) {
            _logger.error("Can not access patch.xml file, please check the patch.xml file");
            e.printStackTrace();
        }
    }

    private Element getActionRootElement() throws Exception {
        Element actionRootElement = null;
        if (this.patchDoc == null) {
            _logger.error("Please initialize Resolver first!");
            throw new RuntimeException("patchDoc is null");
        }
        Element rootElement = patchDoc.getRootElement();
        Elements actionRootNodes = rootElement.getChildElements();
        if ( actionRootNodes != null && actionRootNodes.size() == 1) {
            actionRootElement = actionRootNodes.get(0);
            _logger.info("get the root element: ", actionRootElement.getLocalName());
        }
        return actionRootElement;
    }

    private Elements getActions() {
        _logger.info("Start to get actions");
        Elements actions = null;
        try {
            Element actionRootElement = getActionRootElement();
            actions = actionRootElement.getChildElements();
        } catch (Exception e) {
            _logger.error("there is something wrong in resolving xml");
            e.printStackTrace();
        }
        return actions;
    }

    public ArrayList<FileAction> resolveActions() {
        ArrayList<FileAction> actionsCollection = new ArrayList<FileAction>();
        Elements actions = getActions();
        for(int i = 0; i < actions.size(); i++) {
            Element oneItem = actions.get(i);
            FileAction actionItem = FileActionFactory.generateFileAction(oneItem.getLocalName() ,oneItem);
            actionsCollection.add(actionItem);
        }
        return actionsCollection.size() == 0 ? null : actionsCollection;
    }

    public Document getPatchDoc() {
        return patchDoc;
    }

}
